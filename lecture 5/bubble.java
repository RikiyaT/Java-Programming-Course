import java.util.Scanner;

public class bubble {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int [] arr=new int[x];
        for(int i=0; i<x; i++){
            arr[i]=sc.nextInt();
        }
        
        while(true){
            int count=0;
            for(int i=0; i<x-1; i++){
                if(arr[i]>arr[i+1]){
                    int temp;
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;

                    for(int a=0; a<x; a++){
                        System.out.print(arr[a]+" ");
                    }

                    System.out.println();

                    count=1;
                }
            }

            if(count==0){
                break;
            }

        }
        sc.close();
        
    }
}