package entity;

public class MemberEntity {

    private String memberID;
    private String name;
    private String address;
    private String mobile;

    public MemberEntity() {
    }

    public MemberEntity(String memberID, String name, String address, String mobile) {
        this.memberID = memberID;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MemberEntity [memberID=" + memberID + ", name=" + name + ", address=" + address + ", mobile=" + mobile
                + "]";
    }

}
