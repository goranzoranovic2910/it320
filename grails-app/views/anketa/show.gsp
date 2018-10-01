<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'anketa.label', default: 'Anketa')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-anketa" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index">Pregled anketa</g:link></li>

                
                 <g:if test="${anketa.aktivna}">
                    <li><g:link class="create" action="vote" id="${anketa.id}">Glasaj</g:link></li>
                </g:if>
                
                <li><g:link class="create" action="reports" id="${anketa.id}">Pregled glasanja</g:link></li>

                <sec:access expression="hasRole('ROLE_ADMIN')">
                    <li><g:link class="create" action="create">Nova anketa</g:link></li>
                    <li><g:link class="create" action="sendMailNew" id="${anketa.id}">Mail - Nova anketa</g:link></li>
                    <li><g:link class="create" action="sendMailFinished" id="${anketa.id}">Mail - Zatvorena anketa</g:link></li>
                </sec:access>
            </ul>
        </div>
        <div id="show-anketa" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:display bean="anketa" order="naslov, aktivna" />
            <f:table collection="${anketa.pitanja}" properties="['tekst']" />
            <sec:access expression="hasRole('ROLE_ADMIN')">
                <g:link controller='pitanje' action="create" id="${this.anketa.id}">Add Pitanje</g:link>
            </sec:access>

            <sec:access expression="hasRole('ROLE_ADMIN')">
                <g:form resource="${this.anketa}" method="DELETE">
                    <fieldset class="buttons">
                        <g:link class="edit" action="edit" resource="${this.anketa}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                        <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </fieldset>
                </g:form>
            </sec:access>
            
        </div>
    </body>
</html>
