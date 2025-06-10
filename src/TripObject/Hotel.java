package TripObject;

public class Hotel {

    // === 숙소 고유번호 ===
    private int hotelNum;
    // === 숙소 이름 ===
    private String hotelName;
    // === 숙소 가격 ===
    private int hotelPrice;

    private int regionNum;

    @Override
    public String toString() {
        return "숙소: " +
                "숙소 번호: " + hotelNum +
                ", 숙소 이름: '" + hotelName + '\'' +
                ", 숙소 가격: " + hotelPrice +
                ", 지역 번호: " + regionNum ;
    }

    public int getRegionNum() {
        return regionNum;
    }

    public void setRegionNum(int regionNum) {
        this.regionNum = regionNum;
    }

    public int getHotelNum() {
        return hotelNum;
    }

    public void setHotelNum(int hotelNum) {
        this.hotelNum = hotelNum;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(int hotelPrice) {
        this.hotelPrice = hotelPrice;
    }
}
