/********************************************
 * 회원정보 수정 페이지 (member.js)
 ********************************************/

/* =====================
    프로필 드롭다운
===================== */
const headerProfileBtn = document.getElementById("headerProfileBtn");
const profileDropdown = document.getElementById("profileDropdown");

headerProfileBtn?.addEventListener("click", () => {
    profileDropdown.classList.toggle("hidden");
});


/* =====================
    프로필 이미지 업로드
===================== */
const profileCircle = document.getElementById("profileCircle");
const profileInput = document.getElementById("profileInput");

profileChangeBtn?.addEventListener("click", () => {
    profileInput.click();
});

profileInput?.addEventListener("change", (e) => {
    const file = e.target.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = (ev) => {
        document.getElementById("currentProfileImg").src = ev.target.result;
    };
    reader.readAsDataURL(file);

    console.log("프로필 변경됨:", file.name);
});


/* =====================
    입력 이벤트
===================== */
nicknameInput?.addEventListener("input", (e) => {
    console.log("nickname input:", e.target.value);
});


/* =====================
    버튼 클릭 이벤트
===================== */
updateBtn?.addEventListener("click", () => {
    console.log("회원정보 수정 버튼 클릭됨");
});

withdrawBtn?.addEventListener("click", () => {
    console.log("회원탈퇴 버튼 클릭됨");
    withdrawModal.classList.remove("hidden");
});

withdrawCancelBtn?.addEventListener("click", () => {
    withdrawModal.classList.add("hidden");
});

withdrawConfirmBtn?.addEventListener("click", () => {
    console.log("회원 탈퇴 확인 클릭됨");
    // TODO: 서버 탈퇴 요청 넣을 자리
});
