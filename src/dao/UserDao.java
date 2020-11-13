package dao;

import Utils.JdbcUtil;
import bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public User get(String userName) {
        User user=null;
        Connection conn = new JdbcUtil().getConnection();
        try {
            //创建语句
            String sql = "select * from t_user where userName=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, userName);

            //执行语句
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("userName"),
                        rs.getString("passWord"),
                        rs.getString("chrName"),
                        rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(user.getRole());
        return user;
    }
}
