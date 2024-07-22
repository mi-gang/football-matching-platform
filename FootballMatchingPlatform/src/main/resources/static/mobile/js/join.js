// header, nav 내용 불러오기
$(document).ready(function () {
    $('header').load('/common/header_back_main.html', function () { // 뒤로가기 필요한 페이지는 header_back, 아닌 페이지는 header 사용해주세욤
        $('#page-name').text('회원 가입'); // 해당 페이지 이름 넣어주세욤 ex) 마이페이지
        $('#user-tier').text('A'); // 나중에 세션에서 usertier 구해서 넣기
    });
});

// 유효성 검사

const getNameCheck = RegExp(/^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+$/);
const getIdCheck = RegExp(/^[a-zA-Z0-9]{0,}$/);
const getPwCheck = RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{6,}$/);
const specialCheck = RegExp(/[`~!@#$%^&*|\\\'\";:\/?]/);

//이름 유효성 검사
$("#name").keyup(function(){
    if($("#name").val() !=""){
        if(!getNameCheck.test($("#name").val())){
            $("#text-name").css("margin-right","11.15rem");
            $("#invalid-name-check-fail").css("display", "block");
        }
    }
    else {
        $("#text-name").css("margin-right","20.5rem");
        $("#invalid-name-check-fail").css("display","none");
    }
})
//아이디 유효성 검사
$("#id").on("change",function(){
    const id = $("#id").val();
    console.log(id)
    if(id !=""){
        console.log("널일때 실행")
        if(!getIdCheck.test(id)){
            $("#text-id").css("margin-right","5rem");
            $("#invalid-id-success").css("display","none");
            $("#invalid-id-fail").css("display", "block");
            $("#invalid-id-doub").css("display", "none");
        }
        else{
            console.log("ajax 실행")
            $.ajax({
                type: 'post',
                url: "/user/getUserId",
                data: {"userId":id},
                dataType: 'text',
                success: function(response){
                    console.log("ajax success 실행")
                        console.log("response: "+response);
                        if(response === "true"){
                            console.log("ajax response true 실행")

                            $("#text-id").css("margin-right","8.75rem");
                            $("#invalid-id-success").css("display","block");
                            $("#invalid-id-fail").css("display", "none");
                            $("#invalid-id-doub").css("display", "none");
                        }
                        else{
                            console.log("ajax response false 실행")

                            $("#text-id").css("margin-right","10.75rem");
                            $("#invalid-id-success").css("display","none");
                            $("#invalid-id-fail").css("display", "none");
                            $("#invalid-id-doub").css("display", "block");
                        }
                },
                error: function(error){
                    alert("오류" + error);
                }
            })
        }
    }
    else {
        $("#text-id").css("margin-right","19rem");
        $("#invalid-id-success").css("display","none");
        $("#invalid-id-fail").css("display", "none");
        $("#invalid-id-doub").css("display", "none");
    }
})
//닉네임 중복 검사
$("#nickname").on("change",function(){
    const nickname = $("#nickname").val();
    $.ajax({
        type: 'post',
        url: "/user/getUserNickname",
        data: {"nickname":nickname},
        dataType: 'text',
        success: function(response){
            if(nickname != ""){
                console.log(response);
                if(response === "true"){
                    $("#text-nickname").css("margin-right","8.5rem");
                    $("#invalid-nickname").css("display", "block");
                    $("#invalid-nickname-doub").css("display", "none");
                }
                else{
                    $("#text-nickname").css("margin-right","10.5rem");
                    $("#invalid-nickname").css("display", "none");
                    $("#invalid-nickname-doub").css("display", "block");
                }
            }
            else{
                $("#text-nickname").css("margin-right","19rem");
                $("#invalid-nickname").css("display", "none");
                $("#invalid-nickname-doub").css("display", "none");
            }},
        error: function(error){
            alert("오류" + error);
        }
    })
})
//전화번호 자동 형식
const hypenTel = (target) => {
    target.value = target.value
        .replace(/[^0-9]/g, '')
        .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
}
//비밀번호 가능여부 검사
$("#pw").on("change", function(){
    const pw = $("#pw").val();
    if(pw != ""){
        if(!getPwCheck.test(pw)){
            $("#text-pw").css("margin-right","3rem");
            $("#invalid-pw").css("display", "block");
        }
        else{
            $("#text-pw").css("margin-right","18rem");
            $("#invalid-pw").css("display", "none");
        }
    }
    else{
        $("#text-pw").css("margin-right","18rem");
        $("#invalid-pw").css("display", "none");
    }
})
//비밀번호 일치여부 검사
$("#pw-check").on("keyup", function () {
    if ($("#pw").val() != "") {
        if($(this).val() != $("#pw").val()) {
            $("#text-pw-check").css("margin-right", "3.5rem");
            $("#invalid-pw-check-success").css("display", "none");
            $("#invalid-pw-check-fail").css("display", "block");
        }
        else {
            $("#text-pw-check").css("margin-right", "6.35rem");
            $("#invalid-pw-check-fail").css("display", "none");
            $("#invalid-pw-check-success").css("display", "block");
        }
    }
    else {
        $("#text-pw-check").css("margin-right", "15.5rem");
        $("#invalid-pw-check-success").css("display", "none");
        $("#invalid-pw-check-fail").css("display", "none");
    }
})

//남자 선택시 여자 선택 제거
$("#m").on("click", function () {
    $(this).addClass("click");
    $("#f").removeClass("click");
    $(this).val(1);
    $("#f").val(0);
})

//여자 선택시 남자 선택 제거
$("#f").on("click", function () {
    $(this).addClass("click");
    $("#m").removeClass("click");
    $(this).val(1);
    $("#m").val(0);
})

//경기 지역 선택 창 열기
$("#location").on("click", function () {
    $("#modal-location").css("display", "block");
})
// 창 닫기
$("#close").on("click", function () {
    $("#modal-location").css("display", "none");
})
//경기 지역 선택하기
$("input[name=city]").on("click", function () {
    if ($(this).val() == "서울") {
        $("#gu-list").html(
            `<div class="gu-line">
                        <input type="radio" id="강남구" value="강남구" name="gu"><label for="강남구">강남구</label>
                        <input type="radio" id="강동구" value="강동구" name="gu"><label for="강동구">강동구</label>
                        <input type="radio" id="강북구" value="강북구" name="gu"><label for="강북구">강북구</label>
                        <input type="radio" id="강서구" value="강서구" name="gu"><label for="강서구">강서구</label>
                    </div>
                    <div class="gu-line">
                        <input type="radio" id="관악구" value="관악구" name="gu"><label for="관악구">관악구</label>
                        <input type="radio" id="광진구" value="광진구" name="gu"><label for="광진구">광진구</label>
                        <input type="radio" id="구로구" value="구로구" name="gu"><label for="구로구">구로구</label>
                        <input type="radio" id="금천구" value="금천구" name="gu"><label for="금천구">금천구</label>
                    </div>
                    <div class="gu-line">
                        <input type="radio" id="노원구" value="노원구" name="gu"><label for="노원구">노원구</label>
                        <input type="radio" id="도봉구" value="도봉구" name="gu"><label for="도봉구">도봉구</label>
                        <input type="radio" id="동대문구" value="동대문구" name="gu"><label for="동대문구">동대문구</label>
                        <input type="radio" id="동작구" value="동작구" name="gu"><label for="동작구">동작구</label>
                    </div>`
        )
    }
    else if ($(this).val() == "인천") {
        $("#gu-list").html(
            `<div class="gu-line">
                        <input type="radio" id="계양구" value="계양구" name="gu"><label for="계양구">계양구</label>
                        <input type="radio" id="남구" value="남구" name="gu"><label for="남구">남구</label>
                        <input type="radio" id="남동구" value="남동구" name="gu"><label for="남동구">남동구</label>
                        <input type="radio" id="동구" value="동구" name="gu"><label for="동구">동구</label>
                    </div>
                    <div class="gu-line">
                        <input type="radio" id="부평구" value="부평구" name="gu"><label for="부평구">부평구</label>
                        <input type="radio" id="서구" value="서구" name="gu"><label for="서구">서구</label>
                        <input type="radio" id="연수구" value="연수구" name="gu"><label for="연수구">연수구</label>
                        <input type="radio" id="중구" value="중구" name="gu"><label for="중구">중구</label>
                    </div>
                    <div class="gu-line">
                        <input type="radio" id="강화군" value="강화군" name="gu"><label for="강화군">강화군</label>
                        <input type="radio" id="미추홀구" value="미추홀구" name="gu"><label for="미추홀구">미추홀구</label>
                    </div>`
        )
    }
    else if ($(this).val() == "경기") {
        $("#gu-list").html(
            `<div class="gu-line">
                        <input type="radio" id="고양시" value="고양시" name="gu"><label for="고양시">고양시</label>
                        <input type="radio" id="과천시" value="과천시" name="gu"><label for="과천시">과천시</label>
                        <input type="radio" id="광명시" value="광명시" name="gu"><label for="광명시">광명시</label>
                        <input type="radio" id="광주시" value="광주시" name="gu"><label for="광주시">광주시</label>
                    </div>
                    <div class="gu-line">
                        <input type="radio" id="구리시" value="구리시" name="gu"><label for="구리시">구리시</label>
                        <input type="radio" id="군포시" value="군포시" name="gu"><label for="군포시">군포시</label>
                        <input type="radio" id="김포시" value="김포시" name="gu"><label for="김포시">김포시</label>
                        <input type="radio" id="남양주시" value="남양주시" name="gu"><label for="남양주시">남양주시</label>
                    </div>
                    <div class="gu-line">
                        <input type="radio" id="동두천시" value="동두천시" name="gu"><label for="동두천시">동두천시</label>
                        <input type="radio" id="부천시" value="부천시" name="gu"><label for="부천시">부천시</label>
                        <input type="radio" id="성남시" value="성남시" name="gu"><label for="성남시">성남시</label>
                        <input type="radio" id="수원시" value="수원시" name="gu"><label for="수원시">수원시</label>
                    </div>`
        )
    }
    else {
        $("#gu-list").html(
            ``
        )
    }
})

// 경기지역 선택 시 창 닫기
$("#submit").on("click", function () {
    const city = $("input[name=city]:checked").val();
    const gu = $("input[name=gu]:checked").val();
    const address = city+" "+gu;
    if (city == null) {
        alert("지역을 선택해주세요!");
        return;
    }

    if (gu == undefined) {
        alert("세부지역을 선택해주세요!");
        return;
    }
    $("#location").val(address);
    $("#modal-location").css("display", "none");
})

//회원 정보 저장
$('#join').click(function(){
    if ($("#name").val().length == 0) {
        alert("이름을 입력하세요.");
        return false;
    }

    const joinId = $("#id").val();

    if (joinId.length == 0) {
        alert("아이디를 입력하세요.");
        return false;
    }

    if ($("#nickname").val().length == 0) {
        alert("닉네임을 입력하세요.");
        return false;
    }

    if ($("#pw").val().length == 0) {
        alert("비밀번호를 입력하세요.");
        return false;
    }

    if ($("#pw-check").val().length == 0) {
        alert("비밀번호확인을 입력하세요.");
        return false;
    }

    if ($("#birth").val().length == 0) {
        alert("생년월일을 입력하세요.");
        return false;
    }

    if ($("#m").val() == 0 && $("#f").val() == 0) {
        alert("성별을 선택하세요.");
        return false;
    }

    if ($("#phone").val().length == 0) {
        alert("전화번호를 입력하세요.");
        return false;
    }

    if ($("#email").val().length == 0) {
        alert("이메일을 입력하세요");
        return false;
    }

    if ($("#location").val().length == 0) {
        alert("플레이 지역을 선택하세요.");
        return false;
    }

    else{
        console.log($('.gender').val())
        const id = $('#id').val();
        const pw = $('#pw').val();
        const nickname = $('#nickname').val();
        const name = $('#name').val();
        const birthday = $('#birth').val();
        const gender = $('.gender').val();
        const phoneNumber = $('#phone').val();
        const email = $('#email').val();
        const address = $('#location').val();

        const user = {'userId' : id,
            'password' : pw,
            'nickname' : nickname,
            'name' : name,
            'birthday' : birthday,
            'gender' : gender,
            'phoneNumber' : phoneNumber,
            'email' : email,
            'address' : address};
        $.ajax({
            type:'Post',
            url:'/user/addUserJoin',
            headers : {
                'Content-Type' : 'application/json'
            },
            dataType: 'json',
            data : JSON.stringify(user),
            async: false,
            success: function(response){
                console.log('통신성공: '+response);
                alert('회원가입 완료');
                location.href = '/login';
            },
            error: function(){
                alert('회원가입 실패');
            }
        });
    }});