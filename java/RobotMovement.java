import java.io.*;
class RobotMovement {

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
				System.out.println("file does not exist or not passing any filename as argument.");
				System.exit(0);
		}
		String line = "";
		while ((line = reader.readLine()) != null) {
			if(line.length() == 0) {
				System.out.println("blank line");
			}
			else {
				String rows[] = line.split("\\s+");
				try {
					System.out.println(numberOfPaths(Integer.parseInt(rows[0]),Integer.parseInt(rows[1])));
				}
				catch(NumberFormatException e) {
					//throw new RuntimeException("invalid input");
					System.out.println("invalid input");
				}
			}
		}
	}

	static int numberOfPaths(int rowSize, int columnSize) {
		if (rowSize == 1 || columnSize == 1)
			return 1;
		return numberOfPaths(rowSize - 1, columnSize) + numberOfPaths(rowSize, columnSize - 1);
				
	}
}
