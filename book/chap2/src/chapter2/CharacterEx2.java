package chapter2;

public class CharacterEx2 {
    public static void main(String[] args){
        char ch1 = '한';
        char ch2 = '\uD55C'; //글자 '한'의 유니코드 값(16진수 4개로 나타내고 있으므로 4비트*4개=16비트=2바이트)

        System.out.println(ch1);
        System.out.println(ch2);
    }
}
