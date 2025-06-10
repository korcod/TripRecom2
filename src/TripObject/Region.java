package TripObject;

public class Region {

    // === 지역 고유번호 ===
    private int regionNum;
    // === 지역 이름 ===
    private String regionName;

    @Override
    public String toString() {
        return "지역" +
                "지역 번호" + regionNum +
                ", 지역 이름'" + regionName + '\'' +
                '}';
    }

    public int getRegionNum() {
        return regionNum;
    }

    public void setRegionNum(int regionNum) {
        this.regionNum = regionNum;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
