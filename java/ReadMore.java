import java.io.*;
class ReadMore {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		try {
			File fileObj = new File(args[0]);
			if (fileObj.length() == 0) {
				System.out.println("empty file.");
				System.exit(0);
			}
			reader = new BufferedReader(new FileReader(fileObj));
		} catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
			System.out.println("file is missing or please provide any file.");
			System.exit(0);
		}
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				if (line.length() != 0) {
					if (line.length() > 55) {
						System.out.println(line.trim().substring(0, 40).trim() + "...<Read More>");
					} else {
						System.out.println(line);
					}
				} else {
				System.out.println("blank line.");
			    }
			}
		}
		catch(Exception e) {
			System.out.println("you are reading from non existing file.");
		}
		
	}
}
