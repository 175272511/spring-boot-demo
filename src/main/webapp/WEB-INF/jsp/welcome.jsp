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
<form action="/logout">
登录成功1:${user.username}
    <input type="submit" value="退出">
</form>
    <input id = 'input' value = '点击地图显示地址' disabled style="width: 400px;"/>
<div id="container"></div>
<script src="${urls.getForLookupPath('/js/index.js') }"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=c7f4627f090cf55623e854dd06ba2257"></script>
<script type="text/javascript">

    var map = new AMap.Map('container',{
        resizeEnable: true,
        zoom: 13
    });
    AMap.plugin('AMap.Geocoder',function(){
        var geocoder = new AMap.Geocoder({
            city: "010"//城市，默认：“全国”
        });
        var marker = new AMap.Marker({
            map:map,
            bubble:true
        })
        map.on('click',function(e){
            marker.setPosition(e.lnglat);
            geocoder.getAddress(e.lnglat,function(status,result){
                if(status=='complete'){
                    document.getElementById('input').value = result.regeocode.formattedAddress
                }
            })
        })

    });
</script>
</body>
</html>
