## 변경가능성을 최소화 하라

클래스를 불변으로 만드려면 다음 다섯가지 규칙을 따르면 된다
- 객체의 상태를 변경하는 메서드 (변경자)를 제공하지 않는다
- 클래스를 확장할 수 없도록 한다
  - final로 선언
  - 모든 생성자를 private 혹은 package-private으로 만들고 public 정적 팩터리를 제공
- 모든 필드를 final로 선언한다
- 모든 필드를 private으로 선언한다
- 자신 외에는 내부의 가변 컴포넌트에 접근 할 수 없도록 한다

불변 클래스에도 단점은 있다. 값이 다르면 반드시 독립된 객체로 만들어야 한다.
``` java
BigInteger moby = ..;
moby = moby.flipBit(0); 
``` 
BigInteger는 불변객체. 불변객체의 메서드 flipBit는 비트하나를 바꾼다는것. 
현재 객체의 값을 수정을 한다는것이니까 내부 메서드를 보면 
``` java
return new BigInteger(...)
``` 
이렇게 새로운 객체를 생성해서 반환할 것이다.
<br>

**불변 클래스가 확장 가능하다는 것이 왜 보안상의 문제가 되는가**<br>
BigInteger나 BigDecimal을 설계할 당시엔 불변객체가 사실상 final이어야 한다는 생각이 널리 퍼지지 않았는데 그래서 이 두클래스의 메서드들은 모두 재정의 할 수 있게 설계되었다. 
이게 왜 문제가 될 수 있는지 예시를 보자
``` java
public class BigBigInteger extends BigInteger{
    // 메서드 재정의 가능 -> 가변객체로 재정의 될 가능성 있다
}
```

클라이언트가 객체를 구현하여 사용할때 thread-safe하지 않는 하위객체를 thread-safe한 객체로 생각하여 쓸수도있음. 보안문제 

**p.111**<br>
신뢰할 수 없는 클라이언트로부터 BigInteger나 BigDecimal의 인스턴스를 인수로 받는다면 주의해야한다.  (왜나면 BigInteger나 BigDecimal를 제정의한 하위 객체들이 가변객체로 만들어 버릴 수도 있으니까)
이 값들이 불변이여야 클래스의 보안을 지킬 수 있다면 인수로 받은 객체가 '진짜' BigInteger 혹은 BigDecimal인 지 반드시 확인해야한다. 

다시 말해서 방어적으로 복사해 사용해야한다.
``` java
public statis BigInteger safeInstance(BigInteger val){
    return val.getClass() == BigInteger.class ? val : new BigInteger(val.toByteArray()); 
    // 진짜 BigInteger라면 그대로 리턴하고, 아니면 복사해서 같은 값을 가지는 BigInteger 를 생성해서 반환
}
```


