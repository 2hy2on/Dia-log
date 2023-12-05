  function setActive(element) {
            // 모든 li 요소에서 active 클래스 제거
            var lis = document.querySelectorAll('.container li');
            lis.forEach(function (li) {
                li.classList.remove('active');
            });

            // 클릭한 li 요소에 active 클래스 추가
            element.classList.add('active');
        }