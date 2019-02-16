$(document).ready(function () {
    var url = window.location.search.substring(1);
    $.ajax({
        type: 'get',
        dataType: "json",
        async: false,
        url: "../Message?" + url + "&status=2",
        success: function (data) {
            w(data[0].mid, data[0].name, data[0].diff, data[0].message);
            document.getElementById("mes_name").innerHTML = data[0].name;
            document.getElementById("mes_name1").value = data[0].name;
            document.getElementById("mid").value = data[0].mid;
            // document.getElementById("scid").value = data[0].scid;
        }
    });
});

function w(mid, name, diff, message) {
    // console.log(message);
    // var srt = new Array();
    var data = new Array();
    // srt = message.split("\n");
    // for (var i=0;i<srt.length;i++){
    message = message.replace(/\n/g," ");
    message = message.replace(/\s+/g, ' ');

    // console.log(message);
    var str = new Array();
    str = message.split(" ");
    var str1 = " ";
    for (var k = 0; k < str.length; k++) {
        if (k % 15 == 0 && k != 0) {
            data.push(str1);
            str1 = " "+str[k];
        }
        else {
            if (k==0){
                str1=str1+str[k];
            }
            else {
                str1 += " " + str[k];
            }
        }
    }
    data.push(str1);
    // }
    var html = [];
    for (var i = 0; i < data.length; i++) {
        html.push('<div class="read" id=qaq' + i + '>');
        html.push('<div class="write" id=show' + i + '>' + data[i] + '</div>');
        html.push('<div hidden class="write" id=xread' + i + '>' + data[i] + '</div>');
        if (i == 0) {
            html.push('<input onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false" class="write" id=read' + i + ' oninput="CheckInput(this.id) ">');
        }
        else {
            html.push('<input onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false" class="write" id=read' + i + ' disabled="disabled" oninput="CheckInput(this.id)">');
        }
        html.push('</div>')
    }
    var mainObj = $('#list');
    mainObj.empty();
    mainObj.html(html.join(''));
}
