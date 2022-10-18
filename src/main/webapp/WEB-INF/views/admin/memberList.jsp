<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <script>
        $(function () {
            $('#update button').on('click', function () {
                var currentRow = $(this).closest('tr');

                var memberCode = currentRow.find('#memberCode')[0].value;
                var name = currentRow.find('#memberName')[0].value;
                var point = currentRow.find('td:eq(5)').text();
                var memberLevel = currentRow.find('td:eq(6)').text();

                $('.modal #updateMemberCode').val(memberCode);
                $('.modal #memberName').val(name);
                $('.modal #point').val(point);
                $('.modal #memberLevel').val(memberLevel);
            });
        });


    </script>
    <title>List</title>
</head>
<body>

<input type="hidden" name="memberCode" value="${memberCode}"/>
<input type="hidden" name="pageNumber" value="${pageNumber}"/>
<table class="table">
    <tr>
        <td align="center" bgcolor="pink">
            <div>회원의 포인트와 회원등급 수정이 가능합니다.</div>
        </td>
    </tr>
</table>

<c:if test="${count == 0 || memberList.size() == 0}">
    <table>
        <tr>
            <td align="center">게시판에 저장된 글이 없습니다.</td>
        </tr>
    </table>
</c:if>

<div class="container">
    <c:if test="${count > 0 }">
        <table class="table table-hover">
            <thead>
            <tr>
                <td>회원번호</td>
                <td>이름</td>
                <td>이메일</td>
                <td>전화</td>
                <td>가입일자</td>
                <td>포인트</td>
                <td>회원등급</td>
                <td>수정</td>

            </tr>
            </thead>

            <tbody>
            <c:forEach var="memberDto" items="${memberList}">
                <tr>
                    <td class="tb" value="${memberDto.memberCode}">${memberDto.memberCode}
                        <input type="hidden" id="memberName" name="memberName" value="${memberDto.memberName}"/>
                        <input type="hidden" id="memberCode" name="memberCode" value="${memberDto.memberCode}"/>
                    </td>
                    <td id="memberName" value="${memberDto.memberName}">${memberDto.memberName}</td>

                    <td>${memberDto.email}</td>
                    <td>${memberDto.phone}</td>
                    <td>${memberDto.regDate}</td>
                    <td>${memberDto.point}</td>
                    <td>${memberDto.memberLevel}</td>
                    <td id="update">
                        <button class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal"><i
                                class="fa fa-pencil"></i></button>
                    </td>


                </tr>


            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<br/>

<div class="text-center">
    <ul class="pagination justify-content-center">
        <c:if test="${count > 0 }">
            <c:set var="pageBlock" value="${10}"/>
            <c:set var="pageCount" value="${count/boardSize+(count%boardSize==0 ? 0:1)}"/>

            <fmt:parseNumber var="result" value="${(currentPage-1)/pageBlock}" integerOnly="true"/>
            <c:set var="startPage" value="${result*pageBlock+1}"/>
            <c:set var="endPage" value="${startPage+pageBlock-1}"/>

            <c:if test="${endPage > pageCount}">
                <c:set var="endPage" value="${pageCount}"/>
            </c:if>

            <c:if test="${startPage > pageBlock}">
                <li class="page-item"><a class="page-link"
                                         href="${root}/admin/memberList.do?pageNumber=${startPage-pageBlock}">이전</a>
                </li>
            </c:if>

            <c:forEach var="i" begin="${startPage}" end="${endPage}">
                <li class="page-item"><a class="page-link" href="${root}/admin/memberList.do?pageNumber=${i}">${i}</a>
                <li>
            </c:forEach>

            <c:if test="${endPage > pageCount}">
                <li><a href="${root}/admin/memberList.do?pageNumber=${startPage+pageBlock}">다음</a></li>
            </c:if>

        </c:if>
    </ul>
</div>

<!-- 모달페이지 -->
<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">회원수정</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="form-group">
                    <!-- memberCode는 hidden으로 값만 넘겨줌 -->
                    <input type="hidden" name="id" id="updateMemberCode"/>
                    <div align="center" style="width: 25rem; margin-top: 3rem;">
                        <input type="text" class="form-control" id="updateMemberName"
                               style="width: 5rem; float: left; margin-left: 7rem;" disabled="disabled"/>
                        <a>님의 회원 정보 수정입니다.</a>
                    </div>

                    <div align="center" height="20" width="125" style="margin-top: 3rem;">
                        <p align="center" height="20" width="125">포인트</p>
                        <input type="text" class="form-control" id="point" style="width: 10rem;" name="point"/>
                    </div>

                    <div class="container" align="center" style="width: 10rem;margin-top: 2rem; margin-bottom: 3rem;">
                        <p align="center" height="20" width="125">회원등급</p>
                        <select class="form-control" id="memberLevel" name="memberLevel">
                            <option value="">선택하세요</option>
                            <option value="Admin">관리자</option>
                            <option value="A">일반</option>
                            <option value="Host">호스트</option>
                        </select>
                    </div>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer" style="margin-bottom: 1rem; padding-top: 2rem;">
                    <div style="width:10rem; margin-right: 10rem;" align="center">
                        <button id="modalSubmit" type="button" class="btn btn-info">수정</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">닫기</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    const updateButton = document.getElementById("modalSubmit");
    const updateMemberHandler = async () => {
        const point = document.getElementById("point").value;
        const memberLevel = document.getElementById("memberLevel").value;
        const memberId = document.getElementById("updateMemberCode").value;

        fetch("/v1/admin/members/" + memberId, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "id":memberId,
                "point":point,
                "memberLevel":memberLevel
            })
        }).then(response => {
            if (response.status === 200) {
                alert("수정되었습니다");
            } else {
                alert("수정되지 않았습니다");
            }
            location.href = "/admin/memberList.do";
        });
    }
    updateButton.addEventListener("click", updateMemberHandler);
</script>
</html>
