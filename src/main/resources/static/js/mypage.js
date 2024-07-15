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
                // 여기에 서버로 파일을 업로드하는 코드를 추가할 수 있습니다.
                uploadProfileImage(file);
            }
            reader.readAsDataURL(file);
        }
    });

    // 프로필 수정 버튼 이벤트
    const editProfileBtn = document.querySelector('.btn-edit-profile');
    editProfileBtn.addEventListener('click', function(e) {
        e.preventDefault();
        // 프로필 수정 모달 또는 페이지로 이동하는 로직
        alert('프로필 수정 기능은 아직 구현되지 않았습니다.');
    });

    // 내 여행 플랜 가져오기
    fetchMyTravelPlans();

    // 추천 여행지 가져오기
    fetchRecommendedPlans();
    const toggleButtons = document.querySelectorAll('.toggle-btn');

        toggleButtons.forEach(button => {
            button.addEventListener('click', function() {
                const section = this.closest('.travel-section');
                const content = section.querySelector('.section-content');
                content.classList.toggle('active');

                // 아이콘 변경
                const icon = this.querySelector('i');
                if (content.classList.contains('active')) {
                    icon.classList.replace('fa-chevron-down', 'fa-chevron-up');
                } else {
                    icon.classList.replace('fa-chevron-up', 'fa-chevron-down');
                }
            });
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
            // 필요한 경우 추가 처리
        })
        .catch(error => {
            console.error('프로필 이미지 업로드 중 오류 발생:', error);
        });
}

function fetchMyTravelPlans() {
    fetch('/api/my-travel-plans')
        .then(response => response.json())
        .then(plans => {
            const myTravelPlansContainer = document.getElementById('myTravelPlans');
            myTravelPlansContainer.innerHTML = ''; // 기존 내용 초기화
            plans.forEach(plan => {
                myTravelPlansContainer.appendChild(createPlanElement(plan, true));
            });
        })
        .catch(error => {
            console.error('내 여행 플랜을 가져오는 중 오류 발생:', error);
        });
}

function fetchRecommendedPlans() {
    fetch('/api/recommended-plans')
        .then(response => response.json())
        .then(plans => {
            const recommendedPlansContainer = document.getElementById('recommendedPlans');
            recommendedPlansContainer.innerHTML = ''; // 기존 내용 초기화
            plans.forEach(plan => {
                recommendedPlansContainer.appendChild(createPlanElement(plan, false));
            });
        })
        .catch(error => {
            console.error('추천 여행지를 가져오는 중 오류 발생:', error);
        });
}

function createPlanElement(plan, isMyPlan) {
    const planDiv = document.createElement('div');
    planDiv.className = 'plan';

    const img = document.createElement('img');
    img.src = plan.imageUrl;
    img.alt = plan.title;
    planDiv.appendChild(img);

    const contentDiv = document.createElement('div');
    contentDiv.className = 'plan-content';

    const title = document.createElement('h3');
    title.textContent = plan.title;
    contentDiv.appendChild(title);

    if (isMyPlan) {
        const date = document.createElement('p');
        date.textContent = `출발일: ${plan.departureDate}`;
        contentDiv.appendChild(date);

        const status = document.createElement('p');
        status.textContent = `상태: ${plan.status}`;
        contentDiv.appendChild(status);
    } else {
        const description = document.createElement('p');
        description.textContent = plan.description;
        contentDiv.appendChild(description);
    }

    const button = document.createElement('a');
    button.href = '#';
    button.className = 'btn';
    button.textContent = isMyPlan ? '상세 보기' : '자세히 보기';
    contentDiv.appendChild(button);

    planDiv.appendChild(contentDiv);

    return planDiv;
}