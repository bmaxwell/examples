package com.jboss.examples.ejb3;

import javax.ejb.Remote;

/**
 * @author bmaxwell 
 * Requires >= JBoss 4.2 for annotations
 */
public @Remote interface Hello 
{
	public String hello(String name) throws HelloException;

	public String hello(Long sleepSeconds) ;	

	public TransferReturnValue hello ( TransferParameter param );
}

