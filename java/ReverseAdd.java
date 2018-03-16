import java.io.*;
class ReverseAdd {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferreader = null;
		try {
			File fileObj = new File(args[0]);
			if (fileObj.length() == 0) {
				System.out.println("empty file.");
				System.exit(0);
			}
			bufferreader = new BufferedReader(new FileReader(args[0]));
		} catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
			System.out.println("File does not exist or please provide file name.");
		}
		String line;
		try {
			while ((line = bufferreader.readLine()) != null) {
				line = line.trim();
				if (line.length() != 0) {
					int number = 0;
					int count = 0;
					try {
						number = Integer.parseInt(line);
						if (number < 10000) {
							while (count < 100 && number != 0) {
								int reverse = 0;
								String temp = String.valueOf(number);
								for (int index = temp.length() - 1; index >= 0; index--) {
									reverse = reverse * 10 + temp.charAt(index) - '0';
								}
								if (reverse == number) {
									System.out.println(count + "  " + number);
									break;
								}
								number = number + reverse;
								count++;
							}
						} else
							System.out.println("number should be less than 10000.");
					} catch (NumberFormatException e) {
						System.out.println("invalid input.");
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
