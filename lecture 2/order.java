import java.util.Scanner;

public class small {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[3];  

        int temp=0;

        for(int i=0; i<3; i++){   
            arr[i]=sc.nextInt();  
            System.out.println(" ");
        }

        for (int i = 0; i < 3; i++) {     
            for (int j = i+1; j < 3; j++) {     
               if(arr[i] > arr[j]) {    
                   temp = arr[i];    
                   arr[i] = arr[j];    
                   arr[j] = temp;    
               }     
            }     
        }    

        for (int i = 0; i < 3; i++) {     
            System.out.print(arr[i] + " ");    
        }    

        sc.close();
    }
}