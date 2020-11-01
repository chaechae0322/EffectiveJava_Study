package item30;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Union {

    /* 제너릭 메소드 */
    public static <E> Set<E> union(Set<E> set1, Set<E> set2){
        Set<E> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    public static void main(String[] args) {
        Set<String> boys = Set.of("톰", "제리", "졸리");
        Set<String> girls = Set.of("쉘리","졸리","말리");
        Set<String> people = Union.union(boys, girls);
        System.out.println(people);
    }
}
