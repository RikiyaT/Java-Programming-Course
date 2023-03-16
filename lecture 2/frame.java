import java.util.Scanner;

public class frame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h,w;
        
        for(int a=0;;a++){
            h = sc.nextInt();
            w = sc.nextInt();

            if(h==0 && w==0){
                break;
            }else{

                for(int i=0; i<h; i++){
                    for(int j=0; j<w; j++){
                        if(i==0||i==h-1||j==0||j==w-1){
                            System.out.print("#");
                        }else{
                            System.out.print(".");
                        }
                    }
                    System.out.print("\n");
                }
                System.out.print("\n");
            }
        }

        sc.close();
    }
}