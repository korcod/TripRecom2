package TripObject;

public class Reserve {

    // === 예약 고유번호 ===
    private int reserveNum;
    // === 예약 시간 ===
    private int reserveTime;
    // === 예약 인원수 ===
    private int reservePeopleNum;
    private int memberNum;
    private int restaurNum;
    private  String reserveMenu1;
    private  String reserveMenu2;
    private int reservePrice;

    @Override
    public String toString() {
        return "예약" +
                "예약번호: " + reserveNum +
                ", 예약시간: " + reserveTime +
                ", 예약인원: " + reservePeopleNum +
                ", 회원번호: " + memberNum +
                ", 맛집번호: " + restaurNum +
                ", 예약메뉴1: '" + reserveMenu1 + '\'' +
                ", 예약메뉴2: '" + reserveMenu2 + '\'' +
                ", 예약가격: " + reservePrice;
    }

    public String getReserveMenu1() {
        return reserveMenu1;
    }

    public void setReserveMenu1(String reserveMenu1) {
        this.reserveMenu1 = reserveMenu1;
    }

    public String getReserveMenu2() {
        return reserveMenu2;
    }

    public void setReserveMenu2(String reserveMenu2) {
        this.reserveMenu2 = reserveMenu2;
    }

    public int getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(int reservePrice) {
        this.reservePrice = reservePrice;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public int getRestaurNum() {
        return restaurNum;
    }

    public void setRestaurNum(int restaurNum) {
        this.restaurNum = restaurNum;
    }

    public int getReserveNum() {
        return reserveNum;
    }

    public void setReserveNum(int reserveNum) {
        this.reserveNum = reserveNum;
    }

    public int getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(int reserveTime) {
        this.reserveTime = reserveTime;
    }

    public int getReservePeopleNum() {
        return reservePeopleNum;
    }

    public void setReservePeopleNum(int reservePeopleNum) {
        this.reservePeopleNum = reservePeopleNum;
    }
}
