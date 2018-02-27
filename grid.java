import java.util.*;
import java.io.*;
import java.lang.*;

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


public grid()
{
	size=25;
	a = new String[size][size];
	route = new int[size][size];
	started=0;
	positionLeft=0;
	positionRight=0; //position in array
	counter=0;
	check=true;
	targetposLeft=0;
	targetposRight=0;


    randomNumbLeftPlayer = 3;//rg.nextInt( (size-1)-(size-3) )+(size-3);
    //(15-13)+13; //nextInt(High-Low) + Low;
    randomNumbRightPlayer = 3;//rg.nextInt( (size-1)-(size-2) )+(size-2);
    //(15-14)+14; /// generates number in 5i and 4j
    // USE 20
    randomNumbLeftTarget = 20;//rg.nextInt(3-1)+1;
    randomNumbRightTarget = 20;//rg.nextInt(9-7)+7;


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
    if(started==0)
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
		if( (route[positionLeft-1][positionRight])==69 )
		{
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

		if(route[positionLeft-1][positionRight]!=69)
		{
					String a,b,c;
					a = Integer.toString((positionLeft-1));
					b = Integer.toString(positionRight);
					c = a+","+b;

					int v=closedList.indexOf(c);
					if(v!=-1)
					{

						fArray[0]=1000;
					}
					else{}
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

						fArray[1]=1000;
					}
					else{}
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
						fArray[3]=1000;
					}
					else{}
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
						fArray[2]=1000;
					}
					else{}
		}

int min = fArray[0];
int index=0;
for(int i = 0; i < fArray.length; i++){
	if(min > fArray[i])
    {
	  min = fArray[i];
	  index=i;
	}
}

post=index;

int counter=0;
return post;
}


public int moveMakerThree()
{
	int fSmall;	int post=0;	fSmall = fArray3[0];
	String direction = "";

	for(int ii=0;ii<3;ii++)
	{
		//if(route[][]==69) set value to 100
		if(fArray3[0]==fArray3[1] && fArray3[1]==fArray3[2])
		{
				if(route[positionLeft][positionRight-1]!=69) // checks left slot
				{
					String a,b,c;
					a = Integer.toString((positionLeft));
					b = Integer.toString(positionRight-1);
					c = a+","+b;   // This right here for the first position

					int v=	openList2.indexOf(c);
					if(v!=-1)
					{
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
						fArray3[1]=1000;
					}
					if(v==-1)
					{}
				}

				else if(route[positionLeft-1][positionRight]!=69 ) //checks above slot
				{
					String a,b,c;
					a = Integer.toString((positionLeft-1));
					b = Integer.toString(positionRight);
					c = a+","+b;   // This right here for the first position

					int v=	openList2.indexOf(c);
					if(v!=-1)
					{}
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
										fArray3[0]=1000;
									}
									else{}
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
										fArray3[1]=1000;
									}
									else{}

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
										fArray3[2]=1000;
									}
									else {}
								}
						}
		}


///////


		if(fArray3[0]==fArray3[1] && fArray3[1]!=fArray3[2])
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
										fArray3[0]=1000;
									}
									else {}
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
										fArray3[1]=1000;
									}
									else {}

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
										fArray3[2]=1000;
									}
									else {}
								}
						}

//%%%%%%%%%%%%%%%

			if(route[positionLeft-1][positionRight]==69)//if right side is blocked then check up,down,left
			{
					if(route[positionLeft+1][positionRight]!=69) // checks left slot
					{
						String a,b,c;a = Integer.toString((positionLeft+1));b = Integer.toString(positionRight);
						c = a+","+b;   // This right here for the first position
						//int v=	openList2.indexOf(c);
						int v=	closedList.indexOf(c);
						if(v!=-1)
						{
							fArray3[0]=1000;
						}
						else {}
					}

					if(route[positionLeft][positionRight-1]!=69) // checks left slot
					{
						String a,b,c;a = Integer.toString((positionLeft));b = Integer.toString(positionRight-1);
						c = a+","+b;   // This right here for the first position

						//int v=	openList2.indexOf(c);
						int v=	closedList.indexOf(c);

						if(v!=-1)
						{
							fArray3[1]=1000;
						}
						else {}
					}

					if(route[positionLeft][positionRight+1]!=69) // checks left slot
					{
						String a,b,c;a = Integer.toString((positionLeft));b = Integer.toString(positionRight+1);
						c = a+","+b;   // This right here for the first position
						//int v=	openList2.indexOf(c);
						int v=	closedList.indexOf(c);

						if(v!=-1)
						{
							fArray3[2]=1000;
						}
						else {}
					}

			}

			//%%%%%%%%%%%%%%%%

			}


////////////////////////////////////////////////////////////////////////

	if(fArray3[0]!=fArray3[1] && fArray3[1]==fArray3[2])
	{
				if(route[positionLeft-1][positionRight]!=69 ) //checks above slot
				{
					String a,b,c;
					a = Integer.toString((positionLeft-1));
					b = Integer.toString(positionRight);
					c = a+","+b;   // This right here for the first position

					int v=	openList2.indexOf(c);
					if(v!=-1)
					{
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
										fArray3[2]=1000;
					}
				}

	}

////////////////////////////////////////////////////////////////////////
		if(fArray3[0]==fArray3[2] && fArray3[1]!=fArray3[2])
		{

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

							fArray3[0]=1000;
						}
						else	{}
					}

					if(route[positionLeft+1][positionRight]!=69) // checks left slot
					{
						String a,b,c;a = Integer.toString((positionLeft+1));b = Integer.toString(positionRight);
						c = a+","+b;   // This right here for the first position

						//int v=	openList2.indexOf(c);
						int v=	closedList.indexOf(c);

						if(v!=-1)
						{
							fArray3[1]=1000;
						}
						else	{}
					}

					if(route[positionLeft][positionRight-1]!=69) // checks left slot
					{
						String a,b,c;a = Integer.toString((positionLeft));b = Integer.toString(positionRight-1);
						c = a+","+b;   // This right here for the first position
						//int v=	openList2.indexOf(c);
						int v=	openList2.indexOf(c);

						if(v!=-1)
						{
							fArray3[2]=1000;
						}
						else	{}
					}

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

						fArray3[1]=1000;
					}
					else
					{
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
						fArray3[2]=1000;
					}
					else{
						}

					//fArray3[0]=1000;
					//fArray3[2]=1000;
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
						fArray3[0]=1000;
					}
					else{
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
					}
					//fArray3[1]=1000;
				}
		}

/////////
	}


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

post=index;

int counter=0;

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

	//post=index;
	return post;
}


/////////////////////////
/*
public int moveMakerTwo()
{
	int fSmall;	int post=0;	fSmall = fArray[0];

	for(int ii=0;ii<2;ii++)	System.out.println("movemaker2: " + fArray[ii]);

	for(int ii=0;ii<2;ii++)if(fArray[ii]<fSmall)fSmall=fArray[ii];

	for(int ii=0;ii<2;ii++)	if(fSmall==fArray[ii])post=ii;


	System.out.println("value of pos in movemaker: " + post);
	return post;
}
*/
	public int moveMakerTwo()
	{
		int fSmall;
		int post = 0;
		fSmall = fArray2[0];

		int left = 0;
		int right = 0;
		int up = 0;
		int down = 0;

		if (fArray2[0] == fArray2[1])
		{
			if ((route[positionLeft + 1][positionRight + 1]) == 69) //if down and right is blocked
			{
				if (route[positionLeft - 1][positionRight] != 69) // checks left slot
				{
					String a, b, c;
					a = Integer.toString((positionLeft - 1));
					b = Integer.toString(positionRight);
					c = a + "," + b;   // This right here for the first position
					//int v=	openList2.indexOf(c);
					int v = closedList.indexOf(c);
					if (v != -1) {
						fArray2[0] = 1000;
					} else {}
				}

				if (route[positionLeft][positionRight - 1] != 69) // checks left slot
				{
					String a, b, c;
					a = Integer.toString((positionLeft - 1));
					b = Integer.toString(positionRight);
					c = a + "," + b;   // This right here for the first position
					//int v=	openList2.indexOf(c);
					int v = closedList.indexOf(c);
					if (v != -1) {
						fArray2[1] = 1000;
					} else {}
				}
			}


			if ((route[positionLeft + 1][positionRight - 1]) == 69) //if down and right is blocked
			{
				if (route[positionLeft - 1][positionRight] != 69) // checks left slot
				{
					String a, b, c;
					a = Integer.toString((positionLeft - 1));
					b = Integer.toString(positionRight);
					c = a + "," + b;   // This right here for the first position
					//int v=	openList2.indexOf(c);
					int v = closedList.indexOf(c);
					if (v != -1) {
						fArray2[0] = 1000;
					} else {}
				}

				if (route[positionLeft][positionRight + 1] != 69) // checks left slot
				{
					String a, b, c;
					a = Integer.toString((positionLeft - 1));
					b = Integer.toString(positionRight);
					c = a + "," + b;   // This right here for the first position
					//int v=	openList2.indexOf(c);
					int v = closedList.indexOf(c);
					if (v != -1) {
						fArray2[1] = 1000;
					} else {}
				}
			}


			if ((route[positionLeft + 1][positionLeft - 1]) == 69) //if down and right is blocked
			{
				if (route[positionLeft][positionRight - 1] != 69) // checks left slot
				{
					String a, b, c;
					a = Integer.toString((positionLeft - 1));
					b = Integer.toString(positionRight);
					c = a + "," + b;   // This right here for the first position
					//int v=	openList2.indexOf(c);
					int v = closedList.indexOf(c);
					if (v != -1) {
						fArray2[0] = 1000;
					} else {}

				}

				if (route[positionLeft][positionRight + 1] != 69) // checks left slot
				{
					String a, b, c;
					a = Integer.toString((positionLeft - 1));
					b = Integer.toString(positionRight);
					c = a + "," + b;   // This right here for the first position
					//int v=	openList2.indexOf(c);
					int v = closedList.indexOf(c);
					if (v != -1) {
						fArray2[1] = 1000;
					} else {}

				}
			}

		}


		int min = fArray2[0];
		int index = 0;
		for (int i = 0; i < fArray2.length; i++) {
			if (min > fArray2[i]) {
				min = fArray2[i];
				index = i;
			}
		}
		post = index;


		if ((route[positionLeft + 1][positionRight + 1]) == 69) //if down and right is blocked
		{
			if (post == 1) post = 2;
		}


		if ((route[positionLeft + 1][positionRight - 1]) == 69) //if down and right is blocked
		{
			if (post == 1) post = 3;

		}


		if ((route[positionLeft + 1][positionLeft - 1]) == 69) //if down and right is blocked
		{
			if (post == 1) post = 3;
			if (post == 0) post = 2;
		}

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
	//int indexnum = closedList.indexOf(c);
	//System.out.println("element " + closedList.get(indexnum) + " is located at index :" + closedList.indexOf(c) );



	}

	return 1;
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


/*
paste all variables here
*/

h= (Math.abs(i-targetposLeft))+ (Math.abs(j-targetposRight));

		//(route[positionLeft][positionRight]) puts the actual value of the cell and not the distance of slot from home to node
//f = route[positionLeft][positionRight] + (Math.abs(i-targetposLeft))+ (Math.abs(j-targetposRight));
f = g + (Math.abs(i-targetposLeft))+ (Math.abs(j-targetposRight));
//f=route[positionLeft][positionRight] + (Math.abs(i-targetposLeft))+ (Math.abs(j-targetposRight));

if(counter>0)route[positionLeft][positionRight]=f;  //When counter is 1 it should put cell to 'f'


//f=route[positionLeft][positionRight] + (Math.abs(i-targetposLeft))+ (Math.abs(j-targetposRight));


int q = blockChecker(positionLeft,positionRight);


if(q==1)//up side is blocked
{
checkStatus(positionLeft,positionRight,q);

	fDown=counter + (Math.abs((positionLeft+1)-targetposLeft))+ (Math.abs(positionRight-targetposRight));
	heuristic = String.valueOf(fDown);
	fs.add(heuristic);
	fArray3[0]=fDown;
	i2 = Integer.toString((positionLeft+1));
	j2 = Integer.toString(positionRight);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);

	fLeft=counter + (Math.abs((positionLeft)-targetposLeft))+ (Math.abs((positionRight-1)-targetposRight));
	heuristic = String.valueOf(fLeft);
	fs.add(heuristic);
	fArray3[1]=fLeft;
	i2 = Integer.toString((positionLeft));
	j2 = Integer.toString(positionRight-1);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);

	fRight=g + (Math.abs((positionLeft)-targetposLeft))+ (Math.abs((positionRight+1)-targetposRight));
	heuristic = String.valueOf(fRight);
	fs.add(heuristic);
	fArray3[2]=fRight;
	i2 = Integer.toString((positionLeft));
	j2 = Integer.toString(positionRight+1);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);


// if the next heurisitic value is greater than LASTSLOTPOSITION in open list then move to other side of

	pos = moveMakerThree();
}

else if (q==2)//down is blocked // THIS CONDITION ONLY KNOWS THAT ALL POSITIONS ARE OPEN AND DOES NOT CHECK THE LISTS
{
	sizeMM=3;
	fUp=counter + (Math.abs((positionLeft-1)-targetposLeft))+ (Math.abs(positionRight-targetposRight));
	heuristic = String.valueOf(fUp);
	fs.add(heuristic);
	fArray3[0]=fUp;
	i2 = Integer.toString((positionLeft-1));
	j2 = Integer.toString(positionRight);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);


	fLeft=counter + (Math.abs((positionLeft)-targetposLeft))+ (Math.abs((positionRight-1)-targetposRight));
	heuristic = String.valueOf(fLeft);
	fs.add(heuristic);
	fArray3[1]=fLeft;
	i2 = Integer.toString((positionLeft));
	j2 = Integer.toString(positionRight-1);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);



	fRight=g + (Math.abs((positionLeft)-targetposLeft))+ (Math.abs((positionRight+1)-targetposRight));
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
	sizeMM=3;
	fUp=counter + (Math.abs((positionLeft-1)-targetposLeft))+ (Math.abs(positionRight-targetposRight));
	heuristic = String.valueOf(fUp);
	fs.add(heuristic);
	fArray3[0]=fUp;
	i2 = Integer.toString((positionLeft-1));
	j2 = Integer.toString(positionRight);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);


	fDown=counter + (Math.abs((positionLeft+1)-targetposLeft))+ (Math.abs(positionRight-targetposRight));
	heuristic = String.valueOf(fDown);
	fs.add(heuristic);
	fArray3[1]=fDown;
	i2 = Integer.toString((positionLeft+1));
	j2 = Integer.toString(positionRight);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);


	fRight=g + (Math.abs((positionLeft)-targetposLeft))+ (Math.abs((positionRight+1)-targetposRight));
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
	sizeMM=3;
	fUp=counter + (Math.abs((positionLeft-1)-targetposLeft))+ (Math.abs(positionRight-targetposRight));
	heuristic = String.valueOf(fUp);
	fs.add(heuristic);
	fArray3[0]=fUp;
	i2 = Integer.toString((positionLeft-1));
	j2 = Integer.toString(positionRight);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);


	fDown=counter + (Math.abs((positionLeft+1)-targetposLeft))+ (Math.abs(positionRight-targetposRight));
	heuristic = String.valueOf(fDown);
	fs.add(heuristic);
	fArray3[1]=fDown;
	i2 = Integer.toString((positionLeft+1));
	j2 = Integer.toString(positionRight);
	kk = i2+","+j2;   // This right here for the first position
	openList2.add(kk);



	fLeft=counter + (Math.abs((positionLeft)-targetposLeft))+ (Math.abs((positionRight-1)-targetposRight));
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


sizeMM=4;
//   ->  Replace counter by actual g size from starting node

fUp=counter + (Math.abs((positionLeft-1)-targetposLeft))+ (Math.abs(positionRight-targetposRight));

heuristic = String.valueOf(fUp);
fs.add(heuristic);

fArray[0]=fUp;
i2 = Integer.toString((positionLeft-1));
j2 = Integer.toString(positionRight);
kk = i2+","+j2;   // This right here for the first position
openList2.add(kk);


fDown=counter + (Math.abs((positionLeft+1)-targetposLeft))+ (Math.abs(positionRight-targetposRight));

heuristic = String.valueOf(fDown);
fs.add(heuristic);

fArray[1]=fDown;
i2 = Integer.toString((positionLeft+1));
j2 = Integer.toString(positionRight);
kk = i2+","+j2;   // This right here for the first position
openList2.add(kk);


fLeft=counter + (Math.abs((positionLeft)-targetposLeft))+ (Math.abs((positionRight-1)-targetposRight));

heuristic = String.valueOf(fLeft);
fs.add(heuristic);

fArray[2]=fLeft;
i2 = Integer.toString((positionLeft));
j2 = Integer.toString(positionRight-1);
kk = i2+","+j2;   // This right here for the first position
openList2.add(kk);



fRight=g + (Math.abs((positionLeft)-targetposLeft))+ (Math.abs((positionRight+1)-targetposRight));

heuristic = String.valueOf(fRight);
fs.add(heuristic);

fArray[3]=fRight;
i2 = Integer.toString((positionLeft));
j2 = Integer.toString(positionRight+1);
kk = i2+","+j2;   // This right here for the first position
openList2.add(kk);


pos = moveMaker(); //POS is the smallest heuristic value is all four sides are open
}


if(positionLeft==targetposLeft && positionRight==targetposRight)
{
	route[positionLeft][positionRight] = 73;
	check=false;
	//break;
}

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


	g=route[positionLeft+1][positionRight]+g;//This will add the 1point of the successor for the next loop

	route[positionLeft][positionRight] = route[positionLeft][positionRight] + route[positionLeft+1][positionRight];



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


	g = route[positionLeft][positionRight-1] + g;

	temp= route[positionLeft][positionRight];

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

	g=route[positionLeft][positionRight+1]+g;//This will add the 1point of the successor for the next loop


	temp=route[positionLeft][positionRight]; //temp is the value of the previous slot so we know it exists


	route[positionLeft][positionRight] = route[positionLeft][positionRight] + route[positionLeft][positionRight+1]; //This helps move to the next slot

	route[positionLeft][positionRight-1] = temp;

	//route[positionLeft][positionRight] = route[positionLeft][positionRight+1];

	oldpositionLeft = positionLeft;
	oldpositionRight = positionRight;

	positionLeft = positionLeft;
	positionRight = positionRight+1;

}


route[positionLeft][positionRight] = fSmall;//73;


//////////////////////
a[oldpositionLeft][oldpositionRight]="_";

a[positionLeft][positionRight]="X";  /// This is to show movement on the other string map

if(route[positionLeft-1][positionRight]==69)a[positionLeft-1][positionRight]="B"; //up
if(route[positionLeft+1][positionRight]==69)a[positionLeft+1][positionRight]="B";//down
if(route[positionLeft][positionRight-1]==69)a[positionLeft][positionRight-1]="B";//left
if(route[positionLeft][positionRight+1]==69)a[positionLeft][positionRight+1]="B";//right

counter++;
i++;
j++;
System.out.println("\n");
//gridMap();

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

/*
public int closed()
{
*/
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
/*
	System.out.println("Open : " + openList2);
	System.out.println("Closed : " + closedList);
	return 1;
}
*/

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

   for (int i = 0; i < 5; i++)
    {
        for (int j = 0; j < 5; j++)
        {
			if(randomNumbLeftTarget==list1[ii] && randomNumbRightTarget==list2[jj]);

			else if(randomNumbLeftPlayer==list1[ii] && randomNumbRightPlayer==list2[jj]);

           else {
			  // a[ list1[ii] ][ list2[jj] ] = "B";
			    route[ list1[ii] ][ list2[jj] ] = 69;
			   }
            jj++;
        }
        ii++;
    }


   for (int i = 4; i < 5; i++)
    {
        for (int j = 0; j < 5; j++)
        {
			if(randomNumbLeftTarget==list1[ii] && randomNumbRightTarget==list2[jj]);

			else if(randomNumbLeftPlayer==list1[ii] && randomNumbRightPlayer==list2[jj]);

           else {
			  // a[i][j] = "B";
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
			 //  a[i][j] = "B";
			    route[i][j] = 69;
			   }
            jj++;
        }
        ii++;
    }





for(int i=0;i<size;i++)
{

		route[i][j]	= 69 ;
		//a[i][j]="B";
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
		a[i][j]="B";
		}

	}

}
/*
route[8][8] = 69;
route[8][9] = 69;
route[8][10] = 69;
route[8][11] = 69;
route[8][12] = 69;
/*
a[8][8] = "B";
a[8][9] = "B";
a[8][10] = "B";
a[8][11] = "B";
a[8][12] = "B";
*/
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
/*
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
*/
//gridMap();

    System.out.println();

    return 1;
}

/*
public int gridBlockAdd()
{
  int ii=0;
    int jj=0;
blockMaker();

   for (int i = 0; i < 5; i++)
    {
        for (int j = 0; j < 5; j++)
        {
			if(randomNumbLeftTarget==list1[ii] && randomNumbRightTarget==list2[jj]);

			else if(randomNumbLeftPlayer==list1[ii] && randomNumbRightPlayer==list2[jj]);

           else {
			  // a[ list1[ii] ][ list2[jj] ] = "_";
			    route[ list1[ii] ][ list2[jj] ] = 1;
			   }
            jj++;
        }
        ii++;
    }


   for (int i = 4; i < 5; i++)
    {
        for (int j = 0; j < 5; j++)
        {
			if(randomNumbLeftTarget==list1[ii] && randomNumbRightTarget==list2[jj]);

			else if(randomNumbLeftPlayer==list1[ii] && randomNumbRightPlayer==list2[jj]);

           else {
			  // a[i][j] = "_";
			    route[i][j] = 1;
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
			 //  a[i][j] = "_";
			    route[i][j] = 1;
			   }
            jj++;
        }
        ii++;
    }





for(int i=0;i<size;i++)
{

		route[i][j]	= 1 ;
		//a[i][j]= "_";
	for(int j=0;j<size;j++)
	{

		if(i==0)
		{
		route[i][j]	= 1 ;
		a[i][j]= "_";
		}

		if(i==size-1)
		{
		route[i][j]	= 1 ;
		a[i][j]= "_";
		}

	if(j==size-1)
	{		route[i][j]	= 1 ;
		a[i][j]="_";
		}

	}

}
/*
route[8][8] = 1;
route[8][9] = 1;
route[8][10] = 1;
route[8][11] = 1;
route[8][12] = 1;
/*
a[8][8] = "_";
a[8][9] = "_";
a[8][10] ="_";
a[8][11] ="_";
a[8][12] ="_";
*/
/*
a[8][6] = "_";a[8][7] ="_" ;
a[7][6] = "_";a[7][7] ="_" ;
a[6][6] = "_";a[6][7] ="_" ;
a[5][6] = "_";a[5][7] ="_" ;
a[4][6] = "_";a[4][7] ="_" ;
a[3][6] = "_";a[3][7] ="_" ;


route[8][6] = 1;route[8][7] = 1;
route[7][6] = 1;route[7][7] = 1;
route[6][6] = 1;route[6][7] = 1;
route[5][6] = 1;route[5][7] = 1;
route[4][6] = 1;route[4][7] = 1;
route[3][6] = 1;route[3][7] = 1;
*/
/*
for(int i=0;i<size;i++)
{
	for(int j=0;j<size;j++)
	{

		if(j==4 && i<11)
		{
		route[i][j]	= 1 ;
		a[i][j]="_";
		}

		if(j==10 && i>4)
		{
		route[i][j]	= 1 ;
		a[i][j]="_";
		}
	}
}
*/
//gridMap();
/*
    System.out.println();

    return 1;
}
*/

}
