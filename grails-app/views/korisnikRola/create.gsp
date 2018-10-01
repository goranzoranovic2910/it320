<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'korisnikRola.label', default: 'KorisnikRola')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-korisnikRola" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-korisnikRola" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.korisnikRola}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.korisnikRola}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.korisnikRola}" method="POST">
                <fieldset class="form">
                    <f:all bean="korisnikRola"/>

                    <div class="fieldcontain required">
                        <label for="korisnik">User</label>
                        <g:select name="korisnik"
                                  from="${sviKorisnici}"
                                  value="${korisnikRola.korisnik}"
                                  optionKey="id"
                                  optionValue="username" />
                    </div>
                    <div class="fieldcontain required">
                        <label for="rola">Rola</label>
                        <g:select name="rola"
                                  from="${sveRole}"
                                  value="${korisnikRola.rola}"
                                  optionKey="id"
                                  optionValue="authority" />
                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
