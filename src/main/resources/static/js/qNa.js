document.addEventListener('DOMContentLoaded', () => {
    const newPostForm = document.getElementById('newPostForm');
    const postTitle = document.getElementById('postTitle');
    const postContent = document.getElementById('postContent');

    // 폼 제출 이벤트 핸들러
    newPostForm.addEventListener('submit', (event) => {
        event.preventDefault();

        const title = postTitle.value.trim();
        const content = postContent.value.trim();

        if (title && content) {
            const newPost = {
                title: title,
                content: content,
                date: new Date().toLocaleString()
            };

            // 로컬 스토리지에 게시글 저장
            const posts = JSON.parse(localStorage.getItem('posts')) || [];
            posts.push(newPost);
            localStorage.setItem('posts', JSON.stringify(posts));

            // 글 작성 후 목록 페이지로 이동
            window.location.href = 'qNa_list.html';
        }
    });
});
