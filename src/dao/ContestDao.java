package dao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContestDao {
    public void addContest(String contests_name, String autor, int mid, String mesname, String begin_time, String end_time, String create_time, int times) throws Exception {
        Connection conn = JdbcUtil.getConnection();
        String sql = "insert  into contest(contests_name,autor,mid,mesname,begin_time,end_time,create_time,times) values(?,?,?,?,?,?,?,?)";
        int rs = 0;
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);
            stm.setString(1, contests_name);
            stm.setString(2, autor);
            stm.setInt(3, mid);
            stm.setString(4, mesname);
            stm.setString(5, begin_time);
            stm.setString(6, end_time);
            stm.setString(7, create_time);
            stm.setInt(8, times);
            rs = stm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addconteststatus(int cid, int uid, int mid, String username, String nickname, String message, double speed, double correct_rate, String grade, int wordnum, String time, String time1, String instan) throws Exception {
        Connection conn = JdbcUtil.getConnection();
        String sql = "insert  into conteststatus (cid,uid,mid,username,nickname,mesname,speed,correct_rate,grade,wordnum,time,wrtime,instan) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int rs = 0;
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, cid);
            stm.setInt(2, uid);
            stm.setInt(3, mid);
            stm.setString(4, username);
            stm.setString(5, nickname);
            stm.setString(6, message);
            stm.setDouble(7, speed);
            stm.setDouble(8, correct_rate);
            stm.setString(9, grade);
            stm.setInt(10, wordnum);
            stm.setString(11, time);
            stm.setString(12, time1);
            stm.setString(13, instan);
            rs = stm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public JSONArray get_contest(int page, int limit) throws Exception {
        Connection conn = JdbcUtil.getConnection();
        int li = page * limit - limit;
        String sql;
        if (page == 0) {
            sql = "select * from contest";
        } else {
            sql = "select * from contest limit " + li + "," + limit;
        }

        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonobj = new JSONObject();
            while (rs.next()) {
                jsonobj.put("cid", rs.getInt("cid"));
                jsonobj.put("contests_name", rs.getString("contests_name"));
                jsonobj.put("autor", rs.getString("autor"));
                jsonobj.put("mid", rs.getString("mid"));
                jsonobj.put("begin_time", rs.getString("begin_time"));
                jsonobj.put("end_time", rs.getString("end_time"));
                jsonobj.put("create_time", rs.getString("create_time"));
                jsonobj.put("times", rs.getString("times"));
                jsonarray.add(jsonobj);
            }
            rs.close();
            return jsonarray;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }

    public JSONArray get_contestinfo(int cid) throws Exception {
        Connection conn = JdbcUtil.getConnection();
//        int li = page * limit - limit;
        String sql;
//        if (page == 0) {
//            sql = "select * from contest";
//        } else {
//            sql = "select * from contest limit " + li + "," + page * limit;
//        }
        sql = "select * from contest where cid=" + cid;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonobj = new JSONObject();
            while (rs.next()) {
                jsonobj.put("cid", rs.getInt("cid"));
                jsonobj.put("contests_name", rs.getString("contests_name"));
                jsonobj.put("autor", rs.getString("autor"));
                jsonobj.put("mid", rs.getString("mid"));
                jsonobj.put("mesname", rs.getString("mesname"));
                jsonobj.put("begin_time", rs.getString("begin_time"));
                jsonobj.put("end_time", rs.getString("end_time"));
                jsonobj.put("create_time", rs.getString("create_time"));
                jsonobj.put("times", rs.getString("times"));
                jsonarray.add(jsonobj);
            }
            rs.close();
            return jsonarray;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }

    public String get_time(int cid) throws Exception {
        Connection conn = JdbcUtil.getConnection();
//        int li = page * limit - limit;
        String sql;
//        if (page == 0) {
//            sql = "select * from contest";
//        } else {
//            sql = "select * from contest limit " + li + "," + page * limit;
//        }
        sql = "select * from contest where cid=" + cid;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            String str = "";
            while (rs.next()) {
                str = rs.getString("begin_time") + "--" + rs.getString("end_time") + "--" + rs.getString("times");
            }
            rs.close();
            return str;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }

    public JSONArray get_contestrank(int cid, int page, int limit) throws Exception {
        int li = page * limit - limit;
        int li1 = limit;
        String sql;
        if (page == 0) {

            sql = "SELECT p.* FROM (SELECT * FROM conteststatus where cid='" + cid + "'" + "ORDER BY speed*correct_rate DESC  LIMIT 1000 )p GROUP BY  p.uid ORDER BY speed*correct_rate desc";
// sql="select * from conteststatus where cid='"+cid+"'"+" and csid in (select maxid from (select max(csid) as maxid, max(speed*correct_rate) as qq from conteststatus where cid='"+cid+"'"+" group by uid) b) ORDER BY speed*correct_rate DESC";

        } else {
            sql = "SELECT p.* FROM (SELECT * FROM conteststatus where cid='" + cid + "'" + "ORDER BY speed*correct_rate DESC  LIMIT 1000 )p GROUP BY  p.uid ORDER BY speed*correct_rate desc limit " + li + "," + li1;

        }
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonobj = new JSONObject();
            while (rs.next()) {
                jsonobj.put("csid", rs.getInt("csid"));
                jsonobj.put("cid", rs.getString("cid"));
                jsonobj.put("uid", rs.getString("uid"));
                jsonobj.put("mid", rs.getString("mid"));
//                jsonobj.put("message", rs.getString("message"));
                jsonobj.put("username", rs.getString("username"));
                jsonobj.put("nickname", rs.getString("nickname"));
                jsonobj.put("mesname", rs.getString("mesname"));
                jsonobj.put("speed", rs.getString("speed"));
                jsonobj.put("correct_rate", rs.getString("correct_rate"));
                jsonobj.put("grade", rs.getString("grade"));
                jsonobj.put("wordnum", rs.getString("wordnum"));
                jsonobj.put("time", rs.getString("time"));
                jsonobj.put("wrtime", rs.getString("wrtime"));
//                jsonobj.put("instan", rs.getString("instan"));
//                String str = (String)jsonobj.get("message");
//                str = str.replace("\r\n","'+'\\n'+'");
//                jsonobj.put("message", str);
                jsonarray.add(jsonobj);
            }
            rs.close();
            return jsonarray;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }

    public JSONArray get_usercontestranks(int cid, int uid, int page, int limit) throws Exception {
        int li = page * limit - limit;
        int li1 = limit;
        String sql;
        if (page == 0) {
//            if (sta==1){
            sql = "select * from conteststatus where cid='" + cid + "'" + " and uid='" + uid + "'" + " ORDER BY speed*correct_rate DESC";
//                sql = "select * from conteststatus where cid='" + cid + "'"+" order by speed*correct_rate desc";
//            }
//            else{
//                sql = "select * from message where mid='" + uid + "'";
//            }

        } else {
//            if (sta==1){
            sql = "select * from conteststatus  where cid='" + cid + "'" + " and uid='" + uid + "'" + " order by speed*correct_rate desc limit " + li + "," + li1;
//            }
//            else{
//                sql = "select * from message limit " + li + "," + li1 + "where mid='" + uid + "'";
//            }
        }

        Connection conn = JdbcUtil.getConnection();
//        String sql = "select * from message limit "+li+","+li1+"where mid='"+uid+"'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonobj = new JSONObject();
            while (rs.next()) {
                jsonobj.put("csid", rs.getInt("csid"));
                jsonobj.put("cid", rs.getString("cid"));
                jsonobj.put("uid", rs.getString("uid"));
                jsonobj.put("mid", rs.getString("mid"));
//                jsonobj.put("message", rs.getString("message"));
                jsonobj.put("username", rs.getString("username"));
                jsonobj.put("nickname", rs.getString("nickname"));
                jsonobj.put("mesname", rs.getString("mesname"));
                jsonobj.put("speed", rs.getString("speed"));
                jsonobj.put("correct_rate", rs.getString("correct_rate"));
                jsonobj.put("grade", rs.getString("grade"));
                jsonobj.put("wordnum", rs.getString("wordnum"));
                jsonobj.put("time", rs.getString("time"));
                jsonobj.put("wrtime", rs.getString("wrtime"));
//                jsonobj.put("instan", rs.getString("instan"));
//                String str = (String)jsonobj.get("message");
//                str = str.replace("\r\n","'+'\\n'+'");
//                jsonobj.put("message", str);
                jsonarray.add(jsonobj);
            }
            rs.close();
            return jsonarray;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }

    public JSONArray get_usercontestrank(int csid) throws Exception {
//        int li = page * limit - limit;
//        int li1 = limit;
        String sql;
        sql = "select * from conteststatus where csid='" + csid + "'";
//        if (page == 0) {
////            if (sta==1){
//            sql="select * from conteststatus where cid='"+cid+"'"+"uid="+uid+" and csid in (select maxid from (select max(csid) as maxid from conteststatus where cid='"+cid+"'"+" group by uid) b) ORDER BY speed*correct_rate DESC";
////                sql = "select * from conteststatus where cid='" + cid + "'"+" order by speed*correct_rate desc";
////            }
////            else{
////                sql = "select * from message where mid='" + uid + "'";
////            }
//
//        } else {
////            if (sta==1){
//            sql = "select * from conteststatus  where cid='"+cid + "'"+"uid="+uid+" and csid in (select maxid from (select max(csid) as maxid from conteststatus where cid='"+cid+"'"+" group by uid) b) order by speed*correct_rate desc limit " + li + "," + li1;
////            }
////            else{
////                sql = "select * from message limit " + li + "," + li1 + "where mid='" + uid + "'";
////            }
//        }

        Connection conn = JdbcUtil.getConnection();
//        String sql = "select * from message limit "+li+","+li1+"where mid='"+uid+"'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonobj = new JSONObject();
            while (rs.next()) {
                jsonobj.put("csid", rs.getInt("csid"));
                jsonobj.put("cid", rs.getString("cid"));
                jsonobj.put("uid", rs.getString("uid"));
                jsonobj.put("mid", rs.getString("mid"));
//                jsonobj.put("message", rs.getString("message"));
                jsonobj.put("username", rs.getString("username"));
                jsonobj.put("nickname", rs.getString("nickname"));
                jsonobj.put("mesname", rs.getString("mesname"));
                jsonobj.put("speed", rs.getString("speed"));
                jsonobj.put("correct_rate", rs.getString("correct_rate"));
                jsonobj.put("grade", rs.getString("grade"));
                jsonobj.put("wordnum", rs.getString("wordnum"));
                jsonobj.put("time", rs.getString("time"));
                jsonobj.put("wrtime", rs.getString("wrtime"));
                jsonobj.put("instan", rs.getString("instan"));
//                String str = (String)jsonobj.get("message");
//                str = str.replace("\r\n","'+'\\n'+'");
//                jsonobj.put("message", str);
                jsonarray.add(jsonobj);
            }
            rs.close();
            return jsonarray;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }

    public void updateContestinfo(int cid, String contests_name, String autor, int mid, String mesname, String begin_time, String end_time, int times) throws Exception {
        // TODO Auto-generated method stub
        Connection conn = JdbcUtil.getConnection();
        String sql;
//        System.out.println("-=-=-="+password);
        sql = "update contest set contests_name=?,autor=?,mid=?,mesname=?,begin_time=?,end_time=?,times=? where cid=?";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);
            stm.setString(1, contests_name);
            stm.setString(2, autor);
            stm.setInt(3, mid);
            stm.setString(4, mesname);
            stm.setString(5, begin_time);
            stm.setString(6, end_time);
            stm.setInt(7, times);
            stm.setInt(8, cid);
            stm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}