<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>
<body>

   <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
        <ul class="nav navbar-nav navbar-right">
            <sec:ifLoggedIn>               
                    <a href="#" class="button" role="button" >
                        Hello <sec:username />
                    </a>
                    <g:link controller='korisnik' action="edit" id="${sec.loggedInUserInfo(field: 'id')}">Profile</g:link>
                    <g:link controller='logout'>Logout</g:link>

            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
                <g:link controller='login' action='auth'>Login</g:link>
                <g:link controller='korisnik' action='create'>Register</g:link>
            </sec:ifNotLoggedIn>
        </ul>
    </div>

    <g:layoutBody/>

    <div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>

</body>
</html>
