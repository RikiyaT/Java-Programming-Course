import java.util.Scanner;
public class paint{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        sc.nextLine();
        char[][] image = new char[s][s];
        for(int row=0; row<s; row++){
            String str = sc.nextLine();
            for(int col=0; col<s; col++) {
                image[row][col] = str.charAt(col);
            }
        }
        while(sc.hasNext()) {
            String operation = sc.nextLine();
            String[] oper = operation.split(" ");
            if(oper[0].equals("end")) break;
            if(oper[0].equals("dot")) {
                int Y = Integer.parseInt(oper[1]); 
                int X = Integer.parseInt(oper[2]);
                image[X][Y] = '#';
            }
            else if(oper[0].equals("shift")) {
                int X = Integer.parseInt(oper[1]);
                int Y = Integer.parseInt(oper[2]);
                image = toHorizontal(image, s, X);
                image = toVertical(image, s, Y); 
            }
            else if(oper[0].equals("rotate")) {
                image = rotation(image, s); 
            }
        }
        for(int row=0; row<s; row++) {
            for(int col=0; col<s; col++) {
                System.out.print(image[row][col]);
            }
            System.out.print('\n');
        }
        sc.close();
    }
    public static char[][] rotation(char[][] image, int s) {
        char[][] rotated = new char[s][s];
        for(int row=0; row<s; row++) {
            for(int col=0; col<s; col++) {
                rotated[row][col] = image[s-1-col][row];
            }
        }
        return rotated;
    }
    public static char[][] toHorizontal(char[][] image, int s, int x) {
        char[][] trans = new char[s][s];
        for(int row=0; row<s; row++) {
            for(int col=0; col<s; col++) {
                if((col-x < s) && (col-x >= 0)) {
                    trans[row][col] = image[row][col-x];
                }else {
                    trans[row][col] = '.';
                }
            }
        }
        return trans;
    }

    public static char[][] toVertical(char[][] image, int s, int y) {
        char[][] trans = new char[s][s];
        for(int col=0; col<s; col++) {
            for(int row=0; row<s; row++) {
                if((row-y < s) && (row-y >= 0)) {
                    trans[row][col] = image[row-y][col];
                }else {
                    trans[row][col] = '.';
                }
            }
        }
        return trans;
    }
}