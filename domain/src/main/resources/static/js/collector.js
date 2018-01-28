var userTrack = {
    host: window.location.host,
    href: window.location.href,
    pathName: window.location.pathname,
    referrer: document.referrer
}
$.ajax({
  type: "POST",
  contentType : "application/json; charset=UTF-8",
  url: "/api/track/",
  data: JSON.stringify(userTrack),
  dataType: "json"
});