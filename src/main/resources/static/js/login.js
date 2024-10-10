function submitLogin() {
    const personalId = document.querySelector('input[name="ID"]').value; // 변경: personalId 사용
    const password = document.querySelector('input[name="password"]').value;

    if (!personalId || !password) { // 변경: personalId로 확인
        alert('모든 필드를 입력해주세요.');
        return;
    }

    const loginData = {
        personalId, // 올바른 변수 사용
        password
    };

    fetch('/api/member/login-req', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            return response.json().then(data => {
                throw new Error(data.message || '로그인 실패'); // 에러 메시지 개선
            });
        }
    })
    .then(data => {
        alert(data.message || '로그인이 완료되었습니다.');

        // rank에 따라 다른 페이지로 이동
        switch (data.rank) {
            case '1':
                window.location.href = '/admin/dashboard'; // 관리자 대시보드
                break;
            case '2':
                window.location.href = '/user/apply/dashboard'; // 일반 사용자 홈(승인완료)
                break;
            case '3':
                window.location.href = '/user/apply/non-apply/mypage'; // 일반 사용자 홈(승인요청필요 or 승인대기)
                break;
            case '4':
                window.location.href = '/guest/home'; // 게스트 홈
                break;
            default:
                window.location.href = '/home'; // 기본 홈
        }
    })
    .catch(error => {
        alert(error.message);
    });
}