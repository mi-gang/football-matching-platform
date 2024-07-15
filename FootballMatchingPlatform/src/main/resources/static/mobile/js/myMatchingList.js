
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
                        matchingWrapper.setAttribute('data-matchingSeq', item.matchingSeq);

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
                        matchingTimeInfo.innerHTML  = '&nbsp;' + formattedmatchingTime + ':00 - ' + parseInt((item.matchingTime) + 2) + ':00';

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
                        if (item.matchingStatus != '경기확정' && item.matchingStatus != '매칭실패' && item.matchingStatus != '경기취소') {

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
                            cancelMatchingBtn.className = 'cancel-matching-btn btn-setting';
                            cancelMatchingBtn.setAttribute('data-bs-toggle', 'modal');
                            cancelMatchingBtn.setAttribute('data-bs-target', '#cancelMatchingModal');
                            cancelMatchingBtn.textContent = '매칭 취소';

                            // matching-btn-wrapper에 버튼 추가
                            matchingBtnWrapper.appendChild(cancelMatchingBtn);
                        }

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

$(document).ready(function () {

    if ($(this).find(".cal-matching-status-1, .cal-matching-status-2, .cal-matching-status-3").length > 0) {
        fetch('/totalCount', {
            method: "GET",
            body: JSON.stringify({
                name: "yeri",
                batch: 1
            }),
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
                    });
                } else {
                    console.error('Expected an array but got:', data);
                }
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
    }

    totalNum
});