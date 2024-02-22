package jmwdev.hacker;

public class CompareTriplets {
    public static void main(String[] args) {
        var a = new int[]{1, 2, 3};
        var b = new int[]{3, 2, 1};
        var result = compareTriplets(a,b);
        System.out.printf("%d,%d%n",result[0], result[1]);
    }

    static int[] compareTriplets(int[] a, int[] b) {
        int[] result = new int[]{0,0};
        for (int i = 0; i < a.length; i++) {
            if(a[i] > b[i]) {
                result[0]+=1;
            } else if(a[i] < b[i]) {
                result[1]+=1;
            }
        }
        return result;
    }
}
