import java.util.Scanner;

public class sum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int a=0;;a++){
            String x = sc.nextLine();
            int sum=0;

            for(int i=0; i<x.length(); i++){
                char j=x.charAt(i);
                int z=j-'0';
                sum=sum+z;
            }
            if(sum==0&&x.length()==1){
                break;
            }else{
                System.out.println(sum);
            }
            
        }

        sc.close();
    }
}