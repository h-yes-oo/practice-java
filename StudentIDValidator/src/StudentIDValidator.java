import java.util.Scanner;

public class StudentIDValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("exit")) {
            validateStudentID(input);
            input = scanner.nextLine();
        }
    }
    static boolean isProperLength(String input){
        return input.length() == 10;
    }
    static boolean hasProperDivision(String input){
        return input.charAt(4) == '-';
    }
    static boolean hasProperDigits(String input){
        for (int i = 0; i < input.length(); i++) {
            if (i == 4) {
                continue;
            }
            char ch = input.charAt(i);
            if (ch < '0' || ch > '9') {
                return false;
            }
        }
        return true;
    }
    static void validateStudentID(String input){
        if(!isProperLength(input)){
            System.out.println("The input length should be 10.");
            return;
        } else if (!hasProperDivision(input)){
            System.out.println("Fifth character should be '-'.");
            return;
        } else if (!hasProperDigits(input)){
            System.out.println("Contains an invalid digit.");
            return;
        } System.out.println(input + " is a valid student ID");
    }
}