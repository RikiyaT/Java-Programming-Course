import java.util.Scanner;

public class selection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int [] a=new int[x];
        for(int i=0; i<x; i++){
            a[i]=sc.nextInt();
        }
        

        for(int t=0; t<x-1; t++){
            int min=t;
            for(int i=t+1; i<x; i++){
                if(a[i]<a[min]){
                    min=i;
                }
            }

            if(a[t]!=a[min]){
                int temp=0;
                temp = a[min];
                a[min] = a[t];
                a[t] = temp;

                for(int i=0; i<x; i++){
                    System.out.print(a[i]+" ");
                }

                System.out.println();
            }

            
        }

        sc.close();
        
    }
}