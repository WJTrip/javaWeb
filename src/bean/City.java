package bean;

public class City {
    private String cityId;
    private String provinceId;
    private String cityName;

    public City() {
    }

    public City(String cityId, String provinceId, String cityName) {
        this.cityId = cityId;
        this.provinceId = provinceId;
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
