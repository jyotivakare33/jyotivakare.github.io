import java.io.*;
import java.util.*;
class TextToNumber {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		String line;
		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
		hashmap.put("zero", 0);
		hashmap.put("one", 1);
		hashmap.put("two", 2);
		hashmap.put("three", 3);
		hashmap.put("four", 4);
		hashmap.put("five", 5);
		hashmap.put("six", 6);
		hashmap.put("seven", 7);
		hashmap.put("eight", 8);
		hashmap.put("nine", 9);
		hashmap.put("ten", 10);
		hashmap.put("eleven", 11);
		hashmap.put("twelve", 12);
		hashmap.put("thirteen", 13);
		hashmap.put("fourteen", 14);
		hashmap.put("fifteen", 15);
		hashmap.put("sixteen", 16);
		hashmap.put("seventeen", 17);
		hashmap.put("eighteen", 18);
		hashmap.put("ninteen", 19);
		hashmap.put("twenty", 20);
		hashmap.put("thirty", 30);
		hashmap.put("forty", 40);
		hashmap.put("fifty", 50);
		hashmap.put("sixty", 60);
		hashmap.put("seventy", 70);
		hashmap.put("eighty", 80);
		hashmap.put("ninety", 90);
		hashmap.put("hundred", 100);
		hashmap.put("thousand", 1000);
		hashmap.put("million", 1000000);
		File file = null; 
		try {
			file = new File(args[0]);
			reader = new BufferedReader(new FileReader(file));
		} catch (ArrayIndexOutOfBoundsException | FileNotFoundException exp) {
			System.out.println("File does not exist or not passing any file as an argument");
			System.exit(0);
		}
		if (file.length() == 0) {
			System.out.println("empty file");
			System.exit(0);
		}
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			if (line.length() != 0) {
				String[] modifiedData = line.split("\\s+");
				int number = 0;
				int result = 0;
				int result1 = 0;
				for (String temp : modifiedData) {
					int number1 = hashmap.get(temp);
					if (number1 == 100 || number1 == 1000 || number1 == 1000000)
						number = number * number1;
					else
						number += number1;
					if (number1 == 1000000) {
						result = number;
						number = 0;
					}
					if (number1 == 1000) {
						result1 = number;
						number = 0;
					}
				}
				int total = result + result1 + number;
				if (total < 1000000000)
					System.out.println(total);
				else
					System.out.println("number is out of range.");

			} else
				System.out.println("blank line.");
		}
	}
}
