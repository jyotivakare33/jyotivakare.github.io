import java.io.*;
class HiddenDigits {

	public static void main(String[] args) throws Exception {
		String line;
		BufferedReader reader = null;
		try {
			File file = new File(args[0]);
			if (file.length() == 0) {
				System.out.println("File is empty.");
				System.exit(0);
			}
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
			System.out.println("file does not exist or please provide file name.");
		}	
		try {
		while ((line = reader.readLine()) != null) {
			String result = "";
			if (line.length() != 0) {
				line = line.trim();
				for (int index = 0; index < line.length(); index++) {
					if (line.charAt(index) >= 'a' && line.charAt(index) < 'k') {
						if (Character.isLowerCase(line.charAt(index))) {
							result = result + (line.charAt(index) - 97);
						}
					} else if (Character.isDigit(line.charAt(index))) {
						result = result + line.charAt(index);
					} else {
						result = result + "";
					}
				}
				if (result.equals(""))
					System.out.println("NONE");
				else
					System.out.println(result);

			} else
				System.out.println("blank line.");
		}
		}
		catch(Exception e) {
			System.out.println("you are reading line from non existing file.");
		}
	}
}
