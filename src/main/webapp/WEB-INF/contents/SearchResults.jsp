<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.*"%>
<%@ page import="model.dto.contents.Contents"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Results</title>
</head>
<body>
	<div class="search-results">
		<%
		List<Contents> searchList = (List<Contents>) request.getAttribute("searchList");

		if (searchList != null && !searchList.isEmpty()) {
			Iterator<Contents> iterator = searchList.iterator();
			int index = 1;
		%>
		<div class="gallery">
			<div class="container text-center">
				<%
				while (iterator.hasNext()) {
					Contents content = iterator.next();

					if (index % 4 == 1) {
				%>
				<div class="row">
					<%
					}
					%>
					<div class="col">
						<article class="card" data-bs-toggle="modal"
							data-bs-target="#exampleModal">
							<figure>
								<img src="<%=content.getContentImg()%>"
									alt="<%=content.getGenre()%>">
								<figcaption>
									<p class="h6"><%=content.getTitle()%></p>
								</figcaption>
							</figure>
						</article>
					</div>
					<%
					index++;
					}
					%>
					<%
					if (index % 4 == 1) {
					%>
				</div>
				<%
				}
				%>
			</div>
		</div>
		<%
		}
		%>
	
</body>
</html>
