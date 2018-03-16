import java.io.*;
class ReverseGroups {

	public static void main(String[] args) throws Exception {
		String line;
		BufferedReader reader = null;
		try {
			File file = new File(args[0]);
			if (file.length() == 0) {
				System.out.println("empty file.");
				System.exit(0);
			}
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
			System.out.println("file does not exist or please provide file name.");
			System.exit(0);
		}
		while ((line = reader.readLine()) != null) {
			if (line.length() != 0) {
				line = line.trim();
                int key = 0;
				if (line.contains(",") && line.contains(";")) {
					String[] modifiedData = line.split(";");
					if(modifiedData.length == 2) {
						try {
							key = Integer.parseInt(modifiedData[1].trim());
						}
						catch(NumberFormatException e) {
							System.out.println("key should not be blank. you should provide key.");
						}
						if(key > modifiedData[0].length()) {
							System.out.println("invalid input.");
							break;
						}
						String[] numbers = modifiedData[0].split(",");
						int length = numbers.length;
						int numOfGroups = length / key;
						int numOfElementsLeft = length % key;
						for (int index = key - 1; index >= 0; index--) {
							if(numbers[index].trim().length() == 0) {
								System.out.print("invalid input format.");
								break;
							}
							System.out.print(numbers[index].trim() + ",");
						}
					    for (int index = (key * numOfGroups) - 1; index >= key; index--) {
							System.out.print(numbers[index].trim() + ",");
					    }
					    for (int index = length - numOfElementsLeft; index < numbers.length; index++) {
							String result1;
							result1 = (index == length - 1) ? ("" + numbers[index].trim()) : numbers[index].trim() + ",";
							System.out.print(result1);
						}
					}
					else {
						System.out.println("invalid input format.");
					}
					System.out.print("\n");
				} 
				else {
					System.out.println("invalid input format.");
				}
			}
			else {
				System.out.println("blank line.");
			}
		}
	}
}
