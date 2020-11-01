package item30;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class RecursiveTypeBound {

    // 타입한정인 <E extends Comparable<E>> 는 같은 타입을 비교할 수 있는 타입으로 한정하겠다는 의미
    // 긍가 비교할 수 있는 타입만 받는다.
    public static <E extends Comparable<E>> E max (Collection<E> c){
        E result = null;
        for (E e: c) {
            if(result == null || e.compareTo(result) > 0){
                result = Objects.requireNonNull(e);  // 매개변수(e)가 null 아니면 매개변수(e)를 반환한다.
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(75,55,789,7777);
        int maxValue = RecursiveTypeBound.max(list);

        System.out.println(maxValue);
    }
}
