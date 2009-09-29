/*
 * $Header: /home/cvs/Office/WEB-INF/src/jp/co/drecom/util/SetCharacterEncodingFilter.java,v 1.1.34.1 2006/02/01 14:00:00 yoshikawa Exp $
 * $Revision: 1.1.34.1 $
 * $Date: 2006/02/01 14:00:00 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * [Additional notices, if required by prior licensing conditions]
 *
 */


package filters;


import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>Example filter that sets the character encoding to be used in parsing the
 * incoming request, either unconditionally or only if the client did not
 * specify a character encoding.  Configuration of this filter is based on
 * the following initialization parameters:</p>
 * <ul>
 * <li><strong>encoding</strong> - The character encoding to be configured
 *     for this request, either conditionally or unconditionally based on
 *     the <code>ignore</code> initialization parameter.  This parameter
 *     is required, so there is no default.</li>
 * <li><strong>ignore</strong> - If set to "true", any character encoding
 *     specified by the client is ignored, and the value returned by the
 *     <code>selectEncoding()</code> method is set.  If set to "false,
 *     <code>selectEncoding()</code> is called <strong>only</strong> if the
 *     client has not already specified an encoding.  By default, this
 *     parameter is set to "true".</li>
 * </ul>
 *
 * <p>Although this filter can be used unchanged, it is also easy to
 * subclass it and make the <code>selectEncoding()</code> method more
 * intelligent about what encoding to choose, based on characteristics of
 * the incoming request (such as the values of the <code>Accept-Language</code>
 * and <code>User-Agent</code> headers, or a value stashed in the current
 * user's session.</p>
 *
 * @version $Revision: 1.1.34.1 $ $Date: 2006/02/01 14:00:00 $
 */

public class SetCharacterEncodingFilter implements Filter {


	private static Log log = LogFactory.getLog(SetCharacterEncodingFilter.class);

    protected FilterConfig filterConfig = null;

	protected String defaultEncoding = null;

    protected boolean ignore = true;

	protected boolean autoDetect = true;

    public void destroy() {
		log.debug("Destroying SetCharacterEncodingFilter...");
        this.defaultEncoding 	= null;
        this.filterConfig 		= null;

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
	throws IOException, ServletException {

        // Conditionally select and set the character encoding to be used
        if (ignore || (request.getCharacterEncoding() == null)) {
            String encoding = selectEncoding(request);
            log.debug(encoding);
            if (encoding != null){
                request.setCharacterEncoding(encoding);
            }

			// 2004-05-20  Takanori Ishikawa
			// -----------------------------------------------------------
			// Tomcatの4.1.29 以降の 4.1.* 系バージョン、ならびに 5.0.16 以降の 5.0.* 系バージョンでは、
			// ServletRequestクラスのsetCharacterEncoding()メソッドの挙動が変更されています。
			// POST リクエストの body で渡されるパラメータのコード変換は（これまで通り）行われますが、GETリクエストの
			// クエリーストリングで渡されるパラメータのコード変換は行われないようになりました。
			//
			// このままだと GET で渡したパラメータが文字化けするので、自前で変換します。
			//
			// Servlet spec
			// http://java.sun.com/products/servlet/2.3/javadoc/javax/servlet/ServletRequest.html#setCharacterEncodingjava.lang.String
			//
			// 追記:
			//   FormAuthenticator でリクエストを復元したときには GET メソッドでアクセスしたことになってしまっているので、
			//   下の if 節は必ず実行される。
			//
			if ((request instanceof HttpServletRequest)
				&& ("GET".equalsIgnoreCase(((HttpServletRequest) request).getMethod()))) {
				Map parameterMap = request.getParameterMap();
				if (parameterMap != null) {
					for (Iterator it = parameterMap.keySet().iterator(); it.hasNext();) {
						String name = (String) it.next();
						String[] values = request.getParameterValues(name);
						if (values != null) {
							for (int i = 0; i < values.length; i++) {
								values[i] = new String(values[i].getBytes("ISO-8859-1"));
							}
						}
					}
				}
			}
        }

		// Pass control on to the next filter
        chain.doFilter(request, response);

    }

    public void init(FilterConfig filterConfig) throws ServletException {

		log.debug("Initializing SetCharacterEncodingFilter...");

		String value = null;
		value = filterConfig.getInitParameter("encoding");
		if (null != value) {
			this.defaultEncoding 	= value;
    	}
		log.debug("Setting 'defaultEncoding' to '" + this.defaultEncoding + "'...");


        value = filterConfig.getInitParameter("ignore");
        if (value == null)
            this.ignore = true;
        else if (value.equalsIgnoreCase("true"))
            this.ignore = true;
        else if (value.equalsIgnoreCase("yes"))
            this.ignore = true;
        else
            this.ignore = false;
		log.debug("Setting 'ignore' to '" + this.ignore + "'...");

		value = filterConfig.getInitParameter("autoDetect");
		if (value == null)
			this.autoDetect = true;
		else if (value.equalsIgnoreCase("true"))
			this.autoDetect = true;
		else if (value.equalsIgnoreCase("yes"))
			this.autoDetect = true;
		else
			this.autoDetect = false;
		log.debug("Setting 'autoDetect' to '" + this.autoDetect + "'...");

    }

    protected String selectEncoding(ServletRequest request) throws IOException
    {

		if (!this.autoDetect) {
			return this.defaultEncoding;
		}

		String ret_encoding = "8859_1";
        return ret_encoding;

    }


}
