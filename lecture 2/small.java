import java.util.Scanner;

public class small {
    public static void main(String[] args) {
        Scanner i = new Scanner(System.in);
        int a = i.nextInt();
        int b = i.nextInt();

        if(a>b){
            System.out.println("a > b");
        }else if(b>a){
            System.out.println("a < b");
        }else{
            System.out.println("a == b");
        }


        i.close();
    }
}