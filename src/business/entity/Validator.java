package business.entity;

import java.util.Scanner;

public class Validator {
    public static String getString(Scanner scanner, String suggestion) {
        String input = "";
        do {
            System.out.println(suggestion);
            input = scanner.nextLine();
            if(input.isBlank()){
                System.out.println("Vui lòng không để trống.");
            }else {
                break;
            }
        }while(true);
        return input;
    }
    public static int getInt(Scanner scanner, String suggestion) {
            String input = "";
        do {
             input = getString(scanner, suggestion);
        try {
            return Integer.parseInt(input);
        }catch(NumberFormatException e){
            System.out.println("Vui lòng nhập vào một số.");
        }
        }while (true);
    }

}
