import java.util.*;
import java.io.*;
class Launch {

	public static void main(String[] args) throws Exception {
		Process processObj = null;
		String input = "";
		String listOfPrograms[] = { "TrickOrTreat", "SumOfDigits", "LongestWord", "ReverseWords", "SplitNumber",
				"FizzBuzz", "RightMostChar", "RollerCoaster", "StepWiseWord", "Pangram", "StringSearch",
				"DistinctSubsequence", "SwapCase", "ReadMore", "ShortestRepitition", "StackImplementation",
				"SumOfConsecutivePrime", "EmailValidation", "JustifyText", "EggCrack", "AdvancedCalculator",
				"WorkingExperience", "StringRotation", "ReverseGroups", "TextDollars", "TextToNumber",
				"CompressedSequence", "HiddenDigits", "ReverseAdd" };
		String listOfTextFiles[] = { "trick.txt", "number.txt", "words.txt", "word.txt", "split_number.txt",
				"fizz_buzz.txt", "find_char.txt", "roller_coaster.txt", "stepwise_word.txt", "pangrams1.txt",
				"string_search.txt", "subsequences.txt", "swap_case.txt", "read_more.txt", "shortest_repititions.txt",
				"stack_imp.txt", "prime_num.txt", "email.txt", "justify_text.txt", "egg_drop.txt", "advanced_calc.txt",
				"working_exp.txt", "string_rotation.txt", "reverse_groups.txt", "num_to_words.txt", "text_number.txt",
				"compressed_seq.txt", "hidden.txt", "reverse_add.txt" };
		do {
			System.out.println("enter number between 0 to 29");
			Scanner sc = new Scanner(System.in);
			int number = sc.nextInt();
			for (int index = 0; index < listOfPrograms.length; index++) {
				int index1 = index;
				if (number == index) {
					try {
						Process pro = Runtime.getRuntime().exec("javac " + listOfPrograms[index] + ".java");
						processObj = Runtime.getRuntime().exec("java" + " " + listOfPrograms[index] + " " + listOfTextFiles[index1]);
						printLines(" stdout:", processObj.getInputStream());
						printLines(" stderr:", processObj.getErrorStream());
						System.out.println("need to continue press yes / no");
						input = sc.next();
					} catch (Exception e) {
						System.out.println("exception occurs");
					}
				}
			}
		} while (input.equals("yes"));
	}

	private static void printLines(String name, InputStream instream) throws Exception {
		String line = null;
		BufferedReader readerObj = new BufferedReader(new InputStreamReader(instream));
		while ((line = readerObj.readLine()) != null) {
			System.out.println(name + " " + line);
		}
	}
}
