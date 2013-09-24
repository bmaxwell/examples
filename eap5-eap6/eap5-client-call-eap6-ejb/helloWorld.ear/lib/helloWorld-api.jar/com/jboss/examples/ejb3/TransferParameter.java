package com.jboss.examples.ejb3;

/**
 * @author bmaxwell 
 */
public class TransferParameter implements java.io.Serializable
{

	private String value;

	public TransferParameter()
	{
	}

	public TransferParameter( String value )
	{
		this.value = value;
	}

	public String getValue() 	
	{
		return value;
	}
	public void setValue ( String value )
	{
		this.value = value;
	}

	public String toString()
	{
		return value;
	}
}

