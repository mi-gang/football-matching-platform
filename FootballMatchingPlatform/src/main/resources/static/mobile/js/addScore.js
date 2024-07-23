// 모달 선택 함수
function getModal(modalId) {
    let modalElement = document.getElementById(modalId)
    var modal = new bootstrap.Modal(modalElement)
    return modal;
}

// 한 사람당 check는 한번
$("label").on('click', function () {
    const ch = $(this).parent().children('input')
    const ch2 = $(this).parent().children('label')
    let name = $(this).prev().attr('name');

    $('input[name=' + name + ']').each(function () {
        $(this).next().removeClass('click');
    })
    $(ch2).removeClass('click');
    $(this).addClass('click');

    $(ch).each(function () {
        if ($(this).is(":checked"))
            $(this).prop("checked", false);
    })
    $(this).prev().prop("checked", true);
})

// 등록하기 버튼 클릭 시
$("#submitBtn").on('click', function () {
    let checkedList = $("input:radio[class=btn-check]:checked"); // 라디오 체크된 배열
    // let matchingSeq = $('#submitBtn').attr('data-matchingSeq');
    let matchingAddListSeq = $('#submitBtn').attr('data-matchingAddListSeq');

    for (i = 0; i < checkedList.length; i++) {
        console.log($(checkedList[i]).val());
    }
    let j = 0;

    let infoDTOS = playerList.map(player => ({
        userId: player.userId,
        matchingSeq: parseInt(getMatchingSeq()),
        score: parseInt($(checkedList[j++]).val())
    }));

    if (checkedList.length != 5) {
        tostOn("등수를 입력해 주세요");
    } else
        fetch('/schedule/review-scores?matchingAddListSeq=' + matchingAddListSeq, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                "infoDTOS": infoDTOS
            }),
            credentials: "include" // 세션 정보를 포함
        })
            .then(response => {
                if (response.ok) {
                    getModal('setReviewScoreSuccessModal').show();
                } else {
                    return response.text().then(errorText => {
                        throw new Error(errorText);
                    });
                }
            })
            .catch(error => {
                console.error("Fetch error:", error);
                getModal('setReviewScoreSuccessModal').show();
                $('#setReviewScoreText').text("등록 실패<br>다시 시도해주세요.");
            });
})

// 등록 완료 -> 닫기 버튼 클릭 시
$("#setReviewScoreSuccessModalBtn").on("click", function () {
    location.href = '/myCalendar';
});


// 토스트 실행
function tostOn(str) {
    $('#toast').text(str);
    $('#toast').addClass("active");
    setTimeout(function () {
        $('#toast').removeClass("active");
    }, 1500);
}

// ====================================== 신고 ==========================================

// 버튼 text 변경
$("li").on('click', function () {
    $(this).parent().prev().text($(this).text());

    let matchingSeq = getMatchingSeq();

    if ($(this).text() === '상대팀') {

        let users = [];
        playerList.forEach(player => {
            users.push(player.playerNumber + '번 ' + player.nickname)
        });

        // for (let i = 0; i < playerList.length; i++) {
        //     users.push(i+1+'번 ' + playerList[i].nickname)
        // }
        console.log(users)

        let str = "";
        for (i = 0; i < users.length; i++) {
            str += '<li><a class="dropdown-item" href="#" data-userId="'
                + playerList[i].userId
                + '">' + users[i] + '</a></li>'
        }
        $("#userDropdown").html(str);
        clickChangeText();
    } else if ($(this).text() === '내 팀') {
        fetch(`/schedule/${matchingSeq}/my-team-players`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
            credentials: "include" // 세션 정보를 포함
        })
            .then(response => {
                if (!response.ok) {
                    console.log(response)
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log("data : " + data)
                let str = "";

                if (Array.isArray(data)) {
                    data.forEach(item => {
                        console.log(item);
                        str += '<li><a class="dropdown-item" href="#" data-userId="'
                            + item.userId
                            + '">' + item.playerNumber + '번 ' + item.nickname + '</a></li>'
                    });
                    $("#userDropdown").html(str);
                } else {
                    console.error('Expected an array but got:', data);
                }
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
        clickChangeText();
    }
})

function clickChangeText() {
    $(".modal-content").on("click", "li", function () {
        $(this).parent().prev().text($(this).text());
        let userId = $(this).find('.dropdown-item').attr('data-userId');
        $('#withdrawModal-btn').attr('data-reportedUserId', userId);
    })
}

// 취소할 시, 초기화
$(".btn-close").on('click', function () {
    setReportModal();
})

$()

// 신고하기 버튼 클릭 시
$("#withdrawModal-btn").on("click", function () {
    // null값 return 처리

    let reportedUserId = $('#withdrawModal-btn').attr('data-reporteduserid');
    let reportType = $("#reasonSelect").text();
    let reportContent = $("textarea").val();

    console.log(reportedUserId)
    console.log(reportType)
    console.log(reportContent)

    fetch('/schedule/report', {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            reportType: reportType,
            reportContent: reportContent,
            matchingSeq: getMatchingSeq(),
            reportedUserId: reportedUserId
        }),
        credentials: "include" // 세션 정보를 포함
    })
        .then(response => {
            if (response.ok) {
                getModal('reportSuccessModal').show();
            } else {
                return response.text().then(errorText => {
                    throw new Error(errorText);
                });
            }
        })
        .catch(error => {
            console.error("Fetch error:", error);
            getModal('reportSuccessModal').show();
            $('#reportModalText').html("신고 실패 <br> 각 경기에서 특정 회원에 대해 한 번만 신고할 수 있습니다. ");
            setReportModal();
        });
});

// 내용 초기화
function setReportModal(){
    $("#teamSelect").text("팀 선택");
    $("#userSelect").text("신고 대상 선택");
    $("#reasonSelect").text("신고 사유 선택");
    $("textarea").val("");
}

// 매칭 시퀀스 구하기
function getMatchingSeq() {
    return $('#submitBtn').attr('data-matchingSeq');
}

// 팀 선택 변경 시 처리
// 안됨
$('#teamSelect').on('click', '.dropdown-item', function() {

    $('#userSelect').text('신고 대상 선택');
    // $('#userDropdown').empty();

    $('#reasonSelect').text('신고 사유 선택');
    alert('짜증남')
});

// 신고 대상 선택 변경 시 처리
$('#userSelect').on('click', '.dropdown-item', function() {
    $('#reasonSelect').text('신고 사유 선택');
    alert('짜증남')

});
