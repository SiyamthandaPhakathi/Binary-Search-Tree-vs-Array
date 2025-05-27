import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Program to load a knowledge base throgh a file, query and search the knowledge base in memory and update it using an array.
 * 
 * @param args Not used
 * @author Siyamthanda Phakathi
 * @version March 2025 
 */

public class GenericsKbArrayApp{

    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        KnowledgeBaseArray KnowledgeBaseArray = new KnowledgeBaseArray();
        boolean knowledgeBasePopulated = false;
        while(true){
            try{
                System.out.println("Choose an action from the menu:");
                System.out.println("1. Load a knowledge base from a file");
                System.out.println("2. Update entry in the knowledge base");
                System.out.println("3. Search for a statement in the knowledge base by term");
                System.out.println("4. Search for a statement in the knowledge base by term and sentence");
                System.out.println("5. Search multiple entries with a partial term");
                System.out.println("6. Quit");
                System.out.print("Enter your choice:");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if(choice == 1){
                    System.out.print("Enter file name: ");
                    String fileName = scanner.nextLine();
                    if(!knowledgeBasePopulated){
                        KnowledgeBaseArray = new KnowledgeBaseArray(fileName);
                        knowledgeBasePopulated = true;
                    }
                    else{
                        KnowledgeBaseArray.appendEntries(fileName);
                        System.out.println("Knowledge base has been appended");
                    }
                    
                }
                else if(choice == 2 && knowledgeBasePopulated){
                    System.out.print("Enter the term: ");
                    String term = scanner.nextLine();
                    System.out.print("Enter the statement: ");
                    String statement = scanner.nextLine();
                    System.out.print("Enter the confidence score: ");
                    try{
                        double confidenceScore = Double.parseDouble(scanner.nextLine());
                        KnowledgeBaseArray.UpdateEntry(term,statement,confidenceScore);
                        
                    }
                    catch(Exception e){
                        System.out.println("Confidence score must be a double");
                    }
                    
                }
                else if(choice == 3 && knowledgeBasePopulated){
                    System.out.print("Enter the term to search: ");
                    String term = scanner.nextLine();
                    Entry entryToDisplay = KnowledgeBaseArray.Find(term);
                    if(entryToDisplay == null){System.out.println("Entry not found");}
                    else{
                        System.out.println("Statement found: "+entryToDisplay.getStatement() +" (Confidence score: " +entryToDisplay.getConfidenceScore()+")");
                    }
                    
                }
                else if(choice == 4 && knowledgeBasePopulated){
                    System.out.print("Enter the term: ");
                    String term = scanner.nextLine();
                    System.out.print("Enter the statement: ");
                    String statement = scanner.nextLine();
                    Double entryConfidenceScore = KnowledgeBaseArray.Find(term,statement);
                    if(entryConfidenceScore == null){System.out.println("Entry not found.");}
                    else{System.out.println("The statement was found and has a confidence score of "+entryConfidenceScore+".");}
                    
                }
                else if(choice == 5 && knowledgeBasePopulated){
                    System.out.print("Please enter prefix of term you want that start with it: ");
                    String prefix = scanner.nextLine();
                    KnowledgeBaseArray.PartialSearch(prefix);
                }
                else if(choice == 6){
                    break;
                }
                else if( choice > 0 && choice<6 && !knowledgeBasePopulated){
                    System.out.println("Knowledge base has not been populated. Please load knowledge base.");
                }
                else{
                    System.out.println("Please select only one of the options");
                }
            }
            
        
        catch(InputMismatchException e){
            System.out.println("Please enter a number between 1 and 5");
            scanner.nextLine();                
        }
        catch(IOException e){
            System.out.println("Experienced and error while loading knowledge base. Please ensure filename is correct.");
               
        }
        catch(Exception e){
            System.out.println("An error occured: " + e.getMessage());
                
        }
        
        
    }
    scanner.close();
    }

}