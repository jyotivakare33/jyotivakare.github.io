import java.io.*;
class JustifyText  {
      
    public static void main(String[] args) throws Exception
    {
	    BufferedReader reader = null;
		BufferedReader reader1 = null;
		int count = 0;
		int count1 = 0;
		try {
            File fileObj = new File(args[0]);
            if(fileObj.length() == 0) {
			   System.out.println("empty file.");
			   System.exit(0);
		    }
			reader = new BufferedReader(new FileReader(fileObj));
		    reader1 = new BufferedReader(new FileReader(fileObj));
		}
		catch(Exception e) {
		    System.out.println("File does not exist or not passing any file as an argument.");
		}
		String line;
		try {
			while((line = reader1.readLine()) != null)
			{
				count++;
			}
			String subPart1 = "";
			while((line = reader.readLine()) != null)
			{
				line = line.trim();
			    String[] data = line.split(" ");
			    StringBuffer result = new StringBuffer();
			    StringBuffer buffer = new StringBuffer();
			    if(line.length() != 0) {	  
				    int length1 = 0;
                    line = line + subPart1;
				    String result1 = "";   
				    if( count1 < count)
			        {
						if(data.length == 2)
			            {
				            int firstlength =  data[0].length();
				            int secondlength = data[1].length();
				            for( int index = 0 ; index < (80 - (firstlength + secondlength)); index++)
				            {
								result.append(" ");
				            }
								System.out.print((data[0] + result + data[1]));
			            }
						if(data.length > 2)
						{
							for(int index = 0; index < data.length; index++)
							{
								length1 =  length1 + data[index].length();
							}
				            if(line.length() < 80)
				            {
								int spacelength = (80 - length1) / data.length - 1 ;
					            int reminder = (80 - length1) % data.length - 1;
								for(int index = 0; index < spacelength; index++)
								{
									result.append(" ");
								}
								for(int index = 0; index < data.length; index++)
								{
									int number = 0;
									while(number <= reminder)
									{
										buffer.append(" ");
										number++;
									}
									if(index == 0)
										result1 = result1 + data[index] + result + buffer;
									else 
										result1 = result1 + data[index] + result;
								}
								System.out.print(result1);
							}
							if(line.length() > 80)
							{
							    String subPart = line.substring(0, 80);
							    System.out.println(subPart);
							    subPart1 = line.substring(80, line.length());
							}
						}
						count1++;
					}
					else {
						System.out.println(line);
					}
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
		   
