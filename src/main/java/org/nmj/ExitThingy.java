package org.nmj;

import java.util.ArrayList;
import java.util.Scanner;

public class ExitThingy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput= null;
        ArrayList<String> inputs = new ArrayList<>();
        do {
            System.out.println("type whatever you want: ");
            userInput = scanner.nextLine();
            if(!userInput.equalsIgnoreCase("exit"))
            inputs.add(userInput);
        }while(!userInput.equalsIgnoreCase("exit"));
        if(userInput.equalsIgnoreCase("exit")){
            System.out.println(inputs);
            System.out.println("Bye!");
        }
    }
}
