public class CharacterCounter {
    public static void countCharacter(String str) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
        char letter[] = new char[26];
        int freq[] = new int[26];

        for(int i=0; i<letter.length; i++){
            letter[i] = (char)(i+97);
        }
        for(int i =0; i < str.length(); i++){
            for(int j=0; j<letter.length; j++){
                if(letter[j] == str.charAt(i)){
                    freq[j]++;
                    break;
                }
            }
        }

        for(int i = 0 ; i<letter.length;i++){
            if(freq[i] < 1){
                continue;
            }else {
                printCount(letter[i], freq[i]);
            }
        }

    }

    private static void printCount(char character, int count) {
        System.out.printf("%c: %d times\n", character, count);
    }
}