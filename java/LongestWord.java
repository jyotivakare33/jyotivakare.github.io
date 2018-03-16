import java.io.*;
class LongestWord {

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
		try {
			while ((line = reader.readLine()) != null) {
				String longestWord = "";
				line = line.trim();
				if (line.length() != 0) {
					String[] groupOfData = line.split("\\s+");
					for (int index = 0; index < groupOfData.length; index++) {
						if (groupOfData[index].length() > longestWord.length())
						longestWord = groupOfData[index];
					}
					System.out.println(longestWord);
				} else {
					System.out.println("blank line");
				}
			}
		}
		catch(Exception e) {
			System.out.println("you are reading line from non existing file.");
		}
	}
}
