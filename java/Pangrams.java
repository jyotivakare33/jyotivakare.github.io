import java.io.*;
class Pangrams {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferreader = null;
		try {
			File file = new File(args[0]);
			if (file.length() == 0) {
				System.out.println("empty file.");
				System.exit(0);
			}
			bufferreader = new BufferedReader(new FileReader(file));
		} catch (Exception e) {
			System.out.println("File does not exist or not passing any file as an argument.");
		}
		String line;
		String input = "abcdefghijklmnopqrstuvwxyz";
		try {
			while ((line = bufferreader.readLine()) != null) {
				line = line.trim();
				line = line.toLowerCase();
				if (line.length() != 0) {
					String result = "";
					for (int index = 0; index < input.length(); index++) {
						char item = input.charAt(index);
						if (Character.isLetter(item)) {
							if (!(line.contains(item + ""))) {
								result += item;
							}
						}
					}
					if (result.equals(""))
						System.out.println("null");
					else
						System.out.println(result);
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
