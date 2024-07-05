document.addEventListener('DOMContentLoaded', function () {
    const menuContainer = document.getElementById('menuContainer');
    const guestMenu = document.getElementById('guestMenu');
    const userMenu = document.getElementById('userMenu');
    const logoutButton = document.querySelector('.logout');

    function updateMenu(isLoggedIn) {
        if (isLoggedIn) {
            guestMenu.style.display = 'none';
            userMenu.style.display = 'block';
            logoutButton.style.display = 'block';
        } else {
            guestMenu.style.display = 'block';
            userMenu.style.display = 'none';
            logoutButton.style.display = 'none';
        }
    }

    // 서버에서 전달된 isLoggedIn 값을 사용하여 초기 메뉴 상태 설정
    updateMenu(document.body.classList.contains('logged-in'));

    // 로그인/로그아웃 이벤트 리스너
    document.body.addEventListener('login', function() {
        updateMenu(true);
    });

    document.body.addEventListener('logout', function() {
        updateMenu(false);
    });
});