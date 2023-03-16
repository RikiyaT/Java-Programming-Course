import java.util.Scanner;

public class mergesort {
    public static void printt(int array[]) {
        for(int i=0;i<array.length;i++) {
            System.out.print(array[i]+" ");
        }
        System.out.print("\n");
    }

    public static void merge(int arr[], int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;
 
        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i){
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; ++j){
            R[j] = arr[m + 1 + j];
        }

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
 

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }

        printt(arr);
    }

    public static void sort(int a[], int l, int r){
        if (l < r) {
            int m = (l + r) / 2;

            if((l+r)%2 == 0) m--;
 
            sort(a, l, m);
            sort(a, m + 1, r);
 
            merge(a, l, m, r);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x=sc.nextInt();
        int a[]=new int[x];
        for(int i=0;i<x;i++){
            a[i]=sc.nextInt();
        }

        sort(a,0, x-1);

    }

}