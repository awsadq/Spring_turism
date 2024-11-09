let currentSlide = 0;

function showSlide(index) {
    const slides = document.querySelectorAll('.slide');
    const totalSlides = slides.length;

    if (index >= totalSlides) {
        currentSlide = 0;
    } else if (index < 0) {
        currentSlide = totalSlides - 1;
    } else {
        currentSlide = index;
    }

    const slideContainer = document.querySelector('.slides');
    slideContainer.style.transform = `translateX(-${currentSlide * 100}%)`;
}

function changeSlide(direction) {
    showSlide(currentSlide + direction);
}

showSlide(currentSlide);

setInterval(() => {
    changeSlide(1);
}, 5000);



// const bannerImages = [
//     '/static/images/18photo.jpg',
//     '/static/images/2photo.jpg',
//     '/static/images/3photo.jpg'
// ];
// let currentBannerIndex = 0;
//
// function changeBannerBackground() {
//     const banner = document.querySelector('.banner img');
//     banner.classList.add('hidden'); // Сначала скрываем текущее изображение
//
//     setTimeout(() => {
//         currentBannerIndex = (currentBannerIndex + 1) % bannerImages.length;
//         banner.src = bannerImages[currentBannerIndex];
//         banner.classList.remove('hidden'); // Показываем новое изображение
//     }, 1000); // Время, совпадающее с transition из CSS
// }
//
// // Автоматическая смена фона каждые 5 секунд
// setInterval(changeBannerBackground, 5000);
//
// document.getElementById('filter-form').addEventListener('submit', function(event) {
//     event.preventDefault();
//     const country = document.getElementById('country').value;
//     const tourType = document.getElementById('tourType').value;
//     const duration = document.getElementById('duration').value;
//
//     fetch(`/api/tours?country=${country}&tourType=${tourType}&duration=${duration}`)
//         .then(response => response.json())
//         .then(data => {
//             const toursContainer = document.getElementById('filtered-tours');
//             toursContainer.innerHTML = ''; // Очищаем предыдущие результаты
//
//             data.forEach(tour => {
//                 const tourCard = document.createElement('div');
//                 tourCard.classList.add('card');
//                 tourCard.innerHTML = `
//                     <img src="${tour.imageUrl}" alt="${tour.title}">
//                     <h2>${tour.title}</h2>
//                     <p>${tour.description}</p>
//                     <p><strong>Страна:</strong> ${tour.country}</p>
//                     <p><strong>Длительность:</strong> ${tour.duration} дней</p>
//                     <p><strong>Тип тура:</strong> ${tour.type}</p>
//                     <a href="${tour.link}" class="btn">Подробнее</a>
//                 `;
//                 toursContainer.appendChild(tourCard);
//             });
//         });
// });

