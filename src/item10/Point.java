package item10;

public class Point {
    private final int x;
    private final int y;

    Point(int x, int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public boolean equals(Object obj) {
        /*
            리스코프 치환 법칙 위배
            (상위 클래스를 상속받은 하위 클래스는 상위 클래스로서도 똑같이 잘 작동되어야 한다.)
            getClass 로 비교하면 Point를 상속받은 그 어떤 클래스와도 비교가 안된다. 다 false

            instance of Point 로 비교해라
         */
        if(obj == null || obj.getClass() != getClass()){
            return false;
        }
        Point point = (Point) obj;
        return this.x == point.x && this.y == point.y;
    }

}
