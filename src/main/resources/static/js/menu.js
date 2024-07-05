document.addEventListener('DOMContentLoaded', function () {
    const user = document.body.dataset.user;
    if (user) {
        document.querySelectorAll('.menu-item').forEach(function (item) {
            item.style.display = 'block';
        });
        document.querySelector('.login').style.display = 'none';
        document.querySelector('.join').style.display = 'none';
    }
});
