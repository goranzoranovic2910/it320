<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'anketa.label', default: 'Anketa')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-anketa" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <sec:access expression="hasRole('ROLE_ADMIN')">
                       <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </sec:access>
            </ul>
        </div>
        <div id="list-anketa" class="content scaffold-list" role="main">
            <h1>Pregled anketa</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${anketaList}" order="naslov, aktivna, anonimna" />

            <div class="pagination">
                <g:paginate total="${anketaCount ?: 0}" />
            </div>
        </div>
    </body>
</html>