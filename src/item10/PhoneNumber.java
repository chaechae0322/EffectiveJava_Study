package item10;

/*
    Object의 eqyals를 5가지 규칙에 맞게 재정의한 class

    대칭성, 추이성, 일관성, 반사성, non null
 */
public class PhoneNumber {
    private final short areaCode, prefix, lineNum;

    PhoneNumber(int areaCode, int prefix, int lineNum){
        this.areaCode = rangeCheck(areaCode, 999, "지역코드");
        this.prefix = rangeCheck(prefix, 999, "프리릭스");
        this.lineNum = rangeCheck(lineNum, 9999, "가입자 번호");
    }

    private short rangeCheck(int val, int max, String arg) {
        if(val < 0 || val > max){
            throw new IllegalArgumentException(arg+" : "+val);
        }
        return (short) val;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof PhoneNumber)) return false;

        PhoneNumber phoneNumber = (PhoneNumber) obj;
        return this.lineNum == phoneNumber.lineNum && this.prefix == phoneNumber.prefix
                && this.areaCode == phoneNumber.areaCode;
    }
}
