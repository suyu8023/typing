package dao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusDao {
    public void addstatus(int uid, int mid, String username, String nickname, String message, double speed, double correct_rate, String grade, int wordnum, String time, String time1,String instan) throws Exception {
        Connection conn = JdbcUtil.getConnection();
        String sql = "insert  into status (uid,mid,username,nickname,mesname,speed,correct_rate,grade,wordnum,time,wrtime,instan) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        int rs = 0;
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, uid);
            stm.setInt(2, mid);
            stm.setString(3, username);
            stm.setString(4, nickname);
            stm.setString(5, message);
            stm.setDouble(6, speed);
            stm.setDouble(7, correct_rate);
            stm.setString(8, grade);
            stm.setInt(9, wordnum);
            stm.setString(10, time);
            stm.setString(11, time1);
            stm.setString(12, instan);
            rs = stm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public JSONArray get_ListInfo(int page, int limit,int sta) throws Exception {
        int li = page * limit - limit;
        int li1 = limit;
        String sql;
        if (page == 0) {
            if (sta==1){
                sql = "select * from status order by speed*correct_rate desc";
            }
            else{
                sql = "select * from status order by sid desc";
            }

        } else {
            if (sta==1){
                sql = "select * from status order by speed*correct_rate desc limit " + li + "," + li1;
            }
            else{
                sql = "select * from status order by sid desc limit " + li + "," + li1;
            }

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
                jsonobj.put("sid", rs.getInt("sid"));
                jsonobj.put("uid", rs.getString("uid"));
                jsonobj.put("mid", rs.getString("mid"));
                jsonobj.put("username", rs.getString("username"));
                jsonobj.put("nickname", rs.getString("nickname"));
                jsonobj.put("mesname", rs.getString("mesname"));
                jsonobj.put("speed", rs.getString("speed"));
                jsonobj.put("correct_rate", rs.getString("correct_rate"));
                jsonobj.put("grade", rs.getString("grade"));
                jsonobj.put("wordnum", rs.getString("wordnum"));
                jsonobj.put("time", rs.getString("time"));
                jsonobj.put("wrtime", rs.getString("wrtime"));

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

    public JSONArray get_userlist(int uid, int page, int limit,int sta) throws Exception {
        int li = page * limit - limit;
        int li1 = limit;
        String sql;
        if (page == 0) {
            if (sta==1){
                sql = "select * from status where uid='" + uid + "'"+" order by speed*correct_rate desc";
            }
            else{
                sql = "select * from status where uid='" + uid + "'"+" order by sid desc";
            }

        } else {
            if (sta==1){
                sql = "select * from status where uid='"+uid + "'"+" order by speed*correct_rate desc limit " + li + "," + li1;
            }
            else{
                sql = "select * from status where uid='"+ uid + "'"+" order by sid desc limit " + li + "," + li1;
            }
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
                jsonobj.put("sid", rs.getInt("sid"));
                jsonobj.put("uid", rs.getString("uid"));
                jsonobj.put("mid", rs.getString("mid"));
                jsonobj.put("username", rs.getString("username"));
                jsonobj.put("nickname", rs.getString("nickname"));
                jsonobj.put("mesname", rs.getString("mesname"));
                jsonobj.put("speed", rs.getString("speed"));
                jsonobj.put("correct_rate", rs.getString("correct_rate"));
                jsonobj.put("grade", rs.getString("grade"));
                jsonobj.put("wordnum", rs.getString("wordnum"));
                jsonobj.put("time", rs.getString("time"));
                jsonobj.put("wrtime", rs.getString("wrtime"));

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
    public JSONArray get_userstatusrank(int sid) throws Exception {
        String sql;
        sql = "select * from status where sid='" + sid + "'";

        Connection conn = JdbcUtil.getConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonobj = new JSONObject();
            while (rs.next()) {
                jsonobj.put("cid", rs.getString("sid"));
                jsonobj.put("uid", rs.getString("uid"));
                jsonobj.put("mid", rs.getString("mid"));
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
}
