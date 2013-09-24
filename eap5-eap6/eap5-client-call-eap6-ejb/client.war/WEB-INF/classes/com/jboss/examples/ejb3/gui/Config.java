package com.jboss.examples.ejb3.gui;

public class Config
{
	private String host = "localhost";
	private String port = "4447";
	private String username = "ejbuser";
	private String password = "ejbpass1!";

  private Boolean useTransferObject = false;

	public String getHost() { return host; }
	public void setHost(String host) { this.host = host; }

	public String getPort() { return port; }
	public void setPort(String port) { this.port = port; }

	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public Boolean getUseTransferObject() { return useTransferObject; }
	public void setUseTransferObject(Boolean useTransferObject) { this.useTransferObject = useTransferObject; }
}
