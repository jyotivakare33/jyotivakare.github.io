import java.io.*;
class FizzBuzz {

	public static void main(String[] args) throws IOException {
		BufferedReader readerRefVar = null;
		try {
			File file = new File(args[0]);
			if (file.length() == 0) {
				System.out.println("empty file.");
				System.exit(0);
			}
			readerRefVar = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
			System.out.println("File does not exist or not passing any file as an argument.");
		}
		String line;
		try {
			while ((line = readerRefVar.readLine()) != null) {
				line = line.trim();
				if (line.length() != 0) {
					String[] collectionOfCharacters = line.split("\\s+");
					int firstNumber = 0;
					int secondNumber = 0;
					int range = 0;
					if (collectionOfCharacters.length == 3) {
						try {
							firstNumber = Integer.parseInt(collectionOfCharacters[0]);
							secondNumber = Integer.parseInt(collectionOfCharacters[1]);
							range = Integer.parseInt(collectionOfCharacters[2]);
							if (firstNumber <= 20 && secondNumber <= 20 && range <= 100) {
								for (int index = 1; index <= range; index++) {
									String result = (index % (firstNumber * secondNumber) == 0) ? "FB "
										: (index % secondNumber == 0) ? "B "
												: (index % firstNumber == 0) ? "F " : (index + " ");
									System.out.print(result);
								}
								System.out.print("\n");
							} else {
								System.out.println("firstNumber and second numebr should be less than 20 and thirdNumber should be less than 100.");
							}
						} catch (NumberFormatException e) {
							System.out.println("Invalid input format.");
						}
					} else {
						System.out.println("Invalid input format.");
					}
				} else {
					System.out.println("blank line.");
				}
			}
		} 
		catch(Exception e) {
			 System.out.println("you are reading line from non existing file.");
		}

	}
}
