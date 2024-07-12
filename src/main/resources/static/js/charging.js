document.addEventListener('DOMContentLoaded', function() {
    const totalPointsElement = document.getElementById('totalPoints');
    const chargeBtns = document.querySelectorAll('.charge-btn');
    const chargeHistoryElement = document.getElementById('chargeHistory');

    // 총 포인트 가져오기
    fetchTotalPoints();

    // 충전 내역 가져오기
    fetchChargeHistory();

    // 충전 버튼 이벤트 리스너
    chargeBtns.forEach(btn => {
        btn.addEventListener('click', function() {
            const amount = this.dataset.amount;
            chargePoints(amount);
        });
    });

    function fetchTotalPoints() {
        // API에서 총 포인트를 가져오는 로직
        // 예시: API 호출 대신 임시 데이터 사용
        setTimeout(() => {
            totalPointsElement.textContent = '150,000';
        }, 500);
    }

    function fetchChargeHistory() {
        // API에서 충전 내역을 가져오는 로직
        // 예시: API 호출 대신 임시 데이터 사용
        const mockData = [
            { date: '2024-03-15', amount: '10,000' },
            { date: '2024-03-10', amount: '30,000' },
            { date: '2024-03-05', amount: '50,000' },
        ];

        setTimeout(() => {
            renderChargeHistory(mockData);
        }, 500);
    }

    function renderChargeHistory(data) {
        chargeHistoryElement.innerHTML = '';
        data.forEach(item => {
            const historyItem = document.createElement('div');
            historyItem.className = 'history-item';
            historyItem.innerHTML = `
                <span class="date">${item.date}</span>
                <span class="amount">${item.amount}P</span>
            `;
            chargeHistoryElement.appendChild(historyItem);
        });
    }

    function chargePoints(amount) {
        // 실제로는 여기에 API 호출 로직이 들어갑니다
        alert(`${amount}P 충전 요청이 완료되었습니다.`);
        // 충전 후 총 포인트와 충전 내역을 새로고침합니다
        fetchTotalPoints();
        fetchChargeHistory();
    }
});