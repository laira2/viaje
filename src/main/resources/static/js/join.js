document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('joinForm');
    const emailInput = document.getElementById('email');
    const nicknameInput = document.getElementById('nickname');
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirmPassword');
    const submitBtn = document.querySelector('.submit-btn');

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
        return fetch(`/join/check-email?email=${encodeURIComponent(email)}`)
            .then(response => response.json())
            .then(data => {
                if (data.exists) {
                    showError(emailInput, '이미 사용 중인 이메일입니다.');
                    isEmailValid = false;
                } else {
                    clearError(emailInput);
                    isEmailValid = true;
                }
                toggleSubmitButton();
            })
            .catch(() => {
                showError(emailInput, '이메일 검증 중 오류가 발생했습니다. 다시 시도해 주세요.');
                isEmailValid = false;
                toggleSubmitButton();
            });
    }

    function checkNickname() {
        const nickname = nicknameInput.value;
        return fetch(`/join/check-nickname?nickname=${encodeURIComponent(nickname)}`)
            .then(response => response.json())
            .then(data => {
                if (data.exists) {
                    showError(nicknameInput, '이미 사용 중인 닉네임입니다.');
                    isNicknameValid = false;
                } else {
                    clearError(nicknameInput);
                    isNicknameValid = true;
                }
                toggleSubmitButton();
            })
            .catch(() => {
                showError(nicknameInput, '닉네임 검증 중 오류가 발생했습니다. 다시 시도해 주세요.');
                isNicknameValid = false;
                toggleSubmitButton();
            });
    }

    function toggleSubmitButton() {
        if (isEmailValid && isNicknameValid &&
            passwordInput.value.length >= 8 &&
            passwordInput.value === confirmPasswordInput.value) {
            submitBtn.disabled = false;
        } else {
            submitBtn.disabled = true;
        }
    }

    emailInput.addEventListener('blur', checkEmail);
    nicknameInput.addEventListener('blur', checkNickname);

    passwordInput.addEventListener('input', function() {
        if (passwordInput.value.length < 8) {
            showError(passwordInput, '비밀번호는 8자 이상이어야 합니다.');
        } else {
            clearError(passwordInput);
        }
        toggleSubmitButton();
    });

    confirmPasswordInput.addEventListener('input', function() {
        if (passwordInput.value !== confirmPasswordInput.value) {
            showError(confirmPasswordInput, '비밀번호가 일치하지 않습니다.');
        } else {
            clearError(confirmPasswordInput);
        }
        toggleSubmitButton();
    });

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        // 이메일 및 닉네임 검증이 완료될 때까지 대기
        Promise.all([checkEmail(), checkNickname()]).then(() => {
            if (isEmailValid && isNicknameValid &&
                passwordInput.value.length >= 8 &&
                passwordInput.value === confirmPasswordInput.value) {
                form.submit();
            } else {
                if (!isEmailValid) {
                    showError(emailInput, '이미 사용 중인 이메일입니다.');
                }
                if (!isNicknameValid) {
                    showError(nicknameInput, '이미 사용 중인 닉네임입니다.');
                }
                if (passwordInput.value.length < 8) {
                    showError(passwordInput, '비밀번호는 8자 이상이어야 합니다.');
                }
                if (passwordInput.value !== confirmPasswordInput.value) {
                    showError(confirmPasswordInput, '비밀번호가 일치하지 않습니다.');
                }
                alert('모든 입력 항목을 올바르게 작성해 주세요.');
            }
        }).catch(() => {
            alert('오류가 발생했습니다. 다시 시도해 주세요.');
        });
    });
});