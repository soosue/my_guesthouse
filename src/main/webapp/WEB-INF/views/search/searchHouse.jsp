<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<c:set var="pageBlock" value="${5}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="${root}/resources/css/search/search.css"/>
    <script type="text/javascript" src="${root}/resources/javascript/search/search.js"></script>
    <script type="text/javascript" src="${root}/resources/javascript/jquery/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" href="${root}/resources/javascript/jquery/jquery-ui.css"/>

    <!-- kakaoMap -->
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c37448871d1dc7c24bd47df3b92bf2c3"></script>

    <!-- Swiper -->
    <link rel="stylesheet" href="${root}/resources/css/swiper/swiper.css">
    <script src="${root}/resources/javascript/swiper/swiper.js"></script>

    <script>
        $(function () {
            setRoot('${root}');

            var map = setMap();
            if ('${jsonHouseList}' != '') {
                var house = JSON.parse('${jsonHouseList}').houseJson
                setHouseList(house, '${memberCode}');

                var height = house.length * 15.625 + 1.25;
                //alert(height);
                $(".houseContainer").css("height", height + "rem");

                var marker = setMarker(house, map, '1');
                var content =
                    '<div class="_overlaybox">' +
                    '	<div class="_shadowOverlaybox">' +
                    '		<div class="_houseCode" style="display:none;">' + house[0].houseCode + '</div>' +
                    '		<div class="_overlayImgContainer">' +
                    '			<div class="swiper-container">' +
                    '				<div class="swiper-wrapper">' +
                    // 			'					<div class="swiper-slide"><img style="max-width:100%; height:auto;" alt="img loading" src="'+root+'/image/'+house[0].fileList[0].fileName+'"/></div>' +
                    '					<div class="swiper-slide"><img style="max-width:100%; height:auto;" alt="img loading" src=""/></div>' +
                    '				</div>' +
                    '				<div class="swiper-pagination"></div>' +
                    '				<div class="swiper-button-next"></div>' +
                    '				<div class="swiper-button-prev"></div>' +
                    '			</div>' +
                    '			<div class="_heartButton _heartButton' + 0 + '">' +
                    // 			'				<button aria-label="????????? ?????? ????????????" type="button" class="_heart _r0agyd heart'+0+'"><svg viewBox="0 0 24 24" fill="#FF385C" fill-opacity="1" stroke="#FF385C" stroke-width="1" focusable="false" aria-hidden="true" role="presentation" stroke-linecap="round" stroke-linejoin="round" style="height: 1.3rem; width: 1.3rem; display: block; overflow: visible;"><path d="m17.5 2.9c-2.1 0-4.1 1.3-5.4 2.8-1.6-1.6-3.8-3.2-6.2-2.7-1.5.2-2.9 1.2-3.6 2.6-2.3 4.1 1 8.3 3.9 11.1 1.4 1.3 2.8 2.5 4.3 3.6.4.3 1.1.9 1.6.9s1.2-.6 1.6-.9c3.2-2.3 6.6-5.1 8.2-8.8 1.5-3.4 0-8.6-4.4-8.6" stroke-linejoin="round"></path></svg></button>' +
                    '				<button data-toggle="modal" data-target="#login" aria-label="????????? ?????? ????????????" type="button" class="_heart _r0agyd heart' + 0 + '"><svg viewBox="0 0 24 24" fill="currentColor" fill-opacity="0" stroke="#222222" stroke-width="1.4" focusable="false" aria-hidden="true" role="presentation" stroke-linecap="round" stroke-linejoin="round" style="height: 1.3rem; width: 1.3rem; display: block; overflow: visible;"><path d="m17.5 2.9c-2.1 0-4.1 1.3-5.4 2.8-1.6-1.6-3.8-3.2-6.2-2.7-1.5.2-2.9 1.2-3.6 2.6-2.3 4.1 1 8.3 3.9 11.1 1.4 1.3 2.8 2.5 4.3 3.6.4.3 1.1.9 1.6.9s1.2-.6 1.6-.9c3.2-2.3 6.6-5.1 8.2-8.8 1.5-3.4 0-8.6-4.4-8.6" stroke-linejoin="round"></path></svg></button>' +
                    '			</div>' +
                    '		</div>' +
                    '		<a href="#' + house[0].houseCode + '" style="display:block;">' +
                    '			<div class="_overlayInfoContainer">' +
                    '				<div class="_overlayReviewContainer">' +
                    '					<span class="_starImg"><svg viewBox="0 0 1000 1000" role="presentation" aria-hidden="true" focusable="false" style="height:0.75rem;width:0.75rem;fill:#FF385C"><path d="M972 380c9 28 2 50-20 67L725 619l87 280c11 39-18 75-54 75-12 0-23-4-33-12L499 790 273 962a58 58 0 0 1-78-12 50 50 0 0 1-8-51l86-278L46 447c-21-17-28-39-19-67 8-24 29-40 52-40h280l87-279c7-23 28-39 52-39 25 0 47 17 54 41l87 277h280c24 0 45 16 53 40z"></path></svg></span><span class="_reviewRate"> ' + house[0].revRate + '</span><span class="_reviewCount">(' + house[0].revCount + ')</span>' +
                    '	 			</div>' +
                    '				<div class="_houseName">' + house[0].houseName + '</div>' +
                    '				<div class="_houseFacilities">?????? ' + house[0].people + '???</div>' +
                    '			</div>' +
                    '		</a>' +
                    '	</div>' +
                    '	<div class="_overlayEdge"></div>' +
                    '</div>';

                // ????????? ??????????????? ????????? ???????????????

                // ????????? ??????????????? ???????????????
                var customOverlay = new kakao.maps.CustomOverlay({
                    position: new kakao.maps.LatLng(33.450701, 126.570667),
                    content: content,
                    clickable: true,
                    yAnchor: 1.05,
                    zIndex: 5
                });
                customOverlay.setMap(map);

                for (let i = 0; i < house.length; i++) {
                    kakao.maps.event.addListener(marker[i], 'click', function () {
                        //ajax??? ?????? ??? ?????? ????????????
                        $.ajax({
                            url: '${root}/overlay?houseCode=' + house[i].houseCode,
                            method: "get",
                            success: function (overlay) {
                                //???????????? ??????????????? ?????? ????????????
                                $("._overlaybox ._houseName").text(overlay.houseName);
                                $("._overlaybox ._reviewRate").text(overlay.revRate);
                                $("._overlaybox ._reviewCount").text("(" + overlay.revCount + ")");
                                $("._overlaybox ._people").text(overlay.people);
                                $("._overlaybox ._houseCode").text(overlay.houseCode);
                                $("._overlaybox a").attr("href", "${root}/guestHousePage/guestHouse.do?houseCode=" + overlay.houseCode);
                                $("._overlaybox ._heartButton").attr("class", "_heartButton _heartButton" + i);
                                $("._overlaybox ._heart").attr("class", "_heart _r0agyd heart" + i);
                                if ('${memberCode}' > 0) {
                                    $("._overlaybox ._heart").attr("data-toggle", "");
                                    $("._overlaybox ._heart").attr("data-target", "");
                                }
                                var overlayHeart = $("._overlaybox ._heart svg");
                                if (overlay.zzimed != null) {
                                    overlayHeart.attr("fill", "#FF385C");
                                    overlayHeart.attr("fill-opacity", "1");
                                    overlayHeart.attr("stroke", "#FF385C");
                                    overlayHeart.attr("stroke-width", "1");
                                } else {
                                    overlayHeart.attr("fill", "currentColor");
                                    overlayHeart.attr("fill-opacity", "0");
                                    overlayHeart.attr("stroke", "#222222");
                                    overlayHeart.attr("stroke-width", "1.4");
                                }
                                $("._overlaybox .swiper-wrapper .swiper-slide").remove();
                                if (overlay.fileList.length > 0) {
                                    for (let j = 0; j < overlay.fileList.length; j++) {
                                        var img = '<div class="swiper-slide"><img style="max-width:100%; height:auto;" alt="img loading" src="' + root + '/image/' + overlay.fileList[j].fileName + '"/></div>';
                                        $("._overlaybox .swiper-wrapper").append(img);
                                    }
                                } else {
                                    //?????? ????????? ????????? ??????????????? ???????????? ???????????? ????????????
                                }

                            }

                        });

                        customOverlay.setPosition(new kakao.maps.LatLng(house[i].lat, house[i].lng));
                        //customOverlay.setContent(overlayContent[i]);
                        map.panTo(marker[i].getPosition());
                        //heart('${memberCode}');
                        $("._overlaybox").css("display", "block");
                        swiper = setSwiper();
                    });
                    kakao.maps.event.addListener(marker[i], 'mouseover', function () {
                        var imageSrc = '${root}/image/h1.png', // ?????????????????? ???????????????
                            imageSize = new kakao.maps.Size(50, 84), // ?????????????????? ???????????????
                            imageOption = {offset: new kakao.maps.Point(21, 63)}; // ?????????????????? ???????????????. ????????? ????????? ???????????? ????????? ???????????? ????????? ???????????????.
                        // ????????? ?????????????????? ????????? ?????? ?????????????????? ???????????????
                        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
                        marker[i].setImage(markerImage);
                        marker[i].setZIndex(3);
                    });
                    kakao.maps.event.addListener(marker[i], 'mouseout', function () {
                        var imageSrc = root + '/image/h1.png', // ?????????????????? ???????????????
                            imageSize = new kakao.maps.Size(50, 50), // ?????????????????? ???????????????
                            imageOption = {offset: new kakao.maps.Point(21, 39)};

                        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
                        marker[i].setImage(markerImage);
                        marker[i].setZIndex(0);
                    });
                }

                //???????????? ???????????? ?????? ??? ????????? ?????????, ???????????? ?????? ?????? ??????
                for (let i = 0; i <= house.length; i++) {
                    $(".house" + i).mouseover(function () {
                        var imageSrc = '${root}/image/h1.png', // ?????????????????? ???????????????
                            imageSize = new kakao.maps.Size(50, 84), // ?????????????????? ???????????????
                            imageOption = {offset: new kakao.maps.Point(21, 63)}; // ?????????????????? ???????????????. ????????? ????????? ???????????? ????????? ???????????? ????????? ???????????????.
                        // ????????? ?????????????????? ????????? ?????? ?????????????????? ???????????????
                        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
                        marker[i].setImage(markerImage);
                        marker[i].setZIndex(3);
                    });
                    $(".house" + i).mouseout(function () {
                        var imageSrc = root + '/image/h1.png', // ?????????????????? ???????????????
                            imageSize = new kakao.maps.Size(50, 50), // ?????????????????? ???????????????
                            imageOption = {offset: new kakao.maps.Point(21, 39)};

                        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
                        marker[i].setImage(markerImage);
                        marker[i].setZIndex(0);
                    });
                }

            }
            kakao.maps.event.addListener(map, 'click', function () {
                $("._overlaybox").css("display", "none");
            });


            var swiper = setSwiper();

            $("._overlaybox").css("display", "none");


            //????????? ????????? ????????????
            $(window).bind("scroll", function () {
                console.log($(this));

            });


            //?????? ??????

            //?????? ??????
            heart('${memberCode}');

            //?????? ?????? ???????????? ???
            $("#filter").click(function () {
                if ($("#filterContent").css("display") == "none")
                    $("#filterContent").css("display", "block");
                else
                    $("#filterContent").css("display", "none");
            });

            //?????? ?????? ????????? ??????
            $("input[name='searchHouseName']").val("${searchHouseName}");
            var local = '${local}'.split(",");
            if (local.length > 1) {
                $(local).each(function (i, e) {
                    $("input[value='" + e + "']").attr("checked", "checked");
                })
            } else {
                $("input[class='all']").attr("checked", "checked");
            }
            $("option[value='${people}']").attr("selected", "");


            //?????? ???????????? checkIn??? checkOut ?????? ???????????? placeholder??? value ??????
            setCheckIn('${checkIn}');
            setCheckOut('${checkOut}');

            var date = new Date('${checkIn}');
            date.setDate(date.getDate() + 1);

            // ???????????? ???????????? ????????? ????????? ???????????? ??? ????????? ???????????? ??????
            checkBoxSetting();
            // ???????????? ???????????? ????????? ????????? ??????
            if (!$("input[type='checkbox']").is(":checked")) {
                $(".all").prop("checked", true);
            }

            //????????? ?????? ????????????
            var yearSuffix = "???";
            var dateFormat = "yy-mm-dd";
            var monthNames = ["1???", "2???", "3???", "4???", "5???", "6???", "7???", "8???", "9???", "10???", "11???", "12???"];
            var dayNamesMin = ["???", "???", "???", "???", "???", "???", "???"];
            $("#checkIn").datepicker({
                minDate: 0,
                showMonthAfterYear: true,
                yearSuffix: yearSuffix,
                dateFormat: dateFormat,
                monthNames: monthNames,
                dayNamesMin: dayNamesMin,
                onClose: function (selectedDate) {
                    // #checkOut??? ?????????????????? ??????(+1)
                    var min = new Date(selectedDate);
                    min.setDate(min.getDate() + 1);
                    var minDate = dateToString(min);

                    $("#checkOut").datepicker("option", {minDate: minDate});
                }
            });
            //???????????? ?????? ????????????
            $("#checkOut").datepicker({
                minDate: date,
                showMonthAfterYear: true,
                yearSuffix: yearSuffix,
                dateFormat: dateFormat,
                monthNames: monthNames,
                dayNamesMin: dayNamesMin,
            });


            $("#priceHigh").click(function () {
                $("#sort").val("priceHigh");
                var sortValue = $("#sort").val();
                sort(sortValue);
            });
            $("#priceLow").click(function () {
                $("#sort").val("priceLow");
                var sortValue = $("#sort").val();
                sort(sortValue);
            });
            $("#rateSort").click(function () {
                $("#sort").val("revRate");
                var sortValue = $("#sort").val();
                sort(sortValue);
            });

            $(".dateButton").click(function () {
                if ($(".checkInOutContainer").css("display") == "inline-block") {
                    $(".checkInOutContainer").css("display", "none");
                } else {
                    $(".checkInOutContainer").css("display", "inline-block");
                    $(".peopleContainer").css("display", "none");
                    $(".localContainer").css("display", "none");
                }
            });
            $(".peopleButton").click(function () {
                if ($(".peopleContainer").css("display") == "inline-block") {
                    $(".peopleContainer").css("display", "none");
                } else {
                    $(".peopleContainer").css("display", "inline-block");
                    $(".localContainer").css("display", "none");
                    $(".checkInOutContainer").css("display", "none");
                }
            });
            $(".localButton").click(function () {
                if ($(".localContainer").css("display") == "inline-flex") {
                    $(".localContainer").css("display", "none");
                } else {
                    $(".localContainer").css("display", "inline-flex");
                    $(".checkInOutContainer").css("display", "none");
                    $(".peopleContainer").css("display", "none");
                }
            });
            setPaging('${checkIn}', '${checkOut}', '${local}', '${people}', '${searchHouseName}', $("#sort").val());

            //?????? ????????????
// 		$("input[name='searchHouseName']").autocomplete({
// 			source: ['??????','??????'],
// 			change: function(){
// 				$("input[name='searchHouseName']").keyup(function(event){
// 					if(event.keyCode != '38' && event.keyCode!='40')
// 						$.ajax({
// 							url:'${root}/searchAutocomplete?searchName='+$(this).val(),
// 							method:"get",
// 							success:function(houseNames){

// 							}
// 						});

// 				});
// 			}
// 		});


            //$( "#checkIn" ).datepicker( "show" );

        });

        function setPeople(people) {
            $(".peopleButton").text("?????? " + people.options[people.selectedIndex].text);
        }

        function setDate(date) {
            $("._checkInSpan").text(date.value);
            if (date.value >= $("#checkOut").val()) {
                var checkOutDate = new Date(date.value);
                checkOutDate.setDate(checkOutDate.getDate() + 1);
                $("._checkOutSpan").text(dateToString(checkOutDate));
            }
        }

        function setCheckOutDate(date) {
            $("._checkOutSpan").text(date.value);
        }


        function setPaging(checkIn, checkOut, local, people, searchHouseName, sort) {
            //alert(checkIn+checkOut+local+people+searchHouseName+sort);
            var pageBlock =${pageBlock};

            var result = parseInt('${count/boardSize}');
            var pageCount = '${count%boardSize}' == 0 ? result : result + 1;
            var currentPage = '${currentPage}';
            var result2 = parseInt((currentPage - 1) / pageBlock);
            var startPage = result2 * pageBlock + 1;
            var endPage = startPage + pageBlock - 1;

            //alert("pageBlock: "+pageBlock+", result:"+result+", pageCount: "+pageCount+",\n result2: "+result2+", startPage: "+startPage+", endPage: "+endPage+", currentPage: "+currentPage);
            if (endPage > pageCount) {
                endPage = pageCount;
            }
            if (startPage > 1) {
                $(".pagination").append(
                    '<li class="page-item"><a class="page-link" href="${root}/search?pageNumber=1&checkIn=' + checkIn + '&checkOut=' + checkOut + '&local=' + local + '&people=' + people + '&searchHouseName=${searchHouseName}&sort=${sort}">[??????]</a></li>' +
                    '<li class="page-item"><a class="page-link" href="${root}/search?pageNumber=' + (startPage - pageBlock) + '&checkIn=' + checkIn + '&checkOut=' + checkOut + '&local=' + local + '&people=' + people + '&searchHouseName=${searchHouseName}&sort=${sort}">[??????]</a></li>'
                );
            }
            for (var i = startPage; i <= endPage; i++) {
                if (i == currentPage) {
                    $(".pagination").append(
                        '<li class="page-item active"><a class="page-link" id="' + i + '" class="n">' + i + '</a></li>'
                    );
                } else {
                    $(".pagination").append(
                        '<li class="page-item"><a class="page-link" href="${root}/search?pageNumber=' + i + '&checkIn=' + checkIn + '&checkOut=' + checkOut + '&local=' + local + '&people=' + people + '&searchHouseName=${searchHouseName}&sort=${sort}" id="' + i + '" class="n">' + i + '</a></li>'
                    );
                }
            }
            if (endPage < pageCount) {
                $(".pagination").append(
                    '<li class="page-item"><a class="page-link" href="${root}/search?pageNumber=' + (startPage + pageBlock) + '&checkIn=' + checkIn + '&checkOut=' + checkOut + '&local=' + local + '&people=' + people + '&searchHouseName=${searchHouseName}&sort=${sort}">[??????]</a></li>' +
                    '<li class="page-item"><a class="page-link" href="${root}/search?pageNumber=' + pageCount + '&checkIn=' + checkIn + '&checkOut=' + checkOut + '&local=' + local + '&people=' + people + '&searchHouseName=${searchHouseName}&sort=${sort}">[???]</a></li>'
                );
            }
        }


        function setSwiper() {
            return new Swiper('.swiper-container', {
                pagination: {
                    el: '.swiper-pagination',
                    dynamicBullets: true,
                },
                navigation: {
                    nextEl: '.swiper-button-next',
                    prevEl: '.swiper-button-prev',
                },
            });
        }


        function setMap() {
            var container = document.getElementById("map");
            var options = {
                center: new kakao.maps.LatLng(33.450701, 126.570667),
                level: 10
            };

            var map = new kakao.maps.Map(container, options);
            // ????????? ??????????????? ??????
            map.addControl(new kakao.maps.ZoomControl(), kakao.maps.ControlPosition.BOTTOMRIGHT);
            // ????????? ????????? ???????????? ??????
            map.addControl(new kakao.maps.MapTypeControl(), kakao.maps.ControlPosition.BOTTOMRIGHT);

            return map;
        }


        function sort(sortValue) {
            location.href = root + "/search?pageNumber=${pageNumber}&checkIn=${checkIn}&checkOut=${checkOut}&local=${local}&people=${people}&searchHouseName=${searchHouseName}&sort=" + sortValue;
        }

        //???????????? submit??????
        function confirmSubmit() {

            //????????? local?????? ?????????
            $("input[class='custom-control-input etc']").each(function (i, e) {
                if (e.checked == true) {
                    $("#local").val($("#local").val() + e.value + ",");
                }
            });
        }
    </script>
    <style type="text/css">

        .pagination {
            justify-content: center;
        }

        .house a {
            text-decoration: none;
            color: black;
        }

        ._overlaybox a {
            text-decoration: none;
            color: black;
        }

        ._shadowOverlaybox {
            width: 17.126rem;
            height: 17.542rem;
            border-radius: 0.5rem;
            box-shadow: rgba(0, 0, 0, 0.28) 0px 8px 28px !important;
        }

        ._overlayEdge {
            width: 1rem;
            height: 1rem;
            background-color: white;
            border-bottom-right-radius: 4px !important;
            transform: rotate(45deg) !important;
            box-shadow: rgba(0, 0, 0, 0.28) 0px 8px 28px !important;
            margin: auto;
            margin-top: -0.5rem;
        }

        ._overlayInfoContainer {
            background-color: white;
            border-radius: 0rem 0rem 0.5rem 0.5rem;
            width: 17.125rem;
            height: 6.125rem;
            padding: 1rem;
        }

        ._overlayImgContainer {
            background-color: white;
            border-radius: 0.5rem 0.5rem 0rem 0rem;
            width: 17.125rem;
            height: 11.416rem;
        }

        ._overlaybox {
            width: 17.125rem;
            height: 19.791rem;
        }

        .filterButton {
            display: inline-block;
        }

        .btn {
            border-radius: 0.938rem;
        }

        .custom-select {
            width: 8.75rem;
        }

        .checkInLabel, .checkOutLabel {
            font-size: 1.5rem;
        }

        .checkInContainer, .checkOutContainer {
            width: 12.375rem;
            display: inline-block;
        }

        .localAll, .localJeju, .localSeoguipo {
            font: 1rem bold;
        }

        .localAll {
            width: 4.375rem;
        }

        .localJeju {
            width: 7.5rem;
        }

        .localSeoguipo {
            width: 8.75rem;
        }

        .checkInOutContainer .form-control {
            display: inline-block;
            width: 7.2rem;
            position: absolute;
            bottom: 0.6rem;
        }

        .searchContainer .form-control {
            display: inline-block;
            width: 13.125rem;
            margin-left: 1.875rem;
        }

        .searchContainer button {
            margin-bottom: 0.188rem;
        }

        .calender::before {
            margin-top: 0.625rem;
        }

        /* i{ */
        /* 	position: absolute; */
        /* 	right:0.25rem; */
        /* 	top:-0.313rem; */
        /* } */
        .custom-control {
            margin-bottom: 0rem !important;
        }

        .localContainer {
            display: none;
        }

        .peopleContainer {
            margin-top: 1.125rem;
            width: 12.5rem;
            font-size: 1.25rem;
            display: none;
        }

        .searchContainer {
            font-size: 1.25rem;
            display: inline-block;
            /* 	position: fixed; */
            top: 10rem;
            z-index: 11;
            left: 20rem;
            margin-top: 1.125rem;
            right: 2rem;
            float: right;
            margin-right: 1rem;
        }

        .searchBtn {
            margin-left: 0.625rem;
        }

        .checkInOutContainer {
            margin-top: 0.93rem;
            display: inline-block;
        }

        .checkInOutContainer label {
            font-size: 1.25rem
        }

        .filterBox {
            margin-top: 0.938rem;
            margin-bottom: 0.938rem;
            width: 52.5rem;
            height: 10.063rem;
        }

        .formContainer {
            height: 4.688rem;
            width: 52.5rem;
            background-color: white;
            position: fixed;
            z-index: 10;
            top: 11.638rem;
            border-bottom: solid 0.1rem #cccccc;
        }

        .filterContainer :first-child {
            margin-left: 0.938rem;
            margin-top: 0.931rem;
            margin-bottom: 0.931rem;
        }

        .filterContainer {
            background-color: white;
            width: 52.5rem;
            position: fixed;
            z-index: 10;
            top: 6.8rem;
        }

        .houseListContainer {
            /* 	background-color: #bbccaa; */
            width: 52.5rem;
        }

        .houseListCount {
            /* 	background-color: #aabbcc; */
            padding-left: 1.313rem;
            height: 5rem;
            font-size: 2rem;
            font-weight: bold;
        }

        .houseContainer {
            /* 	background-color: #998877; */
            height: 1rem;
            padding: 0rem 1.5rem;
        }

        ._gig1e7 {
            border-top: #cccc solid 0.125rem;
            width: 49.5rem;
            height: 15.625rem;
        }

        ._ylefn59 {
            margin-top: 1.563rem;
            /* 	background-color: #ccddee; */
            width: 49.5rem;
            height: 14rem;
            position: relative;
        }


        ._houseImg {
            width: 18.75rem;
            height: 12.5rem;
            /* 	background-color: #eeffdd; */
            display: inline-block;
        }

        ._houseInfo {
            width: 29.75rem;
            height: 12.5rem;
            /* 	background-color: #ffddee; */
            display: inline-block;
            position: absolute;
            top: 0rem;
            right: 0rem;
        }

        ._starRate {
            height: 1.125rem;
            /* 	background-color: #ddffee; */
            margin-bottom: 0.5rem;
        }

        ._60dc7z {
            margin-left: 0.25rem;
        }

        ._starImg {
            display: inline-flex;
        }

        ._reviewCount {
            font-size: 0.875rem;
            color: #484848;
        }

        ._houseName {
            font-size: 1.125rem !important;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        ._houseFacilities {
            font-size: 0.875rem;
            color: #717171;
        }

        ._price {
            font-weight: bold;
            font-size: 1.125rem;
        }

        ._oneNight {
            font-size: 1.125rem;
        }

        ._priceContainer {
            position: absolute;
            bottom: 0rem;
            right: 0rem;

        }

        ._heartButton {
            position: absolute;
            border-radius: 2rem;
            top: 0.7rem;
            left: 0.7rem;
            opacity: 0.93;
            z-index: 5;
            width: 2rem;
            height: 2rem;
            background-color: white;
        }

        ._heart {
            background-color: white;
            border: 0rem;
            height: 1rem;
            width: 1rem;
            padding: 0;
            opacity: 0.93;
            position: absolute;
            top: 0.4rem;
            left: 0.35rem;
        }

        .emptyContainer {
            margin-top: 0.938rem;
            width: 1903px;
        }

        .sortContainer {
            /* 	width:51.5rem; */
            display: inline-block;
            text-align: right;
            float: right;
            margin-right: 1rem;
            margin-top: 0.931rem;
        }

        }
        body {
            position: absolute;
            top: 9rem;
        }

        .buttonContainer {
            display: inline-block;
            margin: 0rem !important;
        }

        form {
            position: relative;
            margin-left: 1.876rem;
        }

        .radius_border {
            border: 1px solid #919191;
            border-radius: 5px;
        }

        .custom_typecontrol {
            position: absolute;
            top: 10px;
            right: 10px;
            overflow: hidden;
            height: 30px;
            margin: 0;
            padding: 0;
            z-index: 2;
            font-size: 12px;
            font-family: 'Malgun Gothic', '?????? ??????', sans-serif;
        }

        .custom_typecontrol span {
            display: block;
            width: 65px;
            height: 30px;
            float: left;
            text-align: center;
            line-height: 30px;
            cursor: pointer;
        }

        .custom_typecontrol ._exbtn {
            background: #fff;
            background: linear-gradient(#fff, #e6e6e6);
        }

        .custom_typecontrol ._exbtn {
            background: #f5f5f5;
            background: linear-gradient(#f5f5f5, #e3e3e3);
        }

        .custom_typecontrol ._exbtn:active {
            background: #e6e6e6;
            background: linear-gradient(#e6e6e6, #fff);
        }

        .custom_typecontrol .selected_btn {
            color: #fff;
            background: #425470;
            background: linear-gradient(#425470, #5b6d8a);
        }

        .custom_typecontrol .selected_btn:hover {
            color: #fff;
        }

        .return-top {
            left: 400px;
            bottom: 15px;
            position: fixed;
            z-index: 10;
            border-radius: 3rem;
            background: white;
        }

        ._checkInSpan {
            margin: 0rem !important;
        }

    </style>

</head>
<body>
<c:set var="sort" value="${sort}"/>

<div class="filterBox">
    <div class="emptyContainer">
        <div class="filterContainer">
            <div class="buttonContainer">
                <div class="filterButton">
                    <button class="dateButton btn btn-outline-primary" type="button"><span
                            class="_checkInSpan">${checkIn}</span> ~ <span class="_checkOutSpan">${checkOut}</span>
                    </button>
                </div>
                <div class="filterButton">
                    <button class="peopleButton btn btn-outline-primary" type="button">?????? ${people}???</button>
                </div>
                <div class="filterButton">
                    <button class="localButton btn btn-outline-primary" type="button">??????</button>
                </div>
            </div>
            <div class="sortContainer">
                <!-- 						<button class="btn btn-outline-success" data-toggle="modal" data-target="#login">????????????</button> -->
                <button class="btn btn-outline-success" id="priceHigh">???????????????</button>
                <button class="btn btn-outline-success" id="priceLow">???????????????</button>
                <button class="btn btn-outline-success" id="rateSort">???????????????</button>
                <input id="sort" type="hidden" name="sort"/>
            </div>
        </div>
    </div>
    <div class="formContainer">
        <form name="form" action="${root}/search" method="get" onsubmit="return confirmSubmit()">

            <div class="checkInOutContainer">
                <div class="checkInContainer">
                    <label for="checkIn"><i class="calenderIn far fa-calendar-alt fa-2x"></i></label>
                    <input readonly="readonly" style="background-color: white;" class="form-control" type="text"
                           name="checkIn" id="checkIn" onchange="setDate(this)"/> ~
                </div>
                <div class="checkOutContainer">
                    <label for="checkOut"><i class="calenderOut far fa-calendar-alt fa-2x"></i></label>
                    <input readonly="readonly" style="background-color: white;" class="form-control" type="text"
                           name="checkOut" id="checkOut" onchange="setCheckOutDate(this)"/> ~
                </div>
            </div>

            <div class="peopleContainer">
                ??????
                <select class="custom-select" title="??????" id="searchPeople" name="people" onchange="setPeople(this)">
                    <option value="1">1???</option>
                    <option value="2">2???</option>
                    <option value="3">3???</option>
                    <option value="4">4???</option>
                    <option value="5">5?????????</option>
                </select>
            </div>

            <div class="localContainer form-check-inline">
                <div class="localAll">
                    <div class="custom-control custom-checkbox mb-3">
                        <input type="checkbox" class="custom-control-input all" id="all"/>
                        <label class="custom-control-label" for="all">??????</label>
                    </div>
                </div>
                <div class="localJeju">
                    <div class="custom-control custom-checkbox mb-3">
                        <input type="checkbox" class="custom-control-input etc" id="jeju" value="jeju"/>
                        <label class="custom-control-label" for="jeju">?????????</label>
                    </div>
                    <div class="custom-control custom-checkbox mb-3">
                        <input type="checkbox" class="custom-control-input etc" id="jejuEast" value="jejuEast"/>
                        <label class="custom-control-label" for="jejuEast">????????? ??????</label>
                    </div>
                    <div class="custom-control custom-checkbox mb-3">
                        <input type="checkbox" class="custom-control-input etc" id="jejuWest" value="jejuWest"/>
                        <label class="custom-control-label" for="jejuWest">????????? ??????</label>
                    </div>
                </div>
                <div class="localSeoguipo">
                    <div class="custom-control custom-checkbox mb-3">
                        <input type="checkbox" class="custom-control-input etc" id="seoguipo" value="seoguipo"/>
                        <label class="custom-control-label" for="seoguipo">?????? / ?????????</label>
                    </div>
                    <div class="custom-control custom-checkbox mb-3">
                        <input type="checkbox" class="custom-control-input etc" id="seoguipoEast" value="seoguipoEast"/>
                        <label class="custom-control-label" for="seoguipoEast">???????????? ??????</label>
                    </div>
                    <div class="custom-control custom-checkbox mb-3">
                        <input type="checkbox" class="custom-control-input etc" id="seoguipoWest" value="seoguipoWest"/>
                        <label class="custom-control-label" for="seoguipoWest">???????????? ??????</label>
                    </div>
                    <input type="hidden" name="local" id="local"/>
                </div>
            </div>
            <div class="searchContainer">
                <input class="form-control" type="text" name="searchHouseName" placeholder="???????????????"/>
                <button type="submit" class="searchBtn btn btn-primary" onsubmit="confirmSubmit()">??????</button>
            </div>
        </form>
    </div>
</div>
<div class="houseListContainer">
    <div class="houseListCount">
        ?????? ${count}???
    </div>
    <div class="houseContainer">
        <%-- <div class="house">
            <div class="_gig1e7">
                <div class="_ylefn59">
                    <div class="_houseCode" style="display:none;">10</div>
                    <div class="_houseImg">
                        <div class="swiper-container">
                            <div class="swiper-wrapper">
                                <div class="swiper-slide"><img style="max-width:100%; height:auto;" alt="img loading" src="<spring:url value='/image/house1.png'/>"/></div>
                                <div class="swiper-slide"><img style="max-width:100%; height:auto;" alt="img loading" src="<spring:url value='/image/house2.png'/>"/></div>
                                <div class="swiper-slide"><img style="max-width:100%; height:auto;" alt="img loading" src="<spring:url value='/image/house3.png'/>"/></div>
                            </div>
                            <!-- Add Pagination -->
                            <div class="swiper-pagination"></div>
                            <div class="swiper-button-next"></div>
                            <div class="swiper-button-prev"></div>
                        </div>
                        <div class="_heartButton">
                            <button aria-label="????????? ?????? ????????????" type="button" class="_heart _r0agyd heart${index.index}"><svg viewBox="0 0 24 24" fill="currentColor" fill-opacity="0" stroke="#222222" stroke-width="1.4" focusable="false" aria-hidden="true" role="presentation" stroke-linecap="round" stroke-linejoin="round" style="height: 1.3rem; width: 1.3rem; display: block; overflow: visible;"><path d="m17.5 2.9c-2.1 0-4.1 1.3-5.4 2.8-1.6-1.6-3.8-3.2-6.2-2.7-1.5.2-2.9 1.2-3.6 2.6-2.3 4.1 1 8.3 3.9 11.1 1.4 1.3 2.8 2.5 4.3 3.6.4.3 1.1.9 1.6.9s1.2-.6 1.6-.9c3.2-2.3 6.6-5.1 8.2-8.8 1.5-3.4 0-8.6-4.4-8.6" stroke-linejoin="round"></path></svg></button>
                        </div>
                    </div>
                    <div class="_houseInfo">
                        <div class="_starRate"><span class="_60dc7z"><span class="_starImg"><svg viewBox="0 0 1000 1000" role="presentation" aria-hidden="true" focusable="false" style="height:0.75rem;width:0.75rem;fill:#FF385C"><path d="M972 380c9 28 2 50-20 67L725 619l87 280c11 39-18 75-54 75-12 0-23-4-33-12L499 790 273 962a58 58 0 0 1-78-12 50 50 0 0 1-8-51l86-278L46 447c-21-17-28-39-19-67 8-24 29-40 52-40h280l87-279c7-23 28-39 52-39 25 0 47 17 54 41l87 277h280c24 0 45 16 53 40z"></path></svg></span><span class="_reviewRate">4.83</span><span class="_reviewCount">(163)</span></span></div>
                        <div class="_houseName">????????? ?????? ???????????? ??? '???????????????'</div>
                        <div class="_houseFacilities" style="margin-top: 0.75rem">?????? 3??? ?? ?????? 1??? ?? ?????? 1???</div>
                        <div class="_houseFacilities" style="margin-top: 0.25rem">?????? ?? ?????? ?????? ?????? ?? ?????????????????? ?? ??????</div>
                        <div class="_priceContainer"><span class="_price">???141,596</span><span class="_oneNight">/1???</span></div>
                    </div>
                </div>
            </div>
        </div>	 --%>
    </div>
    <div class="_paging">

        <ul class="pagination"></ul>
    </div>
</div>
<div class="mapContainer">
    <div id="map">
        <div class="custom_typecontrol radius_border">
            <span id="btnGuestHouse" class="selected_btn" onclick="setSearchType('guestHouse',form)">??????</span>
            <span id="btnExperience" class="_exbtn" onclick="setSearchType('experience',form)">??????</span>
        </div>
    </div>
</div>

<span style="font-size: 3rem;"><i class="return-top fas fa-chevron-circle-up"></i></span>
<script type=text/javascript>
    $(document).ready(function () {

        $(".return-top").hide(); // ??? ?????? ??????
        $(function () {

            $(window).scroll(function () {
                if ($(this).scrollTop() > 100) { // ????????? ?????? ??????
                    $('.return-top').fadeIn();
                } else {
                    $('.return-top').fadeOut();
                }
            });

            $('.return-top').click(function () {
                $('body,html').animate({
                    scrollTop: 0
                }, 400);  // ??? ?????? ????????? ??????
                return false;
            });
        });

    });
</script>


<%-- boardSize:${boardSize}, currentPage:${currentPage}, count:${count}<br/><br/> --%>


</body>
</html>
