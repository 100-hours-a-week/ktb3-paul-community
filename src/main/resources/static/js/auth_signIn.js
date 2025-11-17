// auth.js

// 1) 텍스트 입력 이벤트 리스너
document.getElementById("emailInput").addEventListener("input", (e) => {
    console.log("email input:", e.target.value);
});

document.getElementById("passwordInput").addEventListener("input", (e) => {
    console.log("password input:", e.target.value);
});

// 2) 일반 버튼 클릭 이벤트(로그인 버튼)
document.getElementById("loginBtn").addEventListener("click", () => {
    console.log("로그인 버튼 클릭됨");
    // TODO: 이후 fetch로 서버 요청 코드 넣을 자리
});

// 3) 일반 버튼 클릭 이벤트(회원가입 이동 버튼)
document.getElementById("moveToJoinBtn").addEventListener("click", () => {
    console.log("회원가입 페이지로 이동 버튼 클릭");
    // TODO: 페이지 이동 로직 추가 예정
});


