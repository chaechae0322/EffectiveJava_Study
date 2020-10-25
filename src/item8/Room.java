package item8;

import java.lang.ref.Cleaner;
import java.util.Objects;

public class Room implements AutoCloseable {
    /* 왜 static final? 클리너라는 객체는 변경될필요없고 다른 인스턴스들도 공통적으로 사용하는거. */
    private static final Cleaner cleaner = Cleaner.create();
    private static class State implements Runnable {
        /*
            청소가 필요한 자원은 여기에 넣는다.
            절때 Room을 참조해서는 안된다 -> 순환참조 -> 가비지 컬렉터가 못지움
         */
        int numJunkFiles;

        State(int numJunkFiles) {
            this.numJunkFiles = numJunkFiles;
        }

        @Override
        public void run() {
            System.out.println("방 청소!");
        }
    }
    private boolean closed;
    private final State state; // 객체마다 다르네
    private final Cleaner.Cleanable cleanable; // 객체마다 State가 다르니까 얘도 다르게

    public Room(int numJunkFiles){
        state = new State(numJunkFiles);
        cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() throws Exception {
        if(this.closed){
            throw new IllegalStateException();
        }

        closed=true;
        cleanable.clean();
    }

    public void hello(){
        System.out.println("hello~~~~");
    }
}
