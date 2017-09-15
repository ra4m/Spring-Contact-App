<%-- 
    Document   : test
    Created on : Sep 14, 2017, 6:51:51 PM
    Author     : Raam
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <s:url var="url_jqlib" value="/static/js/jquery-3.2.1.min.js" />
        <script src="${url_jqlib}"></script>
               
        <script>
        $(document).ready(function() {
            $("#id_get_time").click(function() {
                $.ajax({
                    url : 'get_time',
                    success : function (data) {
                        $("#id_time").html(data);
                    }
                });
            });
        });
        </script>
        
        <title>JSP Page</title>
    </head>
    <body>
        <h1>AJAX TEST PAGE</h1> <br>
        <button id="id_get_time">Get Server Time</button> <br>
        <p id="id_time"></p>
    </body>
</html>
