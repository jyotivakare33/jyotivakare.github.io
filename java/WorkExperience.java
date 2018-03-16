import java.io.*;
class WorkExperience {
	static final int YEARS = 31;
	static final int MONTHS = 12;

	public static void main(String[] args) throws Exception {

		BufferedReader reader = null;
		BufferedReader reader1 = null;
		File file = null; 
		try {
			file = new File(args[0]);
			reader = new BufferedReader(new FileReader(file));
			reader1 = new BufferedReader(new FileReader(file));
		} catch (Exception e) {
			System.out.println("file is missing or not passing any file as an argument");
		}
		if (file.length() == 0) {
			System.out.println("empty file");
			System.exit(0);
		}
		String line;
		int lineCount = 0;
		while ((line = reader1.readLine()) != null) {
			lineCount++;
		}
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			boolean[][] months = new boolean[YEARS][MONTHS];
			if (line.length() != 0) {
				if (lineCount >= 20 && lineCount <= 40) {
					String[] dates = line.split(";");
					for (String date : dates) {
						String[] content = date.trim().split("-");
						String[] startDate = content[0].trim().split(" ");
						String[] endDate = content[1].trim().split(" ");
						int startMonth = month(startDate[0]);
						int startYear = Integer.valueOf(startDate[1]);
						startYear = startYear - 1990;
						int endMonth = month(endDate[0]);
						int endYear = Integer.valueOf(endDate[1]);
						endYear = endYear - 1990;
						while (true) {
							months[startYear][startMonth] = true;
							startMonth++;
							if (startMonth >= MONTHS) {
								startYear++;
								startMonth = 0;
							}
							if (startYear > endYear) {
								break;
							}
							if (startYear == endYear)
								if (startMonth > endMonth) {
									break;
								}
						}
					}
				} else {
					System.out.println("number of lines are not in the range");
				}

				int monthSum = 0;
				for (int year = 0; year < YEARS; ++year) {
					for (int month = 0; month < MONTHS; ++month) {
						if (months[year][month] == true)
							monthSum++;
					}
				}
				System.out.println(monthSum / 12);

			} else
				System.out.println("blank line");
		}
	}

	static int month(String month) {
		if ("Jan".equals(month))
			return 0;
		else if ("Feb".equals(month))
			return 1;
		else if ("Mar".equals(month))
			return 2;
		else if ("Apr".equals(month))
			return 3;
		else if ("May".equals(month))
			return 4;
		else if ("Jun".equals(month))
			return 5;
		else if ("Jul".equals(month))
			return 6;
		else if ("Aug".equals(month))
			return 7;
		else if ("Sep".equals(month))
			return 8;
		else if ("Oct".equals(month))
			return 9;
		else if ("Nov".equals(month))
			return 10;
		else
			return 11;
	}
}
