<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>리뷰 등록 완료</title>
</head>
<body>
<script type="text/javascript">
    <c:if test="${check > 0}">
    alert("후기가 등록 되었습니다.");
    </c:if>

    <c:if test="${check == -1}">
    alert("등록할 후기가 없습니다.");
    </c:if>
    location.href = "/guestHousePage/guestHouse.do?houseCode=${guestHouseId}";
</script>

</body>
</html>
