<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<f:view>
<html>
	<body>
		<h:form>
			<div style="border:1px solid">
				Name: <h:inputText value="#{bean.name}"/><br/><br/>
				Sleep Seconds: <h:inputText value="#{bean.sleepSeconds}"/><br/>

				<h:outputText value="Host:"/><h:inputText value="#{bean.config.host}"/><br/>
				<h:outputText value="Port:"/><h:inputText value="#{bean.config.port}"/><br/>
				<h:outputText value="Username:"/><h:inputText value="#{bean.config.username}"/><br/>
				<h:outputText value="Password:"/><h:inputText value="#{bean.config.password}"/><br/>

				<h:outputText value="Remote EJB JNDI Lookup Path: #{bean.remoteJNDIPath}"/><br/>

				<h:selectBooleanCheckbox value="#{bean.config.useTransferObject}"/>
				<h:outputText value="Use Transfer Object"/><br/>

				<h:commandButton value="Submit Remote" action="#{bean.hello}"/><br/>
				<h:commandButton value="Submit Remote Sleep" action="#{bean.helloSleep}"/><br/>
			</div>
			<br/>
			Response: <h:outputText value="#{bean.response}"/>
		</h:form>
	</body>
</html>
</f:view>
