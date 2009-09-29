<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="str" uri="http://jakarta.apache.org/taglibs/string" %>
<%@ taglib prefix="mfps-portal" uri="http://www.drecom.co.jp/mfps-portal_1_0" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<title>Drecomerati - DRECOM microformats ping server -</title>
		<meta name="description" content="Drecom microformats ping server-イベント、レビュー、タグ検索" />
		<meta name="keywords" content="Drecom,microformats,ping,server,event,review,tag,マイクロフォーマット、イベント,レビュー,タグ" />
		<link rel="shortcut icon" href="http://mfps.drecom.jp/mfps/images/favicon.ico">
		<style type="text/css">@import "http://mfps.drecom.jp/mfps/css/base.css";  </style>
		<script type="text/javascript" src="http://mfps.drecom.jp/mfps/js/prototype.js" ></script>
		<script language="javascript">
		<!-- //
		function onNekodesu() {
			$('neko-button').src='/mfps/images/button-nekodesu-on.gif';
		}
		function offNekodesu() {
			$('neko-button').src='/mfps/images/button-nekodesu.gif';
		}
		// -->
		</script>
	</head>

	<body>
		 <div id="container">
			<div id="logo"><img src="/mfps/images/logo.gif" alt="drecomerati" /></div>
		 	<h1>drecomerati</h1>
			<div id="site_description"><img src="/mfps/images/confidential.gif" alt="DRECOM Microformats Ping Server" /></div>
			<h2>DRECOM Microformats Ping Server</h2>

<!-- RSS feed entry  -->
			<div id="feed-submit">
				<div class="submit-title">RSS Feed:</div>
				<form action="/mfps/PingMeAction" method="post" class="feed">
					<input type="text" size="55" name="ping_url" id="ping_url" value="http://" />
					<input id="neko-button" type="image" src="/mfps/images/button-nekodesu.gif" onMouseOver="onNekodesu()" onMouseOut="offNekodesu()" name="submitbutton" alt="submit" />
				</form>
				<c:out value="${message}"/>
			</div>

<!-- global navigation -->
			<div id="global_navi">
				<ul>
					<li><a href="#event-today" class="navi"><img src="/mfps/images/navi-today.gif" alt="menu-event-today" /></a></li>
					<li><a href="#event-tomorrow" class="navi"><img src="/mfps/images/navi-tomorrow.gif" alt="menu-event-tomorrow" /></a></li>
					<li><a href="#event-new" class="navi"><img src="/mfps/images/navi-event.gif" alt="menu-event-new" /></a></li>
					<li><a href="#review" class="navi"><img src="/mfps/images/navi-review.gif" alt="menu-review" /></a></li>
					<li><a href="#tag" class="navi"><img src="/mfps/images/navi-tag.gif" alt="menu-tag" /></a></li>
				</ul>
			</div>

<!-- today's event -->
			<div id="event-today">
				<img src="/mfps/images/title-today.gif" alt="title-today-event" class="title-image">
				<div class="title_right">
					<div class="today_event_total">Total: <c:out value="${hCalendarTodayCount}"/> </div>
					<div class="current_time"> <jsp:useBean id="today" class="java.util.Date" />
						Current time:<span>${today}</span></div>
				</div>
			</div>

<!-- today's event list -->
			<c:forEach items="${hCalendarToday}" var="hCalendar">

				<div class="contents">
					<div class="icon-today"><img src="/mfps/images/icon-today.gif" alt="today icon" /></div>
					<!-- no-image -->
					<div class="icon-time"><a href="<c:out value="${hCalendar.url}"/>"><img src="/mfps/images/icon-time-01.gif" alt="today icon" /></a></div>
					<div class="contents_right">
						<div class="location"><c:out value="${hCalendar.location}"/></div>
						<div class="summary"><a href="<c:out value="${hCalendar.url}"/>"><c:out value="${hCalendar.summary}"/></a></div>
						<div class="date"><c:out value="${hCalendar.dtstart}"/> - <c:out value="${hCalendar.dtstamp}"/></div>
						<div class="detail-link">...<a href="<c:out value="${hCalendar.microformats.url}"/>">details</a></div>
						<div class="posted">posted at <c:out value="${hCalendar.microformats.registDate}"/></div>
					</div>
				</div>

			</c:forEach>

<div class="cl"></div>
<!-- tomorrow event -->
			<div id="event-tomorrow">
				<img src="/mfps/images/title-tomorrow.gif" alt="title-tomorrow-event" class="title-image">
				<div class="title_right">
					<div class="today_event_total">Total: <c:out value="${hCalendarTomorrowCount}"/> </div>
				</div>
			</div>

<!-- tomorrow's event list -->
			<c:forEach items="${hCalendarTomorrow}" var="hCalendar">

				<div class="contents">
					<div class="icon-day-under10"><mfps-portal:dayicon dtstart="${hCalendar.dtstart}" /></div>
					<!-- no-image -->
					<div class="icon-time"><a href="<c:out value="${hCalendar.microformats.url}"/>"><img src="/mfps/images/icon-time-<mfps-portal:timeicon dtstart="${hCalendar.dtstart}" />.gif" alt="today icon" /></a></div>
					<div class="contents_right">
						<div class="location"><c:out value="${hCalendar.location}"/></div>
						<div class="summary"><a href="<c:out value="${hCalendar.url}"/>"><c:out value="${hCalendar.summary}"/></a></div>
						<div class="date"><c:out value="${hCalendar.dtstart}"/> - <c:out value="${hCalendar.dtstamp}"/></div>
						<div class="detail-link">...<a href="<c:out value="${hCalendar.microformats.url}"/>">details</a></div>
						<div class="posted">posted at <c:out value="${hCalendar.microformats.registDate}"/></div>
					</div>
				</div>

			</c:forEach>

			<div class="cl"></div>
<!-- new event -->
			<div id="event-new">
				<img src="/mfps/images/title-event.gif" alt="title-new-event" class="title-image">
				<div class="title_right">
					<div class="today_event_total">Total: <c:out value="${hCalendarCount}"/> </div>
				</div>
			</div>


<!-- new event list -->
			<c:forEach items="${hCalendarRecency}" var="hCalendar">

				<div class="contents">
					<div class="icon-day-under10"><mfps-portal:dayicon dtstart="${hCalendar.dtstart}" /></div>
					<!-- no-image -->
					<div class="icon-time"><a href="<c:out value="${hCalendar.microformats.url}"/>"><img src="/mfps/images/icon-time-<mfps-portal:timeicon dtstart="${hCalendar.dtstart}" />.gif" alt="today icon" /></a></div>
					<div class="contents_right">
						<div class="location"><c:out value="${hCalendar.location}"/></div>
						<div class="summary"><a href="<c:out value="${hCalendar.url}"/>"><c:out value="${hCalendar.summary}"/></a></div>
						<div class="date"><c:out value="${hCalendar.dtstart}"/> - <c:out value="${hCalendar.dtstamp}"/></div>
						<div class="detail-link">...<a href="<c:out value="${hCalendar.microformats.url}"/>">details</a></div>
						<div class="posted">posted at <c:out value="${hCalendar.microformats.registDate}"/></div>
					</div>
				</div>

			</c:forEach>

			<div class="cl"></div>

<!-- Reviews -->
			<div id="review">
				<img src="/mfps/images/title-review.gif" alt="title-new-review" class="title-image" />
				<div class="title_right">
					<div class="today_event_total">Total: <c:out value="${hReviewCount}"/> </div>
				</div>
			</div>


<!-- new Reviews list -->

			<c:forEach items="${hReviewRecency}" var="hReview" varStatus="status" >

				<div class="contents">
					<!-- no-image -->
					<div class="review-noimage">
						<a href="<c:out value="${hReview.microformats.url}"/>">
							<c:if test="${hReview.item.photo == null}" >
								<img src="/mfps/images/icon-noimage-review.gif" alt="review icon" />
							</c:if>
							<c:if test="${hReview.item.photo != null}" >
								<img src="<c:out value="${hReview.item.photo}"/>" alt="review icon" width="60px" />
							</c:if>
						</a>
					</div>
					<div class="icon-review">
						<c:if test="${hReview.rating != -1}" >
							<img src="/mfps/images/stars-<c:out value="${hReview.rating}"/>-0.gif" alt="<c:out value="${hReview.rating}"/> stars" />
						</c:if>
						<c:if test="${hReview.rating == -1}" >
							<img src="/mfps/images/stars-0-0.gif" alt="<c:out value="${hReview.rating}"/> stars" />
						</c:if>
					</div>
					<div class="contents_right">
						<div class="item-name"><c:out value="${hReview.dtreviewed}"/></div>
						<div class="summary"><a href="<c:out value="${hReview.permalink}"/>"><c:out value="${hReview.summary}"/></a></div>
						<div class="item-description">
							<str:truncateNicely lower="80" upper="200" appendToEnd="..."><c:out value="${hReview.description}"/></str:truncateNicely>
						<c:if test="${hReview.permalink != ''}"/> &nbsp;<span class="review-more">&gt;&gt;<a href="<c:out value="${hReview.permalink}"/>">more</a></span></a></div>
						<div class="detail-link">...<a href="<c:out value="${hReview.microformats.url}"/>">item details</a></div>
						<div class="posted"><c:out value="${hReview.microformats.registDate}"/></div>
					</div>
				</div>

			</c:forEach>

			<div class="cl"></div>
		 </div>
<!-- footer -->
		<div class="cl"></div>
		<div id="footer">Copyright(C) 2004-2007 Drecom Co.,Ltd All rights reserved.</div>

	</body>

</html>