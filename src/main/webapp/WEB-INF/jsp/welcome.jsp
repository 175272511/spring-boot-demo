<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>


<html>
<head>
    <style type="text/css">
        #container {width:1500px; height: 600px; }
    </style>
</head>

<body>
登录成功1:${user.username}
<form action="/logout">
    <input type="submit" value="退出">
</form>
<div id="container"></div>
<script src="${urls.getForLookupPath('/js/index.js') }"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=c7f4627f090cf55623e854dd06ba2257"></script>
<script type="text/javascript">

    var map= new AMap.Map('container');
    var marker = new AMap.Marker();
    marker.setMap(map);
</script>
</body>
</html>
