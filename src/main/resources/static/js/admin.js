// admin.js
document.addEventListener('DOMContentLoaded', function() {
    const historyDisplay = document.getElementById('historyDisplay');
    const buttons = document.querySelectorAll('.management-btn');

    buttons.forEach(button => {
        button.addEventListener('click', function() {
            const type = this.id.replace('Btn', '');
            displayHistory(type);
        });
    });

    function displayHistory(type) {
        // 여기서 실제로는 서버에서 데이터를 가져와야 합니다.
        // 지금은 예시 데이터를 사용합니다.
        let content = `<h3>${type} 내역</h3><ul>`;

        for(let i = 1; i <= 5; i++) {
            content += `<li>샘플 ${type} 내역 ${i}</li>`;
        }

        content += '</ul>';
        historyDisplay.innerHTML = content;
    }
});

function openNewPage(url) {
    window.open(url, '_blank');
}