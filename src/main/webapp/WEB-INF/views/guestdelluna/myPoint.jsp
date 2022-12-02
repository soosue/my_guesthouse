<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!-- 1.맨 위에 페이지 블록 -->
<c:set var="pageBlock" value="${5}"/>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${root}/resources/css/guestdelluna/menuLayout.css">
    <script type="text/javascript" src="${root}/resources/javascript/jquery/flick/jquery-ui.js"></script>
    <link rel="stylesheet" href="${root}/resources/javascript/jquery/flick/jquery-ui.css">
    <title>포인트 관리</title>
    <script type="text/javascript" src="${root}/resources/xhr/xhr.js"></script>
    <script type="text/javascript"
            src="${root}/resources/javascript/guestdelluna/menuLayout.js"></script>
</head>
<body>
<div id="wrap" style="margin-top: 3rem;">
    <div class="menuL" style="margin-top: 6.5rem;">
        <ul>
            <li><a href="/my-page/reviews.page"
                   style="color: black;">후기</a></li>
            <li><a href="${root}/guestdelluna/memberUpdate.do"
                   style="color: black;">회원수정</a></li>
            <li><a href="${root}/manage/points.page"
                   style="color: black;">포인트관리</a></li>
            <li><a href="${root}/guestdelluna/payList.do"
                   style="color: black;">결제내역</a></li>
            <c:if test="${memberLevel == 'Host'}">
                <hr style="border: 0.0315rem solid #ddd;"/>
                <li><a href="${root}/host/reservationView.do"
                       style="color: black;">숙소예약현황</a></li>
                <li><a href="${root}/host/exReservationView.do"
                       style="color: black;">체험예약현황</a></li>
                <li><a href="${root}/host/salesView.do" style="color: black;">매출조회</a></li>
                <li><a href="${root}/host/houseManagement.do"
                       style="color: black;">게스트하우스 관리</a></li>
                <li><a href="${root}/host/exManagement.do"
                       style="color: black;">체험 관리</a></li>
            </c:if>
            <li><a href="${root}/guestdelluna/memberDelete.do"
                   style="color: black;">회원탈퇴</a></li>
        </ul>
    </div>

    <div class="menuR" style="margin-left: -10rem; margin-top: 5rem;">
        <div id="tabs" class="container"
             style="width: 60rem; margin-top: 1.5rem; margin-left: -4rem;">
            <ul style="border: 0px; background: #ffffff;">
                <li id="pointAccumulates" class="accu"
                    style="float: left; border: 0px; background: #ffffff; margin-top: -3.04rem; margin-left: -0.5rem"><a
                        href="#fragment-1"><span>포인트 적립 내역</span></a></li>
                <li id="pointUses" class="use"
                    style="float: left; border: 0px; background: #ffffff; margin-top: -3.04rem; margin-left: 11rem;"><a
                        href="#fragment-2"><span>포인트 사용 내역</span></a></li>

                <li style="margin-top: -2.3rem; float: right; color: black"> 보유 포인트 : <span
                        style="text-decoration: underline;" id="point"></span>
                </li>
            </ul>

            <div id="fragment-1">
                <div id="accuView">
                    <table class="table table-hover" id="pointAccumulatesTable">
                        <caption style="display:none">Point Accumulates Table</caption>
                        <thead>
                        <tr>
                            <th>번호</th>
                            <th>적립장소</th>
                            <th>적립일</th>
                            <th>적립포인트</th>
                        </tr>
                        </thead>
                        <tbody id="pointAccumulatesBody">
                        <td colspan="8" style="text-align: center">적립된 포인트가 없습니다.</td>
                        </tbody>
                    </table>
                    <div class="text-center">
                        <ul id="paginationPointAccumulates" class="pagination justify-content-center"></ul>
                    </div>
                </div>
            </div>

            <div id="fragment-2">
                <div id="useView">
                    <table class="table table-hover" id="pointUsesTable">
                        <caption style="display:none">Point Uses Table</caption>
                        <thead>
                        <tr>
                            <th>번호</th>
                            <th>사용장소</th>
                            <th>사용일</th>
                            <th>사용포인트</th>
                        </tr>
                        </thead>

                        <tbody id="pointUsesBody">
                        <td colspan="8" style="text-align: center">사용된 포인트가 없습니다.</td>
                        </tbody>
                    </table>
                    <div class="text-center">
                        <ul id="paginationPointUses" class="pagination justify-content-center"></ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript" src="/resources/javascript/paging/paging.js"></script>
<script type="text/javascript">
    $(function () {
        $('#tabs').tabs();
        $("#pointAccumulates").click(() => getPointAccumulates());
        $("#pointUses").click(() => getPointUses());

        addPaginationClickEventTo("paginationPointAccumulates", getPointAccumulates);
        addPaginationClickEventTo("paginationPointUses", getPointUses);

        getPointAccumulates();
        getCurrentPoint();
    });

    const getCurrentPoint = () => {
        fetch("/v1/points/me", {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(response => {
            if (response.status === 200) {
                response.json().then(
                    data => {
                        const pointElement = document.getElementById("point")
                        pointElement.innerHTML = data.point;
                    }
                )
            }
        });
    }

    const getPointAccumulates = (page = 1) => {
        return getDataAndDraw(
            "/v1/point-accumulates/me?page=",
            pointAccumulatesRow,
            "pointAccumulatesTable",
            "pointAccumulatesBody",
            pageComponent,
            "paginationPointAccumulates",
            page
        );
    }

    const getPointUses = (page = 1) => {
        return getDataAndDraw(
            "/v1/point-uses/me?page=",
            pointUsesRow,
            "pointUsesTable",
            "pointUsesBody",
            pageComponent,
            "paginationPointUses",
            page
        );
    }

    const pointAccumulatesRow = ({id, guestHouseName, createdAt, point}) => {
        return `<tr>
                    <td>\${id}</td>
                    <td>\${guestHouseName}</td>
                    <td>\${createdAt} </td>
                    <td>\${point}</td>
                </tr>`;
    };

    const pointUsesRow = ({id, placeName, createdAt, point}) => {
        return `<tr>
                    <td>\${id}</td>
                    <td>\${placeName}</td>
                    <td>\${createdAt}</td>
                    <td>\${point}</td>
                </tr>`;
    }

    const pageComponent = ({text, pageNumber, bold = false}) => {
        return `<li class="page-item"><a class="page-link" data-page="\${pageNumber}" \${bold ? `
        style = "font-weight: bold"` : ""}>\${text}</a><li>`;
    }
</script>
</html>
