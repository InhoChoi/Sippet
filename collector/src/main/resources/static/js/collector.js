var userTrack = {
    host: window.location.host,
    href: window.location.href,
    pathName: window.location.pathname,
    referrer: document.referrer
}

function xmlPost(url) {
    var postUrl = url;
    var xhr = new XMLHttpRequest();

    xhr.open("POST", postUrl, true);
    xhr.setRequestHeader("Content-type", "application/json; charset=UTF-8");
    xhr.send(JSON.stringify(userTrack));

    console.log(userTrack.referrer);
}
