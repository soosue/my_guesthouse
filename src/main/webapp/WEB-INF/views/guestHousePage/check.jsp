<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
    <c:if test="${check > 0 }">
    location.href = "/guestHousePage/reservation.do?houseCode=${houseCode}&memberCode=${memberCode}&checkIn=${checkIn}&checkOut=${checkOut}&people=${people}";
    </c:if>

    <c:if test="${check==0}">
    alert("예약 가능 인원을 초과 하였습니다.");
    location.href = "/guestHousePage/guestHouse.do?houseCode=${houseCode}"
    </c:if>
</script>
</body>
</html>
