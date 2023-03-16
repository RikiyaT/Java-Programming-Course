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

        int low=0;
        int high=x-1;


        while(low<=high){
            int mid=(high+low)/2;
            System.out.println(mid+" "+arr[mid]);

            if(arr[mid]<goal){
                low=mid+1;
            }else if(arr[mid]>goal){
                high=mid-1;
            }else if(arr[mid]==goal){
                break;
            }
            
        }

        sc.close();
        
    }
}