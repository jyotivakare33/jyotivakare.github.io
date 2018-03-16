import java.io.*;
class SwapCase {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		File file = null;
		try {
			file = new File(args[0]);
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
			System.out.println("File does not exist or not passing any file as an argument");
			System.exit(0);
		}
		if (file.length() == 0) {
			System.out.println("empty file");
			System.exit(0);
		}
		String line;
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			if (line.length() != 0) {
				line = line.trim();
				char[] groupOfCharacters = line.toCharArray();
				for (int index = 0; index < line.length(); index++) {
					char item = groupOfCharacters[index];
					if (Character.isLowerCase(item))
						groupOfCharacters[index] = Character.toUpperCase(item);
					if (Character.isUpperCase(item))
						groupOfCharacters[index] = Character.toLowerCase(item);
				}
				String line1 = new String(groupOfCharacters);
				System.out.println(line1);
			} else
				System.out.println("blank line");
		}
	}
}
