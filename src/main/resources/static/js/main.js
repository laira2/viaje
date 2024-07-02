document.addEventListener('DOMContentLoaded', function() {
    const videoContainer = document.getElementById('background-video');
    const videoSource = document.getElementById('video-source');
    const scrollIcon = document.getElementById('scroll-icon');
    const hamburgerIcon = document.getElementById('hamburger-icon');
    const bounceButton = document.getElementById('bounce-button');
    const slideUpContent = document.getElementById('slide-up-content');
    const slideUpInnerContent = document.getElementById('slide-up-inner-content');
    const menuContent = document.getElementById('menu-content');
    const menuInnerContent = document.getElementById('menu-inner-content');

    console.log('Document is ready');

    if (bounceButton && slideUpContent && slideUpInnerContent) {
        console.log('Elements found:', bounceButton, slideUpContent, slideUpInnerContent);

        bounceButton.addEventListener('click', function(event) {
            console.log('Start button clicked');
            fetch('/static/templates/start.html') // 경로 수정
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(data => {
                    console.log('Data fetched:', data);
                    slideUpInnerContent.innerHTML = data;
                    slideUpContent.classList.add('active');
                })
                .catch(error => console.error('Error loading start.html:', error));
            event.stopPropagation(); // 이벤트 버블링 중지
        });

        document.body.addEventListener('click', function(event) {
            if (slideUpContent.classList.contains('active')) {
                console.log('Body clicked');
                slideUpContent.classList.remove('active');
            }
        });

        slideUpContent.addEventListener('click', function(event) {
            event.stopPropagation(); // 슬라이드 업된 창 클릭 시 이벤트 버블링 중지
        });

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
            event.stopPropagation(); // 이벤트 버블링 중지
            fetch('/static/templates/menu.html') // menu.html 경로
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(data => {
                    menuInnerContent.innerHTML = data;
                    document.querySelector('.menu-container').classList.add('active');
                })
                .catch(error => console.error('Error loading menu.html:', error));
        });

        // 오른쪽 아무곳이나 클릭하면 메뉴 숨기기
        document.body.addEventListener('click', function(event) {
            if (document.querySelector('.menu-container').classList.contains('active') && !menuContent.contains(event.target)) {
                document.querySelector('.menu-container').classList.remove('active');
            }
        });

    } else {
        console.error('Button, slide-up content, or inner content not found');
    }
});
