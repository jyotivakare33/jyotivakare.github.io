import java.io.*;
class SumOfDigits {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		File fileObj = new File(args[0]);
		if (fileObj.length() == 0) {
			System.out.println("empty file");
			System.exit(0);
		}
		try {
			reader = new BufferedReader(new FileReader(fileObj));
		} catch (Exception e) {
			System.out.println("File does not exist or not passing any file as an argument");
		}
		String line;
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			if (line.length() != 0) {
				if (line.matches("[0-9]+")) {
					char[] groupOfCharacters = line.toCharArray();
					int[] number = new int[groupOfCharacters.length];
					int sum = 0;
					for (int index = 0; index < groupOfCharacters.length; index++) {
						number[index] = groupOfCharacters[index] - 48;
					}
					for (int index1 = 0; index1 < number.length; index1++) {
						sum = sum + number[index1];
					}
					System.out.println(sum);
				} else
					System.out.println("invalid input");

			} else
				System.out.println("blank line");
		}
	}
}