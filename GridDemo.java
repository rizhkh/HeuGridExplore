import java.util.*;
import java.io.*;
import java.lang.*;

public class GridDemo {

	public static void main(String[] args){
		boolean check=true;
		grid gridObj = new grid();
		Scanner scan = new Scanner(System.in);
		gridObj.direction();
		gridObj.gridInitializer();
		int input ;
		System.out.println();
		gridObj.gridBlockAdd();
		gridObj.gridMap1();
		do
		{
			gridObj.gridMap1();
			check = gridObj.gridMover();
		}
		while(check==true);
		gridObj.gridMap1();
	}

}
