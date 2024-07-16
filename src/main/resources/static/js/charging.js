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
        fetch('/adminCharging')
            .then(response => response.json())
            .then(data => {
                // 총 포인트 업데이트
                totalPointsElement.textContent = calculateTotalPoints(data);
            })
            .catch(error => console.error('총 포인트를 가져오는 중 오류 발생:', error));
        }

    function fetchChargeHistory() {
        fetch('/adminCharging')
            .then(response => response.json())
            .then(data => {
                // 충전 내역 렌더링
                renderChargeHistory(data);
            })
            .catch(error => console.error('충전 내역을 가져오는 중 오류 발생:', error));
        }

    function calculateTotalPoints(transactions) {
        // 포인트 트랜잭션 데이터에서 총 포인트 계산
        let totalPoints = 0;
        transactions.forEach(transaction => {
            totalPoints += parseInt(transaction.amount);
        });
        return totalPoints.toLocaleString();
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