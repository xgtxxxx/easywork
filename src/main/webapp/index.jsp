<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML>
<html manifest="">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="UTF-8">

<title>app</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/icon.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/css/style.css">
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- The line below must be kept intact for Sencha Cmd to build your application -->
<!--  
<script id="microloader" type="text/javascript" src="bootstrap.js"></script>
-->
<script id="microloader" type="text/javascript" src="extjs/include-ext.js?theme=classic"></script>
<script src="${pageContext.request.contextPath}/extjs/utils/ExtUtil.js"></script>
<script src="${pageContext.request.contextPath}/app.js"></script>
<script type="text/javascript">
	var CTX = {
		PATH : '${pageContext.request.contextPath}'
	};
</script>
</head>
<body></body>
</html>
