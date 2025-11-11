package org.nmj;

import java.util.*;

import com.sun.xml.fastinfoset.util.CharArray;
import org.apache.commons.lang3.ArrayUtils;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        String[] animals = {"cat", "dog", "rabbit", "mouse"};
        String wanted = "rabbit";
        for(String animal: animals){
            if (animal.equals(wanted)){
                System.out.println(animal);
            }
        }

        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
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
}
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter something(No space): ");
//        char[] array = scanner.nextLine().toCharArray();
//        String unShuffled = new String(array);
//        System.out.println("Array before shuffling - " + unShuffled);
//        ArrayUtils.shuffle(array);
//        String shuffled = new String(array);
//        System.out.println("Modified Array after shuffling - " + shuffled);
