// 캘린더 생성
const date = new Date();
let currentYear = date.getFullYear();
let currentMonth = date.getMonth();
let currentDate = date.getDate();
let matchingDateList;

const calendarDates = document.querySelector("#calendarDates");
const prevBtn = document.querySelector("#prevBtn");
const nextBtn = document.querySelector("#nextBtn");

// 매칭 정보 불러오기
async function fetchMatchingDates() {
    try {
        const response = await fetch("/schedule/month/" + parseInt(currentMonth + 1), {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        });
        if (!response.ok) {
            throw new Error("Network response was not ok " + response.statusText);
        }

        const data = await response.json();

        if (data.length === 0) {
            matchingDateList = [];
        } else {
            console.log(data);
            console.log(data[0].matchingDate);
            matchingDateList = data;
        }
    } catch (error) {
        console.error("There has been a problem with your fetch operation:", error);
    }
}

// 캘린더 생성
function renderCalendar() {
    document.querySelector(".year-month").textContent = `${currentYear}년 ${currentMonth + 1}월`;
    const firstDayOfMonth = new Date(currentYear, currentMonth, 1); // 첫번째 날 불러오기
    const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate(); // 날짜수 불러오기

    const startDayOfWeek = firstDayOfMonth.getDay();

    calendarDates.innerHTML = ""; // 일자 비우기

    // 빈 날짜(이전 달)
    for (let i = 0; i < startDayOfWeek; i++) {
        const emptyDate = document.createElement("div");
        emptyDate.classList.add("date", "empty");
        calendarDates.appendChild(emptyDate);
    }

    // 현재 달의 날짜
    for (let tmpDate = 1; tmpDate <= daysInMonth; tmpDate++) {

        // date-wrapper
        const dateWrapperElement = document.createElement("div");
        dateWrapperElement.classList.add("date-wrapper");

        // 날짜
        // 날짜 두자리로 포맷팅
        let formattedMonth = parseInt(currentMonth + 1).toString().padStart(2, '0');
        let formattedDay = tmpDate.toString().padStart(2, '0');

        const dateElement = document.createElement("div");
        dateElement.classList.add("date");
        dateElement.setAttribute(
            "id",
            currentYear + "-" + formattedMonth + "-" + formattedDay
        );
        dateElement.textContent = tmpDate;
        console.log(tmpDate)
        console.log(currentDate)
        nowdate = new Date();
        if (currentYear == nowdate.getFullYear() && currentMonth ==nowdate.getMonth() && currentDate == tmpDate)
            dateElement.classList.add("today");

        // 매칭 여부
        const matchingDateElement = document.createElement("div");

        // 매칭 정보에 따라 다른 클래스 지정
        for (let dateNum = 0; dateNum < matchingDateList.length; dateNum++) {
            const tmpMatchingYear = new Date(matchingDateList[dateNum].matchingDate).getFullYear();
            const tmpMatchingMonth = new Date(matchingDateList[dateNum].matchingDate).getMonth() + 1;
            const tmpMatchingDate = new Date(matchingDateList[dateNum].matchingDate).getDate();
            // console.log("tmpMatchingMonth: " + tmpMatchingMonth);
            // console.log("currentMonth: " + parseInt(currentMonth + 1));

            // console.log("tmpMatchingDate: "+tmpMatchingDate);
            // console.log("tmpDate : "+tmpDate)
            // console.log(tmpMatchingDate == tmpDate)
            // console.log(typeof (tmpMatchingDate))
            // console.log(typeof (tmpDate))

            if (currentYear == tmpMatchingYear && tmpMatchingMonth == parseInt(currentMonth + 1) && tmpMatchingDate == tmpDate) {
                if (matchingDateList[dateNum].cancelStatus || matchingDateList[dateNum].matchingStatus == '매칭실패') {
                    matchingDateElement.classList.add("cal-matching-status-1");
                } else if (matchingDateList[dateNum].matchingStatus == '매칭중') {
                    matchingDateElement.classList.add("cal-matching-status-2");
                } else {
                    matchingDateElement.classList.add("cal-matching-status-3");
                }
            }

        }
        dateWrapperElement.appendChild(dateElement);
        dateWrapperElement.appendChild(matchingDateElement);
        calendarDates.appendChild(dateWrapperElement);
    }
}

fetchMatchingDates().then(() => {
    renderCalendar();
})

// 이전 달 버튼 클릭 시
prevBtn.addEventListener("click", () => {
    currentMonth--;
    if (currentMonth < 0) {
        currentMonth = 11;
        currentYear--;
    }
    fetchMatchingDates();
    fetchMatchingDates().then(() => {
        renderCalendar();
    })
});

// 다음 달 버튼 클릭 시
nextBtn.addEventListener("click", () => {
    currentMonth++;
    if (currentMonth > 11) {
        currentMonth = 0;
        currentYear++;
    }
    fetchMatchingDates();
    fetchMatchingDates().then(() => {
        renderCalendar();
    })
});
// 캘린더 끝










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

// 일정 전체보기 클릭 시
    $("#my-matching-list-btn").on("click", function () {
        location.href = '/myMatchingList';
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
    let matchingAddListSeq = $("#cancelModalBtn").attr("data-matchingAddListSeq");
    console.log(matchingAddListSeq);

    if ($("#cancelModalBtn").hasClass("cancel-matching")) {
        fetch(`/schedule/matching-add-list-seq/${matchingAddListSeq}/cancel`, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json",
            },
            credentials: "include" // 세션 정보를 포함
        })
            .then(response => {
                if (response.ok) {
                    getModal('cancelSuccessModal').show();
                } else {
                    return response.text().then(errorText => {
                        throw new Error(errorText);
                    });
                }
            })
            .catch(error => {
                console.error("Fetch error:", error);
                getModal('cancelSuccessModal').show();
                $('#cancelModalText').text("취소 실패<br>다시 시도해주세요.");
            });
    } else if($("#cancelModalBtn").hasClass("remove-matching")) {
        fetch(`/schedule/matching-add-list-seq/${matchingAddListSeq}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
            },
            credentials: "include" // 세션 정보를 포함
        })
            .then(response => {
                if (response.ok) {
                    getModal('cancelSuccessModal').show();
                } else {
                    return response.text().then(errorText => {
                        throw new Error(errorText);
                    });
                }
            })
            .catch(error => {
                console.error("Fetch error:", error);
                getModal('cancelSuccessModal').show();
                $('#cancelModalText').text("취소 실패<br>다시 시도해주세요.");
            });
    }
});

//매칭 취소 완료, 닫기 버튼 클릭 시
$("#cancelSuccessModalBtn").on("click", function () {
    location.href = '/myMatchingList';
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

        const matchingAddListSeq = $(this)
            .closest(".matching-wrapper")
            .attr("data-matchingAddListSeq");
        // const matchingSeq = $(this).closest('.matching-wrapper').data('matchingSeq');

        $("#payMatchingBtn").attr("data-matching-add-list-seq", matchingAddListSeq);
    });
});


// 결제 버튼 클릭 시
$("#payMatchingBtn").on("click", function () {

    let matchingAddListSeq = $("#payMatchingBtn").data("matching-add-list-seq");
    fetch(`/schedule/${matchingAddListSeq}/payment`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json",
        },
        credentials: "include" // 세션 정보를 포함
    })
        .then(response => {
            if (response.ok) {
                console.log(getModal('paymentSuccessModal'))
                getModal('paymentSuccessModal').show();
            } else {
                return response.text().then(errorText => {
                    throw new Error(errorText);
                });

            }
        })
        .catch(error => {
            console.error("Fetch error:", error);
            getModal('paymentSuccessModal').show();
            $('#paymentModalText').text("결제 실패<br>다시 시도해주세요.");
        });

});

// 결제 완료 후 닫기 버튼 클릭 시
$("#paymentSuccessModalBtn").on("click", function () {
    location.href = '/myMatchingList';
});



// 선수 평가 클릭 시
$("#matchings-wrapper").on("click", ".review-matching-btn", function () {
    const matchingSeq = $(this)
        .closest(".matching-wrapper")
        .attr("data-matchingSeq");
    const matchingAddListSeq = $(this)
        .closest(".matching-wrapper")
        .attr("data-matchingAddListSeq");
    location.href = '/addScore/'+ matchingSeq + '/' + matchingAddListSeq;
});

// 내 점수 확인 클릭 시
$("#matchings-wrapper").on("click", ".matching-score-btn", function () {
    const matchingSeq = $(this)
        .closest(".matching-wrapper")
        .attr("data-matchingSeq");
    fetch(`/schedule/${matchingSeq}/score`, {
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
            console.log(data)
            console.log(data.reviewScore)
            console.log(data.teamScore)
            $("#resultScore").text(data.reviewScore);
            $("#reviewScore").text(data.teamScore);
            $("#totalScore").text(data.reviewScore+data.teamScore);
            getModal('scoreModal').show();
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
});



















// 모달 선택 함수
function getModal(modalId){
    let modalElement = document.getElementById(modalId)
    var modal = new bootstrap.Modal(modalElement)
    return modal;
}

// 매칭 리스트 생성 함수
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
    
    if (item.fastAddStatus)
        matchingStatus.textContent = '빠른매칭 진행중';
    if (item.cancelStatus)
        matchingStatus.textContent = '매칭취소';
    

    matchingStatus.className = 'matching-status status-2';
    if (item.matchingStatus == '매칭중')
        matchingStatus.className = 'matching-status status-1';
    else if (item.matchingStatus == '매칭실패' || item.matchingStatus == '경기취소' || item.cancelStatus)
        matchingStatus.className = 'matching-status status-3';


    // fast-matching-wrapper div 생성
    const fastMatchingWrapper = document.createElement('div');
    fastMatchingWrapper.className = 'fast-matching-wrapper';
    // fast-matching-wrapper에 status 추가
    fastMatchingWrapper.appendChild(matchingStatus);

    if (item.fastAddStatus && !item.cancelStatus){
        // // 빠른 매칭만 - matching-user-count span 생성
        // const matchingUserCount = document.createElement('span');
        // matchingUserCount.className = 'matching-user-count';
        // matchingUserCount.textContent = item.userCount;
        //
        // // 빠른 매칭만 - user count와 total count를 담는 div 생성
        // const userCountWrapper = document.createElement('div');
        // userCountWrapper.appendChild(matchingUserCount);
        // userCountWrapper.appendChild(document.createTextNode('/10명'));

        // 빠른 매칭만 - fast-matching-wrapper에 status와 user count 추가
        // fastMatchingWrapper.appendChild(userCountWrapper);

        const matchingUserCountWrapper = document.createElement('div');
        fastMatchingWrapper.appendChild(matchingUserCountWrapper);

        const matchingUserCount = document.createElement('span');
        matchingUserCount.classList.add('matching-user-count');
        matchingUserCount.textContent = item.totalUserCount;
        matchingUserCountWrapper.appendChild(matchingUserCount);

        const matchingUserCountText = document.createElement('span');
        matchingUserCountText.textContent = '/10명';
        matchingUserCountWrapper.appendChild(matchingUserCountText);
    }

    // 경기 확정 시 등번호 생성
    if (item.matchingStatus == '경기확정' || item.matchingStatus == '경기완료'){
        const playerNumber = document.createElement('span');
        playerNumber.textContent = item.playerNumber;

        if (item.team == 'a')
            playerNumber.className = 'my-player-number a-team';
        else
            playerNumber.className = 'my-player-number b-team';

        fastMatchingWrapper.appendChild(playerNumber);
    }

    // 경기 확정 시 등번호 생성
    if (item.teamStatus){
        const teamElement = document.createElement('span');
        teamElement.textContent = '팀';
        teamElement.className = 'team';

        fastMatchingWrapper.appendChild(teamElement);
    }

    // 경기 완료 시 점수 표기
    if (item.matchingStatus == '경기완료'){
        const matchingScoreWrapper = document.createElement('div');
        matchingScoreWrapper.className = 'matching-score-wrapper';

        const aScore = document.createElement('span');
        // matchingScoreWrapper.className = 'matching-score-wrapper';
        aScore.textContent = item.ascore;

        const span = document.createElement('span');
        span.textContent = ' : ';

        const bScore = document.createElement('span');
        bScore.textContent = item.bscore;

        matchingScoreWrapper.appendChild(aScore);
        matchingScoreWrapper.appendChild(span);
        matchingScoreWrapper.appendChild(bScore);
        fastMatchingWrapper.appendChild(matchingScoreWrapper);
    }

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
    matchingContentWrapper.appendChild(fastMatchingWrapper);
    matchingContentWrapper.appendChild(matchingInfo);
    matchingContentWrapper.appendChild(matchingFieldInfo);

    // 결제 완료 시 결제 금액 표기
    if (item.payStatus && item.matchingStatus != '매칭중' && item.matchingStatus != '매칭실패' && !item.cancelStatus) {
        const payInfo = document.createElement('span');
        payInfo.classList.add('pay-info');
        payInfo.textContent = '결제 금액 : 10,000원';
        matchingContentWrapper.appendChild(payInfo);
    }

    // 팀 매칭 시 매칭 성공부터 팀 이름 표기
    if (item.teamStatus && item.matchingStatus != '매칭실패' && !item.cancelStatus
            && (item.matchingStatus == '매칭성공' || item.matchingStatus == '경기확정' || item.matchingStatus == '경기완료')) {

        const teamInfoWrapper = document.createElement('div');
        teamInfoWrapper.className = 'team-info';

        const myTeamName = document.createElement('span');
        // matchingScoreWrapper.className = 'matching-score-wrapper';
        myTeamName.textContent = item.myTeamName;

        const span = document.createElement('span');
        span.textContent = ' VS ';

        const opposingTeamName = document.createElement('span');
        opposingTeamName.textContent = item.opposingTeamName;

        teamInfoWrapper.appendChild(myTeamName);
        teamInfoWrapper.appendChild(span);
        teamInfoWrapper.appendChild(opposingTeamName);
        matchingContentWrapper.appendChild(teamInfoWrapper);
    }

    // matching-btn-wrapper div 생성
    const matchingBtnWrapper = document.createElement('div');
    matchingBtnWrapper.className = 'matching-btn-wrapper';

    console.log(!item.matchingStatus === '경기확정')

    // 경기 확정 상태 아닐 때만 버튼 표기
    if (item.matchingStatus != '경기확정' && item.matchingStatus != '매칭실패'
        && item.matchingStatus != '경기취소' && item.matchingStatus != '경기완료'
        && !item.cancelStatus) {

        if (item.matchingStatus != '매칭중') {
            // pay-matching-btn 버튼 생성
            const payMatchingBtn = document.createElement('button');
            payMatchingBtn.className = 'pay-matching-btn btn-setting';
            payMatchingBtn.setAttribute('data-bs-toggle', 'modal');
            payMatchingBtn.setAttribute('data-bs-target', '#payMatchingModal');
            // payMatchingBtn.setAttribute('data-matching-seq', item.matchingSeq);
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