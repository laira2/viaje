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
            const editForm = document.getElementById(`editForm-${questionsId}`);
            if (editForm) {
                editForm.style.display = editForm.style.display === 'none' ? 'block' : 'none';
            } else {
                console.error(`Edit form with id editForm-${questionsId} not found`);
            }
        };

        // 수정 폼 제출 함수
        window.submitEditForm = (event, questionsId) => {
            event.preventDefault(); // 폼 제출 기본 동작 방지

            console.log("submitEditForm called with questionsId: ", questionsId);
            const form = document.getElementById(`form-edit-${questionsId}`);
            if (!form) {
                console.error(`Form with id form-edit-${questionsId} not found`);
                return;
            }

            const titleInput = form.querySelector('input[name="title"]');
            const contentsTextarea = form.querySelector('textarea[name="contents"]');

            if (!contentsTextarea) {
                console.error(`Textarea with name "contents" not found in form ${form.id}`);
                return;
            }

            const title = titleInput ? titleInput.value : '';
            const contents = contentsTextarea.value;

            console.log("Title: ", title);
            console.log("Contents: ", contents);

            fetch(`/updateQuestion`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    questionsId: questionsId,
                    title: title,
                    contents: contents
                })
            }).then(response => {
                if (response.ok) {
                    console.log("Request succeeded");
                    location.reload();
                } else {
                    console.log("Request failed with status: ", response.status);
                    alert('질문 수정에 실패했습니다.');
                }
            }).catch(error => {
                console.error('Error:', error);
                alert('질문 수정 중 오류가 발생했습니다.');
            });
        };

        // 삭제 함수
        function deleteQuestion(questionsId) {
            if (confirm('이 질문을 삭제하시겠습니까?')) {
                fetch(`/deleteQuestion?questionsId=${questionsId}`, {
                    method: 'DELETE', // DELETE 메서드로 변경
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then(response => {
                    if (response.ok) {
                        location.reload();
                    } else {
                        alert('질문 삭제에 실패했습니다.');
                    }
                }).catch(error => {
                    console.error('Error:', error);
                    alert('질문 삭제 중 오류가 발생했습니다.');
                });
            }
        }

        // 수정 버튼 클릭 시 수정 폼을 보여줍니다.
        document.querySelectorAll('.btn-edit').forEach(button => {
            button.addEventListener('click', function () {
                const questionsId = this.getAttribute('data-question-id');
                toggleEditForm(questionsId);
            });
        });

        // 수정 폼 제출 시
        document.querySelectorAll('.edit-form form').forEach(form => {
            form.addEventListener('submit', function (e) {
                e.preventDefault();
                const questionsId = this.querySelector('input[name="questionsId"]').value;
                submitEditForm(questionsId);
            });
        });

        // 삭제 버튼 클릭 시
        document.querySelectorAll('.btn-delete').forEach(button => {
            button.addEventListener('click', function () {
                const questionsId = this.getAttribute('data-question-id');
                deleteQuestion(questionsId);
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
//
//    // 로컬 스토리지에서 질문 목록 가져오기
//    let questions = JSON.parse(localStorage.getItem('questions')) || [];
//


//    // 질문 목록 렌더링 함수
//    function renderQuestions() {
//        questionList.innerHTML = '';
//        questions.forEach((question, index) => {
//            const questionCard = document.createElement('div');
//            questionCard.classList.add('question-card');
//            questionCard.innerHTML = `
//                <h3 class="question-title">${question.title}</h3>
//                <div class="question-meta">
//                    <span class="user-name">${question.userName}</span>
//                    <span class="question-status">${question.status}</span>
//                </div>
//                <div class="question-details">
//                    <p class="question-content">${question.content}</p>
//                    <p class="question-date">${question.date}</p>
//                </div>
//                <button class="btn-edit" onclick="editQuestion(${index})">수정</button>
//                <button class="btn-delete" onclick="deleteQuestion(${index})">삭제</button>
//                <button class="btn-answer" onclick="toggleAnswerForm(${index})">답변</button>
//                ${renderAnswers(question.answers)}
//                <div class="answer-form" id="answerForm${index}">
//                    <textarea id="answerContent${index}" rows="3" placeholder="답변을 입력하세요"></textarea>
//                    <button onclick="submitAnswer(${index})">답변 제출</button>
//                </div>
//            `;
//            questionList.appendChild(questionCard);
//        });
//    }
//
//    // 답변 렌더링 함수
//    function renderAnswers(answers) {
//        if (!answers || answers.length === 0) return '';
//        return answers.map((answer, index) => `
//            <div class="answer-content">
//                <p>답변 ${index + 1}: ${answer.content}</p>
//                <p class="answer-date">${answer.date}</p>
//                <button class="btn-reply" onclick="toggleReplyForm(${index})">답글</button>
//                <div class="reply-form" id="replyForm${index}" style="display:none;">
//                    <textarea id="replyContent${index}" rows="2" placeholder="답글을 입력하세요"></textarea>
//                    <button onclick="submitReply(${index})">답글 제출</button>
//                </div>
//            </div>
//        `).join('');
//    }
//
//    // 질문 추가 함수
//    function addQuestion(title, content) {
//        const newQuestion = {
//            title: title,
//            content: content,
//            userName: '사용자', // 실제 사용자 이름으로 대체해야 합니다
//            status: '확인중',
//            date: new Date().toLocaleString(),
//            answers: []
//        };
//        questions.push(newQuestion);
//        localStorage.setItem('questions', JSON.stringify(questions));
//        renderQuestions();
//    }
//
//    // 질문 수정 함수
//    window.editQuestion = (index) => {
//        const question = questions[index];
//        const newTitle = prompt('새 제목을 입력하세요:', question.title);
//        const newContent = prompt('새 내용을 입력하세요:', question.content);
//        if (newTitle && newContent) {
//            question.title = newTitle;
//            question.content = newContent;
//            localStorage.setItem('questions', JSON.stringify(questions));
//            renderQuestions();
//        }
//    };
//
//    // 질문 삭제 함수
//    window.deleteQuestion = (index) => {
//        if (confirm('이 질문을 삭제하시겠습니까?')) {
//            questions.splice(index, 1);
//            localStorage.setItem('questions', JSON.stringify(questions));
//            renderQuestions();
//        }
//    };
//
//     // 답변 폼 토글 함수
//    window.toggleAnswerForm = (questionId) => {
//        const answerForm = document.getElementById(`answerForm-${questionId}`);
//        if (answerForm) {
//            answerForm.style.display = answerForm.style.display === 'none' ? 'block' : 'none';
//        } else {
//            console.error(`Answer form with id answerForm-${questionId} not found`);
//        }
//    };
//
//    // 답변 제출 함수
//    window.submitAnswer = (index) => {
//        const answerContent = document.getElementById(`answerContent${index}`).value;
//        if (answerContent) {
//            const newAnswer = {
//                content: answerContent,
//                date: new Date().toLocaleString(),
//                replies: []
//            };
//            if (!questions[index].answers) {
//                questions[index].answers = [];
//            }
//            questions[index].answers.push(newAnswer);
//            localStorage.setItem('questions', JSON.stringify(questions));
//            renderQuestions();
//        }
//    };
//
    // 답글 폼 토글 함수
    window.toggleReplyForm = () => {
        const replyForm = document.getElementById(`replyForm`);
        replyForm.style.display = replyForm.style.display === 'none' ? 'block' : 'none';
    };
//
//    // 답글 제출 함수
//    window.submitReply = (index) => {
//        const replyContent = document.getElementById(`replyContent${index}`).value;
//        if (replyContent) {
//            const newReply = {
//                content: replyContent,
//                date: new Date().toLocaleString()
//            };
//            questions[index].answers.push(newReply);
//            localStorage.setItem('questions', JSON.stringify(questions));
//            renderQuestions();
//        }
//    };
//
//    // 폼 제출 이벤트 리스너
//    questionForm.addEventListener('submit', (e) => {
//        e.preventDefault();
//        const title = document.getElementById('title').value;
//        const content = document.getElementById('content').value;
//        if (title && content) {
//            addQuestion(title, content);
//            questionForm.reset();
//        }
//    });
//
//    // 초기 렌더링
//    renderQuestions();
});