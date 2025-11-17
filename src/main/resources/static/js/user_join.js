

/*******************************************
 * 회원가입 페이지 JS (auth.js)
 *******************************************/

/* ========================
   1) 텍스트 입력 이벤트
   ======================== */
document.getElementById("emailInput").addEventListener("input", (e) => {
    console.log("email input:", e.target.value);

});

document.getElementById("passwordInput").addEventListener("input", (e) => {
    console.log("password input:", e.target.value);
});

document.getElementById("passwordCheckInput").addEventListener("input", (e) => {
    console.log("password check input:", e.target.value);
});

document.getElementById("nicknameInput").addEventListener("input", (e) => {
    console.log("nickname input:", e.target.value);
});


/* ========================
   2) 버튼 클릭 이벤트
   ======================== */
document.getElementById("joinBtn").addEventListener("click", () => {
    console.log("회원가입 버튼 클릭됨");
    // TODO: 서버 전송 로직 추가 자리
});

document.getElementById("goToLoginBtn").addEventListener("click", () => {
    console.log("로그인 페이지 이동");
    // TODO: 이동 로직 추가 예정
});

document.getElementById("backBtn").addEventListener("click", () => {
    console.log("뒤로가기 버튼 클릭됨");
    // TODO: history.back() 혹은 페이지 이동
});


/* ========================
   3) 이미지 첨부 이벤트
   ======================== */

/* 프로필 동그라미 클릭 → 숨겨진 input 클릭 */
document.getElementById("profileCircle").addEventListener("click", () => {
    document.getElementById("profileInput").click();
    console.log("동그라미 클릭됨");
});

/* 프로필 input 변경 → 이미지 미리보기 */
document.getElementById("profileInput").addEventListener("change", (e) => {
    const file = e.target.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = function(event) {
        const circle = document.getElementById("profileCircle");
        circle.innerHTML = ""; // + 텍스트 제거
        const img = document.createElement("img");
        img.src = event.target.result;
        circle.appendChild(img);
    };

    reader.readAsDataURL(file);

    console.log("프로필 이미지 업로드됨:", file.name);
});
