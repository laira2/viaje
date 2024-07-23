// addQuestion 함수 정의
const addQuestion = (title, content) => {
    console.log('Adding question:', title, content);

    fetch('/qnaPost', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            title: title,
            contents: content
        })
    })
        .then(response => response.text()) // 응답을 텍스트로 처리
        .then(text => {
            console.log('Response Text:', text);
            // 페이지 새로고침
            location.reload();
        })
        .catch(error => console.error('Error:', error));
};

document.addEventListener('DOMContentLoaded', () => {
    const questionList = document.getElementById('questionList');
    const questionForm = document.getElementById('questionForm');

    // 답변 폼 토글 함수
    window.toggleAnswerForm = (questionId) => {
        const answerForm = document.getElementById(`answerForm-${questionId}`);
        if (answerForm) {
            answerForm.style.display = answerForm.style.display === 'none' ? 'block' : 'none';
        } else {
            console.error(`Answer form with id answerForm-${questionId} not found`);
        }
    };

    // 수정 폼 토글 함수
    window.toggleEditForm = (questionsId) => {
        console.log(`Toggling edit form for ID: ${questionsId}`);
        const editForm = document.querySelector(`.edit-form[data-question-id="${questionsId}"]`);
        if (editForm) {
            const currentDisplay = window.getComputedStyle(editForm).display;
            console.log(`Current display style: ${currentDisplay}`);
            editForm.style.display = currentDisplay === 'none' ? 'block' : 'none';
            console.log(`New display style: ${editForm.style.display}`);
        } else {
            console.error(`Edit form with data-question-id="${questionsId}" not found`);
        }
    };


    // 수정 버튼 클릭 시 폼을 토글하는 함수
    function toggleEditForm(questionId) {
        // 모든 폼을 숨깁니다
        document.querySelectorAll('.edit-form').forEach(form => {
            form.style.display = 'none';
        });

        // 클릭된 버튼에 해당하는 폼을 보이게 합니다
        const editForm = document.querySelector(`.edit-form[data-question-id="${questionId}"]`);
        if (editForm) {
            // 현재 폼이 숨겨져 있다면 보이게 하고, 보이고 있다면 숨깁니다
            if (editForm.style.display === 'none' || editForm.style.display === '') {
                editForm.style.display = 'block';
            } else {
                editForm.style.display = 'none';
            }
        }
    }

    // 모든 수정 버튼에 클릭 이벤트를 추가합니다
    document.querySelectorAll('.btn-edit').forEach(button => {
        button.addEventListener('click', (event) => {
            const questionId = button.getAttribute('data-question-id');
            toggleEditForm(questionId);
        });
    });

    // 취소 폼 토글 함수
    window.cancelEditForm = (questionsId) => {
        const editForm = document.querySelector(`.edit-form[data-question-id="${questionsId}"]`);
        if (editForm) {
            editForm.style.display = 'none';
        } else {
            console.error(`Edit form with data-question-id="${questionsId}" not found`);
        }
    };

    // 이벤트 위임을 사용한 클릭 이벤트 처리
    document.addEventListener('click', (event) => {
        if (event.target.classList.contains('btn-answer')) {
            const questionId = event.target.getAttribute('data-question-id');
            toggleAnswerForm(questionId);
        }
    });

    // 디버깅: 모든 answer-form 요소 출력
    const allAnswerForms = document.querySelectorAll('.answer-form');
    console.log('All answer form elements:', allAnswerForms);

    // 폼 제출 이벤트 리스너
    questionForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const title = document.getElementById('title').value;
        const content = document.getElementById('content').value;
        if (title && content) {
            addQuestion(title, content);
            questionForm.reset();
        }
    });
});
