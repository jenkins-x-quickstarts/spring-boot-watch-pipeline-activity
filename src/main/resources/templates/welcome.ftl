<html>
<html lang="en">
<head>

    <link rel="stylesheet" type="text/css"
          href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
    <link href="/css/main.css" rel="stylesheet" />

</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Spring Boot</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#about">About</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="starter-template">
        <h1>Watch PipelineActivities using Spring Boot</h1>
        <img border="0" src="https://jenkins-x.io/images/logo.png" width="300" height="300"/>
		<p>
			The example writes any PipelineActivites to the console. Use
			<code>jx logs --env staging jx-staging-&lt;app name&gt;</code> to view the log. Then trigger
			a promotion using
			<code>jx promote &lt;app name&gt; --version=&lt;version in staging&gt; --env production</code>.
		</p>
	<div>
</div>
<!-- /.container -->

<script type="text/javascript"
        src="webjars/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript"
        src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
