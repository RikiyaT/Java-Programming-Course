import java.util.Scanner;
public class circle {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
            double r = sc.nextDouble();
            double pi=Math.PI;

            double area=r*r*pi;
            double circum=r*2*pi;

            System.out.printf("%f %f", area, circum);

        sc.close();
    }
}