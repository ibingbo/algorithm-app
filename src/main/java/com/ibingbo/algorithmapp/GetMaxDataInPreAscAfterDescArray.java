package com.ibingbo.algorithmapp;

/**
 * 一个数组先升序再降序，用最优时间复杂度，求最大值？例如[1,2,2,2,2,3,1]
 * @author zhangbingbing
 * @date 2021/1/11
 */
public class GetMaxDataInPreAscAfterDescArray {

    public static void main(String[] args) {
        int[] arr = new int[] {1, 3, 5, 6, 4, 2, 1};
        System.out.println(getMax(arr));

    }

    public static int getMax(int[] arr) {
        if (arr.length < 2) {
            return arr[0];
        }
        int lo = 0, hi = arr.length - 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] > arr[mid + 1]) {
                hi = mid;
            } else if (arr[mid] < arr[mid + 1]) {
                lo = mid + 1;
            } else {
                if (arr[lo] > arr[hi]) {
                    hi--;
                } else {
                    lo++;
                }
            }
        }
        return arr[lo];
    }

}
