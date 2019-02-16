$(document).ready(function() {
    var photo = document.getElementById("photo");
    var max=14;
    var min=1;
    var li = Math.floor(Math.random()*(max-min+1)+min);
    photo.style.backgroundImage="url('/static/image/"+li+".jpg"+"')";
    // photo.style.backgroundSize="cover";

});