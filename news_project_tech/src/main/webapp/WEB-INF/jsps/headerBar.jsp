<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<body>
      <div class="header__container">
        <div class="header__logo">
          <a href="/" class="header-title">TechNews</a>
          <a href="/" class="header-subtitle">Tech news from all around the world</a>
        </div>
        <div class="header__categories">
        <form action="/dropDownFilters" method="post" class="header-links">
            <input type="hidden" name="filter" value="${filter}">
			<input type="submit" name="category" value="IOS" class="header-link">
			<input type="submit" name="category" value="Android" class="header-link">
			<input type="submit" name="category" value="IT" class="header-link">
			<input type="submit" name="category" value="Global" class="header-link">
        </form>
        </div>
			<c:if test="${not loggedIn}">
		        <div class="header__registration">
		          <ul class="header-links">
		            <li class="header-reg__link">
		              <a href="/login">Login</a>
		            </li>
		            <li class="header-reg__link">
		              <a href="/register">Sign up</a>
		            </li>
		          </ul>
		        </div>
			</c:if>
			<c:if test="${loggedIn}">
			  <div class="user-logo">
				<p class="user-logo--greeting">	Hello, <span>${firstname}</span>! </p>
					
			    <a href="/userProfile">
			      <img class="png-user" alt="" src="/clipart1297398.png">
				</a>
			  </div>
			</c:if>
		</div>
</body>
</html>