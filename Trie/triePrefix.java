// Given  strings. Each string contains only lowercase letters from (both inclusive). The set of  strings is said to be GOOD SET if no string is prefix of another string else, it is BAD SET. (If two strings are identical, they are considered prefixes of each other.)

// For example, aab, abcde, aabcd is BAD SET because aab is prefix of aabcd.

// Print GOOD SET if it satisfies the problem requirement. 
// Else, print BAD SET and the first string for which the condition fails.

// Input Format 
// First line contains , the number of strings in the set. 
// Then next  lines follow, where  line contains  string.

// Constraints 
 
//  Length of the string 

// Output Format 
// Output GOOD SET if the set is valid. 
// Else, output BAD SET followed by the first string for which the condition fails.

import java.io.*;
import java.util.*;

public class Solution {

    static class TrieNode{
        
        TrieNode[] children = new TrieNode[26];;
        boolean endOfWord;
        
        TrieNode(){
            Arrays.fill(this.children, null);
            endOfWord = false;
        }
    }
    
    static class Trie {
        
        public boolean insert(TrieNode root, String str) {
            TrieNode iterator = root;
            
            for(int i = 0; i < str.length(); i++) {
                int index = str.charAt(i) - 'a';
    
                if(iterator.children[index] == null) {
                    iterator.children[index] = new TrieNode();
                } else if( iterator.children[index].endOfWord == true) {
                    return false;
                }
                iterator = iterator.children[index];

            }
            
            for(int i = 0;i < 26; i++) {
                if(iterator.children[i] != null) {
                    return false;
                }
            } // if base string comes after the main string
            
            iterator.endOfWord = true;
            
            return true;
        }
        
    }
    
    static TrieNode root;
    
    public static void main(String[] args) throws IOException {        
        InputStreamReader io = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(io);
        
        TrieNode root = new TrieNode();
        int num = Integer.parseInt(br.readLine());
        Trie tr = new Trie();
        boolean isBad = false;
        
        while(num-- > 0){
            String str = br.readLine();
            if(!tr.insert(root, str)){
                System.out.println("BAD SET");
                System.out.println(str);
                isBad = true;
                break;
            }
        }
        
        if(!isBad) {
            System.out.println("GOOD SET");
        }
    }
}