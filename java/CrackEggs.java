import java.io.*;
class CrackEggs {

	static int max(int firstCase, int secondCase)  {
		return (firstCase > secondCase) ? firstCase : secondCase;
	}

	static int eggDrop(int eggs, int floors) {
		int eggFloor[][] = new int[eggs + 1][floors + 1];
		int result;
		for (int index = 0; index <= eggs; index++) {
			eggFloor[index][1] = 1;
			eggFloor[index][0] = 0;
		}
		for (int index = 1; index <= floors; index++)
			eggFloor[1][index] = index;

		for (int outerindex = 2; outerindex <= eggs; outerindex++) {
			for (int innerindex = 2; innerindex <= floors; innerindex++) {
				eggFloor[outerindex][innerindex] = 999;
				for (int floorNumber = 1; floorNumber <= innerindex; floorNumber++) {
					result = 1 + max(eggFloor[outerindex - 1][floorNumber - 1], eggFloor[outerindex][innerindex - floorNumber]);
					if (result < eggFloor[outerindex][innerindex])
						eggFloor[outerindex][innerindex] = result;
				}
			}
		}
		return eggFloor[eggs][floors];
	}

	public static void main(String args[]) throws IOException {
		int numOfEggs = 0, numOfFloors = 0;
	    String line;
	    BufferedReader reader = null;
	    try {
			File fileObj = new File(args[0]);
			if(fileObj.length() == 0)
			{
				System.out.println("empty file.");
				System.exit(0);
			}
			reader = new BufferedReader(new FileReader(args[0]));
		}
		catch(FileNotFoundException | ArrayIndexOutOfBoundsException e) {
		   System.out.println("file does not exist or not passing file as an argument.");
	   }
		try {
			while((line = reader.readLine()) != null) {
				if(line.length() != 0)
				{
					line = line.trim();
					String data[] = line.split("\\s+ ");
					numOfEggs = Integer.parseInt(data[0]);
					numOfFloors = Integer.parseInt(data[1]);
					System.out.println("Minimum number of trials in worst case with "
					+ numOfEggs + "  eggs and " + numOfFloors + " floors is " 
					+ eggDrop(numOfEggs, numOfFloors));
				}
				else {
					System.out.println("blank line.");
				}
			}
			}
		    catch(Exception e) {
				System.out.println("you are reading line from non existing file.");
		    }
	}
}
