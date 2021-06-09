$(document).ready(function() {
    setInterval(function() {
        cache_clear()
    }, 5000);
});

function cache_clear() {
    window.location.reload();
}