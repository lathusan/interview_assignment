import java.util.Scanner;

public class ColorGridDemo {

	public static void main(String args[]) {

		Scanner scan = new Scanner(System.in);

		System.out.println("\t\t*** Hi ***");
		System.out.println("Welcome to the Color Grid Demo Application.");
		System.out.println("===========================================\n");

		String[] colors = { "R", "G", "B" };
		System.out.print("Enter the Number of rows : ");
		int X = scan.nextInt();
		System.out.print("Enter the Number of columns : ");
		int Y = scan.nextInt();

		ColorGridApp colorGrid = new ColorGridApp(X, Y, colors);
		String grid[][] = colorGrid.initGrid();
		colorGrid.printColorGrid(grid);
		colorGrid.findLargestContinuesGrid(grid);

	}
}