<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contribute</title>
</head>
<body>

	<h1>Add to <c:out value="${lyric.song_name}"/></h1>
	<form:form action="/editSong/${lyric.id}" method="post" modelAttribute="lyric">
		<div>
			<form:errors path="song_name" class="error"/>
			<form:label path="song_name">Song Title: </form:label><br />
			
			<p><form:input path="song_name"/></p>
		</div>
		<div>
			<form:errors path="genre" class="error"/>
			<form:label path="genre">Genre: </form:label><br />
			
			<p><form:input path="genre"/></p>
		</div>
		<div>
			<form:errors path="songLyrics" class="error"/>
			<form:label path="songLyrics">Add to your lyrics: </form:label><br />

			<form:textarea rows="4" path="songLyrics"/>
		</div>
		<div>
			<input type="submit" value="submit"/>
		</div>
	
	</form:form>

	<a href=/home>Cancel</a>
	<c:if test="${lyric.accounts.id eq sessionScope.accountId}">
        <p><a href="<c:url value='/deleteSong/${lyric.id}'/>">Delete</a></p>
    </c:if>
</body>
</html>