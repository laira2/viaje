document.addEventListener('DOMContentLoaded', function() {
    const videoContainer = document.getElementById('background-video');
    const videoSource = document.getElementById('video-source');
    const scrollIcon = document.getElementById('scroll-icon');
    const hamburgerIcon = document.getElementById('hamburger-icon');
    const menuOverlay = document.getElementById('menu-overlay');
    const slideUpContent = document.getElementById('slide-up-content');

    console.log('Main.js: Document is ready');

    if (videoContainer && videoSource && scrollIcon && hamburgerIcon && menuOverlay && slideUpContent) {
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
        videoContainer.play().catch(error => console.error("Video play failed:", error));

        // 이미지 소스 설정
        scrollIcon.src = randomImage;

        // 햄버거 아이콘 클릭 이벤트
        hamburgerIcon.addEventListener('click', function(event) {
            event.stopPropagation();
            menuOverlay.classList.toggle('active');
            if (slideUpContent.classList.contains('active')) {
                slideUpContent.classList.remove('active');
            }
        });

        // 스크롤 아이콘 클릭 이벤트
        scrollIcon.addEventListener('click', function() {
            slideUpContent.classList.toggle('active');
            if (menuOverlay.classList.contains('active')) {
                menuOverlay.classList.remove('active');
            }
        });

        // 메뉴 외부 클릭 시 닫기
        document.addEventListener('click', function(event) {
            if (!menuOverlay.contains(event.target) && event.target !== hamburgerIcon) {
                menuOverlay.classList.remove('active');
            }
        });

        // 하단 페이지 외부 클릭 시 닫기
        document.addEventListener('click', function(event) {
            if (!slideUpContent.contains(event.target) && event.target !== scrollIcon) {
                slideUpContent.classList.remove('active');
            }
        });

    } else {
        console.error('Main.js: Required elements not found');
    }
});