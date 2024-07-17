document.addEventListener('DOMContentLoaded', function() {

    const costInput = document.getElementById('cost');
    let selectedTagsList = [];

    const startDate = document.getElementById('startDate');
    const endDate = document.getElementById('endDate');

    function validateDates() {
        if (startDate.value && endDate.value) {
            if (new Date(endDate.value) < new Date(startDate.value)) {
                alert('여행 종료일은 시작일 이후여야 합니다.');
                endDate.value = '';
            }
        }
    }

    startDate.addEventListener('change', validateDates);
    endDate.addEventListener('change', validateDates);


    // planDetail 관련 코드
    let planDetailCount = document.querySelectorAll('.plan-detail').length;

    function addPlanDetail() {
        const container = document.getElementById('planDetailsContainer');
        const template = document.getElementById('planDetailTemplate');
        const newPlanDetail = template.content.cloneNode(true).querySelector('.plan-detail');

        newPlanDetail.innerHTML = newPlanDetail.innerHTML.replace(/\[INDEX\]/g, planDetailCount);

        const newDeleteBtn = newPlanDetail.querySelector('.removePlanDetailBtn');
        newDeleteBtn.addEventListener('click', function() {
            removePlanDetail(this);
        });

        container.appendChild(newPlanDetail);
        planDetailCount++;
    }

    function removePlanDetail(button) {
        const planDetail = button.closest('.plan-detail');
        planDetail.parentNode.removeChild(planDetail);
        updateIndexes();
    }

    function updateIndexes() {
        const container = document.getElementById('planDetailsContainer');
        const planDetails = container.getElementsByClassName('plan-detail');
        for (let i = 0; i < planDetails.length; i++) {
            // 추가된 부분: label 요소도 업데이트
            const labels = planDetails[i].getElementsByTagName('label');
            const inputs = planDetails[i].getElementsByTagName('input');
            const textareas = planDetails[i].getElementsByTagName('textarea');

            // 변경된 부분: label의 for 속성 업데이트
            for (let label of labels) {
                label.setAttribute('for', label.getAttribute('for').replace(/\[\d+\]/, '[' + i + ']'));
            }
            // 변경된 부분: input과 textarea의 id와 name 속성 업데이트
            for (let input of inputs) {
                input.id = input.id.replace(/\[\d+\]/, '[' + i + ']');
                input.name = input.name.replace(/\[\d+\]/, '[' + i + ']');
            }
            for (let textarea of textareas) {
                textarea.id = textarea.id.replace(/\[\d+\]/, '[' + i + ']');
                textarea.name = textarea.name.replace(/\[\d+\]/, '[' + i + ']');
            }
        }
    }

    // planDetail 관련 이벤트 리스너 추가
    document.getElementById('addPlanDetailBtn').addEventListener('click', addPlanDetail);
    document.querySelectorAll('.removePlanDetailBtn').forEach(btn => {
        btn.addEventListener('click', function() {
            removePlanDetail(this);
        });
    });

    // 폼 제출 이벤트 리스너
    document.querySelector('form').addEventListener('submit', function(e) {
        // 필요한 유효성 검사 로직 추가
    });
});