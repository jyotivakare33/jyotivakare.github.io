import java.io.*;
import java.util.regex.*;
class StringSearch {
	
      public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		File file = null;
		try { 
			file = new File(args[0]);
			reader = new BufferedReader(new FileReader(file));
		} catch (Exception e) {
			System.out.println("File does not exist or not passing any file as an argument.");
		}
		if (file.length() == 0) {
			System.out.println("empty file.");
			System.exit(0);
		}
		String line;
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			if (line.length() != 0) {
				if (line.matches("(.+),(.+)")) {
					String[] data = line.split(",");
					String word1 = data[0].trim();
					String word2 = data[1].trim();
					Pattern patternObj = Pattern.compile(word2);
					Matcher matcherObj = patternObj.matcher(word1);
					if (matcherObj.find())
						System.out.println(true);
					else
						System.out.println(false);
				} else
					System.out.println("invalid input format.");
			} else
				System.out.println("blank line.");
		}
	}
}
