<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lyrics Labs</title>
</head>
<body>
	<h1>Hello, <c:out value="${acc.getAccount_name()}"></c:out></h1>
	<p>
    	<a href="/logout">Log out</a>
	</p>
	<h1>All Song Labs</h1>
			<table>
		
			<tr>
				<th>Song</th>
				<th>Genre</th>
				<th># of Collaborations</th>


			</tr>
				<c:forEach var="Lyric" items="${Lyrics}">
				    <tr>
				        <td><a href="/Song/${Lyric.id}"><c:out value="${Lyric.song_name}"/></a></td>
				        <td><c:out value="${Lyric.genre}"/></td>
				        <td><c:out value="${Lyric.updateCount}"/></td>
				    </tr>
				</c:forEach>
		</table>
	<h1><a href="/newSong">Add a new song to the list</a></h1>
	
</body>
</html>