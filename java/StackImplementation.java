import java.io.*;

interface Stack1 {
	void push(Object number);
	Object pop();
}

public class StackImplementation implements Stack1 {
	int top = -1;
	Object data[];

	public void push(Object number) {
		if (top < data.length) {
			data[++top] = number;
		}
	}

	public Object pop() {
		if (top < -1) {
			System.out.println("stack is empty");
			return 0;
		}
		Object number = data[top--];
		return number;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		try {
			File file = new File(args[0]);
			if (file.length() == 0) {
				System.out.println("empty file.");
				System.exit(0);
			}
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
				System.out.println("file does not exist or not passing any filename as argument.");
				System.exit(0);
		}
		String line = "";
		while ((line = reader.readLine()) != null) {
			StackImplementation stackref = new StackImplementation();
			if (line.length() != 0) {
				line = line.trim();
				String[] groupOfData = line.split("\\s+");
				stackref.data = new Object[groupOfData.length];
				for (int index = 0; index < groupOfData.length; index++) {
					stackref.push(groupOfData[index]);
				}
				for (int index = 1; index <= stackref.data.length; index++) {
					Object result = stackref.pop();
					if (index % 2 != 0)
						System.out.print(result + " ");
				}
			} else {
				System.out.print("blank line.");
			}
			System.out.printf("\n");
		}
	}
}
