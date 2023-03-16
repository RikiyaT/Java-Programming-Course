import java.util.Scanner;

public class binarysearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int [] arr=new int[x];
        for(int i=0; i<x; i++){
            arr[i]=sc.nextInt();
        }
        int goal=sc.nextInt();

        int [] half;

        while(true){
            if(
                System.out.println(half+" "+arr[half]);

            if(arr[half]==goal){
                break;
            }else if(arr[half]>goal){
                if(){
                    half=half/2;
                }else{
                    break;
                }

            }else if(arr[half]<goal)
        }

        sc.close();
        
    }
}