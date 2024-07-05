document.addEventListener('DOMContentLoaded', function () {
    const menuContainer = document.getElementById('menuContainer');
    const teamName = document.querySelector('.team-name');

    // 메뉴 토글 버튼 클릭 이벤트 (이미 있다고 가정)
    document.querySelector('.menu-toggle-btn').addEventListener('click', function() {
        menuContainer.classList.toggle('active');

        // 메뉴 상태 변경 시 로고 스타일 유지
        teamName.style.letterSpacing = 'normal';
        teamName.style.fontKerning = 'normal';
        teamName.style.textRendering = 'optimizeLegibility';
    });

    const user = document.body.dataset.user;
    if (user) {
        document.querySelectorAll('.menu-item').forEach(function (item) {
            item.style.display = 'block';
        });
        document.querySelector('.login').style.display = 'none';
        document.querySelector('.join').style.display = 'none';
    }
});