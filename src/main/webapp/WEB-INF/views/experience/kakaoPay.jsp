<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
<script>
    $(function () {
        var IMP = window.IMP; // 생략가능
        IMP.init('imp51430283'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        var msg;

        IMP.request_pay({
            pg: 'kakaopay',
            pay_method: 'card',
            merchant_uid: 'merchant_' + new Date().getTime(),
            name: 'Jeju Stay 결제',
            amount: '${exPayment}',
            buyer_email: '${memberDto.email}',
            buyer_name: '${memberDto.memberName}',
            buyer_tel: '${memberDto.phone}',
            //m_redirect_url : 'http://www.naver.com'
        }, function (rsp) {
            if (rsp.success) {
                var msg = '결제가 완료되었습니다.';
                /* msg += '고유ID : ' + rsp.imp_uid;
                msg += '상점 거래ID : ' + rsp.merchant_uid;
                msg += '결제 금액 : ' + rsp.paid_amount;
                msg += '카드 승인번호 : ' + rsp.apply_num; */

                alert(msg);

                location.href = '${root}/experience/kakaoPayOk.do?imp_uid=' + rsp.imp_uid + '&merchant_uid=' + rsp.merchant_uid + '&paid_amount=' + rsp.paid_amount
                    + '&exCode=${exCode}&memberCode=${memberCode}&exPeople=${exPeople}&usePoint=${usePoint}&point=${point}&exDateS=${exDateS}';
            } else {
                var msg = '결제에 실패하였습니다.';
                location.href = '${root}/experience/exPage.do?exCode=${exCode}';
                msg += '에러내용 : ' + rsp.error_msg;

            }

        });

    });
</script>

</body>
</html>
