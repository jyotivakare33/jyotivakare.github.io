import java.io.*;
class CompressedSequence {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		BufferedReader reader1 = null;
		String line;
		File file = null;
		try { 
			file = new File(args[0]);
			reader = new BufferedReader(new FileReader(file));
			reader1 = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException  | ArrayIndexOutOfBoundsException e) {
			System.out.println("File does not exist or please provide file name.");
			System.exit(0);
		}
		if (file.length() == 0) {
			System.out.println("empty file.");
			System.exit(0);
		}
		int count1 = 0;
		while ((line = reader1.readLine()) != null) {
			count1++;
		}
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			int count = 1;
			if (line.length() != 0) {
				if (count1 >= 20 && count1 <= 50) {
					String[] text = line.split("\\s+");
					if (text.length < 200) {
						for (int index = 0; index <= text.length - 1; index++) {
							int number =  0;
							try {
								number = Integer.parseInt(text[index]);
							}
							catch (Exception e) {
								System.out.print("");
							}
							if ((text[index]).matches("[0-9]+")) {
								if(number <= 99) {
									if (index == text.length - 1) {
										System.out.println(count + " " + text[text.length - 1]);
										break;
									}
									if (text[index].equals(text[index + 1])) {
										count++;
									} 
									else {
										 if (count != 0)
										 System.out.print(count + " " + text[index] + " - ");
										 count = 1;
									}
								} 
								else {
									  System.out.println("number should be less than 100.");
								}
				