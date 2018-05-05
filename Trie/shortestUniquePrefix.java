// Find shortest unique prefix to represent each word in the list.

// Example:

// Input: [zebra, dog, duck, dove]
// Output: {z, dog, du, dov}
// where we can see that
// zebra = z
// dog = dog
// duck = du
// dove = dov



public class Solution {
    
    class TrieNode {
        TrieNode [] children = new TrieNode[26];
        int count;
        Boolean isEndOfWord;
        TrieNode() {
            count = 0;
            Arrays.fill(children, null);
            isEndOfWord = false;
        }
    }
    
    static TrieNode root;
    
    class Trie {
        
        public void insert(TrieNode root, String str) {
            
            TrieNode iterator = root;
            
            for(int i = 0 ;i < str.length(); i++) {
                
                int index = str.charAt(i) - 'a';
            
                if(iterator.children[index] == null) {
                    iterator.children[index] = new TrieNode();
                }
                
                iterator = iterator.children[index];
                iterator.count = iterator.count + 1;
            }
            iterator.isEndOfWord = true;

        }
        
        
        public int search(TrieNode root, String str) {
            TrieNode iterator = root;
            
            for(int i = 0;i < str.length(); i++) {
                int index = str.charAt(i) - 'a';
                
                if(iterator.children[index].count == 1 || iterator.children[index].isEndOfWord) {
                    return i;
                } 
    
                iterator = iterator.children[index];
        
            }
            
            return -1;
        }
    }
    
    public ArrayList<String> prefix(ArrayList<String> A) {
        TrieNode root = new TrieNode();
        Trie tr = new Trie();
        ArrayList<String> res = new ArrayList<String>();
        
        for(int i = 0 ;i < A.size(); i++ ) {
            String str = A.get(i);
            tr.insert(root, str);
        }
        
        for(int i = 0 ;i < A.size(); i++) {
            String str = A.get(i);
            int index = tr.search(root, str);
            // System.out.println("i "+ i);
            if(index == -1){
                // System.out.println("Problem in Logic");
            }else{
                // System.out.println("here" + index);
                res.add(str.substring(0, index + 1));
           }
        } 
        return res;
    }
}
