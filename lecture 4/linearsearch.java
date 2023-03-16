import java.util.Scanner;

public class linearsearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int [] arr=new int[x];
        for(int i=0; i<x; i++){
            arr[i]=sc.nextInt();
        }
        int goal=sc.nextInt();

        for(int i=0; i<x; i++){

            System.out.println(i+" "+arr[i]);

            if(arr[i]>=goal){
                break;
            }
        }

        sc.close();
        
    }
}