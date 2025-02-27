// 모든 라디오 버튼 가져오기
const radioButtons = document.querySelectorAll('input[name="shipping"]');
const messageBox = document.getElementById('message');

// 라디오 버튼 변경 시 이벤트 리스너 추가
radioButtons.forEach((radio) => {
    radio.addEventListener('change', function () {
        // 선택된 배송 방법 가져오기
        const selectedShipping = document.querySelector('input[name="shipping"]:checked').value;

        // 메시지 업데이트
        messageBox.innerHTML = ''; // 기존 메시지 초기화
        const alertDiv = document.createElement('div');
        alertDiv.className = 'alert alert-info'; // 부트스트랩 alert 스타일 적용
        alertDiv.textContent = `선택한 배송 방법: ${selectedShipping}`;
        messageBox.appendChild(alertDiv);
    });
});
