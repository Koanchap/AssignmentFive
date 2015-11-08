
import java.io.*;
import java.util.*;

/**
 *
 * @author Kotah Chapman Date Written : 11/8/2015 Purpose : comparisons of words
 * in two files to find number of correct and incorrectly spelled words using
 * Binary Search Trees
 */
public class AssignmentFive {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AssignmentFive rd = new AssignmentFive();
        rd.readFileDictionary();
        rd.readFileOliver();
        long avgfound = rd.counterWFCompared / rd.counterWFound;
        long avgnotfound = rd.counterWNFCompared / rd.counterWNotFound;
        System.out.println("Average Comparisons Found = " + avgfound + " Comparisons for words found = " + rd.counterWFCompared + " Number of words found = " + rd.counterWFound);
        System.out.println("Average Comparisons Not Found = " + avgnotfound + " Comparisons for words not found = " + rd.counterWNFCompared + " Number of words found = " + rd.counterWNotFound);

    }
    private BinarySearchTree[] dictionary;
    private long counterWFound = 0;//counter for words found
    private long counterWFCompared = 0;// counter for comparisons for words found
    private long counterWNotFound = 0;//counter for words not found
    private long counterWNFCompared = 0;// counter for comparisons for words not found
    private int[] count = new int[1];//integer array of one element
    /*
     * @requires : Binary Search Tree variable to have been declared.
     * @ensures : Default Constructor creates 26 Binary Search Trees, and instantiates 26
     * Binary Search Trees to the 26 letters a through z
     */

    public AssignmentFive() {
        dictionary = new BinarySearchTree[26];
        for (int i = 0; i < dictionary.length; i++) {
            dictionary[i] = new BinarySearchTree<String>();
        }
    }

    /**
     * @requires : input file random_dictionary.txt
     * @ensures : if input file is read, Binary Search Trees a through z filled
     * with words from file, otherwise error reading file
     */
    public void readFileDictionary() {
        File f = new File("random_dictionary.txt");
        try {
            Scanner inf = new Scanner(f);
            while (inf.hasNext()) {
                String d = inf.nextLine();
                d = d.toLowerCase();
                dictionary[(int) d.charAt(0) - 97].insert(d);
            }
            inf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @requires : input file oliver.txt
     * @ensures : if input file is read, a String is filled with words from file
     * and searched in dictionary Binary Search Trees, and if found increase
     * words found counter and comparisons to find counter, otherwise increase
     * words not found and comparisons to not find counter. Otherwise error
     * reading file
     */
    public void readFileOliver() {
        try {
            FileInputStream inf = new FileInputStream(new File("oliver.txt"));
            char let;
            String str = "";
            String key = "";
            int n = 0;

            while ((n = inf.read()) != -1) {
                let = (char) n;
                if (Character.isLetter(let)) {
                    str += Character.toLowerCase(let);
                }
                if ((Character.isWhitespace(let) || let == '-') && !str.isEmpty()) {
                    key = str;
                    str = "";
                    if (dictionary[(int) key.charAt(0) - 97].search(key, count)) {
                        counterWFound++;
                        counterWFCompared += count[0];
                    } else {
                        counterWNotFound++;
                        counterWNFCompared += count[0];
                    }

                }

            }
            inf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
