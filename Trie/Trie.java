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

			if(iterator.trie[index] == null) { // if there is nothing at index
				return false;
			}else {
				iterator = iterator.trie[index];
			}
		}

		if(iterator != null && iterator.endOfWord == true) { // checking if the word has ended.
			return true;
		} else {
			return false;
		}
	}

	static void suggestions(String str) {
		TrieNode iterator = root;

		for(int i = 0 ; i < str.length(); i++) {
			int index = str.charAt(i) - 'a';

			if(iterator.trie[index] == null) {
				System.out.println("No suggestions available for " + str);
			} else { 
				iterator = iterator.trie[index]; // reaching the end of query string
			}
		}

		printSuggestions(str, iterator, ""); // will perform a dfs from the reference, to all the end of words and print strings
	}

	static void printSuggestions(String str, TrieNode iterator, String res){

		if(iterator == null) {
			return;
		}
		for(int i = 0;i < 26;i++) {
			if(iterator.trie[i] != null) {
				char toAdd = (char) (i + 97);
				 // adding the character to the main query, will do till we reach the end of word
				// making a new string variable everytime as it was overwriting the one, that already exists
				printSuggestions(str, iterator.trie[i], new String(res + toAdd));
			}
		}

		if(iterator.endOfWord == true) {
			System.out.println(str + res);
		}
	}

	public static void main(String[] args) throws java.io.IOException {

		InputStreamReader r = new InputStreamReader(System.in);    
	    BufferedReader br = new BufferedReader(r); 
	    //Buffered Reader is synchronous. Scanner isnot. While using switch the program didnot stop for the input,
	    //while using scanner.
		root = new TrieNode();
		Boolean continueTheScript = true;
		String str = "";

		while(continueTheScript == true){
			System.out.println("Please choose an option");
			System.out.println("1. Insert a String");
			System.out.println("2. Search a String");
			System.out.println("3. Suggestions");
			System.out.println("4. End the program");
			int chosenOption = Integer.parseInt(br.readLine());
			//BufferedReader returns a string
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
					System.out.println("Oh! What do you want suggestions for?");
					str = br.readLine();
					suggestions(str);
					break;
				case 4:
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