package com.jboss.examples.ejb3;

import javax.ejb.Local;

/**
 * @author bmaxwell 
 * Requires >= JBoss 4.2 for annotations
 */
public @Local interface HelloLocal 
{
	public String hello(String name) throws HelloException;

	public String hello(Long sleepSeconds) ;	

	public TransferReturnValue hello ( TransferParameter param );
}

