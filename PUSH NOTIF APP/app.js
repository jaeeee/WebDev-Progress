var requestButton = document.querySelector(".request-button");
var showButton = document.querySelector(".show-button");

function onGranted() {
    requestButton.getElementsByClassName.background = "green";
}

function onDenied() {
    requestButton.getElementsByClassName.background = "red";
}

requestButton.onclick = function() {
    Push.Permission.request(onGranted, onDenied);
}

showButton.onclick = function() {
    Push.create("Hello!", {
        body: "This is a web notification!",
        icon: "icon.png",
        timeout: 5000,
        onClick: function() {
            console.log(this);
        }
    });
};