package dao;

import bean.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public void addUser(String username, String nickname, String email, String password, String ip, String login_time, String reg_time, int status) throws Exception {
        Connection conn = JdbcUtil.getConnection();
        String sql = "insert  into user(username,nickname,email,password,ip,login_time,reg_time,status) values(?,?,?,?,?,?,?,?)";
        int rs = 0;
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, nickname);
            stm.setString(3, email);
            stm.setString(4, password);
            stm.setString(5, ip);
            stm.setString(6, login_time);
            stm.setString(7, reg_time);
            stm.setInt(8, status);
            rs = stm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateUserinfo(int uid, String username, String nickname, String email, String password, int status) throws Exception {
        // TODO Auto-generated method stub
        Connection conn = JdbcUtil.getConnection();
        String sql;
//        System.out.println("-=-=-="+password);
        if (password == null||password.isEmpty()) {
            sql = "update user set username=?,nickname=?,email=?,status=? where uid=?";
            PreparedStatement stm = null;
            try {
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, nickname);
                stm.setString(3, email);
                stm.setInt(4, status);
                stm.setInt(5, uid);
                stm.executeUpdate();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            sql = "update user set username=?,nickname=?,email=?,password=?,status=? where uid=?";
            PreparedStatement stm = null;
            try {
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, nickname);
                stm.setString(3, email);
                stm.setString(4, password);
                stm.setInt(5, status);
                stm.setInt(6, uid);
                stm.executeUpdate();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public void updateUserip(int uid, String login_time, String ip) throws Exception {
        // TODO Auto-generated method stub
        Connection conn = JdbcUtil.getConnection();
        String sql;

        sql = "update user set login_time=?,ip=? where uid=?";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);
            stm.setString(1, login_time);
            stm.setString(2, ip);
            stm.setInt(3, uid);
            stm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public User getAdminInfo(String username, String password) throws Exception {
        // TODO Auto-generated method stub
        User adminbean = new User();
        Connection conn = JdbcUtil.getConnection();
        String sql = "select * from user where username='" + username + "' AND password='" + password + "'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                adminbean.setUid(rs.getInt("uid"));
                adminbean.setUsername(rs.getString("username"));
                adminbean.setName(rs.getString("nickname"));
//                adminbean.setPassword(rs.getString("password"));
                adminbean.setEmail(rs.getString("email"));
                adminbean.setStatus(rs.getInt("status"));
                adminbean.setIp(rs.getString("ip"));
                adminbean.setLogin_time(rs.getString("login_time"));
                adminbean.setReg_time(rs.getString("reg_time"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return adminbean;
    }

    public JSONArray get_userlist(int page, int limit) throws Exception {
        Connection conn = JdbcUtil.getConnection();
        int li = page * limit - limit;
        String sql;
        if (page == 0) {
            sql = "select count(uid) as ss from user";
        } else {
            sql = "select * from user limit " + li + "," + limit;
        }

        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonobj = new JSONObject();
            if (page == 0){
//                System.out.println(stm);
                rs.next();
//                while(rs.next())
//                System.out.println(rs.getInt("ss"));
                jsonobj.put("count", rs.getInt("ss"));
                jsonarray.add(jsonobj);
            }else {
                while (rs.next()) {
                    jsonobj.put("uid", rs.getInt("uid"));
                    jsonobj.put("username", rs.getString("username"));
                    jsonobj.put("nickname", rs.getString("nickname"));
                    jsonobj.put("email", rs.getString("email"));
                    jsonobj.put("status", rs.getString("status"));
                    jsonobj.put("ip", rs.getString("ip"));
                    jsonobj.put("login_time", rs.getString("login_time"));
                    jsonobj.put("reg_time", rs.getString("reg_time"));
                    jsonarray.add(jsonobj);
                }
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

    public JSONArray user_info(int id) throws Exception {
        Connection conn = JdbcUtil.getConnection();
        String sql = "select * from user where uid='" + id + "'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonobj = new JSONObject();
            while (rs.next()) {
                jsonobj.put("uid", rs.getInt("uid"));
                jsonobj.put("username", rs.getString("username"));
                jsonobj.put("nickname", rs.getString("nickname"));
                jsonobj.put("email", rs.getString("email"));
                jsonobj.put("status", rs.getString("status"));
                jsonobj.put("ip", rs.getString("ip"));
                jsonobj.put("login_time", rs.getString("login_time"));
                jsonobj.put("reg_time", rs.getString("reg_time"));
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

    public JSONArray user_info1(String username) throws Exception {
        // TODO Auto-generated method stub
        Connection conn = JdbcUtil.getConnection();
        String sql = "select * from user where username like '%" + username + "%'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonobj = new JSONObject();
            while (rs.next()) {
                jsonobj.put("uid", rs.getInt("uid"));
                jsonobj.put("username", rs.getString("username"));
                jsonobj.put("nickname", rs.getString("nickname"));
                jsonobj.put("email", rs.getString("email"));
                jsonobj.put("status", rs.getString("status"));
                jsonobj.put("ip", rs.getString("ip"));
                jsonobj.put("login_time", rs.getString("login_time"));
                jsonobj.put("reg_time", rs.getString("reg_time"));
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
    public void deleteUser(int uid) throws Exception {
        // TODO Auto-generated method stub
        Connection conn = JdbcUtil.getConnection();
        String sql = "delete from user where uid=?";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, uid);
            stm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
