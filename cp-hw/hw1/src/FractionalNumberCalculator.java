public class FractionalNumberCalculator {
    public static void printCalculationResult(String equation) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
        int numOfOperator = 0;
        int plus = 0;
        int minus = 0;
        int divide = 0;
        int[] divideIdx = new int[3];
        int multiply = 0;
        for(int i = 0; i<equation.length(); i++){
            if(equation.charAt(i) == '/'){
                divideIdx[divide++]=i;
            } else if(equation.charAt(i) == '+'){
                plus = i;
            } else if(equation.charAt(i) == '-'){
                minus = i;
            } else if(equation.charAt(i) == '*'){
                multiply = i;
            }
        }
        if(divide==0){
            if(plus>0){
                String num1 = equation.substring(0,plus-1);
                String num2 = equation.substring(plus+2,equation.length());
                System.out.println(Integer.parseInt(num1)+Integer.parseInt(num2));
            }else if(minus>0){
                String num1 = equation.substring(0,minus-1);
                String num2 = equation.substring(minus+2,equation.length());
                System.out.println(Integer.parseInt(num1)-Integer.parseInt(num2));
            }else if(multiply>0){
                String num1 = equation.substring(0,multiply-1);
                String num2 = equation.substring(multiply+2,equation.length());
                System.out.println(Integer.parseInt(num1)*Integer.parseInt(num2));
            }
        } else if(divide == 1){
            if(plus + minus + multiply == 0){
                String num1 = equation.substring(0,divideIdx[0]-1);
                String num2 = equation.substring(divideIdx[0]+2);
                int denominator = Integer.parseInt(num1);
                int numerator = Integer.parseInt(num2);
                int gcd = FractionalNumber.gcd(denominator,numerator);
                if(numerator/gcd == 1){
                    System.out.println(denominator/gcd);
                } else{
                    System.out.println(denominator/gcd+"/"+numerator/gcd);
                }
            } else {
                if(plus>0){
                    String num1 = equation.substring(0,plus-1);
                    String num2 = equation.substring(plus+2);
                    if(plus>divideIdx[0]){
                        String denominator = num1.substring(0,divideIdx[0]);
                        String numerator = num1.substring(divideIdx[0]+1);
                        int denom = Integer.parseInt(denominator);
                        int nume = Integer.parseInt(numerator);
                        int numint = Integer.parseInt(num2);
                        int denom_result = denom + nume*numint;
                        int nume_result = nume;
                        int gcd = FractionalNumber.gcd(denom_result,nume_result);
                        if(nume_result/gcd == 1){
                            System.out.println(denom_result/gcd);
                        } else{
                            System.out.println(denom_result/gcd+"/"+nume_result/gcd);
                        }
                    } else{
                        int d = 0;
                        for(int i = 0; i<num2.length();i++){
                            if(num2.charAt(i) == '/'){
                                d = i;
                                break;
                            }
                        }
                        String denominator = num2.substring(0,d);
                        String numerator = num2.substring(d+1);
                        int denom = Integer.parseInt(denominator);
                        int nume = Integer.parseInt(numerator);
                        int numint = Integer.parseInt(num1);
                        int denom_result = denom + nume*numint;
                        int nume_result = nume;
                        int gcd = FractionalNumber.gcd(denom_result,nume_result);
                        if(nume_result/gcd == 1){
                            System.out.println(denom_result/gcd);
                        } else{
                            System.out.println(denom_result/gcd+"/"+nume_result/gcd);
                        }
                    }
                }else if(minus>0){
                    String num1 = equation.substring(0,minus-1);
                    String num2 = equation.substring(minus+2);
                    if(minus>divideIdx[0]){
                        String denominator = num1.substring(0,divideIdx[0]);
                        String numerator = num1.substring(divideIdx[0]+1);
                        int denom = Integer.parseInt(denominator);
                        int nume = Integer.parseInt(numerator);
                        int numint = Integer.parseInt(num2);
                        int denom_result = denom - nume*numint;
                        if(denom_result<0){
                            System.out.print("-");
                            denom_result = -denom_result;
                        }
                        int nume_result = nume;
                        int gcd = FractionalNumber.gcd(denom_result,nume_result);
                        if(nume_result/gcd == 1){
                            System.out.println(denom_result/gcd);
                        } else{
                            System.out.println(denom_result/gcd+"/"+nume_result/gcd);
                        }
                    } else{
                        int d = 0;
                        for(int i = 0; i<num2.length();i++){
                            if(num2.charAt(i) == '/'){
                                d = i;
                                break;
                            }
                        }
                        String denominator = num2.substring(0,d);
                        String numerator = num2.substring(d+1);
                        int denom = Integer.parseInt(denominator);
                        int nume = Integer.parseInt(numerator);
                        int numint = Integer.parseInt(num1);
                        int denom_result = nume*numint - denom;
                        if(denom_result<0){
                            System.out.print("-");
                            denom_result = -denom_result;
                        }
                        int nume_result = nume;
                        int gcd = FractionalNumber.gcd(denom_result,nume_result);
                        if(nume_result/gcd == 1){
                            System.out.println(denom_result/gcd);
                        } else{
                            System.out.println(denom_result/gcd+"/"+nume_result/gcd);
                        }
                    }
                }else if(multiply > 0){
                    String num1 = equation.substring(0,multiply-1);
                    String num2 = equation.substring(multiply+2);
                    if(multiply>divideIdx[0]){
                        String denominator = num1.substring(0,divideIdx[0]);
                        String numerator = num1.substring(divideIdx[0]+1);
                        int denom = Integer.parseInt(denominator);
                        int nume = Integer.parseInt(numerator);
                        int numint = Integer.parseInt(num2);
                        int denom_result = denom * numint;
                        int nume_result = nume;
                        int gcd = FractionalNumber.gcd(denom_result,nume_result);
                        if(nume_result/gcd == 1){
                            System.out.println(denom_result/gcd);
                        } else{
                            System.out.println(denom_result/gcd+"/"+nume_result/gcd);
                        }
                    } else{
                        int d = 0;
                        for(int i = 0; i<num2.length();i++){
                            if(num2.charAt(i) == '/'){
                                d = i;
                                break;
                            }
                        }
                        String denominator = num2.substring(0,d);
                        String numerator = num2.substring(d+1);
                        int denom = Integer.parseInt(denominator);
                        int nume = Integer.parseInt(numerator);
                        int numint = Integer.parseInt(num1);
                        int denom_result = denom*numint;
                        int nume_result = nume;
                        int gcd = FractionalNumber.gcd(denom_result,nume_result);
                        if(nume_result/gcd == 1){
                            System.out.println(denom_result/gcd);
                        } else{
                            System.out.println(denom_result/gcd+"/"+nume_result/gcd);
                        }
                    }
                }
            }
        } else if(divide == 2){
            if(plus+minus+multiply>0){
                if(plus>0){
                    String num1 = equation.substring(0,plus-1);
                    String num2 = equation.substring(plus+2);
                    String denomerator1 = num1.substring(0,divideIdx[0]);
                    String numerator1 = num1.substring(divideIdx[0]+1);
                    int denom1 = Integer.parseInt(denomerator1);
                    int nume1 = Integer.parseInt(numerator1);
                    int d = 0;
                    for(int i = 0; i<num2.length();i++){
                        if(num2.charAt(i) == '/'){
                            d = i;
                            break;
                        }
                    }
                    String denominator2 = num2.substring(0,d);
                    String numerator2 = num2.substring(d+1);
                    int denom2 = Integer.parseInt(denominator2);
                    int nume2 = Integer.parseInt(numerator2);
                    int denom_result = denom1*nume2 + denom2*nume1;
                    int nume_result = nume1*nume2;
                    int gcd = FractionalNumber.gcd(denom_result,nume_result);
                    if(nume_result/gcd == 1){
                        System.out.println(denom_result/gcd);
                    } else{
                        System.out.println(denom_result/gcd+"/"+nume_result/gcd);
                    }
                } else if(minus>0){
                    String num1 = equation.substring(0,minus-1);
                    String num2 = equation.substring(minus+2);
                    String denomerator1 = num1.substring(0,divideIdx[0]);
                    String numerator1 = num1.substring(divideIdx[0]+1);
                    int denom1 = Integer.parseInt(denomerator1);
                    int nume1 = Integer.parseInt(numerator1);
                    int d = 0;
                    for(int i = 0; i<num2.length();i++){
                        if(num2.charAt(i) == '/'){
                            d = i;
                            break;
                        }
                    }
                    String denominator2 = num2.substring(0,d);
                    String numerator2 = num2.substring(d+1);
                    int denom2 = Integer.parseInt(denominator2);
                    int nume2 = Integer.parseInt(numerator2);
                    int denom_result = denom1*nume2 - denom2*nume1;
                    if(denom_result<0){
                        System.out.print("-");
                        denom_result = -denom_result;
                    }
                    int nume_result = nume1*nume2;
                    int gcd = FractionalNumber.gcd(denom_result,nume_result);
                    if(nume_result/gcd == 1){
                        System.out.println(denom_result/gcd);
                    } else{
                        System.out.println(denom_result/gcd+"/"+nume_result/gcd);
                    }
                } else if(multiply>0){
                    String num1 = equation.substring(0,multiply-1);
                    String num2 = equation.substring(multiply+2);
                    String denomerator1 = num1.substring(0,divideIdx[0]);
                    String numerator1 = num1.substring(divideIdx[0]+1);
                    int denom1 = Integer.parseInt(denomerator1);
                    int nume1 = Integer.parseInt(numerator1);
                    int d = 0;
                    for(int i = 0; i<num2.length();i++){
                        if(num2.charAt(i) == '/'){
                            d = i;
                            break;
                        }
                    }
                    String denominator2 = num2.substring(0,d);
                    String numerator2 = num2.substring(d+1);
                    int denom2 = Integer.parseInt(denominator2);
                    int nume2 = Integer.parseInt(numerator2);
                    int denom_result = denom1*denom2;
                    int nume_result = nume1*nume2;
                    int gcd = FractionalNumber.gcd(denom_result,nume_result);
                    if(nume_result/gcd == 1){
                        System.out.println(denom_result/gcd);
                    } else{
                        System.out.println(denom_result/gcd+"/"+nume_result/gcd);
                    }
                }
            }else{
                if(equation.charAt(divideIdx[0]-1) == ' ') {
                    // ex. 4 / 2/3
                    String num1 = equation.substring(0,divideIdx[0]-1);
                    String num2 = equation.substring(divideIdx[0]+2);
                    int d = 0;
                    for(int i = 0; i<num2.length();i++){
                        if(num2.charAt(i) == '/'){
                            d = i;
                            break;
                        }
                    }
                    String denominator2 = num2.substring(0,d);
                    String numerator2 = num2.substring(d+1);
                    int denom2 = Integer.parseInt(denominator2);
                    int nume2 = Integer.parseInt(numerator2);
                    int numint = Integer.parseInt(num1);
                    int denom_result = numint * nume2;
                    int nume_result = denom2;
                    int gcd = FractionalNumber.gcd(denom_result,nume_result);
                    if(nume_result/gcd == 1){
                        System.out.println(denom_result/gcd);
                    } else{
                        System.out.println(denom_result/gcd+"/"+nume_result/gcd);
                    }
                }else{
                    // ex. 2/3 / 4
                    String num1 = equation.substring(0,divideIdx[1]-1);
                    String num2 = equation.substring(divideIdx[1]+2);
                    String denomerator1 = num1.substring(0,divideIdx[0]);
                    String numerator1 = num1.substring(divideIdx[0]+1);
                    int denom1 = Integer.parseInt(denomerator1);
                    int nume1 = Integer.parseInt(numerator1);
                    int numint = Integer.parseInt(num2);
                    int denom_result = denom1;
                    int nume_result = nume1*numint;
                    int gcd = FractionalNumber.gcd(denom_result,nume_result);
                    if(nume_result/gcd == 1){
                        System.out.println(denom_result/gcd);
                    } else{
                        System.out.println(denom_result/gcd+"/"+nume_result/gcd);
                    }
                }
            }
        } else if(divide == 3){
            //ex. 2/3 / 2/1
            String num1 = equation.substring(0,divideIdx[1]-1);
            String num2 = equation.substring(divideIdx[1]+2);
            String denomerator1 = num1.substring(0,divideIdx[0]);
            String numerator1 = num1.substring(divideIdx[0]+1);
            int denom1 = Integer.parseInt(denomerator1);
            int nume1 = Integer.parseInt(numerator1);
            int d = 0;
            for(int i = 0; i<num2.length();i++){
                if(num2.charAt(i) == '/'){
                    d = i;
                    break;
                }
            }
            String denominator2 = num2.substring(0,d);
            String numerator2 = num2.substring(d+1);
            int denom2 = Integer.parseInt(denominator2);
            int nume2 = Integer.parseInt(numerator2);
            int denom_result = denom1*nume2;
            int nume_result = nume1*denom2;
            int gcd = FractionalNumber.gcd(denom_result,nume_result);
            if(nume_result/gcd == 1){
                System.out.println(denom_result/gcd);
            } else{
                System.out.println(denom_result/gcd+"/"+nume_result/gcd);
            }
        }
    }
}

class FractionalNumber {
    private int numerator;
    private int denominator;
    public static int gcd(int a, int b){
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return Math.abs(a);
    }
}