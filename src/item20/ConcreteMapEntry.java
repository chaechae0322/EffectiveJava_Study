package com.nts.todo.item20;

import java.util.Map;
import java.util.Objects;

abstract class AbstractMapEntry<K,V> implements Map.Entry<K,V> {
	
	@Override
	public V setValue(V value) {
		return (V) new UnsupportedOperationException();
	}
	
    // Map.Entry.equals의 일반 규약을 구현한다.
	@Override public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Map.Entry))
            return false;
        Map.Entry<?,?> e = (Map.Entry) o;
        return Objects.equals(e.getKey(),   getKey()) // 이거는 주소비교 하는거 아닌가..?
                && Objects.equals(e.getValue(), getValue());
      //와... key가 String 이면 String은 불변객체라서 new 연산자를 쓰지않는이상 상수 pool에 만들어져서
        //같은 주소를 참조하는데 그래서 저게 맞는거고
        // 만약 key를 new 를 이용해서 생성한 객체라면 이 equals 식은 걸맞지 않는다. false가 나온다
        
        // 마찬가지로 hashCode 도 똑같다
    }

    // Map.Entry.hashCode의 일반 규약을 구현한다.
    @Override public int hashCode() {
        return Objects.hashCode(getKey())
                ^ Objects.hashCode(getValue());
    }

    @Override public String toString() {
        return getKey() + "=" + getValue();
    }

}

public class ConcreteMapEntry<K,V> extends AbstractMapEntry<K,V> {
	K key;
	V value;
	
	ConcreteMapEntry(K key, V value){
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}
	
	public static void main(String[] args) {
		Map.Entry<Node, String> entry1 = new ConcreteMapEntry(new Node("java"), "is hungry");
		Map.Entry<Node, String> entry2 = new ConcreteMapEntry(new Node("java"), "is hungry");
		
		System.out.println(entry1.hashCode());
		System.out.println(entry2.hashCode());
		
		System.out.println(entry1.equals(entry2));
	}
	
}

class Node {
	String key;
	Node(String key){
		this.key=key;
	}
}
