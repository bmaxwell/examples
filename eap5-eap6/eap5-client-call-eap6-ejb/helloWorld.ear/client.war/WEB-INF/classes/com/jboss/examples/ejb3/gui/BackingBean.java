package com.jboss.examples.ejb3.gui;

import com.jboss.examples.ejb3.*;

import javax.naming.Context;
import javax.naming.InitialContext;

public class BackingBean {
	
	private String name = "JBoss";
	private String host = "";
	private String response = "";
	private Boolean useTransferObject = false;

	private String local = "java:global/helloWorld/helloWorld-ejb/HelloBean!com.jboss.examples.ejb3.HelloLocal";
	private String remote = "java:global/helloWorld/helloWorld-ejb/HelloBean!com.jboss.examples.ejb3.Hello";

	private Long sleepSeconds = 5L;

	public BackingBean() 
	{
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Long getSleepSeconds() {
		return sleepSeconds;
	}
	public void setSleepSeconds(Long sleepSeconds) {
		this.sleepSeconds = sleepSeconds;
	}

	public String getHost() 
	{
		if(host.length() == 0)
			return "localhost";
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}

	public Boolean getUseTransferObject()
	{
		return useTransferObject;
	}
	public void setUseTransferObject ( Boolean useTransferObject )
	{
		this.useTransferObject = useTransferObject;
	}

	public String getResponse() {
		return response;
	}

	private Context getInitialContext() throws Exception {
		return new InitialContext();
	}	

	private Hello getRemote() throws Exception {
		return (Hello) getInitialContext().lookup(remote);		
	}
	private HelloLocal getLocal() throws Exception {
		return (HelloLocal) getInitialContext().lookup(local);
	}

	// looks up in JNDI on given host and calls hello with name
	public String hello() throws Exception 
	{
		System.out.println ( "Calling Remote interface - useTransferObject: " + useTransferObject );	
		if ( useTransferObject )
			response = getRemote().hello( new TransferParameter (name) ).getValue();	
		else 
			response = getRemote().hello(name);	
		return "";
	}
	
	public String helloSleep() throws Exception 
	{
		System.out.println ( "Calling Remote interface - hello sleep");	
		Context context = new InitialContext();
		response = getRemote().hello(sleepSeconds);
		return "";
	}

	public String helloSleepLocal() throws Exception 
	{
		System.out.println ( "Calling Local interface - hello sleep");	
		response = getLocal().hello(sleepSeconds);
		return "";
	}

	public String helloLocal() throws Exception 
	{
		System.out.println ( "Calling Local interface - useTransferObject: " + useTransferObject );	
		if ( useTransferObject )
			response = getLocal().hello( new TransferParameter (name) ).getValue();	
		else 
			response = getLocal().hello(name);	
		return "";
	}

}
