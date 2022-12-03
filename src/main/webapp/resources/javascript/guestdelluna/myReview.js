function delExpRev(root, value) {
    var url = root + "/guestdelluna/reviewDelete.do";

    var params = "value=" + value;

    sendRequest("GET", url, delCallBackFromServer, params);
}

function delCallBackFromServer() {
    if (xhr.readyState == 4 && xhr.status == 200) {
        alert("후기 삭제 완료됐습니다");
        setTimeout("location.reload()");
    }
}

function expUpdateOk(root, expUpResCode, i) {

    var str = "";

    var expRevContent = document.getElementsByName("expRevContent");

    if (expRevContent[i - 1].value != "") {

        str = expRevContent[i - 1].value;

    }

    var url = root + "/guestdelluna/reviewUpdateOk.do";

    var params = "expUpResCode=" + expUpResCode + "&expRevContent=" + str

    sendRequest("GET", url, updateCallBack, params);
}

function updateCallBack() {
    if (xhr.readyState == 4 && xhr.status == 200) {
        alert("수정 완료됐습니다");
        location.reload();
    }
}

function updateReviewWithModalIdx(reserveCode, idx, revRate) {
    let revContent = "";
    const revContentElements = document.getElementsByName("houseRevContent");

    if (revContentElements[idx - 1].value != "") {
        revContent = revContentElements[idx - 1].value;
    }

    updateReview(reserveCode, revContent, revRate, (response) => {
        if (response.status === 200) {
            alert("수정 완료됐습니다");
            location.reload();
        }
    });
}
