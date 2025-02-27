document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); // 폼 제출 시 페이지 새로 고침 방지

    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();
    const messageBox = document.getElementById('message');

    // 이메일 형식 검증
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!email || !password) {
        messageBox.innerHTML = '<div class="alert alert-danger">모든 필드를 채워 주세요.</div>';
        return;
    }
    if (!emailRegex.test(email)) {
        messageBox.innerHTML = '<div class="alert alert-danger">올바른 이메일 형식을 입력하세요.</div>';
        return;
    }

    // 로그인 성공 메시지
    messageBox.innerHTML = `<div class="alert alert-success">로그인 성공!<br>이메일: ${email}</div>`;
});
