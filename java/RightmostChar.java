import java.io.*;
class RightmostChar {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		try {
			File file = new File(args[0]);
			if (file.length() == 0) {
				System.out.println("empty file.");
				System.exit(0);
			}
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
			System.out.println("File does not exist or not passing any file as an argument.");
		}
		String line;
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			if (line.length() != 0) {
				String[] text = line.split(",");
				if (text.length == 2) {
					if (text[0].contains(text[1]))
						System.out.println(text[0].lastIndexOf(text[1]));
					else
						System.out.println("character not found.");
				} else
					System.out.println("invalid input format.");
			} else
				System.out.println("blank line.");
		}
	}
}
