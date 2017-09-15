<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<s:url var="url_clist" value="/user/clist" />
<s:url var="url_login" value="/index" />
<s:url var="url_logout" value="/logout" />
<s:url var="url_reg_form" value="/reg_form" />
<s:url var="url_ahome" value="/admin/dashboard" />
<s:url var="url_uhome" value="/user/dashboard" />
<s:url var="url_cform" value="/user/contact_form" />


<c:if test="${sessionScope.userId == null}">
      <%-- User is not logged in --%>
      <a href="#">Home</a> | <a href="${url_login}">Login</a> | <a href="${url_reg_form}">Register</a> | <a href="#">About</a>
</c:if>


<c:if test="${sessionScope.userId != null && sessionScope.role == 1}">
      <%-- Admin is logged in. So Admin menu will be displayed --%>
      <a href="${url_ahome}">Home</a> | <a href="<s:url value="/admin/users" />">User List</a> | <a href="${url_logout}">Logout</a>
</c:if>
      
      
<c:if test="${sessionScope.userId != null && sessionScope.role == 2}">
      <%-- A regular user is logged in. So a normal user's menu will be displayed --%>
      <a href="${url_uhome}">Home</a> | <a href="${url_cform}">Add Contact</a> | <a href="${url_clist}">Contact List</a> | <a href="${url_logout}">Logout</a>
</c:if>