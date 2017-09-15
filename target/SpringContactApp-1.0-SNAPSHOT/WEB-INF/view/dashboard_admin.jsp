<%-- 
    Document   : dashboard_user
    Created on : Sep 12, 2017, 12:17:12 PM
    Author     : Raam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Admin - Contact Application</title>
        
        <s:url var="url_css" value="/static/css/style.css" />
        <link href="${url_css}" rel="stylesheet" type="text/css" />
    </head>
    
    <s:url var="url_bg" value="/static/images/bg.jpg" />
    
    <body background="${url_bg}">
        <table border="1" width="80%" align="center">
            <tr>
                <td height="80px"> 
                    <%-- Header --%>
                    <jsp:include page="include/header.jsp" />
                </td>
            </tr>
            
            <tr>
                <td height="25px">
                    <%-- Menu --%>
                    <jsp:include page="include/menu.jsp" />
                </td>
            </tr>
            
            <tr>
                <td height="350px" valign="top">
                    <%-- Page Content --%>
                    <h1>Admin Dashboard</h1>
                    TODO - Admin option in this page
                </td>
            </tr>
            
            <tr>
                <td height="25px">
                    <%-- Footer --%>
                    <jsp:include page="include/footer.jsp" />
                </td>
            </tr>
        </table>
                    
    </body>
</html>

