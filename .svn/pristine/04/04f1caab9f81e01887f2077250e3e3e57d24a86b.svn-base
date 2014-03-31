<!DOCTYPE HTML>
<html>
  <head> 
    <meta charset="utf-8">
    <title>InventNet | Inicio de sesi&oacute;n</title>
    <meta name="description" content="Sistema de Administracion de Inventarios">
<meta name="author" content="Neider Tapia Avila @nrtapia">
<meta name="viewport" content="initial-scale = 1.0">

<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<link rel="stylesheet" href="${resource(dir:'css',file:'bootstrap.min.css')}" />
    <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
  <g:javascript library="application" />
  <g:javascript src="jquery-1.7.1.min.js" /> 

  <style type="text/css">
    .navbar-inner{
      height: 50px;
      background: #f1f1f1;
      border: 1px solid #e5e5e5;
       -webkit-border-radius: 0;
  -moz-border-radius: 0;
    }

    .signin-box,
    .accountchooser-box {
      margin: 12px 0 0;
      padding: 20px 25px 15px;
      background: #f1f1f1;
      border: 1px solid #e5e5e5;
    }

    .features {
      overflow: hidden;
      margin: 2em 0 0;
    }
    .features li {
      margin: 3px 0 2em;
      list-style:none;
    }
    .features img {
      float: left;
      margin: -3px 0 0;
    }
    .features p {
      margin: 0 0 0 68px;
    }
    .features .title {
      font-size: 16px;
      margin-bottom: .3em;
    }
    .features.no-icon p {
      margin: 0;
    }
    .features .small-title {
      font-size: 1em;
      font-weight: bold;
    }

 
    footer-bar {
      position: absolute;
      bottom: 0;
      height: 35px;
      width: 100%;
      border-top: 1px solid #ebebeb;
      overflow: hidden;
    }
    .footer {
      padding-top: 9px;
      font-size: .85em;
      white-space: nowrap;
      line-height: 0;
      display: block;
    }
    .footer ul {
      color: #999;
      float: left;
      max-width: 80%;
    }
    .footer ul li {
      display: inline;
      padding: 0 1.5em 0 0;
    }

    .clearfix:after {
      visibility: hidden;
      display: block;
      font-size: 0;
      content: '.';
      clear: both;
      height: 0;
    }
    * html .clearfix {
      zoom: 1;
    }
    *:first-child+html .clearfix {
      zoom: 1;
    }

    .content {
      padding: 0 44px;
    }

  </style>

  <r:layoutResources />
</head>

<body>

  <nav class="navbar">
    <div class="navbar-inner">
      <div class="container">
        <a class="brand" href="http://www.cys-tic.com/" style="color: #222C5D"><strong>Consultores y Soluciones en TIC</strong></a>
        <ul class="nav">
          <!--
          <li class="active"><a href="#">Home</a></li>
          <li><a href="#">Link</a></li>
          <li><a href="#">Link</a></li>
          -->
        </ul>
      </div>
    </div>
  </nav>

  <div class="container">
    <section>
  
      <div class="row-fluid content">  

        <div class="span7"  >
          <img src="${resource(dir:'images',file:'logo.png')}"/>
          
          <p style="padding-top: 10px; color: #222C5D">
            <em>Sistema profesional para el seguimiento del inventario en las Pymes, simple y confiable.</em>
          </p>

          <ul class="features">

            <li>
              <img src="${resource(dir:'images',file:'admin.png')}" alt="Administraci&&oacute;n">
              <p class="title">Administraci&oacute;n Centralizada</p>
              <p>Permite una mayor integridad de los datos.</p>
            </li>
            <li>
              <img src="${resource(dir:'images',file:'database-42.png')}" alt="Administraci&&oacute;n">
              <p class="title">Basado en Bodegas</p>
              <p>Sistema basado en movimientos de entradas y salidas por Sucursales/Bodegas. 
                Administradas por usuarios independientes.</p>
            </li>
            <li>
              <img src="${resource(dir:'images',file:'square-cloud-42.png')}" alt="Administraci&&oacute;n">
              <p class="title">Software como Servicio, SaaS</p>
              <p>Acceda con un navegador web o un cliente ligero especializado, a través de internet. 
                Proveemos servicio de mantenimiento, operación diaria, y soporte del software. </p>
            </li>
          </ul>

        </div>

        <div class="span4">

          <div class="signin-box">
            <div style="font-size: larger; margin-bottom: 10px">Iniciar sesión</div>
  
            <g:if test='${flash.message}'>
                <div class='alert-error'>${flash.message}</div>
            </g:if>

            <form action='${postUrl}' method='POST' id='loginForm' autocomplete='off'>
              <div>
                <label for="username"><strong>Nombre de usuario</strong></label>
                <g:textField name="j_username" class="input-xlarge" />
              </div>
              <div style="margin-bottom: 10px">
                <label for="password"><strong>Contrase&ntilde;a</strong></label>
                <g:passwordField  name="j_password"  class="input-xlarge" />
              </div>
              <g:submitButton name="submit" value="Iniciar sesión" class="btn btn-primary" />
            </form>

          </div>
        </div>


    </section>

  </div>
  
   <footer>
      <div class="footer-bar accountchooser-box pull-bootom">
        <div class="footer content clearfix">

          <ul>
            <li>© 2012 <a href="http://www.cys-tic.com">Consultores y Soluciones en TIC</a> - <a href="mailto:neider.tapia@gmail.com">neider.tapia@gmail.com</a></li>
          </ul>
        </div>
      </div>
    </footer> 
 <r:layoutResources />
</body>
</html>