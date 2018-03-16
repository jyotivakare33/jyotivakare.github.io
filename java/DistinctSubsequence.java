import java.io.*;
class DistinctSubsequence {

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
			if (line.length() != 0) {
				line = line.trim();
				String[] inputs = line.split(",");
				if (inputs.length == 2) {
					System.out.println(numberOfSequences(inputs[0].trim(), inputs[1].trim()));
				} else
					System.out.println("invalid input");
			} else
				System.out.println("blank line");
		}
	}

	static int numberOfSequences(String first, String second) {
		int[][] table = new int[first.length() + 1][second.length() + 1];
		for (int index = 0; index < first.length(); index++)
			table[index][0] = 1;
		for (int outerIndex = 1; outerIndex <= first.length(); outerIndex++) {
			for (int innerIndex = 1; innerIndex <= second.length(); innerIndex++) {
				if (first.charAt(outerIndex - 1) == second.charAt(innerIndex - 1)) {
					table[outerIndex][innerIndex] += table[outerIndex - 1][innerIndex]
							+ table[outerIndex - 1][innerIndex - 1];
				} else {
					table[outerIndex][innerIndex] += table[outerIndex - 1][innerIndex];
				}
			}
		}
		return table[first.length()][second.length()];
	}

}