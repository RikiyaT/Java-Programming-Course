import java.util.Scanner;
import java.util.*;


public class fill {


    public static void fill(char a[][], int s, int x, int y){

        a[x][y]='#';

        if(x>0){
            if(a[x-1][y]=='.'){
                fill(a, s, x-1, y);
            }
        }
        if(y>0){if(a[x][y-1]=='.'){
                fill(a, s, x, y-1);
            }
        }
        if(x<s-1){
            if(a[x+1][y]=='.'){
                fill(a, s, x+1, y);
            }
        }
        if(y<s-1){
            if(a[x][y+1]=='.'){
                fill(a, s, x, y+1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s=sc.nextInt();
        int y=sc.nextInt();
        int x=sc.nextInt();
        char a[][]=new char[s][s];

        
        for(int i=0; i<s; i++){
            String line=sc.next();
            for(int j=0; j<s; j++){
                a[i][j]=line.charAt(j);
            }
        }

        
        if(a[x][y]=='.'){
            fill(a, s, x, y);
        }

        for(int i=0;i<s;i++){
            for(int j=0; j<s; j++){
                String str=String.valueOf(a[i][j]);
                System.out.print(str);
            }
        System.out.println();
        }

        sc.close();
    }

}