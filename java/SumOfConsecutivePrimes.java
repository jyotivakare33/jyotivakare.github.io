import java.io.*;
import java.util.*;
class SumOfConsecutivePrimes {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = null;
		File file = null;
		try {
			file = new File(args[0]);
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
			System.out.println("File does not exist or not passing any file as an argument");
			System.exit(0);
		}
		if (file.length() == 0) {
			System.out.println("empty file");
			System.exit(0);
		}
		String line;
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			ArrayList<Integer> list = new ArrayList();
			ArrayList<Integer> list1 = new ArrayList();
			int count1 = 0;
			if (line.length() != 0) {
				String collectionOfData[] = line.split("\\s+");
				if (collectionOfData.length != 2) {
					System.out.println("invalid input format");
				}
				try {
					int first = Integer.parseInt(collectionOfData[0]);
					int second = Integer.parseInt(collectionOfData[1]);
					for (int outerIndex = first; outerIndex <= second; outerIndex++) {
						int flag = 0;
						for (int innerIndex = 2; innerIndex < outerIndex; innerIndex++) {
							if ((outerIndex % innerIndex) == 0)
								flag = 1;
						}
						if (flag == 0) {
							list.add(outerIndex);
						}
					}
					for (int index = 0; index < list.size() - 1; index++) {
						int variable = index + 1;
						int count = 0;
						count = variable - index;
						if (count == 1) {
							list1.add(list.get(index) + list.get(variable) + 1);
						}
					}
					for (int outerIndex = 0; outerIndex < list.size(); outerIndex++) {
						for (int innerIndex = 0; innerIndex < list1.size(); innerIndex++) {
							if (list1.get(innerIndex) == list.get(outerIndex)) {
								count1++;
							}
						}
					}
					System.out.print(count1);
					System.out.println();
				} catch (NumberFormatException e) {
					System.out.println("invalid input");
				}
			} else
				System.out.println("blank line");
		}
	}
}
