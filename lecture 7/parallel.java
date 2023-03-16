import java.util.Scanner;
import java.util.*;


public class parallel {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s=sc.nextInt();
        int x=sc.nextInt();
        int y=sc.nextInt();
        char a[][]=new char[s][s];

        
        for(int i=0;i<s;i++){
            String line=sc.next();
            for(int j=0; j<s; j++){
                a[i][j]=line.charAt(j);
            }
        }

        for(int i=0; i<s; i++){
            for(int j=0; j<s; j++){
                if(i-y>=s||i-y<0||j-x>=s||j-x<0){
                    System.out.print(".");
                }else{
                    String str=String.valueOf(a[i-y][j-x]);
                    System.out.print(str);
                }
            }
            System.out.println();
        }

    }

}