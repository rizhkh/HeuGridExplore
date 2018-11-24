
import java.util.*;
import java.io.*;
import java.lang.*;

public class GridDemo {

public static void main(String[] args)
{

	boolean check=true;
	grid g = new grid();
	Scanner scan = new Scanner(System.in);
	g.direction();
	g.gridInitializer();
	//System.out.print("Enter 1 to move left, 5 to up,3 to move right,2 to move down:");
    int input ;//= scan.nextInt();
	//g.gridInitialDirection(input);
	//g.gridMap();
	System.out.println();
	g.gridBlockAdd(); //////////////////////////////////////////////////////////////////////////////////////////////////////////
			g.gridMap1();
	do
	{

		g.gridMap1();
		//System.out.print("Enter 1 to move left, 5 to up,3 to move right,2 to move down:");
    	//input = scan.nextInt();
		check = g.gridMover();
		//g.gridMap();
	}
	while(check==true);

			g.gridMap1();
	//g.closed();

}


}
