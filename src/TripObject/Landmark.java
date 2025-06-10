package TripObject;

public class Landmark {

    // === 명소 고유번호 ===
    private int landmarkNum;
    // === 명소 이름 ==
    private String landmarkName;
    // === 명소 입장가격===
    private int landmarkPrice;

    private int regionNum;

    @Override
    public String toString() {
        return "지역명소" +
                "지역명소 번호: " + landmarkNum +
                ", 지역명소 이름: '" + landmarkName + '\'' +
                ", 지역명소 가격: " + landmarkPrice +
                ", 지역 번호: " + regionNum;
    }

    public int getRegionNum() {
        return regionNum;
    }

    public void setRegionNum(int regionNum) {
        this.regionNum = regionNum;
    }

    public int getLandmarkNum() {
        return landmarkNum;
    }

    public void setLandmarkNum(int landmarkNum) {
        this.landmarkNum = landmarkNum;
    }

    public String getLandmarkName() {
        return landmarkName;
    }

    public void setLandmarkName(String landmarkName) {
        this.landmarkName = landmarkName;
    }

    public int getLandmarkPrice() {
        return landmarkPrice;
    }

    public void setLandmarkPrice(int landmarkPrice) {
        this.landmarkPrice = landmarkPrice;
    }
}
