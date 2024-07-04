document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('joinForm');
    const emailInput = document.getElementById('email');
    const nicknameInput = document.getElementById('nickname');
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirmPassword');

    let isEmailValid = false;
    let isNicknameValid = false;

    function showError(input, message) {
        const formGroup = input.closest('.input-group');
        formGroup.classList.add('error');
        const errorElement = formGroup.querySelector('.error-message');
        errorElement.textContent = message;
    }

    function clearError(input) {
        const formGroup = input.closest('.input-group');
        formGroup.classList.remove('error');
        const errorElement = formGroup.querySelector('.error-message');
        errorElement.textContent = '';
    }

    function checkEmail() {
        const email = emailInput.value;
        fetch(`/join/check-email?email=${encodeURIComponent(email)}`)
            .then(response => response.json())
            .then(data => {
                if (data.exists) {
                    showError(emailInput, '이미 사용 중인 이메일입니다.');
                    isEmailValid = false;
                } else {
                    clearError(emailInput);
                    isEmailValid = true;
                }
            });
    }

    function checkNickname() {
        const nickname = nicknameInput.value;
        fetch(`/join/check-nickname?nickname=${encodeURIComponent(nickname)}`)
            .then(response => response.json())
            .then(data => {
                if (data.exists) {
                    showError(nicknameInput, '이미 사용 중인 닉네임입니다.');
                    isNicknameValid = false;
                } else {
                    clearError(nicknameInput);
                    isNicknameValid = true;
                }
            });
    }

    emailInput.addEventListener('blur', checkEmail);
    nicknameInput.addEventListener('blur', checkNickname);

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        checkEmail();
        checkNickname();

        // 비밀번호 검증
        if (passwordInput.value.length < 8) {
            showError(passwordInput, '비밀번호는 8자 이상이어야 합니다.');
        } else {
            clearError(passwordInput);
        }

        if (passwordInput.value !== confirmPasswordInput.value) {
            showError(confirmPasswordInput, '비밀번호가 일치하지 않습니다.');
        } else {
            clearError(confirmPasswordInput);
        }

        // 모든 검증이 통과되었는지 확인
        setTimeout(() => {
            if (isEmailValid && isNicknameValid &&
                passwordInput.value.length >= 8 &&
                passwordInput.value === confirmPasswordInput.value) {
                form.submit();
            }
        }, 1000); // 서버 응답을 기다리기 위해 약간의 지연 추가
    });
});