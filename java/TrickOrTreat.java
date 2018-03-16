import java.io.*;
class TrickOrTreat {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		String line;
		int lineCount = 0;
		File file = null ;
		try {
			file = new File(args[0]);
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
			System.out.println("File does not exist or not passing any file as an argument.");
			System.exit(0);
		}
		if (file.length() == 0) {
			System.out.println("empty file.");
			System.exit(0);
		}
		while ((line = reader.readLine()) != null) {
			lineCount++;
			int index1 = 0;
			int result = 0;
			int numOfpeople = 0;
			int result1 = 0;
			if (line.length() != 0) {
				if (lineCount > 40) {
					System.out.println("file should not contain more than 40 lines.");
					break;
				}
				if (line.contains(",") && line.contains(":")) {
                 	String[] data = line.split(",");
					if (data.length == 4) {
						int[] groupOfNumbers = new int[data.length];
						for (int index = 0; index < data.length; index++) {
							String[] data1 = data[index].split(":");
							data1[1] = data1[1].trim();
							try {
								groupOfNumbers[index1++] = Integer.parseInt(data1[1]);
							} catch (Exception e) {
								System.out.println("invalid input format.");
							}
						}
						int vampires = groupOfNumbers[0];
						int zombies = groupOfNumbers[1];
						int witches = groupOfNumbers[2];
						int houses = groupOfNumbers[3];
						if (vampires <= 100 && zombies <= 100 & witches <= 100 && houses <= 100) {
							numOfpeople = vampires + zombies + witches;
							result1 = (vampires * 3 + zombies * 4 + witches * 5) * houses;
							if(numOfpeople != 0) {
								result = result1 / numOfpeople;
								System.out.println(result);
							}
                        } else {
								System.out.println("inputs are out of range.");
						}
					} else {
						System.out.println("invalid input format.");
					}
				} else {
					System.out.println("invalid input format.");
				}
			} else {
				System.out.println("blank line.");
			}
		}
    }	
}

	
