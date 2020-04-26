package chapter2;

public class DoubleEx2 {
    public static void main(String[] args){
        double dnum = 1;
        for(int i=0; i<10000; i++){
            dnum = dnum + 0.1;
        }
        System.out.println(dnum);
    }
}

// 결과가 1001이 나와야할 것 같지만 1001.000000000159가 나오는 것으로, 부동 소수점 방식은 오차를 포함한다는 것을 알 수 있음
// 오차를 감수하고서라도 더 넓은 범위의 실수 값을 표현하기 위하여 부동 소수점 방식을 사용
