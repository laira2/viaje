document.addEventListener('DOMContentLoaded', function () {
    const posts = [
        // 예시 데이터
        { boardId: 1, planId: 101, userId: 1001, title: '첫 번째 게시글', createAt: '2024-07-05', updatedAt: '2024-07-05', viewCount: 10, likeCount: 5, status: 'approved' },
        { boardId: 2, planId: 102, userId: 1002, title: '두 번째 게시글', createAt: '2024-07-04', updatedAt: '2024-07-05', viewCount: 20, likeCount: 10, status: 'pending' }
        // 실제 데이터로 대체하십시오.
    ];

    const tbody = document.getElementById('board-content');
    posts.forEach(post => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${post.boardId}</td>
            <td>${post.planId}</td>
            <td>${post.userId}</td>
            <td><a href="/board/${post.boardId}">${post.title}</a></td>
            <td>${post.createAt}</td>
            <td>${post.updatedAt}</td>
            <td>${post.viewCount}</td>
            <td>${post.likeCount}</td>
            <td>${post.status}</td>
        `;
        tbody.appendChild(tr);
    });
});
