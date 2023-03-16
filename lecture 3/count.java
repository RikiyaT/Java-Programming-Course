import java.util.Scanner;

public class count {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
        int[] counter= new int[x.length()];

        for(int i=1; i<=x.length(); i++){
            char c=x.charAt(i);
            char a=Character.toLowerCase(c);
            int ascii=a;
            counter[ascii]++;
        }

        for(int i=97; i<=122; i++){
            char key=(char)i;
            System.out.println(key+":"+counter[i]);
        }

        sc.close();
    }
}