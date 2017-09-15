<%-- 
    Document   : clist
    Created on : Sep 13, 2017, 6:56:00 PM
    Author     : Raam
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact List - Contact Application</title>
        
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
                    <h3>Contact List</h3>
                    
                    <c:if test="${param.act == 'sv'}">
                        <p class="success">
                            Contact saved successfully!!
                        </p>                        
                    </c:if>
                        
                    <c:if test="${param.act == 'del'}">
                        <p class="success">
                            Contact deleted successfully!!
                        </p>                        
                    </c:if>
                        
                        <form action="<s:url value="/user/contact_search" />">
                            <input type="text" name="freeText" value="${param.freeText}" placeholder="Search contacts" />
                            <button>Find</button>
                        </form>
                        <br>
                        
                <form action="<s:url value="/user/bulk_cdelete" />">   
                    <button>Delete selected records!</button> <br><br>
                    <table border="2" cellpadding="3">
                        <tr>
                            <th>Select</th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Remarks</th>
                            <th>Action</th>
                        </tr>
                        
                        <c:if test="${empty contactList}">
                            <tr>
                                <td class="error" colspan="8" align="center">No contacts found for you!</td>
                            </tr>
                        </c:if>
                        
                        <c:forEach var="c" items="${contactList}" varStatus="st">
                            <tr>
                                <td align="center"><input type="checkbox" name="cid" value="${c.contactId}" /></td>
                                <td>${c.contactId}</td>
                                <td>${c.name}</td>
                                <td>${c.email}</td>
                                <td>${c.phone}</td>
                                <td>${c.address}</td>
                                <td>${c.remarks}</td>
                                <s:url var="url_del" value="/user/del_contact">
                                    <s:param name="cid" value="${c.contactId}" />
                                </s:url>
                                <s:url var="url_edit" value="/user/edit_contact">
                                    <s:param name="cid" value="${c.contactId}" />
                                </s:url>
                                <td><a href="${url_edit}">Edit</a> | <a href="${url_del}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>    
                    
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