import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
* Joel White
* W0704929
* 3/23/2022
* All of this code is mine, though the add method is similar to the one provided in the
assignment details.
* https://www.tutorialspoint.com/online_java_formatter.htm was used for some formatting.
*/
class Main {
	static class LinkedList {
		static class node {
			String word;
			String firstThree;
			int value;
			node next;
		}

		static node front;
		static int dupeCounter = 0;
		static char[] alpha = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
				'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		static node newNode(String wordy) {
			node newNode = new node();
			newNode.word = wordy;
			newNode.firstThree = wordCharacterFinder(wordy);
			newNode.value = wordValueFinder(wordy);
			return newNode;
		}

		void add(String word) {
			String wordy = word.toLowerCase();
			int wordVal = wordValueFinder(wordy);
			node curr, prev;
			boolean searching;
			if (front == null) {
				front = newNode(wordy);
			} else if (wordVal < front.value) {
				newFront(wordy);
			} else {
				searching = true;
				curr = front;
				prev = curr;
				while (searching == true) {
					if (curr.value == wordVal) {
						dupeCounter++;
						searching = false;
					} else if (curr.value < wordVal) {
						if (curr.next == null) {
							node wordToAdd = newNode(wordy);
							curr.next = wordToAdd;
							searching = false;
						} else {
							prev = curr;
							curr = curr.next;
						}
					} else if (curr.value > wordVal) {
						node wordToAdd = newNode(wordy);
						prev.next = wordToAdd;
						wordToAdd.next = curr;
						searching = false;
					}
				}
			}
		}

		static void newFront(String wordy) {
			node newNode = newNode(wordy);
			newNode.next = front;
			front = newNode;
		}

		static String wordCharacterFinder(String word) {
			String lowerWord = word.toLowerCase();
			char firstLetter = 0;
			char secondLetter = 0;
			char thirdLetter = 0;
			if (lowerWord.length() == 1) {
				firstLetter = lowerWord.charAt(0);
			} else if (lowerWord.length() == 2) {
				firstLetter = lowerWord.charAt(0);
				secondLetter = lowerWord.charAt(1);
			} else {
				firstLetter = lowerWord.charAt(0);
				secondLetter = lowerWord.charAt(1);
				thirdLetter = lowerWord.charAt(2);
			}
			String wordCharacters = ("" + firstLetter + secondLetter + thirdLetter);
			return wordCharacters;
		}

		static int wordValueFinder(String word) {
			String lowerWord = word.toLowerCase();
			char firstLetter = 0;
			char secondLetter = 0;
			char thirdLetter = 0;
			if (lowerWord.length() == 1) {
				firstLetter = lowerWord.charAt(0);
			} else if (lowerWord.length() == 2) {
				firstLetter = lowerWord.charAt(0);
				secondLetter = lowerWord.charAt(1);
			} else {
				firstLetter = lowerWord.charAt(0);
				secondLetter = lowerWord.charAt(1);
				thirdLetter = lowerWord.charAt(2);
			}
			int position1 = 0;
			char letter1 = 0;
			int firstLetterValue = 0;
			while (position1 < alpha.length) {
				letter1 = alpha[position1];
				if (letter1 == firstLetter) {
					firstLetterValue = ((position1 + 1) * 676);
					position1 = alpha.length;
				} else {
					position1++;
				}
			}
			int position2 = 0;
			char letter2 = 0;
			int secondLetterValue = 0;
			while (position2 < alpha.length) {
				letter2 = alpha[position2];
				if (letter2 == secondLetter) {
					secondLetterValue = ((position2 + 1) * 26);
					position2 = alpha.length;
				} else {
					position2++;
				}
			}
			int position3 = 0;
			char letter3 = 0;
			int thirdLetterValue = 0;
			while (position3 < alpha.length) {
				letter3 = alpha[position3];
				if (letter3 == thirdLetter) {
					thirdLetterValue = ((position3 + 1) * 1);
					position3 = alpha.length;
				} else {
					position3++;
				}
			}
			int wordValue = firstLetterValue + secondLetterValue + thirdLetterValue;
			return wordValue;
		}

		void showList() {
			node curr;
			curr = front;
			if (front == null) {
				System.out.println("Your list is empty.");
			} else {
				System.out.println("Your dictionary is: ");
				while (curr != null) {
					System.out.println(curr.word + " ");
					curr = curr.next;
				}
				System.out.println("");
			}
		}

		void deleteNode(String word) {
			String wordy = word.toLowerCase();
			node curr, prev;
			curr = front;
			prev = front;
			if (front.word.equals(wordy)) {
				front = curr.next;
				curr = null;
				System.out.println("'" + wordy + "' has been deleted");
				return;
			} else {
				while (curr.next != null) {
					prev = curr;
					curr = curr.next;
					if (curr.word.equals(wordy)) {
						prev.next = curr.next;
						curr = null;
						curr = prev.next;
						System.out.println("'" + wordy + "' has been deleted");
						return;
					}
				}
			}
			System.out.println("The word '" + wordy + "' could not be found.");
		}

		void deleteList() {
			node temp = front;
			node after = temp;
			if (front == null) {
				System.out.println("Your list is empty.");
			} else {
				while (temp != null) {
					after = temp.next;
					front = after;
					temp = null;
					temp = after;
				}
				temp = null;
			}
		}

		void fileReader(String fileName) throws FileNotFoundException {
			Scanner sc = new Scanner(new File(fileName)); // takes in a file.
			while (sc.hasNext()) {
				String str = null;
				str = sc.next().replaceAll("\\p{Punct}", ""); // removes any punctuation in the input
				String s = str.toLowerCase(); // converts the input to lower case
				add(s); // adds a new node
			}
		}

		void returnLetter(String letter) {
			node curr;
			curr = front;
			System.out.print("All the words in your list that start with '" + letter + "' are: ");
			while (curr != null) {
				if (letter.length() != 1) {
					System.out.println("Please input only one letter");
					return;
				} else if (letter.length() == 1) {
					if (curr.word.startsWith(letter.toLowerCase())) {
						System.out.print(curr.word + " ");
					}
				}
				curr = curr.next;
			}
			System.out.println();
		}

		int dupes() {
			return dupeCounter;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		LinkedList list = new LinkedList();
		list.fileReader("sample.txt");
		System.out.println("Hello! ");
		System.out.println(
				list.dupes() + " duplicate words were found in your text file and were not added to the dictionary");
		list.showList();
		System.out.println(
				"To edit your dictionary, type 'add' to add a word or 'delete' to delete a word. To show your dictionary, ");
		System.out.println(
				"type 'show' or type 'letter' to view all words that start with a specific letter. To exit, type 'exit'");
		Scanner jeeves = new Scanner(System.in);
		String selection = jeeves.nextLine();
		while (selection.equals("exit") == false) {
			switch (selection) {
			case "add":
				System.out.println("What word would you like to add?");
				String a = jeeves.nextLine();
				list.add(a);
				break;
			case "delete":
				System.out.println("What word would you like to delete?");
				String d = jeeves.nextLine();
				list.deleteNode(d);
				break;
			case "show":
				list.showList();
				break;
			case "letter":
				System.out.println("What letter index would you like to see?");
				String l = jeeves.nextLine();
				list.returnLetter(l);
				break;
			default:
				System.out.println("Invalid input!");
			}
			System.out.println(
					"To edit your dictionary, type 'add' to add a word or 'delete' to delete a word. To show your dictionary, ");
			System.out.println(
					"type 'show' or type 'letter' to view all words that start with a specific letter. To exit, type 'exit'");
			selection = jeeves.nextLine();
		}
		jeeves.close();
	}
}
