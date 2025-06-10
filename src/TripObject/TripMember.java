package TripObject;

public class TripMember {

    // === 회원 고유번호===
    private int memberNum;
    // === 회원 아이디===
    private String memberId;
    // === 회원 비밀번호===
    private String memberPw;
    // === 회원 전화번호===
    private String memberPhone;
    // === 회원 생년월일===
    private String memberBirth;

    @Override
    public String toString() {
        return "회원" +
                "회원 번호" + memberNum +
                ", 회원 아이디: '" + memberId + '\'' +
                ", 회원 비밀번호: '" + memberPw + '\'' +
                ", 회원 전화번호: '" + memberPhone + '\'' +
                ", 회원 생년월일: '" + memberBirth + '\'' +
                ", 회원 포인트: " + memberPoint +
                '}';
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPw() {
        return memberPw;
    }

    public void setMemberPw(String memberPw) {
        this.memberPw = memberPw;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberBirth() {
        return memberBirth;
    }

    public void setMemberBirth(String memberBirth) {
        this.memberBirth = memberBirth;
    }

    public int getMemberPoint() {
        return memberPoint;
    }

    public void setMemberPoint(int memberPoint) {
        this.memberPoint = memberPoint;
    }

    // === 회원 충전 포인트===
    private int memberPoint;

}
