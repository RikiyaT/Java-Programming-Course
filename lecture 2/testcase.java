import java.util.Scanner;

public class testcase {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i=1;;i++){
            int x = sc.nextInt();
            if(x<=0|| x>10000){
                break;
            }
            System.out.println("Case " + i + ": " + x);
        }

        sc.close();
    }
}