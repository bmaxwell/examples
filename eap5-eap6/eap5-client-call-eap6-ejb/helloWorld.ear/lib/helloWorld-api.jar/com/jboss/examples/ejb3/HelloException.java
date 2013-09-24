package com.jboss.examples.ejb3;

public class HelloException extends Exception
{
	public HelloException()
	{
		super();
	}

	public HelloException(String msg)
	{
		super(msg);
	}
}
