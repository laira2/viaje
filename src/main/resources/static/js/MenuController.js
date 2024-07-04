// 메뉴 항목 변경 로직
function updateMenuItems() {
    // 서버에서 세션 정보를 가져오는 방식으로 변경
    const userSession = JSON.parse(document.querySelector('body').dataset.user);

    // 로그인 여부에 따라 메뉴 항목 보이기/숨기기
    const loginItem = document.querySelector('.menu-item.login');
    const joinItem = document.querySelector('.menu-item.join');
    const myPageItem = document.querySelector('.menu-item.mypage');
    const cartItem = document.querySelector('.menu-item.cart');
    const productsItem = document.querySelector('.menu-item.products');
    const boardItem = document.querySelector('.menu-item.board');
    const logoutItem = document.querySelector('.menu-item.logout');

    if (userSession) {
        loginItem.style.display = 'none';
        joinItem.style.display = 'none';
        myPageItem.style.display = 'block';
        cartItem.style.display = 'block';
        productsItem.style.display = 'block';
        boardItem.style.display = 'block';
        logoutItem.style.display = 'block';
    } else {
        loginItem.style.display = 'block';
        joinItem.style.display = 'block';
        myPageItem.style.display = 'none';
        cartItem.style.display = 'none';
        productsItem.style.display = 'none';
        boardItem.style.display = 'none';
        logoutItem.style.display = 'none';
    }
}

// 페이지 로드 시 메뉴 항목 업데이트
window.addEventListener('load', updateMenuItems);

// 로그인/로그아웃 시 메뉴 항목 업데이트
document.addEventListener('login', updateMenuItems);
document.addEventListener('logout', updateMenuItems);