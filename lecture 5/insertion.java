import java.util.Scanner;

public class insertion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int [] a=new int[x];
        for(int i=0; i<x; i++){
            a[i]=sc.nextInt();
        }
        
        for(int j=1; j<a.length; j++){
            int key=a[j];
            int i=j-1;

            while (i >= 0 && a[i] > key) {
                a[i + 1] = a[i];
                i--;
            }

            a[i+1]=key;

            if(a[j]!=key){
                
                for(int c=0; c<a.length; c++){
                    System.out.print(a[c]+" ");
                }
            }

            System.out.println();
        }

            
        

        sc.close();
        
    }
}