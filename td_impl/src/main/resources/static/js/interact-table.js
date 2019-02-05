function showInfo(interact) {
    var exInfo = interact.nextElementSibling;

    if (exInfo.style.display === 'none') {
        exInfo.style.display = '';

    }
    else {
        exInfo.style.display = 'none';
    }
}

