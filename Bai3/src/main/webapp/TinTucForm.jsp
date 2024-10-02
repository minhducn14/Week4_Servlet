<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm tin tức</title>
<script>
	function validateForm() {
		var maTinTuc = document.forms["tinTucForm"]["maTinTuc"].value;
		var tieuDe = document.forms["tinTucForm"]["tieuDe"].value;
		var noiDungTT = document.forms["tinTucForm"]["noiDungTT"].value;
		var lienKet = document.forms["tinTucForm"]["lienKet"].value;
		var maDanhMuc = document.forms["tinTucForm"]["maDanhMuc"].value;

		if (maTinTuc == "" || tieuDe == "" || noiDungTT == ""
				|| maDanhMuc == "") {
			alert("Mã tin tức, Tiêu đề, Nội dung, và Mã danh mục là bắt buộc.");
			return false;
		}

		var lienKetPattern = /^(http:\/\/|https:\/\/)/;
		if (!lienKetPattern.test(lienKet)) {
			alert("Liên kết phải bắt đầu bằng http:// hoặc https://");
			return false;
		}

		if (noiDungTT.length > 255) {
			alert("Nội dung không được quá 255 ký tự.");
			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<div
		style="display: flex; justify-content: space-around; align-items: center;">
		<h1>Thêm tin tức</h1>
		<h1><a href="${pageContext.request.contextPath}">Trang Chủ</a></h1>
	</div>

	<div>
		<form name="tinTucForm" action="TinTucForm" method="post"
			onsubmit="return validateForm()">
			Mã tin tức: <input type="text" name="maTinTuc" required="required" /><br>
			Tiêu đề: <input type="text" name="tieuDe" required="required" /><br>
			Nội dung: <input type="text" name="noiDungTT" required="required" /><br>
			Liên kết: <input type="text" name="lienKet" required="required" /><br>
			Danh mục: <select name="maDanhMuc">
				<c:forEach var="danhMuc" items="${danhSachDanhMuc}">
					<option value="${danhMuc.maDM}">${danhMuc.tenDanhMuc}</option>
				</c:forEach>
			</select> <input type="submit" value="Thêm tin tức" />
		</form>
	</div>
</body>
</html>
