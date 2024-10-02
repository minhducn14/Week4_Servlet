<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản Lý Tin Tức</title>

<style>
div {
	margin: 10px;
	display: flex;
	justify-content: center;
}

table {
	width: 100%;
	border-collapse: collapse;
}

table th, table td {
	border: 1px solid black;
}

th, td {
	padding: 10px;
	text-align: left;
}
</style>

</head>
<body>


	<div
		style="display: flex; justify-content: space-around; align-items: center;">
		<h1>Quản Lý Tin Tức</h1>
		<h1>
			<a href="${pageContext.request.contextPath}">Trang Chủ</a>
		</h1>
	</div>
	
	<div>
		<c:if test="${empty tinTucs}">
			<h2>Không có dữ liệu</h2>
		</c:if>
		<c:if test="${ not empty tinTucs}">
			<table>
				<thead>
					<tr>
						<th>Mã Tin Tức</th>
						<th>Tiêu đề</th>
						<th>Nội dung</th>
						<th>Liên Kết</th>
						<th>Mã Danh Mục</th>
						<th>Chức Năng</th>
					</tr>
				</thead>
				<c:forEach items="${tinTucs}" var="tinTuc">
					<tr>
						<td>${tinTuc.maTinTuc}</td>
						<td>${tinTuc.tieuDe}</td>
						<td>${tinTuc.noiDungTT}</td>
						<td>${tinTuc.lienKet}</td>
						<td>${tinTuc.danhMuc.maDM}</td>
						<td>
							<form action="QuanLyForm" method="post">
								<input type="hidden" name="maTinTuc" value="${tinTuc.maTinTuc}">
								<input type="submit" value="Xoá"
									onclick="return confirm('Are you sure?')">
							</form>
						</td>
				</c:forEach>
				<tbody>

				</tbody>
			</table>
		</c:if>

	</div>
</body>
</html>