<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원 로그아웃</title>
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
</head>
<body>
<script type="text/javascript">
    alert("로그아웃 되었습니다");
    Kakao.init('5a47c72d35ab36aa08feca719cb2bccf');
    Kakao.Auth.logout();

    location.href = "/";
</script>
</body>
</html>
