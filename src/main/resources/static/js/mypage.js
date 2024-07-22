document.addEventListener('DOMContentLoaded', function() {
    // 프로필 사진 변경 기능
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

    // 프로필 수정 버튼 이벤트
    const editProfileBtn = document.querySelector('.btn-edit-profile');
    editProfileBtn.addEventListener('click', function(e) {
        e.preventDefault();
        alert('프로필 수정 기능은 아직 구현되지 않았습니다.');
    });

    // 섹션 버튼 기능
    const sectionButtons = document.querySelectorAll('.section-btn');
    const rightContainer = document.querySelector('.right-container');

    sectionButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.stopPropagation();
            const targetId = this.getAttribute('data-target');
            const targetContent = document.getElementById(targetId);

            // 모든 섹션 숨기기
            document.querySelectorAll('.right-container .section-content').forEach(section => {
                section.style.display = 'none';
            });

            // 선택된 섹션 표시
            if (targetContent) {
                targetContent.style.display = 'block';
            }

            // 오른쪽 컨테이너 표시
            rightContainer.style.display = 'block';
        });
    });

    // 화면 클릭 시 오른쪽 컨테이너 숨기기
    document.addEventListener('click', function(e) {
        if (!rightContainer.contains(e.target) && !e.target.classList.contains('section-btn')) {
            rightContainer.style.display = 'none';
        }
    });

    // 오른쪽 컨테이너 클릭 시 이벤트 전파 중지
    rightContainer.addEventListener('click', function(e) {
        e.stopPropagation();
    });
});

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