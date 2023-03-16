import java.util.Scanner;

public class reverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int temp=0;

        int[] a = new int[x];
        for (int i = 0; i < a.length; i++) {
            a[i] = sc.nextInt();
        }
        for(int j=a.length-1; j>=0; j--){
            System.out.print(a[j]);
        }
        sc.close();
    }
}