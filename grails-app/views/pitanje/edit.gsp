<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'pitanje.label', default: 'Pitanje')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-pitanje" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-pitanje" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.pitanje}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.pitanje}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.pitanje}" method="PUT">
                <g:hiddenField name="version" value="${this.pitanje?.version}" />
                <fieldset class="form">
                    <div class="fieldcontain required">
                        <label for="anketa">Anketa</label>
                        <g:select name="anketa"
                                  from="${sveAnkete}"
                                  value="${this.pitanje.anketa}"
                                  optionKey="id"
                                  optionValue="naslov" />
                    </div>
                    
                    <f:all bean="pitanje" order="tekst"/>
                    <f:table collection="${pitanje.odgovori}" properties="['tekst']" /> 
                    <g:link controller='odgovor' action="create" id="${this.pitanje.id}">Add Odgovor</g:link>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
