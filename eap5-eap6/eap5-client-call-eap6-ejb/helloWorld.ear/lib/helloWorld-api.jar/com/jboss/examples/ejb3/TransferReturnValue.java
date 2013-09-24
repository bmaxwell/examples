package com.jboss.examples.ejb3;

/**
 * @author bmaxwell 
 */
public class TransferReturnValue implements java.io.Serializable
{
	private String value;

	public TransferReturnValue()
	{
	}

	public TransferReturnValue( String value )
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

	public String getString()
	{
		return value;
	}
	
}

