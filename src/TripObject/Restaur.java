package TripObject;

public class Restaur {

    // === 맛집 고유번호 ===
    private int restaurNum;
    private String restaurName;

    @Override
    public String toString() {
        return "맛집: " +
                "맛집 번호: " + restaurNum +
                ", 맛집 이름: '" + restaurName + '\'' +
                ", 맛집 메뉴1: '" + restaurMenu1 + '\'' +
                ", 맛집 메뉴2: '" + restaurmenu2 + '\'' +
                ", 메뉴1 가격: " + restaurPrice1 +
                ", 메뉴2 가격: " + restaurPrice2 +
                ", 지역 번호: " + regionNum ;
    }

    public String getRestaurName() {
        return restaurName;
    }

    public void setRestaurName(String restaurName) {
        this.restaurName = restaurName;
    }

    // === 대표메뉴 1 ===
    private String restaurMenu1;
    // === 대표메뉴 2 ===
    private String restaurmenu2;
    // === 대표메뉴 1 가격  ===
    private int restaurPrice1;
    // === 대표메뉴 2 가격 ===
    private int restaurPrice2;

    private int regionNum;

    public int getRegionNum() {
        return regionNum;
    }

    public void setRegionNum(int regionNum) {
        this.regionNum = regionNum;
    }

    public int getRestaurNum() {
        return restaurNum;
    }

    public void setRestaurNum(int restaurNum) {
        this.restaurNum = restaurNum;
    }

    public String getRestaurMenu1() {
        return restaurMenu1;
    }

    public void setRestaurMenu1(String restaurMenu1) {
        this.restaurMenu1 = restaurMenu1;
    }

    public String getRestaurmenu2() {
        return restaurmenu2;
    }

    public void setRestaurmenu2(String restaurmenu2) {
        this.restaurmenu2 = restaurmenu2;
    }

    public int getRestaurPrice1() {
        return restaurPrice1;
    }

    public void setRestaurPrice1(int restaurPrice1) {
        this.restaurPrice1 = restaurPrice1;
    }

    public int getRestaurPrice2() {
        return restaurPrice2;
    }

    public void setRestaurPrice2(int restaurPrice2) {
        this.restaurPrice2 = restaurPrice2;
    }
}
