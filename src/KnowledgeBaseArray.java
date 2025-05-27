
import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * KnowledgeBaseArray stores knowledge entries in an array.
 * Entries can be loaded from a file, appended, updated, and searched.
 */

public class KnowledgeBaseArray{

    private Entry[] entries;
    
    public KnowledgeBaseArray(){  }
    /**
     * Constructs a KnowledgeBaseArray and loads entries from the specified file.
     *
     * @param fileName The path to the file containing knowledge entries.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public KnowledgeBaseArray(String fileName) throws IOException{
        
        entries = this.addEntries(fileName);
        //use proper try and catch later, this is temp
        System.out.println("Knowledge base loaded successfully.");

    }

    /**
     * Reads knowledge entries from a file, tokenizes each line, and creates Entry objects.
     *
     * @param fileName The path to the file containing knowledge entries.
     * @return An array of Entry objects.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    private Entry[] addEntries(String fileName) throws IOException{

        BufferedReader br = new BufferedReader(new FileReader(fileName));

        int numOfLine = this.CountLines(fileName);

        //create array with enough spaces
        Entry[] entries = new Entry[numOfLine];

        for (int i = 0; i < numOfLine; i++){
            String entry = br.readLine();
            StringTokenizer st = new StringTokenizer(entry,"\t");
            entries[i] = new Entry(st.nextToken(),st.nextToken(),Float.parseFloat(st.nextToken()));
        }
        br.close();
        
        return entries;
    }

    /**
     * Appends entries from a new file to the existing array, handling duplicate entries.
     *
     * @param newFileName The path to the file containing new knowledge entries.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public void appendEntries(String newFileName) throws IOException {
        int numOfLines = this.CountLines(newFileName);
        BufferedReader br = new BufferedReader(new FileReader(newFileName));
        Entry[] appendedEntries = new Entry[this.entries.length + numOfLines];
        int index = 0;
    
        //Copy existing entries into the new Entries array
        for (Entry entry : this.entries) {
            appendedEntries[index] = new Entry(entry.getTerm(), entry.getStatement(), entry.getConfidenceScore());
            index++;
        }
    
        // Add new entries to array and update the duplicates accordingly
        while (index < appendedEntries.length) {
            String line = br.readLine();
                if (line == null) {
                break; 
            }
            StringTokenizer st = new StringTokenizer(line, "\t");
            Entry newEntry = new Entry(st.nextToken(), st.nextToken(), Float.parseFloat(st.nextToken()));
            int duplicateIndex = findDuplicateIndex(appendedEntries, newEntry.getTerm(), index);
    
            if (duplicateIndex == -1) {
                appendedEntries[index] = newEntry;
                index++;
            } else if (appendedEntries[duplicateIndex].getConfidenceScore() < newEntry.getConfidenceScore()) {
                appendedEntries[duplicateIndex] = newEntry;
            }
        }
        
        br.close();
        
    
        this.entries = appendedEntries;
    }
    
    /**
     * Helper method to find the index of duplicate entries.
     *
     * @param entries The array of Entry objects.
     * @param term The term to search for.
     * @param endIndex The index up to which to search.
     * @return The index of the duplicate entry, or -1 if not found.
     */
    private int findDuplicateIndex(Entry[] entries, String term, int endIndex) {
        for (int i = 0; i < endIndex; i++) {
            if (entries[i].getTerm().equals(term)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Counts the number of lines in a text file.
     *
     * @param text The path to the text file.
     * @return The number of lines in the file.
     * @throws IOException If an I/O error occurs while reading the file.
    */
    private int CountLines(String text) throws IOException{
        int lines = 0;
        BufferedReader file = new BufferedReader(new FileReader(text));

        while (file.readLine() != null){
            lines++;
        }
        file.close();
        return lines;
    }

    /**
     * Updates an existing entry with the given term, statement, and confidence score.
     *
     * @param term The term of the entry to update.
     * @param statement The new statement for the entry.
     * @param confidenceScore The new confidence score for the entry.
     */
    public void UpdateEntry(String term, String statement, double confidenceScore){
        int i = FindIndex(term);
        if(entries[i].getConfidenceScore() < confidenceScore){
            entries[FindIndex(term)] = new Entry(term,statement,confidenceScore);
            System.out.println("Statement for "+term+" has been updated");
        }
        else{System.out.println("Did not update, confidence score too low.");}
        
       
    }

    /**
     * Finds and returns an Entry object with the given term.
     *
     * @param term The term to search for.
     * @return The Entry object, or null if not found.
     */
    public Entry Find(String term){
        for(Entry entry : entries){
            if(entry.getTerm().equals(term)){
                return entry;
            }
        }
        return null;
    }

    /**
     * Finds and returns the confidence score of an entry with the given term and statement.
     *
     * @param term The term to search for.
     * @param statement The statement to search for.
     * @return The confidence score, or null if not found.
     */
    public Double Find(String term, String statement){
        for(Entry entry : entries){
            if(entry.getTerm().equals(term) && entry.getStatement().equals(statement)){
                return entry.getConfidenceScore();
            }
        }
        return null;
    }

    /**
     * Performs a partial search for entries with terms starting with the given prefix.
     *
     * @param prefix The prefix to search for.
     */
    public void PartialSearch(String prefix){
        boolean found = false;
        for(Entry entry : entries){
            if(entry.getTerm().startsWith(prefix)){
                System.out.println(entry);
            }
        }
        if(!found ){
            System.out.println("No entries found starting with \""+prefix+"\"");
        }
    }

    /**
     * Finds and returns the index of an entry with the given term.
     *
     * @param term The term to search for.
     * @return The index of the entry, or null if not found.
     */
    private Integer FindIndex(String term){
        int i = 0;
        for(Entry entry : entries){
            
            if(entry.getTerm().equals(term)){
                return i;
            }
            i++;
        }
        return null;
    }
}