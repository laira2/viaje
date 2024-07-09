document.addEventListener('DOMContentLoaded', function() {
    const countries = [
        // 아시아
        { code: 'KR', name: '대한민국', flag: '🇰🇷' },
        { code: 'JP', name: '일본', flag: '🇯🇵' },
        { code: 'CN', name: '중국', flag: '🇨🇳' },
        { code: 'TW', name: '대만', flag: '🇹🇼' },
        { code: 'VN', name: '베트남', flag: '🇻🇳' },
        { code: 'TH', name: '태국', flag: '🇹🇭' },
        { code: 'SG', name: '싱가포르', flag: '🇸🇬' },
        { code: 'MY', name: '말레이시아', flag: '🇲🇾' },
        { code: 'ID', name: '인도네시아', flag: '🇮🇩' },
        { code: 'PH', name: '필리핀', flag: '🇵🇭' },
        { code: 'IN', name: '인도', flag: '🇮🇳' },

        // 유럽
        { code: 'FR', name: '프랑스', flag: '🇫🇷' },
        { code: 'GB', name: '영국', flag: '🇬🇧' },
        { code: 'DE', name: '독일', flag: '🇩🇪' },
        { code: 'IT', name: '이탈리아', flag: '🇮🇹' },
        { code: 'ES', name: '스페인', flag: '🇪🇸' },
        { code: 'PT', name: '포르투갈', flag: '🇵🇹' },
        { code: 'NL', name: '네덜란드', flag: '🇳🇱' },
        { code: 'BE', name: '벨기에', flag: '🇧🇪' },
        { code: 'CH', name: '스위스', flag: '🇨🇭' },
        { code: 'AT', name: '오스트리아', flag: '🇦🇹' },
        { code: 'GR', name: '그리스', flag: '🇬🇷' },

        // 북유럽
        { code: 'SE', name: '스웨덴', flag: '🇸🇪' },
        { code: 'NO', name: '노르웨이', flag: '🇳🇴' },
        { code: 'DK', name: '덴마크', flag: '🇩🇰' },
        { code: 'FI', name: '핀란드', flag: '🇫🇮' },
        { code: 'IS', name: '아이슬란드', flag: '🇮🇸' },

        // 동유럽
        { code: 'PL', name: '폴란드', flag: '🇵🇱' },
        { code: 'CZ', name: '체코', flag: '🇨🇿' },
        { code: 'HU', name: '헝가리', flag: '🇭🇺' },
        { code: 'RO', name: '루마니아', flag: '🇷🇴' },
        { code: 'BG', name: '불가리아', flag: '🇧🇬' },

        // 북아메리카
        { code: 'US', name: '미국', flag: '🇺🇸' },
        { code: 'CA', name: '캐나다', flag: '🇨🇦' },
        { code: 'MX', name: '멕시코', flag: '🇲🇽' },

        // 남아메리카
        { code: 'BR', name: '브라질', flag: '🇧🇷' },
        { code: 'AR', name: '아르헨티나', flag: '🇦🇷' },
        { code: 'CL', name: '칠레', flag: '🇨🇱' },
        { code: 'PE', name: '페루', flag: '🇵🇪' },
        { code: 'CO', name: '콜롬비아', flag: '🇨🇴' },

        // 오세아니아
        { code: 'AU', name: '호주', flag: '🇦🇺' },
        { code: 'NZ', name: '뉴질랜드', flag: '🇳🇿' },

        // 아프리카
        { code: 'ZA', name: '남아프리카공화국', flag: '🇿🇦' },
        { code: 'EG', name: '이집트', flag: '🇪🇬' },
        { code: 'MA', name: '모로코', flag: '🇲🇦' },
        { code: 'KE', name: '케냐', flag: '🇰🇪' },
        { code: 'NG', name: '나이지리아', flag: '🇳🇬' },

        // 중동
        { code: 'AE', name: '아랍에미리트', flag: '🇦🇪' },
        { code: 'SA', name: '사우디아라비아', flag: '🇸🇦' },
        { code: 'TR', name: '터키', flag: '🇹🇷' },
        { code: 'IL', name: '이스라엘', flag: '🇮🇱' },

        // 중앙아시아
        { code: 'KZ', name: '카자흐스탄', flag: '🇰🇿' },
        { code: 'UZ', name: '우즈베키스탄', flag: '🇺🇿' },

        // 러시아
        { code: 'RU', name: '러시아', flag: '🇷🇺' },
    ];

    const tags = [
        { code: 'nature', name: '자연' },
        { code: 'city', name: '도시' },
        { code: 'culture', name: '문화' },
        { code: 'relax', name: '휴식' },
        { code: 'adventure', name: '모험' }
        // 더 많은 태그를 추가할 수 있습니다
    ];

    const currencies = [
        { code: 'KRW', name: '원화' },
        { code: 'USD', name: '달러' },
        { code: 'JPY', name: '엔화' },
        { code: 'EUR', name: '유로' },
        { code: 'CNY', name: '위안화' }
        // 더 많은 화폐 단위를 추가할 수 있습니다
    ];

    function setupDropdown(inputId, dropdownId, items) {
        const input = document.getElementById(inputId);
        const dropdown = document.getElementById(dropdownId);
        const hiddenInput = document.getElementById(`selected${capitalizeFirstLetter(inputId)}`);

        input.addEventListener('focus', () => {
            renderDropdown(items, dropdown, hiddenInput, input);
            dropdown.style.display = 'block';
        });

        input.addEventListener('input', () => {
            const filtered = items.filter(item =>
                item.name.toLowerCase().includes(input.value.toLowerCase())
            );
            renderDropdown(filtered, dropdown, hiddenInput, input);
            dropdown.style.display = 'block';
        });

        document.addEventListener('click', (e) => {
            if (!input.contains(e.target) && !dropdown.contains(e.target)) {
                dropdown.style.display = 'none';
            }
        });
    }

    function renderDropdown(items, dropdown, hiddenInput, input) {
        dropdown.innerHTML = '';
        items.forEach(item => {
            const div = document.createElement('div');
            div.innerHTML = item.flag ? `<span class="country-flag">${item.flag}</span> ${item.name}` : item.name;
            div.addEventListener('click', () => {
                hiddenInput.value = item.code;
                input.value = item.name;
                dropdown.style.display = 'none';
            });
            dropdown.appendChild(div);
        });
    }

    function populateSelect(selectId, items) {
        const select = document.getElementById(selectId);
        items.forEach(item => {
            const option = document.createElement('option');
            option.value = item.code;
            option.textContent = item.name;
            select.appendChild(option);
        });
    }

    function capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }

    setupDropdown('countrySearch', 'countryDropdown', countries);
    setupDropdown('tagsSearch', 'tagsDropdown', tags);
    populateSelect('currency', currencies);
});
