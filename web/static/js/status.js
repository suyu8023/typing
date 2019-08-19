var limit = 13;
var num=1;
$(document).ready(function() {
    var url = window.location.search.substring(1);
    var str="";
    // console.log(url);

    if (url==null){
        str="uid=";
    }
    else{
        str=url;
    }


    // console.log(str);
    $.ajax({
        type: 'get',
        dataType : "json",
        async:false,
        url : "../home/Status?"+str+"&page=0" + "&limit="+limit,
        success:function(data){
            if (data.length==0){
                var totalCount = 0, showCount = 1, limit = 0;
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
                var totalCount = Number(data[0].count), showCount = 1,limit = 13;
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
        html.push(' <thead><tr><th style="font-size: 20px;text-align: center">ID</th><th style="font-size: 20px;text-align: center">用户名</th><th style="font-size: 20px;text-align: center">昵称</th><th style="font-size: 20px;text-align: center">文章</th><th style="font-size: 20px;text-align: center">打字速度</th><th style="font-size: 20px;text-align: center">正确率</th><th style="font-size: 20px;text-align: center">等级</th><th style="font-size: 20px;text-align: center">打字总数</th><th style="font-size: 20px;text-align: center">打字时间</th><th style="font-size: 20px;text-align: center">提交时间</th><th style="font-size: 20px;text-align: center">速度曲线</th></tr></thead><tbody>');
        $.ajax({
            type: 'get',
            dataType : "json",
            async:false,
            url : "../home/Status?"+str+"&page="+currPage+"&limit="+limit,
            success:function(data){
                // console.log(url);
                if (data.length>0) {
                    for (var i = 0; i < showNum; i++) {
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
                        html.push('<tr><td>' + (limit * (currPage - 1) + i+1) + '</td>');
                        html.push('<td>' + '<a href="UserStatus?uid='+data[i].uid+'"'+'>'+data[ i].username+'</a>' + '</td>');
                        html.push('<td>' + '<a href="UserStatus?uid='+data[i].uid+'"'+'>'+data[ i].nickname+'</a>' + '</td>');
                        html.push('<td>' + '<a href="Practice?mid='+data[i].mid+'"'+'>'+data[ i].mesname +'</a>'+ '</td>');
                        html.push('<td>' + data[i].speed+"KPM" + '</td>');
                        html.push('<td>' + data[ i].correct_rate +"%"+ '</td>');
                        if (data[i].grade==="优秀,继续保持!"||data[i].grade==="优秀,继续保持！") {
                            html.push('<td style="color: red">' + data[ i].grade + '</td>');
                        }
                        else if (data[i].grade==="良好,继续加油!"||data[i].grade==="良好,继续加油！") {
                            html.push('<td style="color: #B30099">' + data[ i].grade + '</td>');
                        }
                        else if (data[i].grade==="及格,继续努力!"||data[i].grade==="及格,继续努力！") {
                            html.push('<td style="color:#090">' + data[ i].grade + '</td>');
                        }
                        else if (data[i].grade==="不及格,加强练习!"||data[i].grade==="不及格,加强练习！") {
                            html.push('<td style="color:#25a6ef">' + data[ i].grade + '</td>');
                        }

                        html.push('<td>' + data[ i].wordnum + '</td>');
                        html.push('<td>' + data[i].wrtime + '</td>');
                        html.push('<td>' + data[ i].time + '</td>');
                        html.push('<td>' + '<a href="Usercontest?sid=' + data[i].sid + '"' + '>' + "速度曲线" + '</a>' + '</td>');
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
