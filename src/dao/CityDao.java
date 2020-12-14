package dao;

import Utils.JdbcUtil;
import bean.City;
import bean.Province;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CityDao {
    public ArrayList<Province> queryProvince() {
        ArrayList<Province> list = new ArrayList<Province>();
        Connection conn = new JdbcUtil().getConnection();
        try {
            String sql = "select * from t_province";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Province province = new Province();
                province.setProvinceId(rs.getString("provinceId"));
                province.setProvinceName(rs.getString("provinceName"));
                list.add(province);
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public ArrayList<City> queryCity(String provinceId) {
        ArrayList<City> list = new ArrayList<City>();

        return list;
    }
}
