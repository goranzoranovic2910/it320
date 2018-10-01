<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'korisnik.label', default: 'Korisnik')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-korisnik" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <sec:access expression="hasRole('ROLE_ADMIN')">
                    <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                    <li><g:link class="create" action="create" controller="KorisnikRola">Dodeli rolu</g:link></li>
                </sec:access>

            </ul>
        </div>
        <div id="show-korisnik" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
                <f:display bean="korisnik" order="username, enabled" />
                <f:table collection="${korisnikRole}" properties="['rola']" />
            <g:form resource="${this.korisnik}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.korisnik}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <sec:access expression="hasRole('ROLE_ADMIN')">
                        <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </sec:access>

                </fieldset>
            </g:form>
        </div>
    </body>
</html>
