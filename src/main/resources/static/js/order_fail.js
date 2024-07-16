function triggerFailAnimation() {
    const failureSound = document.getElementById("failure-sound");
    failureSound.play();

    createEmojiBurst();
}

function createEmojiBurst() {
    const emojiContainer = document.getElementById("emoji-container");
    const emojis = ['😢', '😞', '💔', '😡', '😖', '⚠️'];

    function createEmoji() {
        const emoji = document.createElement("div");
        emoji.classList.add("emoji");
        emoji.textContent = emojis[Math.floor(Math.random() * emojis.length)];

        const posX = Math.random() * window.innerWidth;
        const posY = Math.random() * window.innerHeight;

        emoji.style.left = `${posX}px`;
        emoji.style.top = `${posY}px`;

        emojiContainer.appendChild(emoji);

        setTimeout(() => {
            emojiContainer.removeChild(emoji);
        }, 2000);
    }

    // 초기 이모지 생성
    for (let i = 0; i < 30; i++) {
        createEmoji();
    }

    // 주기적으로 새로운 이모지 생성
    setInterval(createEmoji, 200);
}

// 페이지 로드 시 애니메이션 시작
window.onload = () => {
    triggerFailAnimation();

    const chargeButton = document.getElementById("charge-button");
    chargeButton.addEventListener("click", () => {
        window.location.href = "/static/templates/charge.html";
    });
};
