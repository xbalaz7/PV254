<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:page title="Machine Rental" activeNavbarItem="Home">

<jsp:attribute name="body">
    <div class="container">
        <h1>Welcome to Steam Game Recommender</h1>
        <form:form class="form" method="POST" action="${pageContext.request.contextPath}">

                                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                            </form:form>
        <img src="images/title.jpg" width="100%">
    </div>
</jsp:attribute>

</t:page>