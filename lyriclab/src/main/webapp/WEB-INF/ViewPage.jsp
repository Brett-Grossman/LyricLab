<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Song</title>
</head>
<body>
	<h1><strong>Name: <c:out value="${Lyric.song_name}"/></strong></h1>
	<h1>(Started by <c:out value="${Lyric.user_name}"></c:out>)</h1>

	<p>Genre: <c:out value="${Lyric.genre}"/></p>
	<p>Lyrics: <c:out value="${Lyric.songLyrics}"></c:out>
	


    <p><a href="<c:url value='/editSong/${Lyric.id}'/>">Contribute</a></p>
    <a href=/home>Home Page</a>

</body>
</html>