public class IncreasingString {
    public static void printLongestIncreasingSubstringLength(String inputString) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
        String r = "";
        String c = "";
        int length = 0;
        for(int i = 0; i<inputString.length(); i++){
            if(c.equals("")){
                c+=inputString.charAt(i);
                length++;
            } else if(c.charAt(length-1) < inputString.charAt(i)){
                c+=inputString.charAt(i);
                length++;
            } else if(c.charAt(length-1) >= inputString.charAt(i)){
                if(r.length() < c.length()){
                    r=c;
                    c=""+inputString.charAt(i);
                    length=1;
                } else{
                    c=""+inputString.charAt(i);
                    length=1;
                }
            }
        }

        if (c.length() > r.length()){
            r=c;
        }
        System.out.println(r.length());

    }
}
