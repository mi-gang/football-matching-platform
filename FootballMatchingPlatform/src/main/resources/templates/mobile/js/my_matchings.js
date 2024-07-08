
// 캘린더 생성
const date = new Date();
const currentYear = date.getFullYear();
const currentMonth = date.getMonth();

const calendarDates = document.querySelector("#calendarDates");
const prevBtn = document.querySelector("#prevBtn");
const nextBtn = document.querySelector("#nextBtn");

function renderCalendar() {
    document.querySelector('.year-month').textContent = `${currentYear}년 ${currentMonth + 1}월`;

    const firstDayOfMonth = new Date(currentYear, currentMonth, 1); // 첫번째 날 불러오기
    const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();   // 날짜수 불러오기

    const startDayOfWeek = firstDayOfMonth.getDay();

    calendarDates.innerHTML = ""; // 일자 비우기
    // 현재 달의 날짜
    for (let i = 1; i <= daysInMonth; i++) {
        const dateElement = document.createElement("div");
        dateElement.classList.add("date");
        dateElement.textContent = i;
        calendarDates.appendChild(dateElement);
    }
}

renderCalendar()
