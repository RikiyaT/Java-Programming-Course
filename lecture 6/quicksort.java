import java.util.Scanner;
public class quicksort {
    public static void printt(int array[]) {
        for(int i=0;i<array.length;i++) {
            System.out.print(array[i]+" ");
        }
        System.out.print("\n");
    }

    public static void Quicksort(int arr[],int l,int r){
        int p=(l+r)/2;
        int i = l;
        int j = r;
        int pivot=arr[p];
        if(arr[p]>arr[l]&&arr[p]<arr[r]){
            pivot = arr[p];
        }else if(arr[l]>arr[p]&&arr[l]<arr[r]){
            pivot =arr[l];
        }else if(arr[p]<arr[l]&&arr[p]>arr[r]){
            pivot =arr[p];
        }else if(arr[l]<arr[p]&&arr[l]>arr[r]){
            pivot =arr[l];
        }else{
            pivot=arr[r];
        }

        while(i<=j){
            while(arr[i]<pivot){
                i++;
            }
            while(arr[j]>pivot){
                j--;
            }
            if(i<=j){
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
                i++;
                j--;
            }
        }
        printt(arr);
        if (l<j){
            Quicksort(arr,l,j);

        }
        if (r>i){
            Quicksort(arr,i,r);

        }

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int a[]=new int[n];
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
        }

        Quicksort(a,0,n-1);

    }
}