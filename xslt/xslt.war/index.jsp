<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<f:view>
<html>
	<body>
		<h:form>
			<div style="border:1px solid">
				Xml: <h:inputText value="#{bean.xmlPath}"/><br/><br/>
				Xslt: <h:inputText value="#{bean.xsltPath}"/><br/><br/>
				<h:commandButton value="Test" action="#{bean.test}"/><br/>
				<h:outputText value="Transformer Location: #{bean.transformerLocation}"/><br/><br/>
			</div>
			<br/>
			Response:<br/>
			<h:inputTextarea value="#{bean.response}" cols="120" rows="10"/><br/>

			XML:<br/>
			<h:inputTextarea value="#{bean.xml}" cols="120" rows="10"/><br/>

			XSLT:<br/>
			<h:inputTextarea value="#{bean.xslt}" cols="120" rows="10" /><br/>
		</h:form>
	</body>
</html>
</f:view>
