/**
 * 
 */

function deleteExpState(root,value){

		var url = root + "/guestdelluna/zzimExpCancel.do";
		var params = "value=" + value;
		
		sendRequest("GET" , url , deleteStateFromServer , params);
	
}

function deleteStateFromServer(){
	if(xhr.readyState==4 && xhr.status==200){
		
		alert("제거 완료됐습니다.");
		location.reload();
		
	}
}	
function deleteHouse(root,hsValue){
	$.ajax({
		method: "DELETE",
		url: root+"/v1/wishlists",
		data: { houseId: hsValue },
		success: function(){
		},
		error: function(){
		}
	})
}



