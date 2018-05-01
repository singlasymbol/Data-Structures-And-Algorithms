import java.util.*;


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

	public static void main(String[] args) {
		// Scanner scan = new Scanner(System.in);
		// String newString = scan.nextLine();
		root = new TrieNode();

		insert("symbol");
		insert("ankur");
		insert("honey");

		System.out.println("is hoey Present " + search("hoey"));

		System.out.println("is symbol Present " + search("symbol"));
		System.out.println("is ankur Present " + search("ankur"));
		System.out.println("is an Present " + search("an"));
		System.out.println("is kur Present " + search("hoey"));
		System.out.println("is hokur Present " + search("hokur"));


	}
}


// todos
// 1. Add comments
// 2. Make this feasible for upperCase too;