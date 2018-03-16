import java.io.*;
class ShortestRepetition {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		String line;
		try {
		File fileObj = new File(args[0]);
		if (fileObj.length() == 0) {
			System.out.println("empty file.");
			System.exit(0);
		}
			reader = new BufferedReader(new FileReader(fileObj));
		} catch (Exception e) {
			System.out.println("File does not exist or not passing any file as an argument.");
			System.exit(0);
		}
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			if (line.length() != 0) {
				char[] bunchOfCharacters = line.toCharArray();
				char alphabet = bunchOfCharacters[0];
				String result = "" + alphabet;
				int length = 0;
				for (int index = 1; index < bunchOfCharacters.length; index++) {
					if (alphabet != bunchOfCharacters[index]) {
						result = result + bunchOfCharacters[index];
					} else if (alphabet == bunchOfCharacters[index]) {
						if (line.matches(result  +".*" + result ))
							length = result.length();
						else
							result = result + bunchOfCharacters[index];
					}
				}
				if (length != 0)
					System.out.println(length);
				else
					System.out.println(line.length());
			} else
				System.out.println("blank line.");
		}
	}
}
