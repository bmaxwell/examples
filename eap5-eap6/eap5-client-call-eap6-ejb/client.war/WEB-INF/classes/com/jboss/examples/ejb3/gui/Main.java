package com.jboss.examples.ejb3.gui;

public class Main
{
	public static void main(String[] args) 
	{
		try {
			System.setProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager");
			new BackingBean().hello();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
