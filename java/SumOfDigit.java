																																							import java.io.*;class SumOfDigit {	public static void main(String[] args) throws IOException {		BufferedReader reader = null;		File file = null;		try {			file = new File(args[0]);				reader = new BufferedReader(new FileReader(file));		} catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {			System.out.println("File does not exist or not passing any file as an argument.");			System.exit(0);		}		if (file.length() == 0) {			System.out.println("empty file.");			System.exit(0);		}		String line;		while ((line = reader.readLine()) != null) {
System.out.println("jyoti");				line = line.trim();			if (line.length() != 0) {				if (line.matches("[0-9]+")) {					char[] groupOfCharacters = line.toCharArray();	System.out.println("updated program);				int[] number = new int[groupOfCharacters.length];					int sum = 0;					for (int index = 0; index < groupOfCharacters.length; index++) {						number[index] = groupOfCharacters[index] - 48;					}					for (int index1 = 0; index1 < number.length; index1++) {						sum = sum + number[index1];					}					System.out.println(sum);				} else					System.out.println("invalid input.");			} else				System.out.println("blank line.");		}	}}																										