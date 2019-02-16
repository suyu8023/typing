var limit = 13;
var num = 1;
var usern;
$(document).ready(function () {
    $.ajax({
        type: 'get',
        dataType: "text",
        async: false,
        url: "../../LoginServlet?name=",
        success: function (data) {
            var str = data.toString();
            str = str.split('-');
            console.log(str[0] + "-=" + str[1] + "-=" + str[2] + "-=" + str[3] + "-=" + str[4]);
            document.getElementById("user").innerHTML = str[1];
            document.getElementById("user11").innerHTML = str[1];
            usern = str[1];
        }
    });
    var url = window.location.search.substring(1);
    // var str = url.split('&');
    // console.log(str[0]);
    // console.log(str[1]);
    $.ajax({
        type: 'get',
        dataType: "json",
        async: false,
        url: "../../home/Contest/status?" + url + "&page=0" + "&limit=" + limit,
        success: function (data) {
            if (data.length == 0) {
                var totalCount = Number(data.length), showCount = 1, limit = 0;
                createTable(1, limit, totalCount);
                $('#page').pagination({
                    totalCount: totalCount,
                    showCount: showCount,
                    limit: limit,
                    data: data,
                    callback: function (curr, limit, totalCount) {
                        createTable(curr, limit, totalCount);
                    }
                });
            }
            else {
                var totalCount = Number(data.length), showCount = 1, limit = 13;
                // console.log(url.substring(0, 3));
                if (url.substring(0, 3) == "cid") {
                    for (var i = 0; i < data.length; i++) {
                        // console.log(data[i].username,usern);
                        var ss = document.getElementById("user11").innerHTML;
                        // console.log(ss);
                        var flag = 0;

                        if (ss.replace(/(^\s*)|(\s*$)/g, "") == data[i].username.replace(/(^\s*)|(\s*$)/g, "")) {
                            document.getElementById("person").innerHTML = "您的最好成绩为: 第" + (i + 1) + "名";
                            // document.getElementById("person").value ="qwerty";
                            flag = 1;
                            break;
                        }
                        if (flag == 0) {
                            document.getElementById("person").innerHTML = "抱歉，您没有参加此次比赛！";
                        }

                    }
                }
                else{
                    document.getElementById("person").innerHTML = "";

                }
                createTable(1, limit, totalCount);
                $('#page').pagination({
                    totalCount: totalCount,
                    showCount: showCount,
                    limit: limit,
                    data: data,
                    callback: function (curr, limit, totalCount) {
                        createTable(curr, limit, totalCount);
                    }
                });
            }
        }
    });

    function createTable(currPage, limit, total) {
        var html = [], showNum = limit;
        // console.log("=-=="+currPage);
        if (total - (currPage * limit) < 0) showNum = total - ((currPage - 1) * limit);
        html.push(' <table class="table table-hover table-bordered" style="text-align: center" cellspacing="0" width="100%">');
        html.push(' <thead><tr><th style="font-size: 20px;text-align: center">排名</th><th style="font-size: 20px;text-align: center">用户名</th><th style="font-size: 20px;text-align: center">昵称</th><th style="font-size: 20px;text-align: center">文章</th><th style="font-size: 20px;text-align: center">打字速度</th><th style="font-size: 20px;text-align: center">正确率</th><th style="font-size: 20px;text-align: center">等级</th><th style="font-size: 20px;text-align: center">打字总数</th><th style="font-size: 20px;text-align: center">打字时间</th><th style="font-size: 20px;text-align: center">提交时间</th><th style="font-size: 20px;text-align: center">速度曲线</th></tr></thead><tbody>');
        $.ajax({
            type: 'get',
            dataType: "json",
            async: false,
            url: "../../home/Contest/status?" + url + "&page=" + currPage + "&limit=" + limit,
            success: function (data) {
                // console.log(url);
                if (data.length > 0) {
                    for (var i = 0; i < showNum; i++) {
                        var ss = document.getElementById("user11").innerHTML;

                        // console.log(ss.replace(/(^\s*)|(\s*$)/g, ""),data[i].username.replace(/(^\s*)|(\s*$)/g, ""));
                        if (ss.replace(/(^\s*)|(\s*$)/g, "") == data[i].username.replace(/(^\s*)|(\s*$)/g, "")) {
                            html.push('<tr bgcolor="#C1FFC1"><td>' + (limit * (currPage - 1) + i + 1) + '</td>');

                        }
                        else {
                            html.push('<tr><td>' + (limit * (currPage - 1) + i + 1) + '</td>');
                        }
                        // html.push('<tr><td>' + data[limit * (currPage - 1) + i].sid + '</td>');
                        // html.push('<td>' + '<a href="UserStatus?uid='+data[limit * (currPage - 1) + i].uid+'"'+'>'+data[limit * (currPage - 1) + i].username+'</a>' + '</td>');
                        // html.push('<td>' + '<a href="UserStatus?uid='+data[limit * (currPage - 1) + i].uid+'"'+'>'+data[limit * (currPage - 1) + i].nickname+'</a>' + '</td>');
                        // html.push('<td>' + '<a href="Practice?mid='+data[limit * (currPage - 1) + i].mid+'"'+'>'+data[limit * (currPage - 1) + i].mesname +'</a>'+ '</td>');
                        // html.push('<td>' + data[limit * (currPage - 1) + i].speed + '</td>');
                        // html.push('<td>' + data[limit * (currPage - 1) + i].correct_rate + '</td>');
                        // html.push('<td>' + data[limit * (currPage - 1) + i].grade + '</td>');
                        // html.push('<td>' + data[limit * (currPage - 1) + i].wordnum + '</td>');
                        // html.push('<td>' + data[limit * (currPage - 1) + i].wrtime + '</td>');
                        // html.push('<td>' + data[limit * (currPage - 1) + i].time + '</td>');
                        // html.push('<tr><td>' + (limit * (currPage - 1) + i+1) + '</td>');
                        html.push('<td>' + '<a href="ContestUser?uid=' + data[i].uid + "&cid=" + data[i].cid + '"' + '>' + data[i].username + '</a>' + '</td>');
                        html.push('<td>' + '<a href="ContestUser?uid=' + data[i].uid + "&cid=" + data[i].cid + '"' + '>' + data[i].nickname + '</a>' + '</td>');
                        html.push('<td>' + '<a href="../Practice?mid=' + data[i].mid + '"' + '>' + data[i].mesname + '</a>' + '</td>');
                        html.push('<td>' + data[i].speed + "KPM" + '</td>');
                        html.push('<td>' + data[i].correct_rate + "%" + '</td>');
                        if (data[i].grade === "优秀,继续保持!") {
                            html.push('<td style="color: red">' + data[i].grade + '</td>');
                        }
                        else if (data[i].grade === "良好,继续加油!") {
                            html.push('<td style="color: #B30099">' + data[i].grade + '</td>');
                        }
                        else if (data[i].grade === "及格,继续努力!") {
                            html.push('<td style="color:#090">' + data[i].grade + '</td>');
                        }
                        else if (data[i].grade === "不及格,加强练习!") {
                            html.push('<td style="color:#25a6ef">' + data[i].grade + '</td>');
                        }
                        html.push('<td>' + data[i].wordnum + '</td>');
                        html.push('<td>' + data[i].wrtime + '</td>');
                        html.push('<td>' + data[i].time + '</td>');
                        html.push('<td>' + '<a href="../Usercontest?csid=' + data[i].csid + '"' + '>' + "速度曲线" + '</a>' + '</td>');
                        html.push('</tr>');
                    }
                    num++;
                }
                html.push('</tbody></table>');
                var mainObj = $('#list');
                mainObj.empty();
                mainObj.html(html.join(''));
            }
        })
    }

});

