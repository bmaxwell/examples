/**
 *
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright to this software to the public domain worldwide, pursuant to the CC0 Public Domain Dedication. This software is distributed without any warranty.  See <http://creativecommons.org/publicdomain/zero/1.0/>.
 *
 */
package com.jboss.examples.xslt;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.Scanner;

import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

/**
 * @author bmaxwell
 *
 */
public class Xalan 
{

	/**
	 * @param args
	 */
    public static void main(String[] args)
    {    	
    	try
    	{
				test("com/jboss/examples/xslt/cds.xml", "com/jboss/examples/xslt/cds.xsl");

    	}
    	catch ( Exception e )
    	{
    		e.printStackTrace();
    	}
//        if (args.length != 3) 
//        {
//            System.err.println("Usage:");
//            System.err.println("  java " + Xalan.class.getName() + " xmlFileName xsltFileName xmlOutputFileName");
//            System.exit(1);
//        }
//        try 
//        {
//	        transform(args[0], args[1], args[2]);	       
//        }
//        catch ( Exception e )
//        {
//        	e.printStackTrace();
//        }
        
//      String xmlSystemId = new File(xmlFile).toURL().toExternalForm( );
//      String xsltSystemId = new File(xsltFile).toURL().toExternalForm( );

        //transformer.transform(xmlSource, outputTarget);
        
//        org.apache.xalan.xslt.XSLTProcessor processor = org.apache.xalan.xslt.XSLTProcessorFactory.getProcessor( );
//
//        org.apache.xalan.xslt.XSLTInputSource xmlInputSource = new org.apache.xalan.xslt.XSLTInputSource(xmlSystemId);
//
//        org.apache.xalan.xslt.XSLTInputSource xsltInputSource = new org.apache.xalan.xslt.XSLTInputSource(xsltSystemId);
//
//        org.apache.xalan.xslt.XSLTResultTarget resultTree = new org.apache.xalan.xslt.XSLTResultTarget(System.out);
//
//        processor.process(xmlInputSource, xsltInputSource, resultTree);
    }

    public static void transform (String xmlFile, String xsltFile, String outputFile) throws Exception
    {

        TransformerFactory transformFactory = TransformerFactory.newInstance();        
        Transformer transformer = transformFactory.newTransformer( new StreamSource(xsltFile) );
        
        Result outputTarget = new StreamResult( new File(outputFile) );
        
        // Transform the input        
        transformer.transform( new StreamSource(xmlFile), outputTarget );        
    }

    public static ClassLoader transform (InputStream xml, InputStream xslt, OutputStream output) throws Exception
    {
        TransformerFactory transformFactory = TransformerFactory.newInstance();        
        Transformer transformer = transformFactory.newTransformer( new StreamSource(xslt) );
        
        Result outputTarget = new StreamResult( output );
        
        // Transform the input        
        transformer.transform( new StreamSource(xml), outputTarget );
        
        return transformer.getClass().getClassLoader();
    }

    public static Result transform (InputStream xml, InputStream xslt, StringWriter output) throws Exception
    {
        TransformerFactory transformFactory = TransformerFactory.newInstance();        
        Transformer transformer = transformFactory.newTransformer( new StreamSource(xslt) );
        
        Result outputTarget = new StreamResult( output );
        
        // Transform the input        
        transformer.transform( new StreamSource(xml), outputTarget );
        
        return outputTarget;
    }

    public static String test(String xmlString, String xsltString) throws Exception
    {    

			StringWriter writer = new StringWriter();

    	ClassLoader tccl = Thread.currentThread().getContextClassLoader();
    	URL xml = tccl.getResource(xmlString); // "com/jboss/examples/xslt/cds.xml");
    	URL xslt = tccl.getResource(xsltString); //"com/jboss/examples/xslt/cds.xsl");
    	
    	System.out.println("xml url: " + xml);
    	System.out.println("xslt url: " + xslt);
    	
    	//ClassLoader classloader = transform(xml.openStream(), xslt.openStream(), System.out);
    	Result result = transform(xml.openStream(), xslt.openStream(), writer);
    	return writer.toString();
    }

		public static String getTransformerLocation() {
				try {
        	TransformerFactory transformerFactory = TransformerFactory.newInstance();        
        	Transformer transformer = transformerFactory.newTransformer();        
					return "TransfomerFactory: " + getCodeLocation(transformerFactory.getClass()) + " Transformer: " + getCodeLocation(transformer.getClass());
				} catch(Exception e) {
					e.printStackTrace();
					return e.getClass().getName() + ": " + e.getMessage();
				}
		}

		public static String getCodeLocation(Class c)
		{
    	if(c == null)
      	return null;
	    if(c.getProtectionDomain() == null)
  	    return "ProtectionDomain is null";
	    if(c.getProtectionDomain().getCodeSource() == null)
  	    return "ProtectionDomain.CodeSource is null";
	    if(c.getProtectionDomain().getCodeSource().getLocation() == null)
				return "ProtectionDomain.CodeSource.Location is null";
			return c.getProtectionDomain().getCodeSource().getLocation().toString();
		}

	public static String readURL2String(String urlString) {
		try {
			return new Scanner(Thread.currentThread().getContextClassLoader().getResource(urlString).openStream(), "UTF-8").useDelimiter("\\A").next();
		} catch(Exception e) {
			e.printStackTrace();
			return "Failed to read " + urlString + " into a string";
		}
	}


}
