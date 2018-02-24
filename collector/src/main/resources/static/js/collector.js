var userTrack = {
    host: window.location.host,
    href: window.location.href,
    pathName: window.location.pathname,
    referrer: document.referrer
}

function xmlPost() {
    var xhr = new XMLHttpRequest();

    xhr.open("POST", 'http://localhost:8083/api/track/', true);
    xhr.setRequestHeader("Content-type", "application/json; charset=UTF-8");
    xhr.send(JSON.stringify(userTrack));
}

xmlPost();