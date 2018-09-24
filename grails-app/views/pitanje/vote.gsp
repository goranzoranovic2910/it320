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
                    <g:hiddenField name="pitanje" value="${pitanje.id}"/>
                    <g:each in="${pitanje.odgovori}" var="odgovor">

                        <g:if test="${!odgovor.slobodanUnos}">
                            <label for="${odgovor.id}">${odgovor.tekst}</label>
                            <g:radio name="pitanjaGroup" id="${odgovor.id}" value="${odgovor.id}" checked="${odgovor.checked}"/>
                            <br>
                        </g:if>


                    </g:each>
                    <label for="0">Slobodan unos</label>
                    <g:radio name="pitanjaGroup" id="0" value="0"/>
                    <g:textField name="slobodanUnos" />

                </fieldset>
                <fieldset class="buttons">
                    <input type="submit" value="Sacuvaj" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
