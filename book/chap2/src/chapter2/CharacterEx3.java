package chapter2;

public class CharacterEx3 {
    public static void main(String[] args) {
        int a=65;
        int b=-66;

        char a2=65;
        //char b2=-66;

        System.out.println((char)a);
        System.out.println((char)b); //알 수 없는 문자로 출력됨, char형은 본디 음수값을 표현할 수 없음
        System.out.println(a2);
    }
}
