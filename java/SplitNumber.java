import java.io.*;
class SplitNumber {

	public static void main(String[] args) throws IOException {
		BufferedReader readerRefObj = null;
		try {
			File file = new File(args[0]);
			if (file.length() == 0) {
				System.out.println("empty file");
				System.exit(0);
			}
			readerRefObj = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
			System.out.println("file does not exist or not giving any filename as an arguments");
			System.exit(0);
		}
		String line;
		while ((line = readerRefObj.readLine()) != null) {
			try {
				if (line.length() == 0)
					System.out.println("Blank line.");
				else {
					String[] data = line.split(" ");
					String number = data[0].trim();
					String pattern2 = data[1].trim();
					if (number.length() >= 3 && number.length() <= 10) {
						if (number.length() == pattern2.length() - 1) {
							if (pattern2.contains("+")) {
								String[] pattern1 = pattern2.split("\\+");
								System.out.println(Integer.parseInt(number.substring(0, pattern1[0].length()))
										+ Integer.parseInt(number.substring(pattern1[0].length())));
							}
							if (pattern2.contains("-")) {
								String[] pattern1 = pattern2.split("\\-");
								System.out.println(Integer.parseInt(number.substring(0, pattern1[0].length()))
										- Integer.parseInt(number.substring(pattern1[0].length())));
							}
							if (pattern2.contains("*")) {
								String[] pattern1 = pattern2.split("\\*");
								System.out.println(Integer.parseInt(number.substring(0, pattern1[0].length()))
										* Integer.parseInt(number.substring(pattern1[0].length())));
							}
						} 
						else {
							System.out.println("invalid input format.");
						}
					} 
					else {
						System.out.println("number should be greater than equal to 100.");
					}
				}
			} catch (Exception e) {
				System.out.println("Invalid input");
			}
		}
	}
}
