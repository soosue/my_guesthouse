<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${root}/resources/css/guestdelluna/menuLayout.css">
    <script type="text/javascript"
            src="${root}/resources/javascript/guestdelluna/menuLayout.js"></script>
    <title>Insert title here</title>
    <script type="text/javascript" src="${root}/resources/xhr/xhr.js"></script>
    <script type="text/javascript">
        function toServer(root, param) {
            var url = root + "/host/exManagementView.do";
            var params = "pageNumber=" + param;
            sendRequest("GET", url, fromServer, params);
        }

        function fromServer() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                document.getElementById("listView").innerHTML = xhr.responseText;
                var currentPage = $("#currentPage").val();
                var page = "#" + currentPage.toString();
                $(page).css({
                    'color': '#008489',
                    'font-size': '1.2rem',
                    'font-weight': 'bold'
                });
            }
        }

        function cancel(root, exCode) {
            //alert(root + "," + exCode);
            var value = confirm("정말 삭제하시겠습니까?");
            if (value == false) {
                return false;
            } else {
                location.href = root + "/host/exCancel.do?exCode=" + exCode;
            }
        }
    </script>
</head>
<body onload="toServer('${root}','')">
<div id="wrap">
    <div class="menuL">
        <ul>
            <li>
                <a href="/my-page/reviews.page">후기</a>
            </li>
            <li>
                <a href="${root}/guestdelluna/memberUpdate.do">회원수정</a>
            </li>
            <li>
                <a href="${root}/manage/points.page">포인트관리</a>
            </li>
            <li>
                <a href="${root}/guestdelluna/payList.do">결제내역</a>
            </li>
            <c:if test="${memberLevel == 'Host'}">
                <hr style="border: 0.0315rem solid #ddd;"/>
                <li>
                    <a href="${root}/host/reservationView.do">숙소예약현황</a>
                </li>
                <li>
                    <a href="${root}/host/exReservationView.do">체험예약현황</a>
                </li>
                <li>
                    <a href="${root}/host/salesView.do">매출조회</a>
                </li>
                <li>
                    <a href="${root}/host/houseManagement.do">게스트하우스 관리</a>
                </li>
                <li>
                    <a href="${root}/host/exManagement.do">체험 관리</a>
                </li>
            </c:if>
            <li>
                <a href="${root}/guestdelluna/memberDelete.do">회원탈퇴</a>
            </li>
        </ul>
    </div>

    <div class="menuR">
        <h3>체험 관리</h3>
        <p class="menuExplain">현재 승인여부 조회와 등록 취소를 할 수 있습니다.</p>
        <div id="listView">
        </div>
    </div>

</div>

</body>
</html>
