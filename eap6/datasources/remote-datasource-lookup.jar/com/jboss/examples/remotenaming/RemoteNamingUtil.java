package com.jboss.examples.remotenaming;

import javax.naming.Context;
import javax.naming.InitialContext;

import java.util.Properties;

import java.util.Hashtable;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;

import javax.security.auth.login.LoginContext;
import javax.security.auth.callback.CallbackHandler;

public class RemoteNamingUtil 
{
	private Config config = new Config();

	private Context context;

	public RemoteNamingUtil(String host, String port, String username, String password) {
		config.setHost(host);	
		config.setPort(port);	
		config.setUsername(username);	
		config.setPassword(password);	
	}

	public RemoteNamingUtil(Config config) {
		this.config = config;
	}

	public Context getInitialContext() throws Exception {
		if(context == null)
			context = getRemoteProtocolInitialContext();
		return context;
	}

	public void close() throws Exception {
		if(context != null) {
			try {
				context.close();
			} catch(Exception e) {}
			finally {
				context = null;
			}
		}	
	}

	private Context getRemoteProtocolInitialContext() throws Exception
	{

		Hashtable<String, String> env = new Hashtable<String, String>();
    env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		env.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
		env.put("java.naming.provider.url", "remote://" + config.getHost() + ":" + config.getPort());
		env.put("jboss.naming.client.ejb.context", "true");
		env.put(Context.SECURITY_PRINCIPAL, config.getUsername());
		env.put(Context.SECURITY_CREDENTIALS, config.getPassword());

    InitialContext ctx = new InitialContext(env);

    return ctx;
	}

	class Config
	{
		private String host = "localhost";
		private String port = "4447";
		private String username = "";
		private String password = "";

		public String getHost() { return host; }
		public void setHost(String host) { this.host = host; }

		public String getPort() { return port; }
		public void setPort(String port) { this.port = port; }

		public String getUsername() { return username; }
		public void setUsername(String username) { this.username = username; }

		public String getPassword() { return password; }
		public void setPassword(String password) { this.password = password; }
	}
}
