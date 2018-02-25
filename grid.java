import java.util.*;
import java.io.*;
import java.lang.*;


/////// Things to fix
/*
Make sure ArrayOutOfBounds is fixed - no target or player generates on the topmost,leftmost,rightmost or downmost row or column
Deadends in blocks
action cost when you observe blocks
Make blocks hidden until its in your surrounding
check if a cell is already in the open list
*/
///////


public class grid implements gridInterface {


    //Scanner scan = new Scanner(System.in);
    Random rg = new Random();
    int randomNumbLeftTarget;
    int randomNumbRightTarget;
    int randomNumbLeftPlayer;
    int randomNumbRightPlayer;
    int playerlastmoveLeft;
    int playerlastmoveRight;

	boolean check1=true;

    BinaryTree tree ;//= new BinarySearchTree();

	int g=0;
	int f=0;
	int h=0;


    public String[][] a;
    public int[][] route;
    public int[] list1;
    public int[] list2;
	public int started;
	public int positionLeft;
	public int positionRight;
	public int counter;

	public boolean check;
	int size=0;

	public int targetposLeft;
	public int targetposRight;


///////////////////////////
int fUp,fDown,fLeft,fRight;

int sizeMM;
int[] fArray = new int[4];
int[] fArray3 = new int[3];
static int[]fArray33=new int[3];
int[] fArray2 = new int[2];


int fSmall;
int posToMov=0;
int totalg=0;

//int counter=0;
int i=positionLeft;
int j=positionRight;
int firstleftpos=positionLeft;
int firstrightpos=positionRight;

/////////////////////

    LinkedList<String>	fs = new LinkedList<>(); //Fsmall array
    LinkedList<String> openList = new LinkedList<>();
    LinkedList<String> openList2 = new LinkedList<>();
    LinkedList<String> closedList = new LinkedList<String>();

//*** This part generates random number for the starting position




public grid()
{
	size=15;
	a = new String[size][size];
	route = new int[size][size];
	started=0; //Just for checks as the first beginning position is is (4,4) right now
	positionLeft=0;
	positionRight=0; //position in array
	counter=0;
	check=true;
	targetposLeft=0;
	targetposRight=0;


    randomNumbLeftPlayer = 12;//rg.nextInt( (size-1)-(size-3) )+(size-3);
    //(15-13)+13; //nextInt(High-Low) + Low;
    randomNumbRightPlayer = 12;//rg.nextInt( (size-1)-(size-2) )+(size-2);
    //(15-14)+14; /// generates number in 5i and 4j
    randomNumbLeftTarget = 12;//rg.nextInt(3-1)+1;
    randomNumbRightTarget = 8;//rg.nextInt(9-7)+7;


	/*
    randomNumbLeftTarget = rg.nextInt( (size-1)-(size-3) )+(size-3);
    //(15-13)+13; //nextInt(High-Low) + Low;
    randomNumbRightTarget = rg.nextInt( (size-1)-(size-2) )+(size-2);
    //(15-14)+14; /// generates number in 5i and 4j
    randomNumbLeftPlayer = rg.nextInt(3-1)+1;
    randomNumbRightPlayer = rg.nextInt(3-2)+2;

*/

    BinaryTree tree = new BinarySearchTree();

	ArrayList fs = new ArrayList();
    LinkedList<String> openList = new LinkedList<>();
    LinkedList<String> openList2 = new LinkedList<>();
    LinkedList<String> closedList = new LinkedList<String>();
}



//this should be called only once
public int gridInitializer() // Sets up Position player and Target
{
    if(started==0)                  // <- IF STATEMENT
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            a[i][j] = "_";
            if (i == randomNumbLeftTarget && j == randomNumbRightTarget) //if (i == randomNumbLeft && j == randomNumbRight)
            {
                a[i][j] = "T";
				targetposLeft=i;
                targetposRight=j;
            }
            else if (i == randomNumbLeftPlayer && j == randomNumbRightPlayer) //if (i == randomNumbLeft && j == randomNumbRight)
            {
                a[i][j] = "X";
                String ii = Integer.toString(i);
                String jj = Integer.toString(j);
                String kk = ii+","+jj;   // This right here for the first position
            	openList.add(kk);
            }
            else {}//System.out.print(a[i][j] + "|");
        }
    }
	gridBlockAdd();
    System.out.println();
    return 1;
}



public int direction()
{

    if(started==0)                  // <- IF STATEMENT
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            route[i][j] = 01;
            if (i == randomNumbLeftTarget && j == randomNumbRightTarget) //if (i == randomNumbLeft && j == randomNumbRight)
            {
                route[i][j] = 9; //Make sure to mak this a 0
				targetposLeft=i;
                targetposRight=j;
            }

            else if (i == randomNumbLeftPlayer && j == randomNumbRightPlayer) //if (i == randomNumbLeft && j == randomNumbRight)
            {
                route[i][j] = 0;
                positionLeft=i;
                positionRight=j;
            }

            else {}//System.out.print(a[i][j] + "|");
        }
    }

return 1;
}



//////////////////////********************************************************************************************************************************

public int blockChecker(int positionLeft,int positionRight)
{
int q=0;
if( (route[positionLeft-1][positionRight])==69 || (route[positionLeft+1][positionRight])==69
|| (route[positionLeft][positionRight-1])==69 || (route[positionLeft][positionRight+1])==69)
{
System.out.println("Blckchekce");
		if( (route[positionLeft-1][positionRight])==69 )
		{

			System.out.println("blocked");
			q=1;


		}//if Up is blocked

		else if( (route[positionLeft+1][positionRight])==69) q=2; //if down is blocked

		else if( (route[positionLeft][positionRight-1])==69) q=3; //if left is blocked

		else if( (route[positionLeft][positionRight+1])==69) q=4; //if right is blocked

}

 else {q=77;}
 return q;
}


public int moveMaker()
{
	int fSmall;	int post=0;	fSmall = fArray[0];

	for(int ii=0;ii<4;ii++)	System.out.println("movemaker: " + fArray[ii]);

	//for(int ii=0;ii<4;ii++)if(fArray[ii]<fSmall)fSmall=fArray[ii];


		if(route[positionLeft-1][positionRight]!=69)
		{
					String a,b,c;
					a = Integer.toString((positionLeft-1));
					b = Integer.toString(positionRight);
					c = a+","+b;   // This right here for the first position

					int v=closedList.indexOf(c);
					if(v!=-1)
					{
						System.out.println("up");
						System.out.println("v closedList:" + v);
						fArray[0]=1000;
					}
										else{
						System.out.println("up not in closed list");
						}
		}

		if(route[positionLeft+1][positionRight]!=69)
		{
					String a,b,c;
					a = Integer.toString((positionLeft+1));
					b = Integer.toString(positionRight);
					c = a+","+b;   // This right here for the first position

					int v=closedList.indexOf(c);
					if(v!=-1)
					{
						System.out.println("down");
						fArray[1]=1000;
					}
										else{
						System.out.println("down not in closed list");}
		}

		if(route[positionLeft][positionRight+1]!=69)
		{
					String a,b,c;
					a = Integer.toString((positionLeft));
					b = Integer.toString(positionRight+1);
					c = a+","+b;   // This right here for the first position

					int v=closedList.indexOf(c);
					if(v!=-1)
					{
						System.out.println("right");
						System.out.println("v closedList:" + v);
						fArray[3]=1000;
					}
					else{
						System.out.println("right not in closed list");}
		}

		if(route[positionLeft][positionRight-1]!=69)
		{
					String a,b,c;
					a = Integer.toString((positionLeft));
					b = Integer.toString(positionRight-1);
					c = a+","+b;   // This right here for the first position

					int v=closedList.indexOf(c);
					if(v!=-1)
					{
						System.out.println("left");
						System.out.println("v closedList:" + v);
						fArray[2]=1000;
					}
					else{
						System.out.println("left not in closed list");}
		}

System.out.println("fArray[0]"+ fArray[0]);
System.out.println("fArray[1]"+fArray[1]);
System.out.println("fArray[2]"+fArray[2]);
System.out.println("fArray[3]"+fArray[3]);

int min = fArray[0];
int index=0;
       for(int i = 0; i < fArray.length; i++)
	       {
	            if(min > fArray[i])
	            {
	                min = fArray[i];
	                index=i;
	            }
	        }

post=index;
System.out.println("INDEX: " + index);

int counter=0;




/*
//Put a check here if the current position and last position in open list.choose the one that is not in the open list

if(post==0)//UP
{
			String a,b,c;
		a = Integer.toString((positionLeft-1));
		b = Integer.toString(positionRight);
		c = a+","+b;   // This right here for the first position

		int v=	openList2.indexOf(c);
		if(v!=-1)
		{

			System.out.println("SLOT EXISTS IN THE OPEN LIST AT:" + c);
				for(int ii=0;ii<4;ii++)
				{
					if(ii!=2 && fSmall==fArray[ii])
					{
						System.out.println("Success");
						post=ii;
					}
				}
		}
}

if(post==1)//down
{
			String a,b,c;
		a = Integer.toString((positionLeft+1));
		b = Integer.toString(positionRight);
		c = a+","+b;   // This right here for the first position
		int v=	openList2.indexOf(c);
		if(v!=-1)
		{

			System.out.println("SLOT EXISTS IN THE OPEN LIST AT:" + c);
				for(int ii=0;ii<4;ii++)
				{
					if(ii!=2 && fSmall==fArray[ii])
					{
						System.out.println("Success");
						post=ii;
					}
				}
		}
}

if(post==2)//left
{
		String a,b,c;
	a = Integer.toString((positionLeft));
	b = Integer.toString(positionRight-1);
	c = a+","+b;   // This right here for the first position

	System.out.println("current position: " + positionLeft + ", " + positionRight);

	int v=	openList2.indexOf(c);
	if(v!=-1)
	{

		System.out.println("SLOT EXISTS IN THE OPEN LIST AT:" + c);
			for(int ii=0;ii<4;ii++)
			{
				if(ii!=2 && fSmall==fArray[ii])
				{
					System.out.println("Success");
					post=ii;
				}
			}
	}
}

if(post==3)//right
{

}
*/
	System.out.println("value of pos in movemaker: " + post);
	return post;
}









public int moveMakerThree()
{
	int fSmall;	int post=0;	fSmall = fArray3[0];
	String direction = "";


	System.out.println("[moveMakerThree]current position: " + positionLeft + ", " + positionRight);




	for(int ii=0;ii<3;ii++)
	{
		//if(route[][]==69) set value to 100

		if(fArray3[0]==fArray3[1] && fArray3[1]==fArray3[2])
		{
			System.out.println("****************************** yasss bitch");

				if(route[positionLeft][positionRight-1]!=69) // checks left slot
				{
					String a,b,c;
					a = Integer.toString((positionLeft));
					b = Integer.toString(positionRight-1);
					c = a+","+b;   // This right here for the first position

					int v=	openList2.indexOf(c);
					if(v!=-1)
					{

						System.out.println("[checks left slot]SLOT EXISTS IN THE OPEN LIST AT:" + c);

									System.out.println("Success5555555555555555555555555");
										fArray3[2]=1000;
					}

				}


				else if(route[positionLeft+1][positionRight]!=69 ) //checks down slot
				{
					String a,b,c;
					a = Integer.toString((positionLeft+1));
					b = Integer.toString(positionRight);
					c = a+","+b;   // This right here for the first position

					int v=	openList2.indexOf(c);
					if(v!=-1)
					{

						System.out.println("[//checks down slot]SLOT EXISTS IN THE OPEN LIST AT:" + c);

									System.out.println("Success5555555555555555555555555");

					fArray3[1]=1000;
					}

					if(v==-1)
					{
					System.out.println("ZXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
					}
				}

				else if(route[positionLeft-1][positionRight]!=69 ) //checks above slot
				{
					String a,b,c;
					a = Integer.toString((positionLeft-1));
					b = Integer.toString(positionRight);
					c = a+","+b;   // This right here for the first position

					int v=	openList2.indexOf(c);
					if(v!=-1)
					{

						System.out.println("[checks up slot]SLOT EXISTS IN THE OPEN LIST AT:" + c);

									System.out.println("Success5555555555555555555555555");
					}
					fArray3[0]=1000;
				}

		}

///////////////////////////////////////////////////////////////////////////////////

		if(fArray3[0]!=fArray3[1] && fArray3[1]!=fArray3[2])
		{
						if(route[positionLeft+1][positionRight]==69)//if right side is blocked then check up,down,left
						{
								if(route[positionLeft-1][positionRight]!=69) // checks left slot
								{
									String a,b,c;
									a = Integer.toString((positionLeft-1));
									b = Integer.toString(positionRight);
									c = a+","+b;   // This right here for the first position

									int v=	openList2.indexOf(c);
									//int v=	closedList.indexOf(c);

									if(v!=-1)
									{

										System.out.println("UP SLOT EXISTS IN THE OPEN LIST AT:" + c);
										fArray3[0]=1000;
									}
									else System.out.println("UP SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);
								}

								if(route[positionLeft][positionRight-1]!=69) // checks left slot
								{
									String a,b,c;
									a = Integer.toString((positionLeft));
									b = Integer.toString(positionRight-1);
									c = a+","+b;   // This right here for the first position

									//int v=	openList2.indexOf(c);
									int v=	closedList.indexOf(c);

									if(v!=-1)
									{

										System.out.println("LEFT SLOT EXISTS IN THE OPEN LIST AT:" + c);
										fArray3[1]=1000;
									}
									else System.out.println("LEFT SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);

								}

								if(route[positionLeft][positionRight+1]!=69) // checks left slot
								{
									String a,b,c;
									a = Integer.toString((positionLeft));
									b = Integer.toString(positionRight+1);
									c = a+","+b;   // This right here for the first position

									//int v=	openList2.indexOf(c);
									int v=	closedList.indexOf(c);

									if(v!=-1)
									{

										System.out.println("RIGHT SLOT EXISTS IN THE OPEN LIST AT:" + c);
										fArray3[2]=1000;
									}
									else System.out.println("RIGHTS SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);
								}
						}
		}


///////


		if(fArray3[0]==fArray3[1] && fArray3[1]!=fArray3[2])
		{
			System.out.println("(fArray3[0]==fArray3[1] && fArray3[1]!=fArray3[2]) SUPERNOVA");

						if(route[positionLeft+1][positionRight]==69)//if right side is blocked then check up,down,left
						{
								if(route[positionLeft-1][positionRight]!=69) // checks left slot
								{
									String a,b,c;
									a = Integer.toString((positionLeft-1));
									b = Integer.toString(positionRight);
									c = a+","+b;   // This right here for the first position

									int v=	openList2.indexOf(c);
									//int v=	closedList.indexOf(c);

									if(v!=-1)
									{

										System.out.println("UP SLOT EXISTS IN THE OPEN LIST AT:" + c);
										fArray3[0]=1000;
									}
									else System.out.println("UP SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);
								}

								if(route[positionLeft][positionRight-1]!=69) // checks left slot
								{
									String a,b,c;
									a = Integer.toString((positionLeft));
									b = Integer.toString(positionRight-1);
									c = a+","+b;   // This right here for the first position

									//int v=	openList2.indexOf(c);
									int v=	closedList.indexOf(c);

									if(v!=-1)
									{

										System.out.println("LEFT SLOT EXISTS IN THE OPEN LIST AT:" + c);
										fArray3[1]=1000;
									}
									else System.out.println("LEFT SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);

								}

								if(route[positionLeft][positionRight+1]!=69) // checks left slot
								{
									String a,b,c;
									a = Integer.toString((positionLeft));
									b = Integer.toString(positionRight+1);
									c = a+","+b;   // This right here for the first position

									//int v=	openList2.indexOf(c);
									int v=	closedList.indexOf(c);

									if(v!=-1)
									{

										System.out.println("RIGHT SLOT EXISTS IN THE OPEN LIST AT:" + c);
										fArray3[2]=1000;
									}
									else System.out.println("RIGHTS SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);
								}
						}

//%%%%%%%%%%%%%%%

			if(route[positionLeft-1][positionRight]==69)//if right side is blocked then check up,down,left
			{
				System.out.println("!@#ASDASDADADASD@@#!@#ASDSA 2");
					if(route[positionLeft+1][positionRight]!=69) // checks left slot
					{
						String a,b,c;a = Integer.toString((positionLeft+1));b = Integer.toString(positionRight);
						c = a+","+b;   // This right here for the first position
						//int v=	openList2.indexOf(c);
						int v=	closedList.indexOf(c);
						if(v!=-1)
						{
							System.out.println("DOWN SLOT EXISTS IN THE OPEN LIST AT:" + c);
							fArray3[0]=1000;
						}
						else	System.out.println("DOWN SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);
					}

					if(route[positionLeft][positionRight-1]!=69) // checks left slot
					{
						String a,b,c;a = Integer.toString((positionLeft));b = Integer.toString(positionRight-1);
						c = a+","+b;   // This right here for the first position

						//int v=	openList2.indexOf(c);
						int v=	closedList.indexOf(c);

						if(v!=-1)
						{
							System.out.println("LEFT SLOT EXISTS IN THE OPEN LIST AT:" + c);
							fArray3[1]=1000;
						}
						else	System.out.println("LEFT SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);
					}

					if(route[positionLeft][positionRight+1]!=69) // checks left slot
					{
						String a,b,c;a = Integer.toString((positionLeft));b = Integer.toString(positionRight+1);
						c = a+","+b;   // This right here for the first position
						//int v=	openList2.indexOf(c);
						int v=	closedList.indexOf(c);

						if(v!=-1)
						{
							System.out.println("RIGHT SLOT EXISTS IN THE OPEN LIST AT:" + c);
							fArray3[2]=1000;
						}
						else	System.out.println("RIGHT SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);
					}

			}

			//%%%%%%%%%%%%%%%%

			}


////////////////////////////////////////////////////////////////////////

	if(fArray3[0]!=fArray3[1] && fArray3[1]==fArray3[2])
	{
			System.out.println("(fArray3[0]!=fArray3[1] && fArray3[1]==fArray3[2]) yasss bitch");

				if(route[positionLeft-1][positionRight]!=69 ) //checks above slot
				{
					String a,b,c;
					a = Integer.toString((positionLeft-1));
					b = Integer.toString(positionRight);
					c = a+","+b;   // This right here for the first position

					int v=	openList2.indexOf(c);
					if(v!=-1)
					{

						System.out.println("DOWN SLOT EXISTS IN THE OPEN LIST AT:" + c);
					fArray3[0]=1000;
					}
				}

				else if(route[positionLeft+1][positionRight]!=69 ) //checks down slot
				{
					String a,b,c;
					a = Integer.toString((positionLeft+1));
					b = Integer.toString(positionRight);
					c = a+","+b;   // This right here for the first position

					int v=	openList2.indexOf(c);
					if(v!=-1)
					{

						System.out.println("SLOT EXISTS IN THE OPEN LIST AT:" + c);

									System.out.println("Success5555555555555555555555555");
										fArray3[1]=1000;
					}

				}

				else if(route[positionLeft][positionRight-1]!=69) // checks left slot
				{
					String a,b,c;
					a = Integer.toString((positionLeft));
					b = Integer.toString(positionRight-1);
					c = a+","+b;   // This right here for the first position

					int v=	openList2.indexOf(c);
					if(v!=-1)
					{

						System.out.println("SLOT EXISTS IN THE OPEN LIST AT:" + c);

									System.out.println("Success5555555555555555555555555");
										fArray3[2]=1000;
					}
				}

	}

////////////////////////////////////////////////////////////////////////
		if(fArray3[0]==fArray3[2] && fArray3[1]!=fArray3[2])
		{
				System.out.println("!@#ASDASDADADASD@@#!@#ASDSA");
			if(route[positionLeft][positionRight+1]==69)//if right side is blocked then check up,down,left
			{
					if(route[positionLeft-1][positionRight]!=69) // checks left slot
					{
						String a,b,c;a = Integer.toString((positionLeft-1));b = Integer.toString(positionRight);
						c = a+","+b;   // This right here for the first position
						//int v=	openList2.indexOf(c);
						int v=	closedList.indexOf(c);
						if(v!=-1)
						{
							System.out.println("UP SLOT EXISTS IN THE OPEN LIST AT:" + c);
							fArray3[0]=1000;
						}
						else	System.out.println("UP SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);
					}

					if(route[positionLeft+1][positionRight]!=69) // checks left slot
					{
						String a,b,c;a = Integer.toString((positionLeft+1));b = Integer.toString(positionRight);
						c = a+","+b;   // This right here for the first position

						//int v=	openList2.indexOf(c);
						int v=	closedList.indexOf(c);

						if(v!=-1)
						{
							System.out.println("DOWN SLOT EXISTS IN THE OPEN LIST AT:" + c);
							fArray3[1]=1000;
						}
						else	System.out.println("DOWN SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);
					}

					if(route[positionLeft][positionRight-1]!=69) // checks left slot
					{
						String a,b,c;a = Integer.toString((positionLeft));b = Integer.toString(positionRight-1);
						c = a+","+b;   // This right here for the first position
						//int v=	openList2.indexOf(c);
						int v=	openList2.indexOf(c);

						if(v!=-1)
						{
							System.out.println("UP SLOT EXISTS IN THE OPEN LIST AT:" + c);
							fArray3[2]=1000;
						}
						else	System.out.println("UP SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);
					}

			}

/*
			//%%%%%%%%%%%%%%%

			if(route[positionLeft-1][positionRight]==69)//if right side is blocked then check up,down,left
			{
				System.out.println("!@#ASDASDADADASD@@#!@#ASDSA 2");
					if(route[positionLeft+1][positionRight]!=69) // checks left slot
					{
						String a,b,c;a = Integer.toString((positionLeft+1));b = Integer.toString(positionRight);
						c = a+","+b;   // This right here for the first position
						//int v=	openList2.indexOf(c);
						int v=	closedList.indexOf(c);
						if(v!=-1)
						{
							System.out.println("UP SLOT EXISTS IN THE OPEN LIST AT:" + c);
							fArray3[0]=1000;
						}
						else	System.out.println("UP SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);
					}

					if(route[positionLeft][positionRight-1]!=69) // checks left slot
					{
						String a,b,c;a = Integer.toString((positionLeft));b = Integer.toString(positionRight-1);
						c = a+","+b;   // This right here for the first position

						//int v=	openList2.indexOf(c);
						int v=	closedList.indexOf(c);

						if(v!=-1)
						{
							System.out.println("DOWN SLOT EXISTS IN THE OPEN LIST AT:" + c);
							fArray3[1]=1000;
						}
						else	System.out.println("DOWN SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);
					}

					if(route[positionLeft][positionRight+1]!=69) // checks left slot
					{
						String a,b,c;a = Integer.toString((positionLeft));b = Integer.toString(positionRight+1);
						c = a+","+b;   // This right here for the first position
						//int v=	openList2.indexOf(c);
						int v=	openList2.indexOf(c);

						if(v!=-1)
						{
							System.out.println("UP SLOT EXISTS IN THE OPEN LIST AT:" + c);
							fArray3[2]=1000;
						}
						else	System.out.println("UP SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);
					}

			}

			//%%%%%%%%%%%%%%%%
			*/

				if(route[positionLeft][positionRight-1]!=69) // checks left slot
				{
					String a,b,c;
					a = Integer.toString((positionLeft));
					b = Integer.toString(positionRight-1);
					c = a+","+b;   // This right here for the first position

					//int v=	openList2.indexOf(c);
					int v=	closedList.indexOf(c);

					if(v!=-1)
					{

						System.out.println("LEFT SLOT EXISTS IN THE OPEN LIST AT:" + c);
						fArray3[1]=1000;
					}
					else
					{
						System.out.println("LEFT SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);
					}

					//fArray3[0]=1000;
					//fArray3[2]=1000;
				}

				if(route[positionLeft][positionRight+1]!=69) // checks left slot
				{
					String a,b,c;
					a = Integer.toString((positionLeft));
					b = Integer.toString(positionRight+1);
					c = a+","+b;   // This right here for the first position

					//int v=	openList2.indexOf(c);
					int v=	closedList.indexOf(c);

					if(v!=-1)
					{

						System.out.println("right SLOT EXISTS IN THE OPEN LIST AT:" + c);
						fArray3[2]=1000;
					}
					else{

						System.out.println("right SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);
						}

					//fArray3[0]=1000;
					//fArray3[2]=1000;
				}

				else if(route[positionLeft+1][positionRight]!=69 ) //checks down slot
				{
					String a,b,c;
					System.out.println("RIGHT SLOT IS HEREEEEEEEEEEEEEE");
					a = Integer.toString((positionLeft+1));
					b = Integer.toString(positionRight);
					c = a+","+b;   // This right here for the first position

					int v=	openList2.indexOf(c);
					if(v!=-1)
					{

						System.out.println("DOWN SLOT EXISTS IN THE OPEN LIST AT:" + c);
											fArray3[0]=1000;
					}
					else{

						System.out.println("DOWN SLOT DOES NOT * EXISTS IN THE OPEN LIST AT:" + c);
						}
					//fArray3[2]=1000;
				}

				else if(route[positionLeft-1][positionRight]!=69 ) //checks above slot
				{
					String a,b,c;
					a = Integer.toString((positionLeft-1));
					b = Integer.toString(positionRight);
					c = a+","+b;   // This right here for the first position

					int v=	openList2.indexOf(c);
					if(v!=-1)
					{

						System.out.println("SLOT EXISTS IN THE OPEN LIST AT:" + c);

									System.out.println("Success5555555555555555555555555");
					}
					//fArray3[1]=1000;
				}
		}

/////////
		System.out.println(" value at index " + ii + " : " + fArray3[ii]);
	}
/*
	for(int ii=0;ii<3;ii++)
	{
		System.out.println("value of fArray3 " + ii + " : " + fArray3[ii]);
			System.out.println("Smallest at " + ii + ": " + fSmall);
		if(fArray3[ii]<fSmall)
		{
			fSmall=fArray3[ii];
		}
	}
*/

int min = fArray3[0];
int index=0;
       for(int i = 0; i < fArray3.length; i++)
	       {
	            if(min > fArray3[i])
	            {
	                min = fArray3[i];
	                index=i;
	            }
	        }

System.out.println("post is : " + index);
post=index;
	//System.out.println("Calling sort!");
	//Arrays.sort(fArray3);
	//fSmall=fArray3[0];
	//.out.println("[moveMakerThree] Smallest: " + fSmall);

int counter=0;
//int[] c=new int[3];


//if(route[][]==69) set value to 100
/*
	for(int ii=00;ii<3;ii++)
	{
		if(fSmall==fArray3[ii])
		{
			post=ii;
			counter++;
		}
	}
*/


/*
if(counter>1)
{
	System.out.println("post :" + post);
	System.out.println("213****************************************8 more than one same small value");

	if(post==0)//UP
	{
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& post0");
		String a,b,c;
		a = Integer.toString((positionLeft-1));
		b = Integer.toString(positionRight);
		c = a+","+b;   // This right here for the first position

		int v=	openList2.indexOf(c);
		if(v!=-1)
		{

			System.out.println("SLOT EXISTS IN THE OPEN LIST AT:" + c);
				for(int ii=0;ii<4;ii++)
				{
					if(ii!=2 && fSmall==fArray[ii])
					{
						System.out.println("Success");
						post=ii;
					}
				}
		}
	}

	if(post==1)//down
	{
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& post1");
			String a,b,c;
		a = Integer.toString((positionLeft+1));
		b = Integer.toString(positionRight);
		c = a+","+b;   // This right here for the first position
		int v=	openList2.indexOf(c);
		if(v!=-1)
		{

			System.out.println("SLOT EXISTS IN THE OPEN LIST AT:" + c);
				for(int ii=0;ii<4;ii++)
				{
					if(ii!=2 && fSmall==fArray[ii])
					{
						System.out.println("Success");
						post=ii;
					}
				}
		}
	}

	if(post==2)//left
	{
			String a,b,c;
		a = Integer.toString((positionLeft));
		b = Integer.toString(positionRight-1);
		c = a+","+b;   // This right here for the first position

		System.out.println("current position: " + positionLeft + ", " + positionRight);

		int v=	openList2.indexOf(c);
		if(v!=-1)
		{

			System.out.println("SLOT EXISTS IN THE OPEN LIST AT:" + c);

				for(int ii=0;ii<4;ii++)
				{

					if(ii!=2 && fSmall==fArray[ii])
					{
						System.out.println("Success");
						post=ii;
					}

				}

		}
	}

	if(post==3)//right
	{

		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& post3");
		String a,b,c;
		a = Integer.toString((positionLeft));
		b = Integer.toString(positionRight+1);
		c = a+","+b;   // This right here for the first position

		System.out.println("current position: " + positionLeft + ", " + positionRight);

		int v=	openList2.indexOf(c);
		if(v!=-1)
		{

			System.out.println("SLOT EXISTS IN THE OPEN LIST AT:" + c);
				for(int ii=0;ii<4;ii++)
				{
					if(ii!=2 && fSmall==fArray[ii])
					{
						System.out.println("Success");
						post=ii;
					}
				}
		}

	}
}

*/

/*
if(fArray3[0]!=fArray3[1] && fArray3[1]!=fArray3[2])
{
	if(route[positionLeft+1][positionRight]==69)
	{}
}
*/


if(route[positionLeft-1][positionRight]==69)//down blocked
{

	if(post==2){post=3;}

	if(post==1){post=2;}

	if(post==0){post=1;}

}

if(route[positionLeft+1][positionRight]==69)//down blocked
{
	if(post==2){post=3;}
	else if(post==1){post=2;}
}

if(route[positionLeft][positionRight-1]==69)//down blocked
{
	if(post==2)post=3;
}


	System.out.println("value of pos in movemaker: " + post);
	//post=index;
	return post;
}

public int moveMakerTwo()
{
	int fSmall;	int post=0;	fSmall = fArray[0];

	for(int ii=0;ii<2;ii++)	System.out.println("movemaker2: " + fArray[ii]);

	for(int ii=0;ii<2;ii++)if(fArray[ii]<fSmall)fSmall=fArray[ii];

	for(int ii=0;ii<2;ii++)	if(fSmall==fArray[ii])post=ii;


	System.out.println("value of pos in movemaker: " + post);
	return post;
}


public int checkStatus(int positionLeft,int positionRight,int q)
{


/*
right side cell -> (positionLeft,positionRight+1)
left side cell -> (positionLeft,positionRight-1)
down cell -> (positionLeft+1,positionRight)

check if its blocked

*/

	if(q==1)//	rightward position()
	{

		String a,b,c;
	a = Integer.toString((positionLeft));
	b = Integer.toString(positionRight+1);
	c = a+","+b;   // This right here for the first position
	System.out.println("Current position(checkStatus): " + positionLeft + "," + positionRight);
	System.out.println("position we are looking forward to(checkStatus): " + positionLeft + "," + (positionRight+1));
	System.out.println("ayyyyy we out here :" + closedList.indexOf(c));

	//int indexnum = closedList.indexOf(c);
	//System.out.println("element " + closedList.get(indexnum) + " is located at index :" + closedList.indexOf(c) );



	}

	return 1;
}



public int queueStatus(int positionLeft,int positionRight) //this will check surround boxes every time
{
	//check the four directions which one is in the closed or open list
	//if closed list
		//if it mov

		System.out.println("[queueStatus] current position:" + positionLeft + "," + positionRight);
		//////////*******************8


	String a,b,upPos,downPos,leftPos,rightPos;
	a = Integer.toString(positionLeft-1);
	b = Integer.toString(positionRight);
	upPos = a+","+b;

	a = Integer.toString(positionLeft+1);
	b = Integer.toString(positionRight);
	downPos = a+","+b;

	a = Integer.toString(positionLeft);
	b = Integer.toString(positionRight-1);
	leftPos = a+","+b;   // This right here for the first position

	a = Integer.toString(positionLeft);
	b = Integer.toString(positionRight+1);
	rightPos = a+","+b;   // This right here for the first position
	int az=0;


	if( closedList.contains(downPos)==true )
	{
		az=1;
	System.out.println(downPos + " is in closed list open list");
	}

	if( closedList.contains(leftPos)==true )
	{
		az=2;
	System.out.println(leftPos + " is in closed list open list");
	}

	if( closedList.contains(rightPos)==true )
	{
		az=3;
			System.out.println(rightPos + " is in closed list open list");
	}



	//System.out.println("position we are looking forward to(checkStatus): " + positionLeft + "," + (positionRight+1));
	//System.out.println("ayyyyy we out here :" + closedList.indexOf(c));


	return az;
}


public boolean gridMover() //Note:how the program ends is it will show the target end and player take another step then end make sure your counting is correct
{
String i2;
String j2;
String kk;
String heuristic;
int oldpositionLeft = positionLeft;
int oldpositionRight = positionRight;

int pos=0;


System.out.println("Current position : " + positionLeft + "," + positionRight);

/*
paste all variables here
*/

//f= g + h
//h = abs (current_cell.x � goal.x) + abs (current_cell.y � goal.y)
//f=counter + (Math.abs(i-targetposLeft))+ (Math.abs(j-targetposRight));


h= (Math.abs(i-targetposLeft))+ (Math.abs(j-targetposRight));

System.out.println("********");

System.out.println("f: g + h");

		//(route[positionLeft][positionRight]) puts the actual value of the cell and not the distance of slot from home to node
//f = route[positionLeft][positionRight] + (Math.abs(i-targetposLeft))+ (Math.abs(j-targetposRight));
f = g + (Math.abs(i-targetposLeft))+ (Math.abs(j-targetposRight));


System.out.println("f:" + g + "+" + h + " = " + f);
System.out.println("********");
//f=route[positionLeft][positionRight] + (Math.abs(i-targetposLeft))+ (Math.abs(j-targetposRight));

if(counter>0)route[positionLeft][positionRight]=f;  //When counter is 1 it should put cell to 'f'


//f=route[positionLeft][positionRight] + (Math.abs(i-targetposLeft))+ (Math.abs(j-targetposRight));
//System.out.println("abs:" + f);

//System.out.println("g:" + g );


int q = blockChecker(positionLeft,positionRight);
//System.out.println("q block : " + q);


if(q==1)//up side is blocked
{
checkStatus(positionLeft,positionRight,q);
 int az = queueStatus(positionLeft,positionRight);

	fDown=counter + (Math.abs((positionLeft+1)-targetposLeft))+ (Math.abs(positionRight-targetposRight));
	System.out.println("abs of down:" + fDown);
	heuristic = String.valueOf(fDown);
	fs.add(heuristic);
	fArray3[0]=fDown;
	i2 = Integer.toString((positionLeft+1));
	j2 = Integer.toString(positionRight);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);

	fLeft=counter + (Math.abs((positionLeft)-targetposLeft))+ (Math.abs((positionRight-1)-targetposRight));
	System.out.println("abs of left:" + fLeft);
	heuristic = String.valueOf(fLeft);
	fs.add(heuristic);
	fArray3[1]=fLeft;
	i2 = Integer.toString((positionLeft));
	j2 = Integer.toString(positionRight-1);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);

	fRight=g + (Math.abs((positionLeft)-targetposLeft))+ (Math.abs((positionRight+1)-targetposRight));
	System.out.println("abs of right:" + fRight);
	heuristic = String.valueOf(fRight);
	fs.add(heuristic);
	fArray3[2]=fRight;
	i2 = Integer.toString((positionLeft));
	j2 = Integer.toString(positionRight+1);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);


	System.out.println("heuristic of left:" + fLeft);
	System.out.println("heuristic of right:" + fRight);
	System.out.println("heuristic of down:" + fDown);

// if the next heurisitic value is greater than LASTSLOTPOSITION in open list then move to other side of

	pos = moveMakerThree();
	System.out.println("posiiton zxc: " + pos);
}

else if (q==2)//down is blocked // THIS CONDITION ONLY KNOWS THAT ALL POSITIONS ARE OPEN AND DOES NOT CHECK THE LISTS
{
	sizeMM=3;
	fUp=counter + (Math.abs((positionLeft-1)-targetposLeft))+ (Math.abs(positionRight-targetposRight));
	System.out.println("abs of upper:" + fUp);
	heuristic = String.valueOf(fUp);
	fs.add(heuristic);
	fArray3[0]=fUp;
	i2 = Integer.toString((positionLeft-1));
	j2 = Integer.toString(positionRight);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);


	fLeft=counter + (Math.abs((positionLeft)-targetposLeft))+ (Math.abs((positionRight-1)-targetposRight));
	System.out.println("abs of left:" + fLeft);
	heuristic = String.valueOf(fLeft);
	fs.add(heuristic);
	fArray3[1]=fLeft;
	i2 = Integer.toString((positionLeft));
	j2 = Integer.toString(positionRight-1);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);



	fRight=g + (Math.abs((positionLeft)-targetposLeft))+ (Math.abs((positionRight+1)-targetposRight));
	System.out.println("abs of right:" + fRight);
	heuristic = String.valueOf(fRight);
	fs.add(heuristic);
	fArray3[2]=fRight;
	i2 = Integer.toString((positionLeft));
	j2 = Integer.toString(positionRight+1);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);

	pos = moveMakerThree();

}

else if (q==3)//left side is blocked
{
		System.out.println("LEFT IS BLOCKED");
	sizeMM=3;
	fUp=counter + (Math.abs((positionLeft-1)-targetposLeft))+ (Math.abs(positionRight-targetposRight));
	System.out.println("abs of upper:" + fUp);
	heuristic = String.valueOf(fUp);
	fs.add(heuristic);
	fArray3[0]=fUp;
	i2 = Integer.toString((positionLeft-1));
	j2 = Integer.toString(positionRight);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);


	fDown=counter + (Math.abs((positionLeft+1)-targetposLeft))+ (Math.abs(positionRight-targetposRight));
	System.out.println("abs of down:" + fDown);
	heuristic = String.valueOf(fDown);
	fs.add(heuristic);
	fArray3[1]=fDown;
	i2 = Integer.toString((positionLeft+1));
	j2 = Integer.toString(positionRight);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);


	fRight=g + (Math.abs((positionLeft)-targetposLeft))+ (Math.abs((positionRight+1)-targetposRight));
	System.out.println("abs of right:" + fRight);
	heuristic = String.valueOf(fRight);
	fs.add(heuristic);
	fArray3[2]=fRight;
	i2 = Integer.toString((positionLeft));
	j2 = Integer.toString(positionRight+1);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);

	pos = moveMakerThree();

}


else if (q==4)//right side is blocked
{

	System.out.println("UP IN THIS MUHAFUHA");
	sizeMM=3;
	fUp=counter + (Math.abs((positionLeft-1)-targetposLeft))+ (Math.abs(positionRight-targetposRight));
	System.out.println("abs of upper:" + fUp);
	heuristic = String.valueOf(fUp);
	fs.add(heuristic);
	fArray3[0]=fUp;
	i2 = Integer.toString((positionLeft-1));
	j2 = Integer.toString(positionRight);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);


	fDown=counter + (Math.abs((positionLeft+1)-targetposLeft))+ (Math.abs(positionRight-targetposRight));
	System.out.println("abs of down:" + fDown);
	heuristic = String.valueOf(fDown);
	fs.add(heuristic);
	fArray3[1]=fDown;
	i2 = Integer.toString((positionLeft+1));
	j2 = Integer.toString(positionRight);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);



	fLeft=counter + (Math.abs((positionLeft)-targetposLeft))+ (Math.abs((positionRight-1)-targetposRight));
	System.out.println("abs of left:" + fLeft);
	heuristic = String.valueOf(fLeft);
	fs.add(heuristic);
	fArray3[2]=fLeft;
	i2 = Integer.toString((positionLeft));
	j2 = Integer.toString(positionRight-1);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);

	pos = moveMakerThree();

}



else
{



//////***************************************************************************
//// This part is only if all four sides are open


//String l = queueStatus(positionLeft,positionRight);
//System.out.println("Passed string is : " + l);

System.out.println("All sides are open!!!!!!!!!!!!!!");

sizeMM=4;
//   ->  Replace counter by actual g size from starting node

fUp=counter + (Math.abs((positionLeft-1)-targetposLeft))+ (Math.abs(positionRight-targetposRight));
System.out.println("abs of upper:" + fUp);
heuristic = String.valueOf(fUp);
fs.add(heuristic);

fArray[0]=fUp;
i2 = Integer.toString((positionLeft-1));
j2 = Integer.toString(positionRight);
kk = i2+","+j2;   // This right here for the first position
openList2.add(kk);


fDown=counter + (Math.abs((positionLeft+1)-targetposLeft))+ (Math.abs(positionRight-targetposRight));
System.out.println("abs of down:" + fDown);
heuristic = String.valueOf(fDown);
fs.add(heuristic);

fArray[1]=fDown;
i2 = Integer.toString((positionLeft+1));
j2 = Integer.toString(positionRight);
kk = i2+","+j2;   // This right here for the first position
openList2.add(kk);


fLeft=counter + (Math.abs((positionLeft)-targetposLeft))+ (Math.abs((positionRight-1)-targetposRight));
System.out.println("abs of left:" + fLeft);
heuristic = String.valueOf(fLeft);
fs.add(heuristic);

fArray[2]=fLeft;
i2 = Integer.toString((positionLeft));
j2 = Integer.toString(positionRight-1);
kk = i2+","+j2;   // This right here for the first position
openList2.add(kk);



fRight=g + (Math.abs((positionLeft)-targetposLeft))+ (Math.abs((positionRight+1)-targetposRight));
System.out.println("abs of right:" + fRight);
heuristic = String.valueOf(fRight);
fs.add(heuristic);

fArray[3]=fRight;
i2 = Integer.toString((positionLeft));
j2 = Integer.toString(positionRight+1);
kk = i2+","+j2;   // This right here for the first position
openList2.add(kk);

	System.out.println("heuristic of left:" + fLeft);
	System.out.println("heuristic of right:" + fRight);
	System.out.println("heuristic of down:" + fDown);
	System.out.println("heuristic of up:" + fUp);

pos = moveMaker(); //POS is the smallest heuristic value is all four sides are open
System.out.println("smallest heuristic value is : " + pos);
}

//////
////		Make this below into a new method because movement is here
//////	while you are able to know there's a block it still moves into the block
///////	once you have a method you can not move thru the block
/////
//WHEN PLAYER POSITION IS EQUAL TO TARGET LOCATION IT ENDS

if(positionLeft==targetposLeft && positionRight==targetposRight)
{
	route[positionLeft][positionRight] = 73;
	check=false;
	//break;
}

//indexLeft and Right is the - + in the positions




else if(pos==0)  //moves UP
{

listMaker(positionLeft,positionRight,1,0);


	g=route[positionLeft-1][positionRight]+g;//This will add the 1point of the successor for the next loop
	//route[positionLeft][positionRight] = route[positionLeft-1][positionRight];

	route[positionLeft][positionRight] = route[positionLeft][positionRight] + route[positionLeft-1][positionRight];

oldpositionLeft = positionLeft;
oldpositionRight = positionRight;

	positionLeft = positionLeft-1;
	positionRight = positionRight;
}

else if(pos==1) //moves down
{
listMaker(positionLeft,positionRight,-1,0);


System.out.println("<g in down before> : " + g);

	g=route[positionLeft+1][positionRight]+g;//This will add the 1point of the successor for the next loop

System.out.println("<g in down before> : " + g);

	System.out.println("Current position:" + positionLeft + "," + positionRight);
	System.out.println("Value in current pos:" + route[positionLeft][positionRight]);


	route[positionLeft][positionRight] = route[positionLeft][positionRight] + route[positionLeft+1][positionRight];

	System.out.println("new position:" + positionLeft + "," + positionRight);


	//route[positionLeft][positionRight] = route[positionLeft+1][positionRight];

	oldpositionLeft = positionLeft;
oldpositionRight = positionRight;

	positionLeft = positionLeft+1;
	positionRight = positionRight;

}

else if(pos==2) //moves left
{

//i2 = Integer.toString((positionLeft));
//j2 = Integer.toString(positionRight-1);
listMaker(positionLeft,positionRight,0,1);

	int temp=0;

	System.out.println("[Right g before:]" + g);

	g = route[positionLeft][positionRight-1] + g;

	System.out.println("[Right g after:]" + g);

	temp= route[positionLeft][positionRight];

	System.out.println("Current position:" + positionLeft + "," + positionRight);

	g=route[positionLeft][positionRight-1]+g;//This will add the 1point of the successor for the next loop



	route[positionLeft][positionRight] = route[positionLeft][positionRight] + route[positionLeft][positionRight-1];
	//route[positionLeft][positionRight] = route[positionLeft][positionRight-1];

	oldpositionLeft = positionLeft;
oldpositionRight = positionRight;

	positionLeft = positionLeft;
	positionRight = positionRight-1;

}

else if(pos==3) //moves right
{
listMaker(positionLeft,positionRight,0,-1);

	int temp=0;
System.out.println("RightPostion:" + positionRight);

	g=route[positionLeft][positionRight+1]+g;//This will add the 1point of the successor for the next loop


	temp=route[positionLeft][positionRight]; //temp is the value of the previous slot so we know it exists


	route[positionLeft][positionRight] = route[positionLeft][positionRight] + route[positionLeft][positionRight+1]; //This helps move to the next slot


	System.out.println("%%%%%%% route[positionLeft][positionRight] " + route[positionLeft][positionRight]);
	System.out.println("%%%%%%% route[positionLeft][positionRight-1] " + route[positionLeft][positionRight-1]);

	route[positionLeft][positionRight-1] = temp;

	//route[positionLeft][positionRight] = route[positionLeft][positionRight+1];

oldpositionLeft = positionLeft;
oldpositionRight = positionRight;

	positionLeft = positionLeft;
	positionRight = positionRight+1;

}


route[positionLeft][positionRight] = fSmall;//73;


//////////////////////
//a[oldpositionLeft][oldpositionRight]="_";

a[positionLeft][positionRight]="X";  /// This is to show movement on the other string map
counter++;
i++;
j++;
System.out.println("***");
//gridMap();
System.out.println("***");

	    return check;
}


public int listMaker(int positionLeft,int positionRight,int indexLeft,int indexRight)
{
String i2;
String j2;
String kk;

i2 = Integer.toString((positionLeft-indexLeft));
j2 = Integer.toString(positionRight-indexRight);
kk = i2+","+j2;   // This right here for the first position

closedList.add(kk);
//System.out.println("current elements in openlist:\n " + openList2);
//System.out.println("current elements in closedlist:\n " + closedList);
int abc = openList2.indexOf(kk);
openList2.remove(kk);
//System.out.println("current elements in openlist:\n " + openList2);


//This part removes an element from openlist if its already in closed list
for(int i=0; i<closedList.size(); i++)
{
String wrd = closedList.get(i);
openList2.remove(wrd);
}

//String gfa = Collections.min(closedList);

	return 1;
}


public int closed()
{
/*
	int[][] b = new int[openList2.size()][2];
	int[] s = new int[openList2.size()];
	int p=0;
	int q=0;
	int k=0;
	String word;
	int leftnumber,rightnumber;
/*
		word=openList2.peek();
		System.out.println("zzzzzzzzz " + word);
		leftnumber = Character.getNumericValue(word.charAt(0)) ;
		rightnumber = Character.getNumericValue(word.charAt(2)) ;
		System.out.println("z " + leftnumber);
			System.out.println("z " + rightnumber);
*/
/*	int z=openList2.size();

	for(int i=0 ; i<z; i++)
	{

		for(int j=0; j<1;j++)
		{
		word=openList2.peek();
		leftnumber = Character.getNumericValue(word.charAt(0)) ;
		rightnumber = Character.getNumericValue(word.charAt(2)) ;
		openList2.poll();
		b[leftnumber][j]=leftnumber;
		b[][j]=rightnumber;
		}

	}

	for(int i=0 ; i<z; i++)
	{

		for(int j=0; j<1;j++)
		{
			System.out.println(b[i][j]);
		}

	}
*/
	System.out.println("Open : " + openList2);
	System.out.println("Closed : " + closedList);
	return 1;
}


///////&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7777



public int gridMap1()
{
	    for (int i = 0; i < size; i++)
	    {
	        for (int j = 0; j < size; j++)
	        {
	            System.out.print(a[i][j] + "|");
	        }
	        System.out.println();///To show proper shape of the array
    }
    return 1;
}

public int gridMap()
{
	    for (int i = 0; i < size; i++)
	    {
	        for (int j = 0; j < size; j++)
	        {
	            System.out.print(route[i][j] + "|");
	        }
	        System.out.println();///To show proper shape of the array
    }
    return 1;
}



public int blockMaker()
{
//Only reason i did not put a number checker here to see if a number is repeating because
//if they are they are overtaken in blocks so in this case the more the repition the better
//for the map-you either get a map full of blocks or you get very little blocks

	list1=new int[25];
	list2=new int[25];

	 for(int i=0;i<12;i++)
	 {
			int blockNumber = rg.nextInt(size);
			int blockNumber2 = rg.nextInt(size);
			list1[i]=blockNumber;
			list2[i]=blockNumber2;
	 }

    return 1;

}


public int gridBlockAdd()
{
  int ii=0;
    int jj=0;
blockMaker();
/*
   for (int i = 0; i < 5; i++)
    {
        for (int j = 0; j < 5; j++)
        {
			if(randomNumbLeftTarget==list1[ii] && randomNumbRightTarget==list2[jj]);

			else if(randomNumbLeftPlayer==list1[ii] && randomNumbRightPlayer==list2[jj]);

           else {
			   a[ list1[ii] ][ list2[jj] ] = "B";
			    route[ list1[ii] ][ list2[jj] ] = 69;
			   }
            jj++;
        }
        ii++;
    }
*/
/*
   for (int i = 4; i < 5; i++)
    {
        for (int j = 0; j < 5; j++)
        {
			if(randomNumbLeftTarget==list1[ii] && randomNumbRightTarget==list2[jj]);

			else if(randomNumbLeftPlayer==list1[ii] && randomNumbRightPlayer==list2[jj]);

           else {
			   a[i][j] = "B";
			    route[i][j] = 69;
			   }
            jj++;
        }
        ii++;
    }

   for (int i = 5; i <6; i++)
    {
        for (int j = 6; j < 9; j++)
        {
			if(randomNumbLeftTarget==list1[ii] && randomNumbRightTarget==list2[jj]);

			else if(randomNumbLeftPlayer==list1[ii] && randomNumbRightPlayer==list2[jj]);

           else {
			   a[i][j] = "B";
			    route[i][j] = 69;
			   }
            jj++;
        }
        ii++;
    }

*/



for(int i=0;i<size;i++)
{

		route[i][j]	= 69 ;
		a[i][j]="B";
	for(int j=0;j<size;j++)
	{

		if(i==0)
		{
		route[i][j]	= 69 ;
		a[i][j]="B";
		}

		if(i==size-1)
		{
		route[i][j]	= 69 ;
		a[i][j]="B";
		}

	if(j==size-1)
	{		route[i][j]	= 69 ;
		a[i][j]="B";}

	}

}

/*
a[8][6] = "B";a[8][7] = "B";
a[7][6] = "B";a[7][7] = "B";
a[6][6] = "B";a[6][7] = "B";
a[5][6] = "B";a[5][7] = "B";
a[4][6] = "B";a[4][7] = "B";
a[3][6] = "B";a[3][7] = "B";


route[8][6] = 69;route[8][7] = 69;
route[7][6] = 69;route[7][7] = 69;
route[6][6] = 69;route[6][7] = 69;
route[5][6] = 69;route[5][7] = 69;
route[4][6] = 69;route[4][7] = 69;
route[3][6] = 69;route[3][7] = 69;
*/

for(int i=0;i<size;i++)
{
	for(int j=0;j<size;j++)
	{

		if(j==4 && i<11)
		{
		route[i][j]	= 69 ;
		a[i][j]="B";
		}

		if(j==10 && i>4)
		{
		route[i][j]	= 69 ;
		a[i][j]="B";
		}
	}
}

//gridMap();

    System.out.println();

    return 1;
}

}
