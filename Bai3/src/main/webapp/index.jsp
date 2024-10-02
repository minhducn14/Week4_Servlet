<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Website quản lý tin tức trực tuyến</title>

<style>
div {
	justify-content: center;
	align-items: center;
	border: 2px solid;
	display: flex;
}

a {
	margin: 50px;
	font-size: 20px;
}

img {
	height: 100px;
	background-size: cover;
}
</style>
</head>
<body>


	<div>
		<div id="background-title">
			<img src="resources/images/news.png" alt="news" />
		</div>
	</div>

	<div style="height: 100px">
		<a href="${pageContext.request.contextPath}/DanhSachTinTuc">Danh sách tin tức</a>| 
		<a href="${pageContext.request.contextPath}/TinTucForm">Thêm tin tức mới</a>| 
		<a href="${pageContext.request.contextPath}/QuanLyForm">Chức năng quản lý</a>
	</div>
	<div style="height: 500px"></div>

	<div>Vũ Nguyễn Minh Đức - 21095511 - DHKTPM17BTT</div>


</body>
</html>