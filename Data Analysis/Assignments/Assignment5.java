import java.util.*;

class GFG {

    // function to find the length of longest
    // increasing contiguous subarray
    public static int lenOfLongIncSubArr(int arr[],
            int n) {
        // 'max' to store the length of longest
        // increasing subarray
        // 'len' to store the lengths of longest
        // increasing subarray at different
        // instants of time
        int max = 1, len = 1, tmp = 0;

        // traverse the array from the 2nd element
        for (int i = 1; i < n; i++) {
            // if current element if greater than
            // previous element, then this element
            // helps in building up the previous
            // increasing subarray encountered
            // so far
            if (arr[i] >= arr[0])
                len++;
            else {
                // check if 'max' length is less
                // than the length of the current
                // increasing subarray. If true,
                // than update 'max'
                if (max < len)
                    max = len;

                // reset 'len' to 1 as from this
                // element again the length of the
                // new increasing subarray is being
                // calculated
                len = 1;
            }
        }

        // comparing the length of the last
        // increasing subarray with 'max'
        if (max < len)
            max = len;

        // required maximum length
        return max;
    }

    /* Driver program to test above function */
    public static void main(String[] args) {
        int arr[] = { 1, 14, 5, 5, 2, 5 };
        int n = arr.length;
        System.out.println("Length = " +
                lenOfLongIncSubArr(arr, n));

    }
}