<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add a new song to the list</title>
</head>
<body>

	<h1>Start a new Song</h1>
		<form:form action="Song" modelAttribute="lyric" class="form" method="post">
		
			<div class="form-row">
			 	<form:errors path="song_name" class="error"/>
				<form:label for="song_name" path="song_name">Song:</form:label>
				<form:input type="text" path="song_name"/>
			</div>
			
			<div class="form-row">
				<form:errors path="genre" class="error"/>
				<form:label for="genre" path="genre">genre:</form:label>
				<form:input type="text" path="genre"/>
			</div>
			
			<div class="form-row">
				<form:errors path="songLyrics" class="songLyrics"/>
				<form:label for="songLyrics" path="songLyrics">Add your lyrics:</form:label>
				<form:textarea path="songLyrics"/>
			</div>
			
			<div class="form-row">
				<input type="submit" value="Submit" class="btn-primary"/>
			</div>
			
		</form:form>
	<a href=/home>Cancel</a>

</body>
</html>