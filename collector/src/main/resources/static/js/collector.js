var userTrack = {
    host: window.location.host,
    href: window.location.href,
    pathName: window.location.pathname,
    referrer: document.referrer
};

var TrackLogger = {
    init: function (url) {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("POST", url);
        xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xmlhttp.send(JSON.stringify(userTrack));
    }
};