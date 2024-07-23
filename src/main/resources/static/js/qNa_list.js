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


    // // 수정 버튼 클릭 시 수정 폼을 보여줍니다.
    // document.querySelectorAll('.edit-form').forEach(form => {
    //     form.style.display = 'block';
    // });

    // 수정 폼 토글 함수
    window.toggleEditForm = (questionsId) => {
        console.log(`Toggling edit form for ID: ${questionsId}`);
        const editForm = document.querySelector(`.edit-form[data-question-id="${questionsId}"]`);
        console.log(`Edit form element found:`, editForm); // 추가된 로그
        if (editForm) {
            const currentDisplay = window.getComputedStyle(editForm).display;
            editForm.style.display = currentDisplay === 'none' ? 'block' : 'none';
        } else {
            console.error(`Edit form with data-question-id="${questionsId}" not found`);
        }
    };

    // 모든 수정 버튼에 클릭 이벤트를 추가합니다
    document.querySelectorAll('.btn-edit').forEach(button => {
        button.addEventListener('click', (event) => {
            const questionId = button.getAttribute('data-question-id'); // data-question-id 값 가져오기
            toggleEditForm(questionId); // 가져온 값을 toggleEditForm 함수에 전달
        });
    });

    // 모든 수정 폼에 제출 이벤트를 추가합니다
    document.querySelectorAll('.edit-form form').forEach(form => {
        form.addEventListener('submit', (event) => {
            event.preventDefault(); // 폼 기본 제출 방지
            const formData = new FormData(form); // 폼 데이터 가져오기
            const questionsId = form.id.split('-')[1]; // 폼 ID에서 questionsId 추출
            formData.append('questionsId', questionsId); // formData에 questionsId 추가

            fetch('/updateQuestion', {
                method: 'POST',
                body: new URLSearchParams(formData),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            })
                .then(response => {
                    if (response.ok) {
                        alert('수정되었습니다!');
                        location.reload(); // 페이지 새로고침
                    } else {
                        return response.text().then(text => { throw new Error(text) });
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('오류가 발생했습니다.');
                });
        });
    });

    // 이벤트 위임을 사용한 클릭 이벤트 처리
    document.addEventListener('click', (event) => {
        if (event.target.classList.contains('btn-answer')) {
            const questionsId = event.target.getAttribute('data-question-id');
            toggleAnswerForm(questionsId);
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
