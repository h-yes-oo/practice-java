package chapter2;

public class TypeInference {
    public static void main(String[] args){
        // var 로 변수를 선언하면서 초기화하면 자동으로 자료형을 추론해줌, 단 자바 10 이상에서만 사용가능 !!
        var i = 10;
        var j = 10.0;
        var str = "hello";

        System.out.println(i);
        System.out.println(j);
        System.out.println(str);
    }
}
