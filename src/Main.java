import java.util.Scanner;
/**
 * This program is responsible for handling interaction between the array and BST implementations of the Knowledge base programs
 * 
 * @param args Not used
 * @author Siyamthanda Phakathi
 * @version March 2025
 */
public class Main {
    
    public static void main(String[] args) {
        
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose which data structure you would like to use");
            System.out.println("1: Simple Array");
            System.out.println("2: Binary Search Tree");

            int choice = scanner.nextInt();

            if(choice == 1){GenericsKbArrayApp.main(args);}
            else if(choice == 2){GenericsKbBSTApp.main(args);}
            else{System.out.println("Closing program.");}

        }
        catch(Exception e){
            System.out.println("Experienced error. Closing program.");
        }
        

    }
}
