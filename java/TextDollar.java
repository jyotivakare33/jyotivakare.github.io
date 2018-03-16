import java.io.*;
import java.util.*;
class TextDollar {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = null;
		File file = null;
		try {
			file = new File(args[0]);
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException  | ArrayIndexOutOfBoundsException e) {
			System.out.println("file does not exist or not passing any file as an argument.");
		}
		String result = "dollars";
		HashMap<Integer, String> hashmap = new HashMap<Integer, String>();
		hashmap.put(1, "one");
		hashmap.put(2, "two");
		hashmap.put(3, "three");
		hashmap.put(4, "four");
		hashmap.put(5, "five");
		hashmap.put(6, "six");
		hashmap.put(7, "seven");
		hashmap.put(8, "eight");
		hashmap.put(9, "nine");
		hashmap.put(10, "ten");
		hashmap.put(11, "eleven");
		hashmap.put(12, "twelve");
		hashmap.put(13, "thirteen");
		hashmap.put(14, "fourteen");
		hashmap.put(15, "fifteen");
		hashmap.put(16, "sixteen");
		hashmap.put(17, "seventeen");
		hashmap.put(18, "eighteen");
		hashmap.put(19, "ninteen");
		hashmap.put(20, "twenty");
		hashmap.put(30, "thirty");
		hashmap.put(40, "forty");
		hashmap.put(50, "fifty");
		hashmap.put(60, "sixty");
		hashmap.put(70, "seventy");
		hashmap.put(80, "eighty");
		hashmap.put(90, "ninty");
		hashmap.put(100, "hundred");
		hashmap.put(1000, "thousand");
		hashmap.put(100000, "lakh");
		hashmap.put(100000000, "crore");
		if (file.length() == 0) {
			System.out.println("empty file.");
			System.exit(0);
		}
		String line;
		while ((line = reader.readLine()) != null) {
			int number = 0;
			if (line.length() != 0) {
				line = line.trim();
				try {
					number = Integer.parseInt(line);
					if (number <= 20) {
						System.out.println(hashmap.get(number) + result);
					} else if (number > 20 && number <= 100) {
						int number1 = number / 10;
						int reminder1 = number % 10;
						if (reminder1 != 0) {
							System.out.println(hashmap.get(number1 * 10) + hashmap.get(reminder1) + result);
						} else {
							System.out.println(hashmap.get(number1 / 10) + hashmap.get(number1 * 10) + result);
						}

					} else if (number > 100 && number <= 1000) {
						int number1 = number / 100;
						int reminder1 = number % 100;
						if (reminder1 == 0)
							System.out.println(hashmap.get(1000) + result);
						else if (reminder1 <= 20) {
							System.out
									.println(hashmap.get(number1) + hashmap.get(100) + hashmap.get(reminder1) + result);
						} else {

							int number2 = reminder1 / 10;
							int reminder2 = reminder1 % 10;
							System.out.println(hashmap.get(number1) + hashmap.get(100) + hashmap.get(number2 * 10)
									+ hashmap.get(reminder2) + result);
						}
					} else if (number > 1000 && number < 10000) {
						int number1 = number / 1000;
						int reminder1 = number % 1000;
						if (reminder1 == 0) {
							System.out.println(hashmap.get(number1) + hashmap.get(1000) + result);
						}
						if (reminder1 > 20 && reminder1 < 100) {
							int number2 = reminder1 / 10;
							int reminder2 = reminder1 % 10;
							System.out.println(hashmap.get(number1) + hashmap.get(1000) + hashmap.get(number2 * 10)
									+ hashmap.get(reminder2));
						}
						if (reminder1 > 100 && reminder1 <= 999) {
							int number3 = reminder1 / 100;
							int reminder3 = reminder1 % 100;
							if (reminder3 <= 20) {
								System.out.println(hashmap.get(number1) + hashmap.get(1000) + hashmap.get(number3)
										+ hashmap.get(100) + hashmap.get(reminder3) + result);
							} else {
								int number4 = reminder3 / 10;
								int reminder4 = reminder3 % 10;
								System.out.println(hashmap.get(number1) + hashmap.get(1000) + hashmap.get(number3)
										+ hashmap.get(100) + hashmap.get(number4 * 10) + hashmap.get(reminder4)
										+ result);
							}
						}
						if (reminder1 <= 20) {
							System.out.println(
									hashmap.get(number1) + hashmap.get(1000) + hashmap.get(reminder1) + result);
						}
					} else if (number >= 10000 && number < 100000) {
						int number1 = number / 1000;
						int reminder1 = number % 1000;
                                                if(number1 > 20 && number1 < 99)
                                                      System.out.print(hashmap.get((number1 / 10) * 10) + hashmap.get(number1 % 10) + "thousand");
						if (reminder1 == 0) {
							System.out.println(hashmap.get(number1) + hashmap.get(1000) + result);
						}
						if (reminder1 > 20 && reminder1 < 100) {
							int number2 = reminder1 / 10;
							int reminder2 = reminder1 % 10;
							System.out.println(hashmap.get(number1) + hashmap.get(1000) + hashmap.get(number2 * 10)
									+ hashmap.get(reminder2));
						}
						if (reminder1 > 100 && reminder1 <= 999) {
							int number3 = reminder1 / 100;
							int reminder3 = reminder1 % 100;
							if (reminder3 <= 20 && reminder3 != 0) {
								System.out.println(hashmap.get(number1) + hashmap.get(1000) + hashmap.get(number3)
										+ hashmap.get(100) + hashmap.get(reminder3) + result);
							} else {
								int number4 = reminder3 / 10;
								int reminder4 = reminder3 % 10;
								System.out.println(hashmap.get(number1) + hashmap.get(1000) + hashmap.get(number3)
										+ hashmap.get(100) + hashmap.get(number4 * 10) + hashmap.get(reminder4)
										+ result);
							}
							if (reminder3 == 0)
								System.out.println(hashmap.get(number1) + hashmap.get(1000) + hashmap.get(number3)
										+ hashmap.get(100) + result);
						}
						if (reminder1 <= 20) {
							System.out.println(hashmap.get(number1) + hashmap.get(1000) + hashmap.get(reminder1) + result);
						}
					}

				} catch (Exception e) {
					System.out.println("invalid input");
				}
			} else
				System.out.println("blank line");
		}
	}
}
