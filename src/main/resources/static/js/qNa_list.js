document.addEventListener('DOMContentLoaded', () => {
    const boardContainer = document.getElementById('boardContainer');

    // 로컬 스토리지에서 게시글 목록 가져오기
    const posts = JSON.parse(localStorage.getItem('posts')) || [];

    // 게시글 목록 렌더링 함수
    function renderPosts() {
        boardContainer.innerHTML = '<h2>문의 게시판 목록</h2>'; // 기존 제목 유지
        posts.forEach((post, index) => {
            const postItem = document.createElement('div');
            postItem.classList.add('post-item');
            postItem.innerHTML = `
                <h3>${post.title}</h3>
                <p>${post.content}</p>
                <button class="btn-edit" onclick="editPost(${index})">수정</button>
                <button class="btn-delete" onclick="deletePost(${index})">삭제</button>
            `;
            boardContainer.appendChild(postItem);
        });
    }

    // 게시글 수정 함수
    window.editPost = (index) => {
        const title = prompt('새 제목을 입력하세요:', posts[index].title);
        const content = prompt('새 내용을 입력하세요:', posts[index].content);

        if (title && content) {
            posts[index].title = title;
            posts[index].content = content;
            localStorage.setItem('posts', JSON.stringify(posts));
            renderPosts();
        }
    };

    // 게시글 삭제 함수
    window.deletePost = (index) => {
        if (confirm('이 게시글을 삭제하시겠습니까?')) {
            posts.splice(index, 1);
            localStorage.setItem('posts', JSON.stringify(posts));
            renderPosts();
        }
    };

    // 페이지 로드 시 게시글 렌더링
    renderPosts();
});
