package com.jboss.examples.ejb3.gui;

import com.jboss.examples.ejb3.*;

import javax.naming.Context;
import javax.naming.InitialContext;

import java.util.Properties;

public class BackingBean {
	
	private String name = "JBoss";
	private Config config = new Config();
	private String response = "";
	private Boolean useTransferObject = false;

	private String remote = "ejb:helloWorld/helloWorld-ejb//HelloBean!com.jboss.examples.ejb3.Hello";

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

	public String getRemoteJNDIPath() {
		return remote;
	}

	public Config getConfig() 
	{
		return config;
	}
	public void setConfig(Config config) {
		this.config = config;
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

		Properties p = new Properties();
		p.put("remote.connections", "default");
		p.put("remote.connection.default.host", config.getHost()); 
		p.put("remote.connection.default.port", config.getPort());  
		p.put("remote.connection.default.username", config.getUsername());  
		p.put("remote.connection.default.password", config.getPassword());  
		p.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");
		p.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false"); // the server defaults to SSL_ENABLED=false
		// ...
		p.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		return new InitialContext(p);
	}	

	private Hello getRemote() throws Exception {
		return (Hello) getInitialContext().lookup(remote);		
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
}
