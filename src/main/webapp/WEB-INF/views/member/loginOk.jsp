<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인창</title>
</head>
<body>

<script type="text/javascript">
    <c:if test="${memberLevel != null}">
    alert("로그인 되었습니다.");
    if ('${beforeURL}' == null) {
        location.href = "/";
    } else {
        location.href = "${beforeURL}";
    }
    </c:if>

    <c:if test="${memberLevel == null}">
    alert("로그인 실패하였습니다. 가입하지 않은 이메일이거나 잘못된 비밀번호입니다.");
    location.href = "/";
    </c:if>
</script>
</body>
</html>
