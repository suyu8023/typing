function ti(status) {
    var date = new Date();
    var now = (Array(2).join(0)+date.getFullYear()).slice(-4) + '-' + (Array(2).join(0)+(date.getMonth()+1)).slice(-2) + '-' + (Array(2).join(0)+date.getDate()).slice(-2)+
        ' ' + (Array(2).join(0)+date.getHours()).slice(-2) + ':' + (Array(2).join(0)+date.getMinutes()).slice(-2) + ':' + (Array(2).join(0)+date.getSeconds()).slice(-2);
    if (status==1) {
        document.getElementById("rel_time").value = now;
        document.getElementById("upd_time").value = now;
    }
    else if (status==2){
        document.getElementById("upd_time").value = now;
    }
}