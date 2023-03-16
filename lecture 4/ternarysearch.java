import java.util.Scanner;
import java.lang.Math;

public class ternarysearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double a=sc.nextDouble();
        double b=sc.nextDouble();
        double h=Math.pow(10, 9);
        double l=0;
        double p=Math.pow(10, -6);

        while((h-l)>=p){
            double x1=l+(h-l)/3;
            double x2=h-(h-l)/3;
            double y1=a*x1*x1+b*x1;
            double y2=a*x2*x2+b*x2;
            
            System.out.printf("%.6f %.6f %.6f %.6f %.6f %.6f", l, x1, x2, h, y1, y2);
            if(y1>y2){
                h=x2;
            }else if(y1<y2){
                l=x1;
            }
        }

        sc.close();
        
    }
}