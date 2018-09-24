<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'pitanje.label', default: 'Pitanje')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-pitanje" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="show-pitanje" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
             <fieldset class="form">

                 <ol class="property-list pitanje">

                     <li class="fieldcontain">
                         <span id="tekst-label" class="property-label">Anketa</span>
                         <div class="property-value">
                             <f:display id="anketa" bean="pitanje" property="anketa" wrapper="anketa"/>
                         </div>
                     </li>

                 </ol>


                  <f:display bean="pitanje" order="tekst" />
                  <f:table collection="${pitanje.odgovori}" properties="['tekst']" />            
            </fieldset>
           
            <g:link controller='odgovor' action="create" id="${this.pitanje.id}">Add Odgovor</g:link>
            <g:form resource="${this.pitanje}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.pitanje}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
