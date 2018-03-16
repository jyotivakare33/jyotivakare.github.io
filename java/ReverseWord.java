import java.io.*;
class ReverseWord {

	public static void main(String args[]) throws IOException {
		BufferedReader reader = null;
		String line;
		try {
			File file = new File(args[0]);
			if (file.length() == 0) {
				System.out.println("empty file");
				System.exit(0);
			} 
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
			System.out.println("File does not exist or not passing any file as an argument");
			System.exit(0);
		}
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			if (line.length() != 0) {
				String[] groupOfData = line.split("\\s+");
				for (int index = groupOfData.length - 1; index >= 0; index--) {
					System.out.print(groupOfData[index] + " ");
				}
				System.out.println();
			} else {
				System.out.println("blank line");
			}
		}
	}
}
