
// 로그아웃
$("header").on("click", "#logout", function () {
     alert('로그아웃')
});

// 정보 수정 - 비밀번호 확인 페이지 이동
$("#edit-info").on("click", function () {
    alert('정보 수정 - 비밀번호 확인 페이지 이동')
});

// 나의 문의 페이지 이동
$("#more-inquiry").on("click", function () {
    alert('나의 문의 페이지 이동')
});



////////////////////// 내 정보 수정 //////////////////////////////


// 정보 수정 - 정보 수정 페이지 이동
$("#check-pw-btn").on("click", function () {
    alert('비밀번호 확인 메서드 실행 , 모달창 또는 정보 수정 - 정보 수정 페이지 이동')
});

// 회원 정보 수정 폼

// 비밀번호 수정 버튼 클릭 시
$("#edit-pw-btn").on("click", function () {
    alert('비밀번호 수정 페이지로 이동')
});

// 회원 탈퇴 버튼 클릭 시
$("#withdrawModal-btn").on("click", function () {
    alert('회원 탈퇴 메소드 실행')
});

// 수정하기 버튼 클릭 시
$("#my-info-edit-btn").on("click", function () {
    alert('회원 정보 수정 메소드 실행')
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
    alert('회원 비밀번호 수정 메소드 실행')
});




////////////////////// 문의 내역 //////////////////////////////

// 문의하기 버튼 클릭 시
$('#add-inquiry-btn').on('click', function () {
    alert('문의 작성 페이지 이동')
});

// 문의 상세 페이지 이동
$('#container').on('click', '.inquiry-detail', function () {
    alert('문의 상세 페이지 이동')
});


// 문의 작성 페이지
// 등록하기 버튼 클릭 시
$('#addinquiry-btn').on('click', function () {
    const inquiryTitle = $("#addinquiry-title-input").val();
    const inquiryContent = $("#addinquiry-content-input").val();

    alert(inquiryTitle)
    alert(inquiryContent)
    alert('문의 등록')
});
