// main.js

document.addEventListener('DOMContentLoaded', function() {
    const videoContainer = document.getElementById('background-video');
    const videoSource = document.getElementById('video-source');
    const scrollIcon = document.getElementById('scroll-icon');
    const hamburgerIcon = document.getElementById('hamburger-icon');
    const menuContent = document.getElementById('menu-content');
    const menuInnerContent = document.getElementById('menu-inner-content');

    console.log('Main.js: Document is ready');

    if (videoContainer && videoSource && scrollIcon && hamburgerIcon && menuContent && menuInnerContent) {
        console.log('Main.js: Elements found');

        // 비디오 파일 목록
        const videos = [
            '/static/videos/ex1.mp4',
            '/static/videos/ex2.mp4',
            '/static/videos/ex3.mp4',
            '/static/videos/ex4.mp4'
        ];

        // 버튼 이미지 파일 목록
        const images = [
            '/static/images/book.png',
            '/static/images/down-arrow.png',
            '/static/images/free.png',
            '/static/images/cloud2.png'
        ];

        // 무작위로 비디오 파일 선택
        const randomVideo = videos[Math.floor(Math.random() * videos.length)];

        // 무작위로 이미지 파일 선택
        const randomImage = images[Math.floor(Math.random() * images.length)];

        // 비디오 소스 설정
        videoSource.src = randomVideo;
        videoContainer.load();

        // 이미지 소스 설정
        scrollIcon.src = randomImage;

        // 햄버거 아이콘 클릭 이벤트 추가
        hamburgerIcon.addEventListener('click', function(event) {
            event.stopPropagation();
            fetch('/static/templates/menu.html')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(data => {
                    menuInnerContent.innerHTML = data;
                    document.querySelector('.menu-container').classList.add('active');
                    // 슬라이드 업 창 닫기
                    const slideUpContent = document.getElementById('slide-up-content');
                    if (slideUpContent) {
                        slideUpContent.classList.remove('active');
                    }
                })
                .catch(error => console.error('Error loading menu.html:', error));
        });

        // 오른쪽 아무곳이나 클릭하면 메뉴 숨기기
        document.body.addEventListener('click', function(event) {
            const menuContainer = document.querySelector('.menu-container');
            if (menuContainer && menuContainer.classList.contains('active') && !menuContent.contains(event.target)) {
                menuContainer.classList.remove('active');
            }
        });

    } else {
        console.error('Main.js: Required elements not found');
    }
});