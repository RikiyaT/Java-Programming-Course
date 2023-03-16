import java.util.Scanner;
import java.lang.Math;

public class bisection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double k=sc.nextDouble();
        double h=k;
        double l=0;
        double p=Math.pow(10, -6);

        while((h-l)>=p){
            double mid=l+(h-l)/2;
            double c=mid*mid;
            System.out.printf("%.6f %.6f %.6f %.6f", l, mid, h, c);
            if(c>k){
                h=mid;
            }else if(c<k){
                l=mid;
            }
        }

        sc.close();
        
    }
}