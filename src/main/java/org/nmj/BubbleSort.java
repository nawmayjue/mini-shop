package org.nmj;

public class BubbleSort {
    public static void main(String[] args){
        int[] array = {12,6,3,2,8,1};
        int[] numbers = sortingMethod(array);
        int wantedNum = 8;
        int left = 0;
        int right = numbers.length-1;
        while(left<=right){
            int mid = (right+left)/2;

            if (numbers[mid]==wantedNum) {
                System.out.println(mid);
            } else if (numbers[mid]<wantedNum) {
                left = mid+1;
            } else {
                right = mid - 1;
            }

        }
    }

    public static int[] sortingMethod(int[] array){
        for(int i = 0; i < array.length-1; i++){
            for(int j = 0; j < array.length-i-1; j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1]=temp;
                }
            }
        }
        return array;
    }
}
