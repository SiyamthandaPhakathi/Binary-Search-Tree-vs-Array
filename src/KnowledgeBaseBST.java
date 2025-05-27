import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * KnowledgeBaseBST stores knowledge entries in a Binary Search Tree.
 * Entries can be loaded from a file, appended, updated, and searched.
 */
public class KnowledgeBaseBST {
    
    BinarySearchTree<Entry> entries;

    public KnowledgeBaseBST(){}
    
    /**
     * Constructs a KnowledgeBaseBST and loads entries from the specified file.
     *
     * @param fileName The path to the file containing knowledge entries.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public KnowledgeBaseBST(String fileName) throws IOException{

        entries = this.addEntries(fileName);
        System.out.println("Knowledge base loaded successfully.");
    }


    /**
     * Reads knowledge entries from a file, tokenizes each line, and inserts them into a Binary Search Tree.
     *
     * @param fileName The path to the file containing knowledge entries.
     * @return A BinarySearchTree containing the loaded entries.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    private BinarySearchTree<Entry> addEntries(String fileName) throws IOException{
        BinarySearchTree<Entry> entries = new BinarySearchTree<Entry>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String entry;
        while((entry = br.readLine())!= null){
            StringTokenizer st = new StringTokenizer(entry,"\t");
            entries.insert(new Entry(st.nextToken(),st.nextToken(),Double.parseDouble(st.nextToken())));

        }
        br.close();

        return entries;  
    }
    

    /**
     * Appends entries from a new file to the existing Binary Search Tree, handling duplicate entries.
     *
     * @param newFileName The path to the file containing new knowledge entries.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public void appendEntries(String newFileName) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(newFileName));
        String entry;

        
        while ((entry = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(entry, "\t");
            Entry newEntry = new Entry(st.nextToken(), st.nextToken(), Double.parseDouble(st.nextToken()));

            BinaryTreeNode<Entry> existingEntry = entries.find(newEntry);

            if (existingEntry == null) {
                entries.insert(newEntry);
            } else if (existingEntry.entry.getConfidenceScore() < newEntry.getConfidenceScore()) {
                entries.delete(existingEntry.entry);
                entries.insert(newEntry);
            }
        }

        br.close();

    }

    /**
     * Finds and returns an Entry object with the given term.
     *
     * @param term The term to search for.
     * @return The Entry object, or null if not found.
     */
    public Entry Find(String term){
        try {
            return entries.find(new Entry(term,null,0)).entry;
        } catch (Exception e) {
            return null;
        }
        
    }

    /**
     * Performs a partial search for entries with terms starting with the given prefix.
     *
     * @param prefix The prefix to search for.
     */
    public void PartialSearch(String prefix) {
        //Start our search from the root
        PartialSearch(prefix, entries.root);  
    }

    /**
     * Recursively performs a partial search for entries with terms starting with the given prefix.
     *
     * @param prefix The prefix to search for.
     * @param node The current node in the Binary Search Tree.
     */
    private void PartialSearch(String prefix, BinaryTreeNode<Entry> node) {
        if (node == null) {
            return;
        }

        Entry entry = node.entry;
        if (entry.getTerm().startsWith(prefix)) {
            entries.visit(node); 
        }

        PartialSearch(prefix, node.getLeft());
        PartialSearch(prefix, node.getRight());
    }


    /**
     * Finds and returns the confidence score of an entry with the given term and statement.
     *
     * @param term The term to search for.
     * @param statement The statement to search for.
     * @return The confidence score, or null if not found.
     */
    public Double Find(String term, String statement){
        Entry entry = Find(term);
        if(entry.getStatement().equals(statement)){
            return entry.getConfidenceScore();
        }
        return null;
    }
    
    
    /**
     * Updates an existing entry or inserts a new entry based on the given Entry object.
     * If the entry term does not exist, it inserts the newEntry.
     * If the entry term does exist, it compares confidence scores and updates if the newEntry has a higher score.
     *
     * @param newEntry The Entry object to update or insert.
     */
    public void UpdateEntry(Entry newEntry){
        Entry oldEntry = this.Find(newEntry.getTerm());
        //Check if term exists in the tree
        //adds new entry
        if(oldEntry == null){
            entries.insert(newEntry);
            System.out.println("Entry "+newEntry.getTerm()+" has been added to the knowledge base.");
        }
        //updates entry based on the confidence scores
        else if(oldEntry.getConfidenceScore() < newEntry.getConfidenceScore()){
            entries.delete(oldEntry);
            entries.insert(newEntry);
            System.out.println("Statement for the term "+oldEntry.getTerm()+" has been updated.");
        }

        else{System.out.println("Entry has not been updated,confidence score is too low.");}
        
    }

}
