import java.util.Scanner;
import java.util.*;


public class rotation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s=sc.nextInt();
        char a[][]=new char[s][s];

        
        for(int i=0;i<s;i++){
            String line=sc.next();
            for(int j=0; j<s; j++){
                a[i][j]=line.charAt(j);
            }
        }


        for(int i=0;i<s;i++)  
        {  
            for(int j=i;j<s;j++)  
            {  
                char temp = a[i][j];  
                a[i][j] = a[j][i];  
                a[j][i] = temp;  
            }  
        }  

        for(int i=0;i<s;i++)  
        {  
            int low = 0, high = s-1;  
            while(low < high)  
            {  
                char temp = a[i][low];  
                a[i][low] = a[i][high];  
                a[i][high] = temp;  
                low++;  
                high--;  
            }  
        }


        for(int i=0; i<s; i++){
            for(int j=0; j<s; j++){
                    String str=String.valueOf(a[i][j]);
                    System.out.print(str);
            }
            System.out.println();
        }

    }

}