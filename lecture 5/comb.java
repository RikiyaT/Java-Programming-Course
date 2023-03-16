import java.util.Scanner;

public class comb {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int [] a=new int[x];
        for(int i=0; i<x; i++){
            a[i]=sc.nextInt();
        }
        
        int h=x*10/13;

        while(true){
            int t=0;
            for(int i=0; i+h<x; ++i){
                if(a[i]>a[i+h]){
                    int temp;
                    temp=a[i];
                    a[i]=a[i+h];
                    a[i+h]=temp;
                    ++t;
                    for(int z=0; z<x; z++){
                        System.out.print(a[z]+ " ");
                    }
                    System.out.println();
                }

            }

            if(h==1){
                if(t==0){
                    break;
                }
            }else{
                h=h*10/13;
            }
        }
        

        sc.close();
        
    }
}