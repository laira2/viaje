document.addEventListener('DOMContentLoaded', function() {
    // 프로필 사진 변경 기능을 설정합니다.
    const avatarUpload = document.getElementById('avatarUpload'); // 파일 업로드 input 요소를 선택합니다.
    const avatarPreview = document.getElementById('avatarPreview'); // 프로필 사진 미리보기 이미지 요소를 선택합니다.

    avatarUpload.addEventListener('change', function(event) { // 파일 선택 시 이벤트 리스너를 추가합니다.
        const file = event.target.files[0]; // 업로드된 파일 객체를 가져옵니다.
        if (file) { // 파일이 존재하는 경우
            const reader = new FileReader(); // FileReader 객체를 생성합니다.
            reader.onload = function(e) { // 파일 읽기가 완료되면 실행되는 함수입니다.
                avatarPreview.src = e.target.result; // 읽어온 파일의 결과를 미리보기 이미지의 src로 설정합니다.
                uploadProfileImage(file); // 파일을 서버로 업로드하는 함수를 호출합니다.
            }
            reader.readAsDataURL(file); // 파일을 DataURL 형식으로 읽어옵니다.
        }
    });

    // 프로필 수정 버튼 이벤트를 설정합니다.
    const editProfileBtn = document.querySelector('.btn-edit-profile'); // 프로필 수정 버튼 요소를 선택합니다.
    editProfileBtn.addEventListener('click', function(e) { // 클릭 시 이벤트 리스너를 추가합니다.
        e.preventDefault(); // 기본 동작(링크 이동 등)을 방지합니다.
        alert('프로필 수정 기능은 아직 구현되지 않았습니다.'); // 버튼 클릭 시 알림을 표시합니다.
    });

    // 섹션 버튼 기능을 설정합니다.
    const sectionButtons = document.querySelectorAll('.section-btn'); // 모든 섹션 버튼을 선택합니다.
    const infoHeader = document.getElementById('infoHeader'); // 오른쪽 컨테이너 상단의 제목 요소를 선택합니다.
    const rightContainer = document.querySelector('.right-container'); // 오른쪽 컨테이너를 선택합니다.

    sectionButtons.forEach(button => { // 각 버튼에 대해 반복합니다.
        button.addEventListener('click', function(e) { // 버튼 클릭 시 이벤트 리스너를 추가합니다.
            e.stopPropagation(); // 클릭 이벤트가 부모 요소로 전파되지 않도록 합니다.
            const targetId = this.getAttribute('data-target'); // 클릭된 버튼의 'data-target' 속성 값을 가져옵니다.
            const targetContent = document.getElementById(targetId); // 'data-target' 속성에 해당하는 섹션 콘텐츠를 선택합니다.
            const buttonTitle = this.getAttribute('data-title'); // 버튼의 'data-title' 속성 값을 가져옵니다.

            // 오른쪽 영역 상단에 버튼 이름을 표시합니다.
            infoHeader.innerText = buttonTitle; // 상단 제목에 버튼 제목을 설정합니다.

            // 모든 섹션 콘텐츠를 숨깁니다.
            document.querySelectorAll('.right-container .section-content').forEach(section => {
                section.style.display = 'none'; // 모든 섹션 콘텐츠의 display 속성을 'none'으로 설정하여 숨깁니다.
            });

            // 선택된 섹션 콘텐츠를 표시합니다.
            if (targetContent) {
                targetContent.style.display = 'block'; // 선택된 섹션 콘텐츠의 display 속성을 'block'으로 설정하여 표시합니다.
            }

            // 오른쪽 컨테이너를 표시합니다.
            rightContainer.style.display = 'block'; // 오른쪽 컨테이너의 display 속성을 'block'으로 설정하여 표시합니다.
        });
    });

    // 화면 클릭 시 오른쪽 컨테이너를 숨깁니다.
    document.addEventListener('click', function(e) { // 문서 전체에 클릭 이벤트 리스너를 추가합니다.
        if (!rightContainer.contains(e.target) && !e.target.classList.contains('section-btn')) { // 클릭된 요소가 오른쪽 컨테이너 내부가 아니고 섹션 버튼도 아닌 경우
            rightContainer.style.display = 'none'; // 오른쪽 컨테이너의 display 속성을 'none'으로 설정하여 숨깁니다.
        }
    });

    // 오른쪽 컨테이너 클릭 시 이벤트 전파를 중지합니다.
    rightContainer.addEventListener('click', function(e) { // 오른쪽 컨테이너에 클릭 이벤트 리스너를 추가합니다.
        e.stopPropagation(); // 클릭 이벤트의 전파를 중지하여 오른쪽 컨테이너 내부 클릭 시 부모 요소로 전파되지 않도록 합니다.
    });
});

// 프로필 이미지를 서버로 업로드하는 함수입니다.
function uploadProfileImage(file) {
    const formData = new FormData(); // FormData 객체를 생성합니다.
    formData.append('profileImage', file); // 파일을 'profileImage'라는 이름으로 FormData 객체에 추가합니다.

    fetch('/api/upload-profile-image', { // 서버로 POST 요청을 보냅니다.
        method: 'POST', // HTTP 메서드를 POST로 설정합니다.
        body: formData // 요청 본문에 FormData 객체를 설정합니다.
    })
        .then(response => response.json()) // 서버 응답을 JSON 형식으로 변환합니다.
        .then(data => {
            console.log('프로필 이미지가 성공적으로 업로드되었습니다:', data); // 성공적으로 업로드된 경우 콘솔에 메시지를 표시합니다.
        })
        .catch(error => {
            console.error('프로필 이미지 업로드 중 오류 발생:', error); // 오류 발생 시 콘솔에 오류 메시지를 표시합니다.
        });
}
