// 캘린더 생성
const date = new Date();
let currentYear = date.getFullYear();
let currentMonth = date.getMonth();
let currentDate = date.getDate();

const calendarDates = document.querySelector("#calendarDates");
const prevBtn = document.querySelector("#prevBtn");
const nextBtn = document.querySelector("#nextBtn");

function renderCalendar() {
  document.querySelector(".year-month").textContent = `${currentYear}년 ${
    currentMonth + 1
  }월`;

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
  for (let i = 1; i <= daysInMonth; i++) {
    // date-wrapper
    const dateWrapperElement = document.createElement("div");
    dateWrapperElement.classList.add("date-wrapper");

    // 날짜
    const dateElement = document.createElement("div");
    dateElement.classList.add("date");
    dateElement.setAttribute(
      "id",
      currentYear + "-" + parseInt(currentMonth + 1) + "-" + i
    );
    dateElement.textContent = i;
    if (i == currentDate) dateElement.classList.add("today");

    // 매칭 여부
    const matchingDateElement = document.createElement("div");

    // 매칭 정보 있는 날이면 등록, 매칭 상태 불러와서 매칭 상태에 따라 다른 클래스 지정
    if (currentMonth == 6 && i == 5)
      // matchingDateElement.dataset.matchingStatus = "2";
      matchingDateElement.classList.add("cal-matching-status-1");

    if (currentMonth == 6 && i == 8)
      matchingDateElement.classList.add("cal-matching-status-2");

    if (currentMonth == 6 && i == 11)
      matchingDateElement.classList.add("cal-matching-status-3");

    dateWrapperElement.appendChild(dateElement);
    dateWrapperElement.appendChild(matchingDateElement);
    calendarDates.appendChild(dateWrapperElement);
  }
}

renderCalendar();

// 이전 달 버튼 클릭 시
prevBtn.addEventListener("click", () => {
  currentMonth--;
  if (currentMonth < 0) {
    currentMonth = 11;
    currentYear--;
  }
  renderCalendar();
});

// 다음 달 버튼 클릭 시
nextBtn.addEventListener("click", () => {
  currentMonth++;
  if (currentMonth > 11) {
    currentMonth = 0;
    currentYear++;
  }
  renderCalendar();
});

// 날짜 클릭 시
$(".calendar-dates").on("click", ".date-wrapper", function () {
  $(".click-date").removeClass("click-date");
  $(this).find(".date").addClass("click-date"); // find -> 자식 요소 찾기

  // 클릭한 날짜 불러오기
  console.log($(this).find(".date").attr("id"));
  // 매칭이 있는 경우 해당 날짜 매칭 정보들 불러오기
});

// 일정 전체보기 클릭 시
$("#my-matching-list-btn").on("click", function () {
  alert("일정 전체보기 페이지 이동");
});

// 매칭 취소 버튼 클릭 시
$("#matchings-wrapper").on("click", ".cancel-matching-btn", function () {});

// 결제 모달 오픈 버튼 클릭 시
$("#matchings-wrapper").on("click", ".pay-matching-btn ", function () {
  alert("뭔데");
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
