package com.jboss.examples.ejb3.gui;

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
import org.jboss.security.auth.callback.SecurityAssociationHandler;
import org.jboss.security.SimplePrincipal;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

public class EJBUtil {
	
	private Config config = new Config();

	private String remote = "helloWorld/helloWorld-ejb//HelloBean!com.jboss.examples.ejb3.Hello";

	private Context context;

	public EJBUtil(String host, String port, String username, String password) {
		config.setHost(host);	
		config.setPort(port);	
		config.setUsername(username);	
		config.setPassword(password);	
	}

	public Context getInitialContext() throws Exception {
		if(context == null)
			context = getEJBCLIENT_34_Scoped_InitialContext();
		return context;
	}

	public void closeContext() throws Exception {
		if(context != null) {
			try {
				context.close();
			} catch(Exception e) {}
			finally {
				context = null;
			}
		}	
	}

	private Context getEJBCLIENT_34_Scoped_InitialContext() throws Exception {
		Properties p = new Properties();
		p.put("endpoint.name", "client-endpoint");
		p.put("remote.connections", "default");
		p.put("remote.connection.default.host", config.getHost()); 
		p.put("remote.connection.default.port", config.getPort());  
		p.put("remote.connection.default.username", config.getUsername());  
		p.put("remote.connection.default.password", config.getPassword());  
		p.put("org.jboss.ejb.client.scoped.context", "true");
		p.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false"); // the server defaults to SSL_ENABLED=false
		// ...
		p.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		return (Context) new InitialContext(p).lookup("ejb:");
	}	

/*
	private Hello getRemote() throws Exception {
		return (Hello) getInitialContext().lookup(remote);		
	}
*/

		public static Context getEJBProtocolInitialContext(String host, String port, String username, String password) throws Exception 
		{
        Properties p = new Properties();
        {
            p.put("remote.connections", "default");
            p.put("remote.connection.default.host", host);
            p.put("remote.connection.default.port", port);
            p.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
            p.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");
            // The following setting is required when deferring to JAAS
            p.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");

            p.put("remote.connection.default.username", username);
            p.put("remote.connection.default.password", password);
        }        
 
        EJBClientConfiguration cc = new PropertiesBasedEJBClientConfiguration(p);
        ContextSelector<EJBClientContext> selector = new ConfigBasedEJBClientContextSelector(cc);
        EJBClientContext.setSelector(selector);

        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
				InitialContext ctx = new InitialContext(env);

        //Object obj = ctx.lookup("ejb:ejb-test/ejb-client//Hello!jboss.example.ejb.Hello");
        //Hello ejbObject = (Hello) obj;
        //System.out.println(ejbObject.sayHello());
				return ctx;
		}

		public static Context getRemoteProtocolInitialContext(String host, String port, String username, String password) throws Exception
		{

        Hashtable<String, String> env = new Hashtable<String, String>();

        env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
				env.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
				env.put("java.naming.provider.url", "remote://" + host + ":" + port);
				env.put("jboss.naming.client.ejb.context", "true");
				env.put(Context.SECURITY_PRINCIPAL, username);
				env.put(Context.SECURITY_CREDENTIALS, password);

        InitialContext ctx = new InitialContext(env);

        //Object obj = ctx.lookup("ejb-client//Hello!jboss.example.ejb.Hello");
        //Hello ejbObject = (Hello) obj;
        //System.out.println(ejbObject.sayHello());
        return ctx;
		}


class Config
{
	private String host = "localhost";
	private String port = "4447";
	private String username = "ejbuser";
	private String password = "ejbpass1!";

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
