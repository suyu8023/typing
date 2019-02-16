var fLen = 0;
var poi = 0, ss = 0, li, len1;
var correct_rate = 0;
var wordnum1 = 0;
var wordnum = 0;
var wrongnum = 0;
var wrongnum1 = 0;
var truenum = 0;
var speed = 0;
var times = 0;
var qtime = 0;
var flag = 0;
var arr = new Array();
var word1 = 0;
var wrong1 = 0;
var data="[";

function CheckInput(id) {
    correct_rate = 0;
    wordnum = 0;
    wrongnum = 0;
    truenum = 0;
    speed = 0;
    times = 0;
    var s = id.slice(4);

    var readid = "show" + s;
    var writeid = "read" + s;
    var xread = "xread" + s;
    var qaq = "qaq" + s;
    if (flag === 0) {
        time1();
        countTime();
        speed1();
        flag = 1;
    }
    var hi = document.documentElement.clientHeight;
    li = parseInt(hi / 10);
    var wrstr = document.getElementById(writeid).value;//input
    var str = document.getElementById(xread).innerHTML;//比对
    str = str.slice(1);
    var len = wrstr.length;
    var elem = document.getElementById(readid);//文本
    var dis = str.length;
    var dataset = new Array();
    for (var i = 0; i < dis; i++) {
        dataset[i] = -1;
    }
    var mystr = new Array();
    // if(len>dis+1){
    //     len=dis+1;
    // }
    if (len <= dis) {
        for (var i = 0; i < len; i++) {
            if (wrstr[i] != str[i]) {
                dataset[i] = 1;
            } else {
                dataset[i] = 0;
            }
        }
        for (i = 0; i < str.length; i++) {
            mystr[i] = str[i];
        }
        elem.innerHTML = SetDIV(dataset, mystr);

    } else if ((len == dis + 1 && wrstr[len - 1] != " ") || len > dis + 1) {
        for (var i = 0; i < dis; i++) {
            if (wrstr[i] != str[i]) {
                dataset[i] = 1;
            } else {
                dataset[i] = 0;
            }
        }
        for (i = 0; i < str.length; i++) {
            mystr[i] = str[i];
        }
        elem.innerHTML = SetDIV(dataset, mystr);
    }
    else if (len == dis + 1 && wrstr[len - 1] == " ") {
        // for (var i = 0; i < dis; i++) {
        //     if (wrstr[i] != str[i]) {
        //         dataset[i] = 1;
        //     } else {
        //         dataset[i] = 0;
        //     }
        // }
        // for (i = 0; i < str.length; i++) {
        //     mystr[i] = str[i];
        // }
        // elem.innerHTML = SetDIV(dataset, mystr);
        // for (var i = 0; i < len; i++) {
        //     if (wrstr[i] != str[i]) {
        //         dataset[i] = 1;
        //     } else {
        //         dataset[i] = 0;
        //     }
        // }
        // for (i = 0; i < str.length; i++) {
        //     mystr[i] = str[i];
        // }
        // elem.innerHTML = SetDIV(dataset, mystr);
        // if () {
        s = Number(s);
        var len2 = document.getElementById(qaq).offsetTop - document.documentElement.scrollTop;
        len2 = parseInt(len2);
        if (len2 > li && arr[s] == null) {
            arr[s] = 1;
            ss = 0;
            poi = document.documentElement.scrollTop;
            len1 = len2 - li;
            GoTop(poi);
        }

        s = s + 1;
        var nextid = "read" + s;
        document.getElementById(writeid).disabled = "disabled";
        document.getElementById(nextid).disabled = false;
        document.getElementById(nextid).focus();
        wordnum1 = word1;
        wrongnum1 = wrong1;
        // }
    }

}

function SetDIV(DataSet, MyStr) {

    for (var i = 0; i < DataSet.length; i++) {

        if (DataSet[i] == 1) {
            MyStr[i] = '<span style="background: red;color: white" >' + MyStr[i] + '</span>';
            wrongnum++;
        }
        if (DataSet[i] == 0) {
            MyStr[i] = '<span style="color:#008B45;">' + MyStr[i] + '</span>';
            truenum++;
        }
    }
    word1 = wordnum1 + wrongnum + truenum;
    wrong1 = wrongnum1 + wrongnum;
    var correct_rate = (word1 - wrong1) / word1 * 100;
    document.getElementById("wordsnum").innerHTML = word1;
    document.getElementById("wordsnum1").value = word1;
    document.getElementById("wrongnum").innerHTML = wrong1;
    document.getElementById("wrongnum1").value = wrong1;
    document.getElementById("correct_rate").innerHTML = correct_rate.toFixed(2) + "%";
    document.getElementById("correct_rate1").value = correct_rate.toFixed(2);
    var HTStr = "";
    for (var i = 0; i < MyStr.length; i++) {
        HTStr += MyStr[i];
    }
    return HTStr;
}

var currentPosition, timer;

function GoTop(poi) {
    timer = setInterval("runToTop(poi)", 100);
}

function runToTop(poi) {
    currentPosition = poi;
    ss += 10;
    if (poi === 0) {
        if (ss < len1) {
            window.scrollTo(0, currentPosition + ss);
        }
        else {
            window.scrollTo(0, currentPosition + len1);
            clearInterval(timer);
        }
    }
    else {
        if (ss < len1) {
            window.scrollTo(0, currentPosition + ss);
        }
        else {
            window.scrollTo(0, currentPosition + len1);
            clearInterval(timer);
        }
    }
}

function countTime() {
    ++qtime;
    var leftTime = qtime;
    var d, h, m, s;
    // if (qtime%5==0){
    //     data=data+"{y:"+document.getElementById("speed1").value+"name:'"+qtime+"'},";
    // }
    if (leftTime >= 0) {
        m = Math.floor(leftTime / 60);
        s = Math.floor(leftTime % 60);
    }
    if (qtime%5==0){
        data=data+"{y:"+document.getElementById("speed1").value+",x:"+qtime+"},";
    }
    document.getElementById("times").innerHTML = (Array(2).join(0) + m).slice(-2) + ":" + (Array(2).join(0) + s).slice(-2);
    document.getElementById("times2").value = (Array(2).join(0) + m).slice(-2) + ":" + (Array(2).join(0) + s).slice(-2);
    setTimeout(countTime, 1000);

}

function speed1() {
    var s = (word1 - wrong1) / qtime * 60;
    document.getElementById("speed").innerHTML = s.toFixed(1);
    document.getElementById("speed1").value = s.toFixed(1);
    setTimeout(speed1, 1000);

}

function time1() {
    var date = new Date();
    var now = (Array(2).join(0) + date.getFullYear()).slice(-4) + '-' + (Array(2).join(0) + (date.getMonth() + 1)).slice(-2) + '-' + (Array(2).join(0) + date.getDate()).slice(-2) +
        ' ' + (Array(2).join(0) + date.getHours()).slice(-2) + ':' + (Array(2).join(0) + date.getMinutes()).slice(-2) + ':' + (Array(2).join(0) + date.getSeconds()).slice(-2);
    document.getElementById("times1").value = now;
}
function shunshi() {
    data=data+']';
    document.getElementById("instan").value=data;
}
//
// var move = document.getElementById('move');
// //全局变量
// var x = 0;
// var y = 0;
// var l = 0;
// var t = 0;
// var isDown = false;//当前是否
// var cha=function(e)
// {
// //获取当前鼠标的位置
//     x = e.clientX;
//     y = e.clientY;
// //获取当前元素的top和left
//     l = move.offsetLeft;
//     t = move.offsetTop;
// //修改全局变量
//     isDown = true;
// };
// //绑定鼠标按下事件
// document.getElementById('move').onmousedown =cha;
// //绑定鼠标移动事件
// window.onmousemove = function(e)
// {
// //判断当前鼠标是否按下
//     if(!isDown){return}
//
// //获取当前鼠标位置
//     var _x = e.clientX;
//     var _y = e.clientY;
//
// //计算新的left和top
//     var newL = _x-(x-l);
//     var newT = _y-(y-t);
//
// //修改位置
//     move.style.left = newL+'px';
//     move.style.top = newT+'px';
// };
//
//
//
// //绑定鼠标抬起事件
// move.onmouseup = function(e)
// {
// //修改全局变量
//     isDown = false;
// };
