document.addEventListener('DOMContentLoaded', function() {
    const countries = [
        { code: 'KR', name: '대한민국', flag: '🇰🇷', currency: '₩' },
        { code: 'JP', name: '일본', flag: '🇯🇵', currency: '¥' },
        { code: 'CN', name: '중국', flag: '🇨🇳', currency: '元' },
        { code: 'TW', name: '대만', flag: '🇹🇼', currency: 'NT$' },
        { code: 'VN', name: '베트남', flag: '🇻🇳', currency: '₫' },
        { code: 'TH', name: '태국', flag: '🇹🇭', currency: '฿' },
        { code: 'SG', name: '싱가포르', flag: '🇸🇬', currency: 'S$' },
        { code: 'MY', name: '말레이시아', flag: '🇲🇾', currency: 'RM' },
        { code: 'ID', name: '인도네시아', flag: '🇮🇩', currency: 'Rp' },
        { code: 'PH', name: '필리핀', flag: '🇵🇭', currency: '₱' },
        { code: 'IN', name: '인도', flag: '🇮🇳', currency: '₹' },
        { code: 'FR', name: '프랑스', flag: '🇫🇷', currency: '€' },
        { code: 'GB', name: '영국', flag: '🇬🇧', currency: '£' },
        { code: 'DE', name: '독일', flag: '🇩🇪', currency: '€' },
        { code: 'IT', name: '이탈리아', flag: '🇮🇹', currency: '€' },
        { code: 'ES', name: '스페인', flag: '🇪🇸', currency: '€' },
        { code: 'PT', name: '포르투갈', flag: '🇵🇹', currency: '€' },
        { code: 'NL', name: '네덜란드', flag: '🇳🇱', currency: '€' },
        { code: 'BE', name: '벨기에', flag: '🇧🇪', currency: '€' },
        { code: 'CH', name: '스위스', flag: '🇨🇭', currency: 'CHF' },
        { code: 'AT', name: '오스트리아', flag: '🇦🇹', currency: '€' },
        { code: 'GR', name: '그리스', flag: '🇬🇷', currency: '€' },
        { code: 'SE', name: '스웨덴', flag: '🇸🇪', currency: 'kr' },
        { code: 'NO', name: '노르웨이', flag: '🇳🇴', currency: 'kr' },
        { code: 'DK', name: '덴마크', flag: '🇩🇰', currency: 'kr' },
        { code: 'FI', name: '핀란드', flag: '🇫🇮', currency: '€' },
        { code: 'IS', name: '아이슬란드', flag: '🇮🇸', currency: 'kr' },
        { code: 'PL', name: '폴란드', flag: '🇵🇱', currency: 'zł' },
        { code: 'CZ', name: '체코', flag: '🇨🇿', currency: 'Kč' },
        { code: 'HU', name: '헝가리', flag: '🇭🇺', currency: 'Ft' },
        { code: 'RO', name: '루마니아', flag: '🇷🇴', currency: 'lei' },
        { code: 'BG', name: '불가리아', flag: '🇧🇬', currency: 'лв' },
        { code: 'US', name: '미국', flag: '🇺🇸', currency: '$' },
        { code: 'CA', name: '캐나다', flag: '🇨🇦', currency: 'C$' },
        { code: 'MX', name: '멕시코', flag: '🇲🇽', currency: 'Mex$' },
        { code: 'BR', name: '브라질', flag: '🇧🇷', currency: 'R$' },
        { code: 'AR', name: '아르헨티나', flag: '🇦🇷', currency: 'ARS$' },
        { code: 'CL', name: '칠레', flag: '🇨🇱', currency: 'CLP$' },
        { code: 'PE', name: '페루', flag: '🇵🇪', currency: 'S/.' },
        { code: 'CO', name: '콜롬비아', flag: '🇨🇴', currency: 'COP$' },
        { code: 'AU', name: '호주', flag: '🇦🇺', currency: 'A$' },
        { code: 'NZ', name: '뉴질랜드', flag: '🇳🇿', currency: 'NZ$' },
        { code: 'ZA', name: '남아프리카공화국', flag: '🇿🇦', currency: 'R' },
        { code: 'EG', name: '이집트', flag: '🇪🇬', currency: 'E£' },
        { code: 'MA', name: '모로코', flag: '🇲🇦', currency: 'MAD' },
        { code: 'KE', name: '케냐', flag: '🇰🇪', currency: 'KSh' },
        { code: 'NG', name: '나이지리아', flag: '🇳🇬', currency: '₦' },
        { code: 'AE', name: '아랍에미리트', flag: '🇦🇪', currency: 'AED' },
        { code: 'SA', name: '사우디아라비아', flag: '🇸🇦', currency: 'SAR' },
        { code: 'TR', name: '터키', flag: '🇹🇷', currency: '₺' },
        { code: 'IL', name: '이스라엘', flag: '🇮🇱', currency: '₪' },
        { code: 'KZ', name: '카자흐스탄', flag: '🇰🇿', currency: '₸' },
        { code: 'UZ', name: '우즈베키스탄', flag: '🇺🇿', currency: 'soʻm' },
        { code: 'RU', name: '러시아', flag: '🇷🇺', currency: '₽' },
    ];

    const tags = [
//            { code: 'Recommendation', name: 'Viaje 추천 Plan', colname: '추천'},
            { code: 'active', name: 'active plan', colname: '액티비티' },
            { code: 'taste', name: '맛집 계획', colname: '맛집'},
            { code: 'adventure', name: '쉼 休', colname: '휴식'},
        ];


    const countrySearch = document.getElementById('countrySearch');
    const countryDropdown = document.getElementById('countryDropdown');
    const selectedCountry = document.getElementById('selectedCountry');
    const costInput = document.getElementById('cost');
    const currencySymbol = document.getElementById('currencySymbol');
    const tagsSearch = document.getElementById('tagsSearch');
    const tagsDropdown = document.getElementById('tagsDropdown');
    const selectedTagsContainer = document.getElementById('selectedTagsContainer');
    const selectedTagsInput = document.getElementById('selectedTags');
    const domesticBtn = document.getElementById('domesticBtn');
    const overseasBtn = document.getElementById('overseasBtn');
    const travelTypeInput = document.getElementById('travelType');

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

    function setupDropdown(input, dropdown, items, onSelect) {
        input.addEventListener('focus', () => {
            renderDropdown(items, dropdown, onSelect);
            dropdown.style.display = 'block';
        });

        input.addEventListener('input', () => {
            const filtered = items.filter(item =>
                item.name.toLowerCase().includes(input.value.toLowerCase())
            );
            renderDropdown(filtered, dropdown, onSelect);
            dropdown.style.display = 'block';
        });

        document.addEventListener('click', (e) => {
            if (!input.contains(e.target) && !dropdown.contains(e.target)) {
                dropdown.style.display = 'none';
            }
        });
    }

    function renderDropdown(items, dropdown, onSelect) {
        dropdown.innerHTML = '';
        items.forEach(item => {
            const div = document.createElement('div');
            div.innerHTML = item.flag ? `<span class="country-flag">${item.flag}</span> ${item.name}` : item.name;
            div.addEventListener('click', () => {
                onSelect(item);
                dropdown.style.display = 'none';
            });
            dropdown.appendChild(div);
        });
    }

    function updateCurrencySymbol(item) {
        selectedCountry.value = item.code;
        countrySearch.value = `${item.flag} ${item.name}`;
        currencySymbol.textContent = item.currency;

        // 추가
        if (item.code === 'KR') {
            updateTravelType('domestic');
        } else {
            updateTravelType('overseas');
        }
    }

    function updateTravelType(type) {
        travelTypeInput.value = type;
        if (type === 'domestic') {
            domesticBtn.classList.add('active');
            overseasBtn.classList.remove('active');
        } else {
            overseasBtn.classList.add('active');
            domesticBtn.classList.remove('active');
        }
        updateSelectedTags();
    }
    domesticBtn.addEventListener('click', () => updateTravelType('domestic'));
    overseasBtn.addEventListener('click', () => updateTravelType('overseas'));

    function selectTag(item) {
        if (!selectedTagsList.some(tag => tag.colname === item.colname)) {
            selectedTagsList.push({ colname: item.colname, name: item.name });
            updateSelectedTags();
        }
        tagsSearch.value = '';
    }

    function updateSelectedTags() {
        selectedTagsContainer.innerHTML = '';
        // 추가
        selectedTagsList = selectedTagsList.filter(tag => tag.colname !== '국내' && tag.colname !== '해외');

        // 추가
        const travelTypeTag = {
            colname: travelTypeInput.value === 'domestic' ? '국내' : '해외',
            name: travelTypeInput.value === 'domestic' ? '국내 여행' : '해외 여행'
        };
        selectedTagsList.push(travelTypeTag);

        selectedTagsList.forEach(tag => {
            const tagElement = document.createElement('span');
            tagElement.className = 'selected-tag';
            tagElement.textContent = tag.name;
            // 수정
            if (tag.colname !== '국내' && tag.colname !== '해외') {
                tagElement.onclick = (e) => {
                    e.preventDefault();
                    removeTag(tag.colname);
                };
            }
            selectedTagsContainer.appendChild(tagElement);
        });
        selectedTagsInput.value = selectedTagsList.map(tag => tag.colname).join(',');
    }

    function removeTag(tagColname) {
        selectedTagsList = selectedTagsList.filter(tag => tag.colname !== tagColname);
        updateSelectedTags();
    }

    setupDropdown(countrySearch, countryDropdown, countries, updateCurrencySymbol);
    setupDropdown(tagsSearch, tagsDropdown, tags, selectTag);

    // 새로운 이미지 업로드 관련 코드
    const planImageInput = document.getElementById('planImageInput');
    const certImageInput = document.getElementById('certImageInput');
    const addPlanImageBtn = document.getElementById('addPlanImage');
    const addCertImageBtn = document.getElementById('addCertImage');
    const planImagePreview = document.getElementById('planImagePreview');
    const certImagePreview = document.getElementById('certImagePreview');
    const planImagesInput = document.getElementById('planImages');
    const certImagesInput = document.getElementById('certImages');

    let planImageFiles = [];
    let certImageFiles = [];

    function addImageToPreview(file, previewElement, imageFiles) {
        if (file.type.startsWith('image/')) {
            const reader = new FileReader();
            reader.onload = function(e) {
                const img = document.createElement('img');
                img.src = e.target.result;
                img.style.width = '100px';
                img.style.height = '100px';
                img.style.objectFit = 'cover';
                img.style.margin = '5px';

                const removeBtn = document.createElement('button');
                removeBtn.textContent = '삭제';
                removeBtn.onclick = function() {
                    previewElement.removeChild(img);
                    previewElement.removeChild(removeBtn);
                    const index = imageFiles.indexOf(file);
                    if (index > -1) {
                        imageFiles.splice(index, 1);
                    }
                    updateHiddenInput(imageFiles, previewElement.id === 'planImagePreview' ? planImagesInput : certImagesInput);
                };

                previewElement.appendChild(img);
                previewElement.appendChild(removeBtn);
                imageFiles.push(file);
                updateHiddenInput(imageFiles, previewElement.id === 'planImagePreview' ? planImagesInput : certImagesInput);
            };
            reader.readAsDataURL(file);
        } else {
            alert('이미지 파일만 업로드 가능합니다.');
        }
    }

    function updateHiddenInput(files, hiddenInput) {
        const dataTransfer = new DataTransfer();
        files.forEach(file => {
            dataTransfer.items.add(file);
        });
        hiddenInput.files = dataTransfer.files;
    }

    addPlanImageBtn.addEventListener('click', function() {
        planImageInput.click();
    });

    addCertImageBtn.addEventListener('click', function() {
        certImageInput.click();
    });

    planImageInput.addEventListener('change', function(e) {
        addImageToPreview(e.target.files[0], planImagePreview, planImageFiles);
    });

    certImageInput.addEventListener('change', function(e) {
        addImageToPreview(e.target.files[0], certImagePreview, certImageFiles);
    });

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