document.addEventListener('DOMContentLoaded', () => {
    const postList = document.getElementById('postList');
    const newPostForm = document.getElementById('newPostForm');
    const postTitle = document.getElementById('postTitle');
    const postContent = document.getElementById('postContent');

    const posts = [];

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

            posts.push(newPost);
            renderPosts();
            postTitle.value = '';
            postContent.value = '';
        }
    });

    function renderPosts() {
        postList.innerHTML = posts.map((post) => `
            <div class="post-item">
                <h3>${post.title}</h3>
                <p>${post.content}</p>
                <small>${post.date}</small>
            </div>
        `).join('');
    }
});