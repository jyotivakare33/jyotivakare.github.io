			import java.io.*;class RollerCoaster {	public static void main(String[] args) throws IOException {		BufferedReader reader = null;		String line;		try {			File fileObj = new File(args[0]);			if (fileObj.length() == 0) {				System.out.println("empty file.");				System.exit(0);			}			reader = new BufferedReader(new FileReader(fileObj));		} catch (Exception e) {			System.out.println("File does not exist or please provide file name.");			System.exit(0);		}		while ((line = reader.readLine()) != null) {			line = line.trim();			boolean flag = true;			String temp = "";			if (line.length() != 0) {				for (int index = 0; index < line.length(); index++) {					char alphanumeric = line.charAt(index);					if (Character.isLetter(alphanumeric)) {						if (flag)							temp = temp + Character.toUpperCase(alphanumeric);						else							temp = temp + Character.toLowerCase(alphanumeric);						flag = !flag;					} else						temp = temp + alphanumeric;				}				System.out.println(temp);			} else				System.out.println("blank line.");		}	}}		