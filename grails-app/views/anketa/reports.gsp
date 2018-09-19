<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'anketa.label', default: 'Anketa')}" />
        <title>Pregled glasanja</title>
    </head>
    <body>

        <div id="show-anketa" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <h3>${anketa.naslov}</h3>
                <fieldset>
                    <g:each in="${anketa.pitanja}" var="pitanje">
                         <g:link controller='pitanje' action='report' id="${pitanje.id}" >${pitanje.tekst}</g:link>
                        <br>
                    </g:each>
                </fieldset>

        </div>
    </body>
</html>
