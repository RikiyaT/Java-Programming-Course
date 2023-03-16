import java.util.Scanner;
import java.lang.Math;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int [] a=new int[x];
        for(int i=0; i<x; i++){
            a[i]=sc.nextInt();
        }
        
        int h = 1;
        int mx = x / 3 + (x % 3);

        for(int i=0; i<10000; i++){
            int p = (int)Math.pow(3, i);
            if ((p - 1) / 2 > mx) {
                break ;
            }
            h = (p - 1) / 2;
        }

        while(h>=1){
            for(int i = h; i < a.length; i++){
                boolean    chged = false;
                for (int j = i - h; j >= 0; j -= h) {
                    int temp = a[j + h];
                    if (a[j] > temp) {
                        chged = true;
                        a[j + h] = a[j];
                        a[j] = temp;
                    }
                }
                if (chged) {
                    for(int z=0; z<x; z++){
                        System.out.print(a[z]+ " ");
                    }
                    System.out.println();
                }
            }
            h /= 3;
        }

        sc.close();
        
    }
}