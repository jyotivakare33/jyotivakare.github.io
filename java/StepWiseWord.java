import java.io.*;
import java.util.*;
class StepWiseWord {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		String line;
		int lineCount = 0;
		File file = null;
		try {
			file = new File(args[0]);
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException  | ArrayIndexOutOfBoundsException e) {
			System.out.println("File does not exist or not passing any file as an argument.");
			System.exit(0);
		}
		if (file.length() == 0) {
			System.out.println("empty file.");
			System.exit(0);
		}
		ArrayList<String> list = new ArrayList<String>();
		while ((line = reader.readLine()) != null) {
			lineCount++;
			String max = "";
			line = line.trim();
			if (lineCount > 40) {
				System.out.println("line count should be less than 40.");
				break;
			}
			if (line.length() != 0) {
				String[] groupOfCharacters = line.split("\\s+");
				if (groupOfCharacters.length >= 5 && groupOfCharacters.length <= 15) {
					for (int index = 0; index < groupOfCharacters.length; index++) {
						if (groupOfCharacters[index].length() <= 10) {
							if (groupOfCharacters[index].length() > max.length())
								max = groupOfCharacters[index];
						}
					}
					list.add(max);
				} else
					list.add("invalid");
			} else
				list.add("blank");
		}
		for (int outerIndex = 0; outerIndex < list.size(); outerIndex++) {
			for (int innerIndex = 0; innerIndex < list.get(outerIndex).length(); innerIndex++) {

				if (list.get(outerIndex).equals("invalid")) {
					System.out.print("line should contain 5 to 15 words.");
					break;
				}
				if (list.get(outerIndex).equals("blank")) {
					System.out.print("blank line.");
					break;
				} else {
					System.out.print(list.get(outerIndex).charAt(innerIndex) + " ");
					for (int innerMostIndex = 0; innerMostIndex <= innerIndex; innerMostIndex++) {
						if (innerIndex == list.get(outerIndex).length() - 1)
							break;
						else
							System.out.print("*");
					}
				}
			}
			System.out.println();
		}
	}
}
