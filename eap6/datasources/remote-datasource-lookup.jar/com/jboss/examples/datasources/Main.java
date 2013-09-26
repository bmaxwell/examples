package com.jboss.examples.datasources;

import com.jboss.examples.remotenaming.*;

import javax.naming.Context;

public class Main
{
	public static void main(String[] args) 
	{
		try 
		{
			RemoteNamingUtil util = new RemoteNamingUtil("localhost", "4447", "", "");
			try 
			{
				Context context = util.getInitialContext();
				Object object = context.lookup("datasources/ExampleDS");
				System.out.println("Object class: " + object.getClass().getName());
				//object = context.lookup("java:jboss/exported/datasources/ExampleDS");
			}
			finally
			{
				util.close();
			}	
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
}
