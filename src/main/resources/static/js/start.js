document.addEventListener('DOMContentLoaded', function() {
    const bounceButton = document.getElementById('bounce-button');
    const slideUpContent = document.getElementById('slide-up-content');
    const slideUpInnerContent = document.getElementById('slide-up-inner-content');

    if (bounceButton && slideUpContent && slideUpInnerContent) {
        bounceButton.addEventListener('click', function() {
            console.log('Button clicked');
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
        });
    } else {
        console.error('Button, slide-up content, or inner content not found');
    }
});
