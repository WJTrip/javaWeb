package dao;

import Utils.JdbcUtil;
import bean.City;
import bean.Province;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProvinceCityDao {
    public ArrayList<Province> queryProvince(){
        ArrayList<Province> list = new ArrayList<Province>();
        Connection conn = new JdbcUtil().getConnection();
        try {
            String sql = "select * from t_province ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Province province = new Province();
                province.setProvinceId(rs.getString("provinceId"));
                province.setProvinceName(rs.getString("provinceName"));

                list.add(province); // 将对象存放于集合中
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<City> queryCity(String provinceId){
        ArrayList<City> list=new ArrayList<City>();
        Connection conn = new JdbcUtil().getConnection();
        try {

            // 3.创建语句
            String sql = "select * from t_city where provinceId=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, provinceId);
            // 4.执行语句
            ResultSet rs = pst.executeQuery();
            // 5.结果处理
            while (rs.next()) {
                City city = new City();
                city.setCityId(rs.getString("cityId"));
                city.setProvinceId(rs.getString("provinceId"));
                city.setCityName(rs.getString("cityName"));

                list.add(city); // 将对象存放于集合中
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
