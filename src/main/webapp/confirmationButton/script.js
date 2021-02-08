
var btnOpen = document.querySelector('.js-open');
var btnClose = document.querySelector('.js-close');
var modal = document.querySelector('.js-modal');

function toggleClasses() {
  btnOpen.classList.toggle('is-active');
  modal.classList.toggle('is-active');
}

// Open nav when clicking sandwich button
btnOpen.addEventListener('click', function() {
  toggleClasses();
});

// Open nav when clicking sandwich button
btnClose.addEventListener('click', function() {
  toggleClasses();
});