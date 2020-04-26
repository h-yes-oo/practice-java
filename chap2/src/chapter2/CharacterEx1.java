package chapter2;

public class CharacterEx1 {
    public static void main(String[] args){
        char ch1 = 'A';
        System.out.println(ch1); //문자의 형태로 출력
        System.out.println((int)ch1); //아스키 코드 값으로 출력

        char ch2 = 66;
        System.out.println(ch2); //아스키 코드 값이 66인 문자 B 출력

        int ch3 = 67;
        System.out.println(ch3);
        System.out.println((char)ch3); //아스키 코드 값이 67인 문자 C 출력
    }
}
