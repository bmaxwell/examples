/**
 * 
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright to this software to the public domain worldwide, pursuant to the CC0 Public Domain Dedication. This software is distributed without any warranty.  See <http://creativecommons.org/publicdomain/zero/1.0/>.
 *
 */
package com.jboss.examples.gui;

import com.jboss.examples.xslt.*;

public class BackingBean {
	
	private String xmlPath = "com/jboss/examples/xslt/cds.xml";
	private String xsltPath = "com/jboss/examples/xslt/cds.xsl";

	private String xml;
	private String xslt;

	private String response = "";

	public BackingBean() 
	{
	}

	public String getXmlPath() {
		return xmlPath;
	}
	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}

	public String getXsltPath() {
		return xsltPath;
	}
	public void setXsltPath(String xsltPath) {
		this.xsltPath = xsltPath;
	}


	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getXslt() {
		return xslt;
	}
	public void setXslt(String xslt) {
		this.xslt = xslt;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public String getTransformerLocation() {
		return Xalan.getTransformerLocation();
	}	

	public String test() throws Exception {

		response = Xalan.test(xmlPath, xsltPath);

		xml = Xalan.readURL2String(xmlPath);
		xslt = Xalan.readURL2String(xsltPath);

		return "";
	}

}
