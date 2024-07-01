document.addEventListener('DOMContentLoaded', function() {
    const bounceButton = document.getElementById('bounce-button');
    const slideUpContent = document.getElementById('slide-up-content');
    const slideUpInnerContent = document.getElementById('slide-up-inner-content');

    if (bounceButton && slideUpContent && slideUpInnerContent) {
        bounceButton.addEventListener('click', function(event) {
            console.log('Start button clicked');
            fetch('/templates/start.html')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(data => {
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
    } else {
        console.error('Button, slide-up content, or inner content not found');
    }
});
