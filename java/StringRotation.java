import java.io.*;
class StringRotation {

	public static void main(String[] args) throws Exception {
		String line;
		BufferedReader reader = null;
		File file = null;
		try {
			file = new File(args[0]);
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
			System.out.println("file does not exist or not passing any filename as an argument");
			System.exit(0);
		}
		if (file.length() == 0) {
			System.out.println("empty file");
			System.exit(0);
		}
		while ((line = reader.readLine()) != null) {
			String result = "";
			if (line.length() != 0) {
				line = line.trim();
				String[] alteredData = line.split(",");
				String word1 = alteredData[0].trim();
				result = word1 + word1;
				if (result.contains(alteredData[1])) {
					System.out.println(true);
				} else
					System.out.println(false);
			} else
				System.out.println("blank line");
		}
	}
}
