document.addEventListener('DOMContentLoaded', function() {
    // 프로필 사진 변경 기능을 설정합니다.
    const avatarUpload = document.getElementById('avatarUpload');
    const avatarPreview = document.getElementById('avatarPreview');

    avatarUpload.addEventListener('change', function(event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                avatarPreview.src = e.target.result;
                uploadProfileImage(file);
            }
            reader.readAsDataURL(file);
        }
    });

    // 프로필 수정 버튼 이벤트를 설정합니다.
    const editProfileBtn = document.querySelector('.btn-edit-profile');
    editProfileBtn.addEventListener('click', function(e) {
        e.preventDefault();
        alert('프로필 수정 기능은 아직 구현되지 않았습니다.');
    });

    // 섹션 버튼 기능을 설정합니다.
    const sectionButtons = document.querySelectorAll('.section-btn');
    const infoHeader = document.getElementById('infoHeader');
    const rightContainer = document.querySelector('.right-container');

    sectionButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.stopPropagation();
            const targetId = this.getAttribute('data-target');
            const targetContent = document.getElementById(targetId);
            const buttonTitle = this.getAttribute('data-title');

            infoHeader.innerText = buttonTitle;

            document.querySelectorAll('.right-container .section-content').forEach(section => {
                section.style.display = 'none';
            });

            if (targetContent) {
                targetContent.style.display = 'block';
            }

            rightContainer.style.display = 'block';
        });
    });

    // 좋아요 버튼 클릭 시 섹션 표시 기능 추가
    const likeButton = document.querySelector('.btn-like');
    likeButton.addEventListener('click', function(e) {
        e.stopPropagation();
        const targetId = 'likedPlans';
        const targetContent = document.getElementById(targetId);
        const buttonTitle = '좋아요';

        infoHeader.innerText = buttonTitle;

        document.querySelectorAll('.right-container .section-content').forEach(section => {
            section.style.display = 'none';
        });

        if (targetContent) {
            targetContent.style.display = 'block';
        }

        rightContainer.style.display = 'block';
    });

    // 화면 클릭 시 오른쪽 컨테이너를 숨깁니다.
    document.addEventListener('click', function(e) {
        if (!rightContainer.contains(e.target) && !e.target.classList.contains('section-btn') && !e.target.classList.contains('btn-like')) {
            rightContainer.style.display = 'none';
        }
    });

    // 오른쪽 컨테이너 클릭 시 이벤트 전파를 중지합니다.
    rightContainer.addEventListener('click', function(e) {
        e.stopPropagation();
    });
});

// 프로필 이미지를 서버로 업로드하는 함수입니다.
function uploadProfileImage(file) {
    const formData = new FormData();
    formData.append('profileImage', file);

    fetch('/api/upload-profile-image', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            console.log('프로필 이미지가 성공적으로 업로드되었습니다:', data);
        })
        .catch(error => {
            console.error('프로필 이미지 업로드 중 오류 발생:', error);
        });
}
