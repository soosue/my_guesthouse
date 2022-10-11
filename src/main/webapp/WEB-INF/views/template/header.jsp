<%@ page language="java" contentType="text/html; charset=UTF-8"


         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">


<head>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="${root}/resources/css/header/header.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <script src="https://t1.kakaocdn.net/kakao_js_sdk/2.0.0/kakao.min.js"
            integrity="sha384-PFHeU/4gvSH8kpvhrigAPfZGBDPs372JceJq3jAXce11bVA6rMvGWzvP4fMQuBGL"
            crossorigin="anonymous"></script>
    <script type="text/javascript">
        $(function () {
            $('ul.tab li').click(function () {
                var activeTab = $(this).attr('data-tab');
                $('ul.tab li').removeClass('current');
                $('.tabcontent').removeClass('current');
                $(this).addClass('current');
                $('#' + activeTab).addClass('current');
            })
        });

    </script>

    <script>
        $(function () {
            if ('${memberLevel}' == 'Admin') {
                $(".navbar").css("background-color", "#ffe6de");
            }
        });
    </script>


    <style type="text/css">

        html, body {
            color: #484848;
            font-family: 'Nanum Gothic', sans-serif !important;
        }


    </style>

</head>
<body>

<script type="text/javascript">
    function msgAlim(root) {
        if ('${memberLevel}' != null) {

            $.ajax({
                type: "GET",
                url: root + "/guestdelluna/msgView.do",
                dataType: "text",
                error: function () {
                    alert("값못가져옴 ㅠ");
                },
                success: function (msgData) {
                    $("#msgCnt").html(msgData); //div에 받아온 값을 넣는다.
                    //alert(msgData);
                    //alert(112);
                    var modal = document.getElementById("myModal");
                    $("#_asdf").click(function () {
                        $("#myModal").css("display", "block");
                    })
                    window.onclick = function (event) {
                        if (event.target == modal) {
                            modal.style.display = "none";
                        }
                    }

                }

            });

        }
    }
</script>

<!-- 헤더영역 -->

<nav class="navbar navbar-expand-sm fixed-top"
     style="background-color: white; height: 7rem;">
    <div class="container-fluid">
        <div class="navbar-header">

            <!--
                width: 23.125rem;
                height: 14rem;
                margin-top: 2rem;
              -->
            <%-- <img src="${root}/resources/images/logo1.png" style="width: 3.125rem; height: 3.125rem;"/> --%>
            <a class="active" href="/"
               style="font-size: 2rem; font-weight: bold; color: black; margin-left: 1rem;">
                <img src="${root}/resources/images/logoFinal.png" style="width: 11rem;"/>
            </a>
        </div>
        <!-- <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#probootstrap-menu" aria-controls="probootstrap-menu" aria-expanded="false" aria-label="Toggle navigation">

      <span><i class="ion-navicon"></i></span>
    </button> -->

        <ul class="nav navbar-nav navbar-right" style="margin-right: 5rem;">

            <c:if test="${memberLevel == null}">
                <li class="nav-item"><a class="nav-link" href="${root}" style="color:black !important">HOME</a></li>

                <li id="log">
                    <button class="btn" data-toggle="modal" data-target="#login">로그인/회원가입
                        <!-- <i class="fa fa-user"></i> --></button>
                </li>
            </c:if>

            <c:if test="${memberLevel != null}">
                <li class="nav-item"><a class="nav-link"
                                        href="${root}/search?checkIn=&checkOut=&local=&people=1&searchHouseName=">게스트
                    하우스 검색</a></li>
                <li class="nav-item"><a class="nav-link"
                                        href="${root}/experience?checkIn=&checkOut=&local=&people=1&searchExName=">체험
                    검색</a></li>
                <li class="nav-item"><a class="nav-link" href="${root}/host/register.do">호스팅하기</a></li>

                <c:if test="${memberLevel =='Host'}">
                    <li class="nav-item"><a class="nav-link" href="${root}/experience/exHost.do">체험 등록하기</a></li>
                </c:if>

                <li class="nav-item"><a class="nav-link" href="${root}/guestdelluna/zzimlist.do">장바구니</a></li>
                <li class="nav-item"><a class="nav-link" href="/v1/member/logout">로그아웃</a></li>
                <li class="nav-item"><a class="nav-link" href="${root}/guestdelluna/myInfo.do">마이페이지</a></li>

                <c:if test="${memberLevel =='Admin'}">
                    <!-- <li class="nav-item"><a class="nav-link" href="${root}/admin/experienceList.do">체험 관리</a></li>
                    <li class="nav-item"><a class="nav-link" href="${root}/admin/houseList.do">게스트하우스 관리(등록 승인, 취소)</a></li>
                    <li class="nav-item"><a class="nav-link" href="${root}/admin/memberList.do">회원관리</a></li>
                    -->

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            관리자
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="${root}/admin/experienceList.do">체험 관리</a>
                            <a class="dropdown-item" href="${root}/admin/houseList.do">게스트하우스 관리</a>
                            <a class="dropdown-item" href="${root}/admin/memberList.do">회원관리</a>
                        </div>
                    </li>
                </c:if>

            </c:if>
        </ul>
    </div>
</nav>

<!-- //헤더영역 -->


<!-- 모달페이지 -->


<div class="modal fade" id="login">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">로그인 / 회원가입</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <form action="/v1/member/login" method="post" name="createForm">
                <section id="container">

                    <input type="hidden" id="isCasinoGuide" value="">
                    <div class="loginWrap">

                        <div class="fullBg">
                            <div class="innerBox">
                                <div class="reserveLogin clearFixed">

                                    <div>
                                        <!-- <div class="tapBox">
                                            <ul class="tab">
                                                <li class="current" data-tab="tab1"><a href="#">회원 로그인</a></li>
                                                <li data-tab="tab2"><a href="#">카카오 로그인</a></li>
                                            </ul>
                                        </div> -->


                                        <ul class="nav nav-tabs">
                                            <li class="nav-item">
                                                <a class="nav-link active" data-toggle="tab" href="#myLogin">일반회원
                                                    로그인</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" data-toggle="tab" href="#kakaoLogin">카카오 로그인</a>
                                            </li>
                                        </ul>

                                        <div class="tab-content">
                                            <div class="tab-pane fade show active" id="myLogin">
                                                <div class="loginBox">
                                                    <div class="col" align="center">
                                                        <input class="form-control" style="margin-top: 2.5rem;"
                                                               type="email" id="email" name="email"
                                                               placeholder="이메일을 입력하세요" required="required">

                                                    </div>
                                                    <div class="col" align="center">
                                                        <input class="form-control" type="password" id="password"
                                                               name="password" placeholder="비밀번호를 입력하세요"
                                                               required="required">

                                                    </div>
                                                    <div align="center">
                                                        <button class="btn btn-info btn-block" style="width: 18rem;"
                                                                type="submit">로그인
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="tab-pane fade" id="kakaoLogin">
                                                <div class="loginBox">
                                                    <div class="inp" align="center">
                                                        <button type="button" class="btn btn-warning"
                                                                style="width: 18rem; margin-top: 3rem;"
                                                                onclick="kakaoLogin()" value="카카오 로그인">카카오 로그인
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>


                                        <div>
                                            <div class="modal-footer" style="text-align: center; margin-top: 3rem;">
                                                <a style="font-size:1rem; margin-right: 2rem;">아직 회원이 아니신가요?</a>
                                                <button class="btn btn-outline-success"
                                                        onclick="location.href='${root}/member/register.page'"
                                                        type="button">회원가입
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </form>
        </div>
    </div>
</div>
</body>

<script type='text/javascript'>
    Kakao.init('28118e836a017712fcde6026b1e65d72');

    function kakaoLogin() {
        Kakao.Auth.authorize({
            redirectUri: window.location.origin + '/v1/member/kakaologin'
        });
    }
</script>
</html>
