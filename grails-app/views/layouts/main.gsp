<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
    <head>
        <title><g:layoutTitle default="InventNet" /></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead/>
        <r:layoutResources />
        <g:javascript src="jquery/jquery.blockUI.js" />
        <ga:trackPageview />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
          <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
          </div>
      
        <div id="grailsLogo">
          <a href="${createLink(uri: '/')}">
            <img src="${resource(dir:'images',file:'logo.png')}" alt="" border="0" />
          </a>
          <div style="float: right;">
            | <g:link controller="logout">Cerrar sesi&oacute;n</g:link> |
          </div>
        </div>
      
        <g:layoutBody />
        <g:javascript library="application"/>
        <r:layoutResources />
    </body>
</html>