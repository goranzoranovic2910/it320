<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'pitanje.label', default: 'Pitanje')}" />
        <title>Odgovorite na pitanje</title>
    </head>
    <body>

        <div id="show-pitanje" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <h3>${pitanje.tekst}</h3>
            <g:form METHOD="POST" url="[action:'voteSave',controller:'pitanje']">
                <fieldset>

                    <g:each in="${pitanje.odgovori}" var="odgovor">
                        <label for="${odgovor.id}">${odgovor.tekst}</label>
                        <g:radio name="pitanjaGroup" id="${odgovor.id}" value="${odgovor.id}" checked="${odgovor.checked}"/>
                        <br>
                    </g:each>
                </fieldset>
                <fieldset class="buttons">
                    <input type="submit" value="Sacuvaj" />
                    <%--<g:actionSubmit value="Sacuvaj odgovor"/>
                     <g:link class="save" controller="pitanje" action="voteSave" resource="${this.pitanje}">Dalje</g:link> --%>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
