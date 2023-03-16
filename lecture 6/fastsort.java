import java.util.Scanner;

public class fastsort {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int i=sc.nextInt();

        calc(n, m, i);

    }

    public static void calc(int n, int m, int i){
    
        int count[]= new int[m];

        for(int a=0; a<m; a++){
            count[a]=0;
        }

        int f1=0;
        int f2=1%m;
        count[0]=1;
        count[f2]=1;

        for(int a=0; a<n-2; a++){
            int rem=(f2+f1)%m;
            count[rem]=count[rem]+1;

            f1=f2;
            f2=rem;
        }

        if(count[0]>=i){
            System.out.println("0");
        }else{
            for(int a=1; a<m; a++){
                count[a]=count[a-1]+count[a];
                if(count[a]>=i){
                    System.out.println("this is " + a);
                    break;
                }
            }
        }

    }

}