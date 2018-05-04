// We're going to make our own Contacts application! The application must perform two types of operations:

// add name, where  is a string denoting a contact name. This must store  as a new contact in the application.
// find partial, where  is a string denoting a partial name to search the application for. It must count the number of contacts starting with  and print the count on a new line.
// Given  sequential add and find operations, perform each operation in order.

// Input Format

// The first line contains a single integer, , denoting the number of operations to perform. 
// Each line  of the  subsequent lines contains an operation in one of the two forms defined above.

// Constraints

// It is guaranteed that  and  contain lowercase English letters only.
// The input doesn't have any duplicate  for the  operation.
// Output Format

// For each find partial operation, print the number of contact names starting with  on a new line.


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static class TrieNode {
        TrieNode [] tr = new TrieNode[26];
        ArrayList corresspondingContacts = new ArrayList();
        TrieNode(){
            Arrays.fill(this.tr, null);
        }
    }
    
    static TrieNode root;
    
    static class Trie {
        
        public void insert(String str, TrieNode root) {
            TrieNode iterator = root;
            
            for(int i = 0; i < str.length(); i++ ) {
                int index = str.charAt(i) - 'a';
                
                if(iterator.tr[index] == null) {
                    iterator.tr[index] = new TrieNode();
                }
                iterator = iterator.tr[index];
                iterator.corresspondingContacts.add(str);
            }
        }
        
        public int search(String str, TrieNode root) {
            TrieNode iterator = root;
            
            for(int i = 0; i < str.length(); i++) {
                int index = str.charAt(i) - 'a';
                
                if(iterator.tr[index] == null) {
                    return 0;
                } else {
                    iterator = iterator.tr[index];
                }
            }
            return iterator.corresspondingContacts.size();
        }
        
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        TrieNode root = new TrieNode();
        Trie trie = new Trie();
        for(int a0 = 0; a0 < n; a0++){
            String op = in.next();
            String contact = in.next();
            
            switch(op) {
                case "add":
                    trie.insert(contact, root);
                    break;
                case "find":
                    System.out.println(trie.search(contact, root));
                    break;
                default :
                    System.out.println("Should not come here!");
                    break;
            }
            
        }

    }
}
