package item8;

public class RoomTest {
    public static void main(String[] args) throws Exception{
        /*try(var room = new Room(7)){  이렇게 클라이언트에서 닫는거 안해줄때 가비지컬렉터가 회수할 때 Cleaner가 close 호출
            room.hello();
        }*/

        RoomTest runner = new RoomTest();
        runner.run();
        System.gc();
    }

    private void run() {
        Room room = new Room(7);
        room.hello();
    }

}
