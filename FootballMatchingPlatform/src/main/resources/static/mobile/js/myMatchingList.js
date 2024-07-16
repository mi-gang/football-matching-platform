// 모달 요소 선택
let cancelSuccessModal = document.getElementById('cancelSuccessModal')

// 모달 초기화
var modal = new bootstrap.Modal(cancelSuccessModal)


// 날짜 클릭 시
$(".calendar-dates").on("click", ".date-wrapper", function () {

    $("#matchings-wrapper").html('');

    $(".click-date").removeClass("click-date");
    $(this).find(".date").addClass("click-date"); // find -> 자식 요소 찾기

    // 클릭한 날짜 불러오기
    let clickedDate = $(this).find(".date").attr("id")
    console.log(clickedDate)

// 매칭이 있는 경우 해당 날짜 매칭 정보들 불러오기
    if ($(this).find(".cal-matching-status-1, .cal-matching-status-2, .cal-matching-status-3").length > 0) {
        fetch('/schedule/date/' + clickedDate, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                if (Array.isArray(data)) {
                    data.forEach(item => {
                        console.log(item);
                        getMatchigList(item);
                    });
                } else {
                    console.error('Expected an array but got:', data);
                }
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
    }
});


// 매칭 취소 버튼 클릭 시 (매칭중)
$("#matchings-wrapper").on("click", ".remove-matching-btn", function () {
    const matchingAddListSeq = $(this)
        .closest(".matching-wrapper")
        .attr("data-matchingAddListSeq");
    console.log(matchingAddListSeq)

    $("#cancelModalBtn").attr("data-matchingAddListSeq", matchingAddListSeq);
    $("#cancelModalBtn").addClass("remove-matching");
});

// 매칭 취소 버튼 클릭 시 (매칭 성공 이후)
$("#matchings-wrapper").on("click", ".cancel-matching-btn", function () {
    const matchingAddListSeq = $(this)
        .closest(".matching-wrapper")
        .attr("data-matchingAddListSeq");
    console.log(matchingAddListSeq)

    $("#cancelModalBtn").attr("data-matchingAddListSeq", matchingAddListSeq);
    $("#cancelModalBtn").addClass("cancel-matching");
});

// 매칭 취소 모달 - 취소하기 버튼 클릭 시
$("#cancelModalBtn").on("click", function (){
    let matchingAddListSeq = $("#cancelModalBtn").data("matchingAddListSeq");

    if ($("#myElement").hasClass("cancel-matching")) {
        fetch(`/schedule/matching-add-list-seq/${matchingAddListSeq}/cancel`, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json",
            },
            credentials: "include" // 세션 정보를 포함
        })
            .then(response => {
                if (response.ok) {
                    // modal.show()
                    var myModal = new bootstrap.Modal(document.getElementById('cancelSuccessModal'), {
                        keyboard: false
                    });
                    myModal.show();
                } else {
                    return response.text().then(errorText => {
                        throw new Error(errorText);
                    });
                }
            })
            .catch(error => {
                console.error("Fetch error:", error);
                alert("An error occurred while updating cancel status.");
            });
    } else if($("#myElement").hasClass("remove-matching")) {
        fetch(`/schedule/matching-add-list-seq/${matchingAddListSeq}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
            },
            credentials: "include" // 세션 정보를 포함
        })
            .then(response => {
                if (response.ok) {
                    modal.show()

                } else {
                    return response.text().then(errorText => {
                        throw new Error(errorText);
                    });
                }
            })
            .catch(error => {
                console.error("Fetch error:", error);
                alert("An error occurred while delete matching.");
            });
    }
});


//매칭 취소 완료, 닫기 버튼 클릭 시
$("#cancelSuccessModalBtn").on("click", function () {
    location.href = '/schedule/matches';
});


$(document).ready(function () {

// 결제 모달 오픈 버튼 클릭 시
    $("#matchings-wrapper").on("click", ".pay-matching-btn ", function () {
        // 모달에 정보 옮겨놓기
        const matchingDate = $(this)
            .closest(".matching-wrapper")
            .find(".matching-date-info")
            .text();
        const matchingTime = $(this)
            .closest(".matching-wrapper")
            .find(".matching-time-info")
            .text();
        const matchingField = $(this)
            .closest(".matching-wrapper")
            .find(".matching-field-info")
            .text();

        console.log(matchingDate);
        console.log(matchingTime);
        console.log(matchingField);
        $(".modal-matching-info").text(matchingDate + " " + matchingTime);
        $(".modal-matching-field").text(matchingField);

        const matchingSeq = $(this)
            .closest(".matching-wrapper")
            .attr("data-matchingSeq");
        // const matchingSeq = $(this).closest('.matching-wrapper').data('matchingSeq');

        $("#payMatchingBtn").attr("data-matching-seq", matchingSeq);

        console.log(matchingSeq)
    });
});

// 결제 버튼 클릭 시
$("#payMatchingBtn").on("click", function () {

    let matchingSeq = $("#payMatchingBtn").data("matching-seq");
    fetch(`/schedule/${matchingSeq}/payment`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json",
        },
        credentials: "include" // 세션 정보를 포함
    })
        .then(response => {
            if (response.ok) {
                // Bootstrap 5에서 모달을 여는 방법
                var myModal = new bootstrap.Modal(document.getElementById('paymentSuccessModal'), {
                    keyboard: false
                });
                myModal.show();
            } else {
                return response.text().then(errorText => {
                    throw new Error(errorText);
                });
            }
        })
        .catch(error => {
            console.error("Fetch error:", error);
            alert("An error occurred while updating payment status.");
        });

});

// 선수 평가 클릭 시
$("#matchings-wrapper").on("click", ".review-matching-btn", function () {
    const matchingSeq = $(this)
        .closest(".matching-wrapper")
        .attr("data-matchingSeq");
    location.href = '/addScore/'+ matchingSeq;
});


$(document).ready(function () {
    fetch('/schedule/matches/count', {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            $('#totalNum').text(data);
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });

    fetch('/schedule/matches', {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            if (Array.isArray(data)) {
                data.forEach(item => {
                    console.log(item);
                    getMatchigList(item);
                });
            } else {
                console.error('Expected an array but got:', data);
            }
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
});


// 매칭 리스트 생성
function getMatchigList(item) {
    // matching-wrapper div 생성
    const matchingWrapper = document.createElement('div');
    matchingWrapper.className = 'matching-wrapper';
    matchingWrapper.setAttribute('data-matchingSeq', item.matchingSeq);
    matchingWrapper.setAttribute('data-matchingAddListSeq', item.matchingAddListSeq);

    // matching-content-wrapper div 생성
    const matchingContentWrapper = document.createElement('div');
    matchingContentWrapper.className = 'matching-content-wrapper';

    // matching-status span 생성
    const matchingStatus = document.createElement('span');
    matchingStatus.textContent = item.matchingStatus;

    matchingStatus.className = 'matching-status status-2';
    if (item.matchingStatus == '매칭중')
        matchingStatus.className = 'matching-status status-1';
    else if (item.matchingStatus == '매칭실패' || item.matchingStatus == '경기취소')
        matchingStatus.className = 'matching-status status-3';


    // 빠른 매칭만 - fast-matching-wrapper div 생성
    const fastMatchingWrapper = document.createElement('div');
    fastMatchingWrapper.className = 'fast-matching-wrapper';
    // 빠른 매칭만 - matching-user-count span 생성
    const matchingUserCount = document.createElement('span');
    matchingUserCount.className = 'matching-user-count';
    matchingUserCount.textContent = item.userCount;
    // 빠른 매칭만 - user count와 total count를 담는 div 생성
    const userCountWrapper = document.createElement('div');
    userCountWrapper.appendChild(matchingUserCount);
    userCountWrapper.appendChild(document.createTextNode('/10명'));
    // 빠른 매칭만 - fast-matching-wrapper에 status와 user count 추가
    fastMatchingWrapper.appendChild(matchingStatus);
    fastMatchingWrapper.appendChild(userCountWrapper);

    // matching-info div 생성
    const matchingInfo = document.createElement('div');
    matchingInfo.className = 'matching-info';

    // matching-date-info span 생성
    const matchingDateInfo = document.createElement('span');
    matchingDateInfo.className = 'matching-date-info';
    matchingDateInfo.textContent = item.matchingDate;

    // matching-time-info span 생성
    const matchingTimeInfo = document.createElement('span');
    matchingTimeInfo.className = 'matching-time-info';

    let formattedmatchingTime = item.matchingTime.toString().padStart(2, '0');
    matchingTimeInfo.innerHTML = '&nbsp;' + formattedmatchingTime + ':00 - ' + parseInt((item.matchingTime) + 2) + ':00';

    // matching-field-info span 생성
    const matchingFieldInfo = document.createElement('span');
    matchingFieldInfo.className = 'matching-field-info';
    matchingFieldInfo.textContent = item.fieldName;

    // matching-info div에 date와 time info 추가
    matchingInfo.appendChild(matchingDateInfo);
    matchingInfo.appendChild(matchingTimeInfo);

    // matching-content-wrapper에 모든 요소 추가
    matchingContentWrapper.appendChild(matchingStatus);
    matchingContentWrapper.appendChild(matchingInfo);
    matchingContentWrapper.appendChild(matchingFieldInfo);

    // matching-btn-wrapper div 생성
    const matchingBtnWrapper = document.createElement('div');
    matchingBtnWrapper.className = 'matching-btn-wrapper';

    console.log(!item.matchingStatus === '경기확정')

    // 경기 확정 상태 아닐 때만
    if (item.matchingStatus != '경기확정' && item.matchingStatus != '매칭실패' && item.matchingStatus != '경기취소' && item.matchingStatus != '경기완료') {

        if (item.matchingStatus != '매칭중') {
            // pay-matching-btn 버튼 생성
            const payMatchingBtn = document.createElement('button');
            payMatchingBtn.className = 'pay-matching-btn btn-setting';
            payMatchingBtn.setAttribute('data-bs-toggle', 'modal');
            payMatchingBtn.setAttribute('data-bs-target', '#payMatchingModal');
            if (item.payStatus) {
                payMatchingBtn.textContent = '결제 완료';
                payMatchingBtn.disabled = true;
            } else {
                payMatchingBtn.textContent = '결제하기';
            }
            matchingBtnWrapper.appendChild(payMatchingBtn);
        }

        // cancel-matching-btn 버튼 생성
        const cancelMatchingBtn = document.createElement('button');
        cancelMatchingBtn.setAttribute('data-bs-toggle', 'modal');
        cancelMatchingBtn.setAttribute('data-bs-target', '#cancelMatchingModal');

        if (item.matchingStatus == '매칭중')
            cancelMatchingBtn.className = 'remove-matching-btn btn-setting';
        else
            cancelMatchingBtn.className = 'cancel-matching-btn btn-setting';


        cancelMatchingBtn.textContent = '매칭 취소';

        // matching-btn-wrapper에 버튼 추가
        matchingBtnWrapper.appendChild(cancelMatchingBtn);

    } else if (item.matchingStatus == '경기완료') {
        // review-matching-btn 버튼 생성
        const reviewMatchingBtn = document.createElement('button');
        reviewMatchingBtn.className = 'review-matching-btn btn-setting';
        reviewMatchingBtn.textContent = '선수 평가';

        console.log(item.reviewStatus)
        if (item.reviewStatus) {
            reviewMatchingBtn.textContent = '평가 완료';
            reviewMatchingBtn.disabled = true;
        }

        // matching-score-btn 버튼 생성
        const matchingScoreBtn = document.createElement('button');
        matchingScoreBtn.className = 'matching-score-btn btn-setting';
        matchingScoreBtn.textContent = '내 점수 확인';
        if (!item.opposingTeamReviewStatus)
            matchingScoreBtn.disabled = true;

        matchingBtnWrapper.appendChild(reviewMatchingBtn);
        matchingBtnWrapper.appendChild(matchingScoreBtn);

    }

    // matching-wrapper에 content wrapper와 button wrapper 추가
    matchingWrapper.appendChild(matchingContentWrapper);
    matchingWrapper.appendChild(matchingBtnWrapper);

    // 'matchings-wrapper' 요소에 matching-wrapper 추가
    document.getElementById('matchings-wrapper').appendChild(matchingWrapper);
}