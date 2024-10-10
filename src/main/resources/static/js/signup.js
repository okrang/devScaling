// ID 체크 함수
function checkId() {
    const id = document.querySelector('input[name="id"]').value;

    if (!id) {
        alert('ID를 입력해주세요.');
        return;
    }

    fetch(`/api/member/check-id/${id}`, {
        method: 'GET',
    })
    .then(response => {
        if (response.ok) {
            return response.text();
        } else {
            throw new Error('ID 중복');
        }
    })
    .then(message => {
        alert(message);
    })
    .catch(error => {
        alert(error.message);
    });
}

// 회원가입 요청 함수
function submitSignup() {
    const id = document.querySelector('input[name="id"]').value;
    const password = document.querySelector('input[name="password"]').value;
    const confirmPassword = document.querySelector('input[name="confirmPassword"]').value;
    const name = document.querySelector('input[name="name"]').value;
    const email = document.querySelector('input[name="email"]').value;
    const nickname = document.querySelector('input[name="nickname"]').value;
    const baekjoonId = document.querySelector('input[name="baekjoonId"]').value;

    if (!id || !password || !confirmPassword || !name || !email || !nickname) {
        alert('모든 필드를 입력해주세요.');
        return;
    }

    if (password !== confirmPassword) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
    }

    const signupData = {
        id,
        password,
        name,
        email,
        nickname,
        baekjoonId
    };

    fetch('/api/member/signup-req', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(signupData)
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('회원가입 실패');
        }
    })
    .then(data => {
        alert(data.message || '회원가입이 완료되었습니다.');
        // 성공적으로 가입된 후에 페이지를 이동할 수 있습니다.
        window.location.href = '/login'; // 로그인 페이지로 이동
    })
    .catch(error => {
        alert(error.message);
    });
}