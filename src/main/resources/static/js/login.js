/*
document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("loginForm");
    const container = document.querySelector('.container');

    loginForm.addEventListener("submit", (event) => {
        event.preventDefault();
        login();
    });

    async function login() {
        const email = document.getElementById("login-username").value;
        const password = document.getElementById("login-password").value;
        const errorMessage = document.getElementById("error-message");

        const response = await fetch("/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ username, password }),
        });

        if (response.ok) {
            // 로그인 성공 시
            errorMessage.textContent = "";
            alert("로그인 성공!");
            window.location.href = "/";
        } else {
            // 로그인 실패 시
            const errorText = await response.text();
            errorMessage.textContent = `로그인 실패: ${errorText}`;
        }
    }

    container.addEventListener('mouseenter', () => {
        container.classList.add('expanded');
    });

    container.addEventListener('mouseleave', () => {
        container.classList.remove('expanded');
    });
});

container.addEventListener('mouseenter', () => {
        container.classList.add('expanded');
    });

    container.addEventListener('mouseleave', () => {
        container.classList.remove('expanded');
    });
*/