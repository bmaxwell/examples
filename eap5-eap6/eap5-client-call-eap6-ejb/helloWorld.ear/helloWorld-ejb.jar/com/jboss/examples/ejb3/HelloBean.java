package com.jboss.examples.ejb3;

import java.util.logging.Logger;

import javax.ejb.Stateless;

/**
 * 
 * @author bmaxwell
 * Requires >= JBoss 4.2 for annotations
 */
@Stateless 
public class HelloBean implements Hello, HelloLocal
 {
	
	public static final String NAME = HelloBean.class.getSimpleName();

	public static final String RemoteJNDIName =  NAME + "/remote";
	public static final String LocalJNDIName =  NAME + "/local";
	
	private Logger log = Logger.getLogger(this.getClass().toString());
	
	public String hello(String name) throws HelloException
	{
		log.info("hello("+name+") = Hello " + name);
		return "Hello " + name;
	}

	public String hello(Long sleepSeconds) 
	{
		try 
		{
			Thread.sleep(sleepSeconds * 1000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Exception occurred during sleep: " + e.getClass().getName() + ": " + e.getMessage();
		}
		return "Slept for " + sleepSeconds + " seconds";
	}

	public TransferReturnValue hello ( TransferParameter param )
	{
		log.info("hello("+ param +") = Hello " + param );
		return new TransferReturnValue ( "Hello " + param );
	}

}
