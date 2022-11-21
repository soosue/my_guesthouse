<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>List</title>
</head>
<body>
<div style="background-color: pink; height: 50px; text-align: center; line-height : 50px">회원의 포인트와 회원등급 수정이 가능합니다.</div>
<div class="container">
    <table class="table table-hover" id="membersTable">
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
        <tbody id="membersBody">
        <td colspan="8" style="text-align: center">게시판에 저장된 글이 없습니다.</td>
        </tbody>
    </table>
</div>
<br/>
<div class="text-center">
    <ul id="pagination" class="pagination justify-content-center"></ul>
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

<script type="text/javascript" src="/resources/javascript/paging/paging.js"></script>
<script type="text/javascript">
    const getMembers = (page = 1) => {
        return getDataAndDraw(
            "/v1/admin/members?page=",
            memberRow,
            "membersTable",
            "membersBody",
            pageComponent,
            "pagination",
            page
        );
    }

    const memberRow = ({id, name, email, phoneNumber, createdAt, point, memberLevel}) => {
        return `<tr>
                    <td class="tb" value="\${id}">\${id}
                    <input type="hidden" id="memberName" name="memberName" value="\${name}"/>
                    <input type="hidden" id="memberCode" name="memberCode" value="\${id}"/>
                    </td>
                    <td id="memberName" value="\${name}">\${name}</td>
                    <td>\${email}</td>
                    <td>\${phoneNumber}</td>
                    <td>\${createdAt}</td>
                    <td>\${point}</td>
                    <td>\${memberLevel}</td>
                    <td id="update">
                        <button class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal">
                            <i class="fa fa-pencil"></i>
                        </button>
                    </td>
                    </tr>`;
    };

    const pageComponent = ({text, pageNumber, bold = false}) => {
        return `<li class="page-item"><a class="page-link" data-page="\${pageNumber}" \${bold ? `
        style = "font-weight: bold"` : ""}>\${text}</a><li>`;
    }

    document.addEventListener("DOMContentLoaded", () => {
        addPaginationClickEventTo("pagination", getMembers);
        getMembers();
    })
</script>
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
                "id": memberId,
                "point": point,
                "memberLevel": memberLevel
            })
        }).then(response => {
            if (response.status === 200) {
                alert("수정되었습니다");
            } else {
                alert("수정되지 않았습니다");
            }
            location.href = "/v1/admin/members.page";
        });
    }
    updateButton.addEventListener("click", updateMemberHandler);
</script>
</html>
