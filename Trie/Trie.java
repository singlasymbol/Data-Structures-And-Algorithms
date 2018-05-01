import java.util.*;
import java.io.*;  


class Trie {

	static class TrieNode {
		TrieNode [] trie = new TrieNode[26];
		Boolean endOfWord;

		TrieNode() {
			endOfWord = false;
			for(int i = 0; i < 26;i++) {
				this.trie[i] = null;
			}
		}
	}

	static TrieNode root;

	static void insert(String str) {

		TrieNode iterator = root;
		int stringLength = str.length();

		for(int i = 0;i < stringLength;i++ ) {

			int index = str.charAt(i) - 'a';

			if(iterator.trie[index] == null) {
				iterator.trie[index] = new TrieNode();
			}

			iterator = iterator.trie[index];
		}

		iterator.endOfWord = true;
	}

	static Boolean search(String str) {
		TrieNode iterator = root;
		int stringLength = str.length();

		for(int i = 0; i < stringLength; i++) {
			int index = str.charAt(i) - 'a';
			// System.out.println("Character is " + str.charAt(i) + " index " + index + " isNull " + iterator.trie[index]);

			if(iterator.trie[index] == null) {
				return false;
			}else {
				iterator = iterator.trie[index];
			}
		}

		if(iterator != null && iterator.endOfWord == true) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) throws java.io.IOException {

		InputStreamReader r = new InputStreamReader(System.in);    
	    BufferedReader br = new BufferedReader(r); 
		root = new TrieNode();
		Boolean continueTheScript = true;
		String str = "";

		while(continueTheScript == true){
			System.out.println("Please choose an option");
			System.out.println("1. Insert a String");
			System.out.println("2. Search a String");
			System.out.println("3. End the program");

			int chosenOption = Integer.parseInt(br.readLine());
			switch(chosenOption) {
				case 1 :
					System.out.println("Enter a string to insert");
					str = br.readLine();
					insert(str);
					break;
				case 2:
					System.out.println("Please enter the string to search");
					str = br.readLine();
					System.out.println("is " + str + " present? = " + search(str));
					break;	
				case 3:
					continueTheScript = false;	
					break;
				default :
					System.out.println("Should not come here");
					break;		
			}

		}


	}
}


// todos
// 1. Add comments
// 2. Make this feasible for upperCase too;