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
        if (tmpDate == currentDate) dateElement.classList.add("today");

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
                        console.log(item);

                        // matching-wrapper div 생성
                        const matchingWrapper = document.createElement('div');
                        matchingWrapper.className = 'matching-wrapper';

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
                        matchingTimeInfo.innerHTML  = '&nbsp;' + item.matchingTime + ':00 - ' + parseInt((item.matchingTime) + 2) + ':00';

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

                        // cancel-matching-btn 버튼 생성
                        const cancelMatchingBtn = document.createElement('button');
                        cancelMatchingBtn.className = 'cancel-matching-btn btn-setting';
                        cancelMatchingBtn.setAttribute('data-bs-toggle', 'modal');
                        cancelMatchingBtn.setAttribute('data-bs-target', '#cancelMatchingModal');
                        cancelMatchingBtn.textContent = '매칭 취소';

                        // matching-btn-wrapper에 버튼 추가
                        matchingBtnWrapper.appendChild(cancelMatchingBtn);

                        // matching-wrapper에 content wrapper와 button wrapper 추가
                        matchingWrapper.appendChild(matchingContentWrapper);
                        matchingWrapper.appendChild(matchingBtnWrapper);

                        // 'matchings-wrapper' 요소에 matching-wrapper 추가
                        document.getElementById('matchings-wrapper').appendChild(matchingWrapper);
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
        alert("일정 전체보기 페이지 이동");
    });

// 매칭 취소 버튼 클릭 시
    $("#matchings-wrapper").on("click", ".cancel-matching-btn", function () {
    });

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
        $(".matching-info").text(matchingDate + " " + matchingTime);
        $(".matching-field").text(matchingField);

        const matchingSeq = $(this)
            .closest(".matching-wrapper")
            .attr("data-matchingSeq");
        // const matchingSeq = $(this).closest('.matching-wrapper').data('matchingSeq');

        $("#payMatchingBtn").data("matchingSeq", matchingSeq);
    });