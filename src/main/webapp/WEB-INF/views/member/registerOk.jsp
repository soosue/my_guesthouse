<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입 확인</title>
</head>
<body>

<script type="text/javascript">
    <c:if test="${check > 0}">
    alert("회원가입이 되었습니다.");
    </c:if>
    <c:if test="${check == 0}">
    alert("회원가입에 실패하셨습니다.");
    </c:if>
    location.href = "/";
</script>
</body>
</html>
