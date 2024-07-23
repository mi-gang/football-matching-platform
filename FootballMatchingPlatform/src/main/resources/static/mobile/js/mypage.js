
// 로그아웃
$("header").on("click", "#submit", function () {
	$.ajax({
    	type:'get',
    	url:'/user/logout',
		success:function(response){
			alert('로그아웃 완료');
			location.href = response;
		},
		error: function(){
			alert('오류');
		}		
    });

});

// 정보 수정 - 비밀번호 확인 페이지 이동
$("#edit-info").on("click", function () {
	location.href = "/myPageCheckPW";
});

// 나의 문의 페이지 이동
$("#more-inquiry").on("click", function () {
	location.href = 'myPageInquiry';
});



////////////////////// 내 정보 수정 //////////////////////////////


// 정보 수정 - 정보 수정 페이지 이동
$('#check-pw-btn').on("click",function(){
	const pwInput = $('#input-pw').val();
	if(pwInput.length == 0){
		alert('비밀번호를 입력해주세요');
		return false;
	}
	$.ajax({
		type: 'get',
		url: 'user/findMyPw',
		data: {pwInput},
		dataType: 'text',
		success:function(result){
			if(result == 'true'){
				alert('비밀번호 인증완료');
				location.href='myPageEditInfo';
			}
			else{
				alert('비밀번호를 확인해주세요');	
				return false;
			}
		},
		error:function(){
			alert('error');
			return false;
		}
	})
})

// 회원 정보 수정 폼
$('document').ready(function(){
	//수정 전 내 정보 불러오기
	$.ajax({
		type:'get',
		url: "/user/getMyInfo",
		dataType:"json",
		success: function(data){
			$('#edit-name').val(data.result.name);
			$('#id').empty().append(data.result.userId);
			$('#edit-nickname').val(data.result.nickname);
			$('#birth').empty().append(data.result.birthday);
			$('#edit-email').val(data.result.email);
			$('#edit-phone').val(data.result.phoneNumber);
		},
		error: function(){
			alert('회원 정보 에러');
			location.href='/login';
		}		
	})
})

// 비밀번호 수정 버튼 클릭 시
$("#edit-pw-btn").on("click", function () {
    location.href='myPageEditPW';
});

// 회원 탈퇴 버튼 클릭 시
$("#withdrawModal-btn").on("click", function () {
    alert('회원 탈퇴 메소드 실행')
});

// 수정하기 버튼 클릭 시
$("#edit-pw-btn").on("click", function () {
    location.href='myPageEditPW';
});

// 회원 탈퇴 버튼 클릭 시
$("#withdrawModal-btn").on("click", function () {
    alert('회원 탈퇴 메소드 실행')
});

// 수정하기 버튼 클릭 시
$("#my-info-edit-btn").on("click", function () {
	if ($("#edit-name").val().length == 0) {
		alert("이름을 입력하세요.");
		return false;
	}
		            
	if ($("#edit-nickname").val().length == 0) {
		alert("닉네임을 입력하세요.");
		return false;
	}

	if ($("#edit-phone").val().length == 0) {
		alert("전화번호를 입력하세요.");
		return false;
	}

	if ($("#edit-email").val().length == 0) {
		alert("이메일을 입력하세요");
		return false;
	}

	else{
		const id = $('#id').val();
		const nickname = $('#edit-nickname').val();
		const name = $('#edit-name').val();
		const phoneNumber = $('#edit-phone').val();
		const email = $('#edit-email').val();
		        	
		const user = {'userId' : id,
			'nickname' : nickname,
		    'name' : name,
		    'phoneNumber' : phoneNumber,
		    'email' : email,};
		$.ajax({
			type:'Post',
			url:'/user/setMyInfo',
			headers : {
				'Content-Type' : 'application/json'
			},
			dataType: 'json',
			data : JSON.stringify(user),
			async: false,
			success: function(response){
				console.log('통신성공: '+response);
				alert('정보수정 완료');
				location.href = '/myPage';
			},
			error: function(){
				alert('정보수정 실패');
			}
		});
	}
});

// 회원 아이디, 이메일 확인 페이지

$('#check-id-email-btn').on('click', function () {
    const userId = $("#user-id").val();
    const userEmail = $("#user-email").val();

    alert(userId)
    alert(userEmail)
    alert('아이디, 이메일 확인 메소드')
});

// 비밀번호 수정 페이지
// 비밀번호 수정 버튼 클릭 시
$("#edit-pw-edit-btn").on("click", function () {
	//비밀번호 공백
	if($("#new-pw").val().length == 0){
		alert("비밀번호를 입력하세요");
		return false;
	}
	
	const pw = $("#new-pw").val();
	//비밀번호 수정
	$.ajax({
		type:'post',
		url:'/user/setPassword',
		data:{pw},
		success: function(result){
			console.log("통신성공" + result);
			alert('비밀번호 변경완료');
			location.href='/myPage';
		},
		error: function(){
			alert('비밀번호 수정오류');
			return false;
		}		
	});
});




////////////////////// 문의 내역 //////////////////////////////

// 문의하기 버튼 클릭 시 (새로 만들어야함)
$('#add-inquiry-btn').on('click', function () {
    location.href='';
});

// 문의 상세 페이지 이동
 $(".inquiry-wrapper").on("click", ".inquiry-detail", function() {
	console.log($(this).data("inquirySeq"));
    $.ajax({
    	type:'get',
    	url:'/user/pathDetailInfo/'+$(this).data("inquirySeq"),
		success:function(response){
			location.href='myPageInquiryDetail';
		},
		error: function(){
			alert('오류');
		}		
    });
});

// 문의 상세 페이지

// 문의 작성 페이지
// 등록하기 버튼 클릭 시
$('#addinquiry-btn').on('click', function () {
    const inquiryTitle = $("#addinquiry-title-input").val();
    const inquiryContent = $("#addinquiry-content-input").val();

    alert(inquiryTitle)
    alert(inquiryContent)
    alert('문의 등록')
});
