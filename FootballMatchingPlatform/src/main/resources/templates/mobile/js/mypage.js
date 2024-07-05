
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
$("#withdraw-btn").on("click", function () {
    alert('회원 탈퇴 페이지로 이동')
});

// 수정하기 버튼 클릭 시
$("#my-info-edit-btn").on("click", function () {
    alert('회원 정보 수정 메소드 실행')
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