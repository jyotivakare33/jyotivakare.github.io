import java.sql.*;
import java.util.*;
import java.io.*;
import java.lang.*;
import org.postgresql.util.PSQLException;
class AddDataToPrograms
{
	public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException
	{
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection connectionDatabase = DriverManager.getConnection("jdbc:postgresql://localhost/" , "postgres" , "postgres");
			Statement statementDatabase = connectionDatabase.createStatement();
			ResultSet resultSetDatabase = statementDatabase.executeQuery("select datname from pg_database;");
			while(resultSetDatabase.next())
			{
				System.out.println(resultSetDatabase.getString(1));
			}
			System.out.println("Enter the database name which you want to modify");
			Scanner inputDatabase = new Scanner(System.in);
			String databaseName = inputDatabase.next();
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/" + databaseName , "postgres" , "postgres");
			Statement statement = connection.createStatement();
			ResultSet resultSet;
			String[] types = {"TABLE"};
			String column = "" , insertValue = "" , createTableSyntax = "", line , result = "";
			resultSet = connection.getMetaData().getTables(null, null, "%", types);
			String[] javaFileName ={"Trick Or Treat", "Sum Of Digits", "Hidden Digits", "Longest Words", "Reverse Words", "Split TheNumber", "Fizz Buzz" ,
								"Right Most Char", "Roller Coaster", "Step Wise Word", "Pangrams", "String Search", "Distinct Subsequence", 
								"Swap Case", "Read More", "Shortest Repeatations", "Stack Interface", "Consecutive Prime", "Email Validation", 
								"Justify The Text", "Robot Movements", "Egg Crack", "Advance Calculator", "Working Experience", "String Rotation", 
								"Reverse Groups", "Text Dollar", "Text To Number", "Compressed Sequence", "Reverse And Add"};
			String[] name ={"Halloween.txt" , "SumOfDigits.txt" , "HiddenDigits.txt" , "LongestWord.txt" , "ReverseWords.txt" , "SplitTheNumber.txt" , "FizzBuzz.txt" ,
								"RightMostChar.txt" , "RollerCoaster.txt" , "StepWiseWord.txt" , "Pangrams.txt" , "StringSearching.txt" , "DistinctSubsequence.txt" ,
								"SwapCase.txt" , "ReadMore.txt" , "ShortestRepetition.txt" , "StackImplementation.txt" , "ConsecutivePrimes.txt" , "EmailValidation.txt" ,
								"JustifyTheText.txt" , "RobotMovements.txt" , "CrackingEggs.txt" , "AdvancedCalculator.txt" , "WorkingExperience.txt" , "StringRotation.txt" ,
								"ReverseGroups.txt" , "TextDollar.txt" , "TextToNumber.txt" , "CompressedSequence.txt" , "ReverseAndAdd.txt" };
			if(resultSet.next())
			{
				resultSet = connection.getMetaData().getTables(null, null, "%", types);
				System.out.println("List of tables present in the " + databaseName + " are ");
				while(resultSet.next())
				{
					System.out.println(resultSet.getString(3));
				}
				System.out.println("choose the table name you want to modify.");
				Scanner inputTableName = new Scanner(System.in);
				String tablename = inputTableName.next();
				while(true)
				{
					resultSet = statement.executeQuery("select *from " + tablename);
					resultSet.next();
					System.out.println("Enter any number to continue or enter 0 to exit.");
					Scanner input = new Scanner(System.in);
					int number = input.nextInt();
					if(number != 0)
					{
						for(int index = 0 ; index < javaFileName.length; index ++)
						{
							BufferedReader localBufferedReader = new BufferedReader(new FileReader("text/" + name[index]));
							while ((line = localBufferedReader.readLine()) != null)
							{
								result += line  + "\n";
							};
							int number2 = index+1;
							statement.executeUpdate("Insert into " + tablename + " Values (default, '" + javaFileName[index] +"', '" + result + "');");
							System.out.println("Insert into " + tablename + " Values (default, '" + javaFileName[index] +"', '" + result + "' );");
							System.out.println("data inserted into table.");
							result = "";
							line = "";
						}
					}else
					{
						break;
					}
				}
			}else
			{
				System.out.println("No tables present in this data base.");
			}
		}catch(PSQLException ex)
		{
			System.out.println("Invalid input, PSQL exception raised.");
			ex.printStackTrace();
		}
	}
}
