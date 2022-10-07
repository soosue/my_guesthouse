function checkEmail() {
    let email = document.getElementById("emailAA").value;

    let url = "/v1/member/check?";
    let params = "email=" + email;

    $.ajax({
        url: url + params,
        method: "get",
        success: function (check) {
            if (check == 1) {
                $("#idid #id_check").text("이미 존재하는 이메일입니다. 다른 이메일을 사용하시길 바랍니다.");
                $("#idid #id_check").css("color", "red");
                $("#btnSubmit").attr("disabled", true);
            } else {
                $("#idid #id_check").text("사용가능한 이메일입니다.");
                $("#idid #id_check").css("color", "blue");
            }
        }
    });
}
