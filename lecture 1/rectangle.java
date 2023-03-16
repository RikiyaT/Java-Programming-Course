import java.util.Scanner;

public class rectangle {
    public static void main(String[] args) {
        Scanner i = new Scanner(System.in);
        int a = i.nextInt();
        int b=i.nextInt();

        int area=a*b;
        int length=(2*a)+(2*b);

        System.out.println(area+" "+length);

        i.close();
    }
}