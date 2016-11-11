package inp;

import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter the input file: ");
		String input = reader.nextLine();
		reader.close();

		String output = "result.txt";

		INPTransformer trans = new INPTransformer(input);
		trans.transform(output);

		System.out.println("Output to " + output);
		System.out.println("Completed");
	}
}
