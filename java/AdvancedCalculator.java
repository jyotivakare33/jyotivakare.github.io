import java.io.*;
class AdvancedCalculator {

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
		} catch (FileNotFoundException  | ArrayIndexOutOfBoundsException e) {
			System.out.println("File does not exist or not passing any file as an argument.");
		}
		try {
			while ((line = reader.readLine()) != null) {
				if (line.length() != 0) {
					line = line.trim();
					if(line.contains("+") || line.contains("-") || line.contains("*") || line.contains("/")
					|| line.contains("%")) {
						for(int index = 0; index < line.length(); index++)
						{
							char operator = line.charAt(index);
							switch(operator)
							{
								case '+': String[] first = line.split("\\+");
										  System.out.println(Integer.parseInt(first[0].trim())
										  + Integer.parseInt(first[1].trim()));
									      break;
								case '-': String[] second = line.split("\\-");
									      System.out.println(Integer.parseInt(second[0].trim()) 
									      - Integer.parseInt(second[1].trim()));
									      break;
								case '*': String[] third = line.split("\\*");
										  System.out.println(Integer.parseInt(third[0].trim()) 
						                  * Integer.parseInt(third[1].trim()));
						                  break;
								case '%': String[] fourth = line.split("\\%");
						                  System.out.println(Integer.parseInt(fourth[0].trim())
						                  % Integer.parseInt(fourth[1].trim()));
						                  break;
							    case '/': String[] fifth = line.split("\\/");
									      try {
											    System.out.print(Integer.parseInt(fifth[0].trim())
												/ Integer.parseInt(fifth[1].trim()));
										  } catch (ArithmeticException e) {
												System.out.println("divisor should not be zero.");
										  }
										break;
							}
						}	
					} 
					else {
						System.out.println("invalid input format.");
					}
			    } else {
					System.out.println("blank line.");
				}
		   }
		}
		catch ( Exception e) {
			System.out.println("you are reading line from non existing file.");
		}
	}
}
