package jp.co.drecom.mfps.model.dto;

public class XFNRegDto {

	private Long id = null;

	private String name = "";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";

	    String retValue = "";

	    retValue = "XFNRegDto ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "name = " + this.name + TAB
	        + " )";

	    return retValue;
	}

}
