package com.ibingbo.algorithmapp.sort;

/**
 * @author zhangbingbing
 * @date 2020/12/15
 */
public class SortDemo {

    public static void main(String[] args) {
        int[] arr = {2, 5, 3, 1, 7, 9, 6, 8, 4, 0};
        int[] arr1 = {1, 4, 6, 8, 9, 10};
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }
                quickSort(arr, 0, arr.length - 1);
        //        selectSort(arr, arr.length);
        //        maoPao(arr, arr.length);
        mergeSort(arr, arr1);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }
    }

    /**
     * 快速排序
     * 针对数组arr，首先任意选取一个数据（通常先用数组的第一个数）作为关键数据，
     * 然后将所有比它小的数据都放到它前面，所有比它大的数据放到它后面
     */
    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int key = arr[start];
        int i = start, j = end;
        while (i < j) {
            while (i < j && key <= arr[j]) {
                j--;
            }
            arr[i] = arr[j];
            while (i < j && key >= arr[i]) {
                i++;
            }
            arr[j] = arr[i];
        }
        arr[i] = key;
        quickSort(arr, start, i - 1);
        quickSort(arr, i + 1, end);
    }

    /**
     * 选择排序
     * 循环遍历第N次选出最N大的数据放在第N个位置上
     */
    public static void selectSort(int[] arr, int len) {
        if (len <= 0) {
            return;
        }
        int index, tmp;
        for (int i = 0; i < len; i++) {
            index = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[index] < arr[j]) {
                    index = j;
                }
            }
            tmp = arr[i];
            arr[i] = arr[index];
            arr[index] = tmp;
        }
    }

    /**
     * 冒泡排序
     * 循环遍历，两两比较，把大的数往后移
     *
     * @param arr
     * @param len
     */
    public static void maoPao(int[] arr, int len) {
        int tmp;
        for (int j = len - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                if (arr[i] > arr[i + 1]) {
                    tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                }
            }
        }
    }

    /**
     * 归并排序
     * @param a
     * @param b
     */
    public static void mergeSort(int[] a, int[] b) {
        int[] arr = new int[a.length + b.length];
        int i = 0, j = 0, index = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                arr[index++] = a[i++];
            } else {
                arr[index++] = b[j++];
            }
        }
        while (i < a.length) {
            arr[index++] = a[i++];
        }
        while ((j < b.length)) {
            arr[index++] = b[j++];
        }
        for (int jj = 0; jj < arr.length; jj++) {
            System.out.println(arr[jj]);
        }
    }

}
