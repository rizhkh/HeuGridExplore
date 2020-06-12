import java.util.*;
import java.io.*;
import java.lang.*;

public class grid implements gridInterface {

    Random rg = new Random();
    int randomNumbLeftTarget, randomNumbRightTarget, randomNumbLeftPlayer, randomNumbRightPlayer, playerlastmoveLeft, playerlastmoveRight;
	boolean check1=true;
	int g=0, f=0, h=0;


    public String[][] a;
    public int[][] route;
    public int[] list1, list2;
	public int started, positionLeft, positionRight, counter;

	public boolean check;
	int size=0;

	public int targetposLeft, targetposRight;

	int fUp,fDown,fLeft,fRight;

	int sizeMM;
	int[] fArray = new int[4];
	int[] fArray3 = new int[3];
	static int[]fArray33=new int[3];
	int[] fArray2 = new int[2];

	int fSmall, posToMov=0, totalg=0, i=positionLeft, j=positionRight, firstleftpos=positionLeft, firstrightpos=positionRight;

    LinkedList<String>	fs = new LinkedList<>(); //Fsmall array
    LinkedList<String> openList = new LinkedList<>();
    LinkedList<String> openList2 = new LinkedList<>();
    LinkedList<String> closedList = new LinkedList<String>();


public grid(){

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
    randomNumbLeftPlayer = 3;
    randomNumbRightPlayer = 3;
    randomNumbLeftTarget = 7;
    randomNumbRightTarget = 7;
	ArrayList fs = new ArrayList();
    LinkedList<String> openList = new LinkedList<>();
    LinkedList<String> openList2 = new LinkedList<>();
    LinkedList<String> closedList = new LinkedList<String>();
}

/*Sets up Position player and Target */
public int gridInitializer(){
    if(started==0)
    for (int i = 0; i < size; i++){
        for (int j = 0; j < size; j++){
            a[i][j] = "_";
            if (i == randomNumbLeftTarget && j == randomNumbRightTarget){
                a[i][j] = "T";
				targetposLeft=i;
                targetposRight=j;
            }
            else if (i == randomNumbLeftPlayer && j == randomNumbRightPlayer){
                a[i][j] = "X";
                String ii = Integer.toString(i);
                String jj = Integer.toString(j);
                String kk = ii+","+jj;   // This right here for the first position
            	openList.add(kk);
            }
        }
    }
	gridBlockAdd();
    System.out.println();
    return 1;
}

public int direction(){

    if(started==0){
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				route[i][j] = 01;
				if (i == randomNumbLeftTarget && j == randomNumbRightTarget){
					route[i][j] = 9; //Make sure to mak this a 0
					targetposLeft=i;
					targetposRight=j;
				}

				else if (i == randomNumbLeftPlayer && j == randomNumbRightPlayer){
					route[i][j] = 0;
					positionLeft=i;
					positionRight=j;
				}
			}
		}
	}
	return 1;
}

public int blockChecker(int positionLeft,int positionRight){
	int q=0;
	if( (route[positionLeft-1][positionRight])==69 || (route[positionLeft+1][positionRight])==69 || (route[positionLeft][positionRight-1])==69 || (route[positionLeft][positionRight+1])==69){
			if( (route[positionLeft-1][positionRight])==69 ) q=1;
			else if( (route[positionLeft+1][positionRight])==69) q=2; //if down is blocked
			else if( (route[positionLeft][positionRight-1])==69) q=3; //if left is blocked
			else if( (route[positionLeft][positionRight+1])==69) q=4; //if right is blocked
	}
	else q=77;

	return q;
}


public int moveMaker(){
	int fSmall;	int post=0;	fSmall = fArray[0];

	if(route[positionLeft-1][positionRight]!=69){
		String a,b,c;
		a = Integer.toString((positionLeft-1));
		b = Integer.toString(positionRight);
		c = a+","+b;

		int v=closedList.indexOf(c);
		if(v!=-1)fArray[0]=1000;
	}

	if(route[positionLeft+1][positionRight]!=69){
		String a,b,c;
		a = Integer.toString((positionLeft+1));
		b = Integer.toString(positionRight);
		c = a+","+b;
		int v=closedList.indexOf(c);
		if(v!=-1) fArray[1]=1000;
	}

	if(route[positionLeft][positionRight+1]!=69){
		String a,b,c;
		a = Integer.toString((positionLeft));
		b = Integer.toString(positionRight+1);
		c = a+","+b;
		int v=closedList.indexOf(c);
		if(v!=-1)fArray[3]=1000;
	}

	if(route[positionLeft][positionRight-1]!=69){
		String a,b,c;
		a = Integer.toString((positionLeft));
		b = Integer.toString(positionRight-1);
		c = a+","+b;
		int v=closedList.indexOf(c);
		if(v!=-1) fArray[2]=1000;
	}

	int min = fArray[0];
	int index=0;
	for(int i = 0; i < fArray.length; i++){
		if(min > fArray[i]){
		  min = fArray[i];
		  index=i;
		}
	}

	post=index;
	int counter=0;
	return post;
}


public int moveMakerThree(){
	int fSmall;	int post=0;	fSmall = fArray3[0];
	String direction = "";

	for(int ii=0;ii<3;ii++){

		if(fArray3[0]==fArray3[1] && fArray3[1]==fArray3[2]){
				/* checks left slot */
				if(route[positionLeft][positionRight-1]!=69){
					String a,b,c;
					a = Integer.toString((positionLeft));
					b = Integer.toString(positionRight-1);
					c = a+","+b;   // This right here for the first position

					int v=	openList2.indexOf(c);
					if(v!=-1)fArray3[2]=1000;
				}
				/* checks down slot */
				else if(route[positionLeft+1][positionRight]!=69 ){
					String a,b,c;
					a = Integer.toString((positionLeft+1));
					b = Integer.toString(positionRight);
					c = a+","+b;   // This right here for the first position

					int v =	openList2.indexOf(c);
					if(v!=-1) fArray3[1]=1000;

					if(v==-1)
					{
					}
				}
				/* checks the up slot */
				else if(route[positionLeft-1][positionRight]!=69 ){
					String a,b,c;
					a = Integer.toString((positionLeft-1));
					b = Integer.toString(positionRight);
					c = a+","+b;   // This right here for the first position

					int v =	openList2.indexOf(c);
					if(v!=-1){}
					fArray3[0]=1000;
				}
		}

		if(fArray3[0]!=fArray3[1] && fArray3[1]!=fArray3[2]){
						/* if right side is blocked then check up,down,left */
						if(route[positionLeft+1][positionRight]==69){
								/* checks left slot */
								if(route[positionLeft-1][positionRight]!=69){
									String a,b,c;
									a = Integer.toString((positionLeft-1));
									b = Integer.toString(positionRight);
									c = a+","+b;
									int v =	openList2.indexOf(c);
									if(v!=-1)fArray3[0]=1000;
									else{}
								}

								if(route[positionLeft][positionRight-1]!=69){
									String a,b,c;
									a = Integer.toString((positionLeft));
									b = Integer.toString(positionRight-1);
									c = a+","+b;
									int v =	closedList.indexOf(c);
									if(v!=-1)fArray3[1]=1000;
									else{}
								}

								if(route[positionLeft][positionRight+1]!=69){
									String a,b,c;
									a = Integer.toString((positionLeft));
									b = Integer.toString(positionRight+1);
									c = a+","+b;
									int v =	closedList.indexOf(c);
									if(v!=-1)fArray3[2]=1000;
									else {}
								}
						}
		}

		if(fArray3[0]==fArray3[1] && fArray3[1]!=fArray3[2]){
						if(route[positionLeft+1][positionRight]==69){
								if(route[positionLeft-1][positionRight]!=69){
									String a,b,c;
									a = Integer.toString((positionLeft-1));
									b = Integer.toString(positionRight);
									c = a+","+b;
									int v =	openList2.indexOf(c);
									if(v!=-1){
										fArray3[0]=1000;
									}
									else {}
								}

								if(route[positionLeft][positionRight-1]!=69){
									String a,b,c;
									a = Integer.toString((positionLeft));
									b = Integer.toString(positionRight-1);
									c = a+","+b;
									int v =	closedList.indexOf(c);
									if(v!=-1)fArray3[1]=1000;
									else {}
								}

								if(route[positionLeft][positionRight+1]!=69){
									String a,b,c;
									a = Integer.toString((positionLeft));
									b = Integer.toString(positionRight+1);
									c = a+","+b;
									int v =	closedList.indexOf(c);
									if(v!=-1)fArray3[2]=1000;
									else {}
								}
						}

						if(route[positionLeft-1][positionRight]==69){
								if(route[positionLeft+1][positionRight]!=69){
									String a,b,c;a = Integer.toString((positionLeft+1));b = Integer.toString(positionRight);
									c = a+","+b;
									int v =	closedList.indexOf(c);
									if(v!=-1)fArray3[0]=1000;
									else {}
								}

								if(route[positionLeft][positionRight-1]!=69){
									String a,b,c;a = Integer.toString((positionLeft));b = Integer.toString(positionRight-1);
									c = a+","+b;
									int v =	closedList.indexOf(c);
									if(v!=-1)fArray3[1]=1000;
									else {}
								}

								if(route[positionLeft][positionRight+1]!=69){
									String a,b,c;a = Integer.toString((positionLeft));b = Integer.toString(positionRight+1);
									c = a+","+b;
									int v =	closedList.indexOf(c);
									if(v!=-1)fArray3[2]=1000;
									else {}
								}
						}
		}

		if(fArray3[0]!=fArray3[1] && fArray3[1]==fArray3[2])
		{
					if(route[positionLeft-1][positionRight]!=69 ){
						String a,b,c;
						a = Integer.toString((positionLeft-1));
						b = Integer.toString(positionRight);
						c = a+","+b;   // This right here for the first position
						int v =	openList2.indexOf(c);
						if(v!=-1)fArray3[0]=1000;
					}

					else if(route[positionLeft+1][positionRight]!=69 ){
						String a,b,c;
						a = Integer.toString((positionLeft+1));
						b = Integer.toString(positionRight);
						c = a+","+b;
						int v =	openList2.indexOf(c);
						if(v!=-1)fArray3[1]=1000;
					}

					else if(route[positionLeft][positionRight-1]!=69){
						String a,b,c;
						a = Integer.toString((positionLeft));
						b = Integer.toString(positionRight-1);
						c = a+","+b;   // This right here for the first position
						int v =	openList2.indexOf(c);
						if(v!=-1) fArray3[2]=1000;
					}

		}

		if(fArray3[0]==fArray3[2] && fArray3[1]!=fArray3[2]){

			if(route[positionLeft][positionRight+1]==69){
					if(route[positionLeft-1][positionRight]!=69){
						String a,b,c;a = Integer.toString((positionLeft-1));b = Integer.toString(positionRight);
						c = a+","+b;
						int v =	closedList.indexOf(c);
						if(v!=-1)fArray3[0]=1000;
						else	{}
					}

					if(route[positionLeft+1][positionRight]!=69){
						String a,b,c;a = Integer.toString((positionLeft+1));b = Integer.toString(positionRight);
						c = a+","+b;
						int v=	closedList.indexOf(c);
						if(v!=-1)fArray3[1]=1000;
						else	{}
					}

					if(route[positionLeft][positionRight-1]!=69){
						String a,b,c;a = Integer.toString((positionLeft));b = Integer.toString(positionRight-1);
						c = a+","+b;
						int v =	openList2.indexOf(c);
						if(v!=-1)fArray3[2]=1000;
						else	{}
					}
			}

			if(route[positionLeft][positionRight-1]!=69){
				String a,b,c;
				a = Integer.toString((positionLeft));
				b = Integer.toString(positionRight-1);
				c = a+","+b;
				int v =	closedList.indexOf(c);
				if(v!=-1)fArray3[1]=1000;
				else{	}
			}

			if(route[positionLeft][positionRight+1]!=69)
			{
				String a,b,c;
				a = Integer.toString((positionLeft));
				b = Integer.toString(positionRight+1);
				c = a+","+b;
				int v =	closedList.indexOf(c);
				if(v!=-1)fArray3[2]=1000;
				else{}
			}

			else if(route[positionLeft+1][positionRight]!=69
			{
				String a,b,c;
				a = Integer.toString((positionLeft+1));
				b = Integer.toString(positionRight);
				c = a+","+b;   // This right here for the first position
				int v=	openList2.indexOf(c);
				if(v!=-1) fArray3[0]=1000;

				else{}
			}

			else if(route[positionLeft-1][positionRight]!=69 ) //checks above slot
			{
				String a,b,c;
				a = Integer.toString((positionLeft-1));
				b = Integer.toString(positionRight);
				c = a+","+b;   // This right here for the first position
				int v=	openList2.indexOf(c);
				if(v!=-1){}
			}
		}
	}

	int min = fArray3[0];
	int index=0;
    for(int i = 0; i < fArray3.length; i++){
		if(min > fArray3[i]){
			min = fArray3[i];
	        index=i;
	    }
	}
	post=index;
	int counter=0;

	if(route[positionLeft-1][positionRight]==69){
		if(post==2)post=3;
		if(post==1)post=2;
		if(post==0)post=1;
	}

	if(route[positionLeft+1][positionRight]==69){
		if(post==2){post=3;}
		else if(post==1){post=2;}
	}

	if(route[positionLeft][positionRight-1]==69){
		if(post==2)post=3;
	}
	return post;
}

public int moveMakerTwo(){
		int fSmall;
		int post = 0;
		fSmall = fArray2[0];

		int left = 0, right = 0, up = 0, down = 0;

		if (fArray2[0] == fArray2[1]){

			if ((route[positionLeft + 1][positionRight + 1]) == 69){

				if (route[positionLeft - 1][positionRight] != 69){
					String a, b, c;
					a = Integer.toString((positionLeft - 1));
					b = Integer.toString(positionRight);
					c = a + "," + b;
					int v = closedList.indexOf(c);
					if (v != -1) fArray2[0] = 1000;
					else {}
				}

				if (route[positionLeft][positionRight - 1] != 69){
					String a, b, c;
					a = Integer.toString((positionLeft - 1));
					b = Integer.toString(positionRight);
					c = a + "," + b;
					int v = closedList.indexOf(c);
					if (v != -1)fArray2[1] = 1000;
					else {}
				}
			}

			if ((route[positionLeft + 1][positionRight - 1]) == 69){

				if (route[positionLeft - 1][positionRight] != 69){
					String a, b, c;
					a = Integer.toString((positionLeft - 1));
					b = Integer.toString(positionRight);
					c = a + "," + b;
					int v = closedList.indexOf(c);
					if (v != -1)fArray2[0] = 1000;
					else {}
				}

				if (route[positionLeft][positionRight + 1] != 69){
					String a, b, c;
					a = Integer.toString((positionLeft - 1));
					b = Integer.toString(positionRight);
					c = a + "," + b;
					int v = closedList.indexOf(c);
					if (v != -1)fArray2[1] = 1000;
					else {}
				}
			}


			if ((route[positionLeft + 1][positionLeft - 1]) == 69){
				if (route[positionLeft][positionRight - 1] != 69){
					String a, b, c;
					a = Integer.toString((positionLeft - 1));
					b = Integer.toString(positionRight);
					c = a + "," + b;
					int v = closedList.indexOf(c);
					if (v != -1)fArray2[0] = 1000;
					else {}
				}

				if (route[positionLeft][positionRight + 1] != 69){
					String a, b, c;
					a = Integer.toString((positionLeft - 1));
					b = Integer.toString(positionRight);
					c = a + "," + b;   // This right here for the first position
					//int v=	openList2.indexOf(c);
					int v = closedList.indexOf(c);
					if (v != -1)fArray2[1] = 1000;
					else {}
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

		if ((route[positionLeft + 1][positionRight + 1]) == 69){
			if (post == 1) post = 2;
		}

		if ((route[positionLeft + 1][positionRight - 1]) == 69){
			if (post == 1) post = 3;

		}

		if ((route[positionLeft + 1][positionLeft - 1]) == 69){
			if (post == 1) post = 3;
			if (post == 0) post = 2;
		}

		return post;
	}

public int checkStatus(int positionLeft,int positionRight,int q){
	if(q==1){
		String a,b,c;
		a = Integer.toString((positionLeft));
		b = Integer.toString(positionRight+1);
		c = a+","+b;   // This right here for the first position
		System.out.println("Current position(checkStatus): " + positionLeft + "," + positionRight);
		System.out.println("position we are looking forward to(checkStatus): " + positionLeft + "," + (positionRight+1));
		System.out.println("ayyyyy we out here :" + closedList.indexOf(c));
	}
	return 1;
}

public boolean gridMover(){
String i2, j2, kk, heuristic;
int oldpositionLeft = positionLeft;
int oldpositionRight = positionRight;
int pos=0;

h= (Math.abs(i-targetposLeft))+ (Math.abs(j-targetposRight));
f = g + (Math.abs(i-targetposLeft))+ (Math.abs(j-targetposRight));

if(counter>0)route[positionLeft][positionRight]=f;
int q = blockChecker(positionLeft,positionRight);

/* up side is blocked */
if(q==1){
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

	pos = moveMakerThree();
}

/* down is blocked - THIS CONDITION ONLY KNOWS THAT ALL POSITIONS ARE OPEN AND DOES NOT CHECK THE LISTS */
else if (q==2){
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

	/* left side is blocked */
	else if (q==3){
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

	/* right side is blocked */
	else if (q==4){
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

	else{
		sizeMM=4;
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

	if(positionLeft==targetposLeft && positionRight==targetposRight){
		route[positionLeft][positionRight] = 73;
		check=false;
	}

	/* moves UP */
	else if(pos==0){
		listMaker(positionLeft,positionRight,1,0);
		g=route[positionLeft-1][positionRight]+g;//This will add the 1point of the successor for the next loop
		route[positionLeft][positionRight] = route[positionLeft][positionRight] + route[positionLeft-1][positionRight];
		oldpositionLeft = positionLeft;
		oldpositionRight = positionRight;
		positionLeft = positionLeft-1;
		positionRight = positionRight;
	}

	/* moves down */
	else if(pos==1){
		listMaker(positionLeft,positionRight,-1,0);
		g=route[positionLeft+1][positionRight]+g;//This will add the 1point of the successor for the next loop
		route[positionLeft][positionRight] = route[positionLeft][positionRight] + route[positionLeft+1][positionRight];
		oldpositionLeft = positionLeft;
		oldpositionRight = positionRight;
		positionLeft = positionLeft+1;
		positionRight = positionRight;
	}

	/* moves left */
	else if(pos==2){
		listMaker(positionLeft,positionRight,0,1);
		int temp=0;
		g = route[positionLeft][positionRight-1] + g;
		temp= route[positionLeft][positionRight];
		g=route[positionLeft][positionRight-1]+g;//This will add the 1point of the successor for the next loop
		route[positionLeft][positionRight] = route[positionLeft][positionRight] + route[positionLeft][positionRight-1];
		oldpositionLeft = positionLeft;
		oldpositionRight = positionRight;
		positionLeft = positionLeft;
		positionRight = positionRight-1;
	}

	/* moves right */
	else if(pos==3){
		listMaker(positionLeft,positionRight,0,-1);
		int temp=0;
		g=route[positionLeft][positionRight+1]+g;//This will add the 1point of the successor for the next loop
		temp=route[positionLeft][positionRight]; //temp is the value of the previous slot so we know it exists
		route[positionLeft][positionRight] = route[positionLeft][positionRight] + route[positionLeft][positionRight+1]; //This helps move to the next slot
		route[positionLeft][positionRight-1] = temp;
		oldpositionLeft = positionLeft;
		oldpositionRight = positionRight;
		positionLeft = positionLeft;
		positionRight = positionRight+1;
	}

	route[positionLeft][positionRight] = fSmall;//73;
	a[positionLeft][positionRight]="X";  /// This is to show movement on the other string map
	if(route[positionLeft-1][positionRight]==69)a[positionLeft-1][positionRight]="B"; //up
	if(route[positionLeft+1][positionRight]==69)a[positionLeft+1][positionRight]="B";//down
	if(route[positionLeft][positionRight-1]==69)a[positionLeft][positionRight-1]="B";//left
	if(route[positionLeft][positionRight+1]==69)a[positionLeft][positionRight+1]="B";//right
	counter++;
	i++;
	j++;
	System.out.println("\n");

	return check;
}


public int listMaker(int positionLeft,int positionRight,int indexLeft,int indexRight){
	String i2;
	String j2;
	String kk;

	i2 = Integer.toString((positionLeft-indexLeft));
	j2 = Integer.toString(positionRight-indexRight);

	kk = i2+","+j2;   // This right here for the first position
	closedList.add(kk);
	int abc = openList2.indexOf(kk);
	openList2.remove(kk);

	//This part removes an element from openlist if its already in closed list
	for(int i=0; i<closedList.size(); i++){
		String wrd = closedList.get(i);
		openList2.remove(wrd);
		}
	return 1;
}


public int gridMap1(){
	    for (int i = 0; i < size; i++){
	        for (int j = 0; j < size; j++){
	            System.out.print(a[i][j] + "|");
	        }
	        System.out.println();
    }
    return 1;
}

public int gridMap(){
	    for (int i = 0; i < size; i++){
	        for (int j = 0; j < size; j++){
	            System.out.print(route[i][j] + "|");
	        }
	        System.out.println();
    }
    return 1;
}



public int blockMaker(){
	/* Only reason I did not put a number checker here to see if a number is repeating because if they are they are overtaken in blocks so in this case the more the repition the better for the map-you either get a map full of blocks or you get very little blocks */

	list1=new int[25];
	list2=new int[25];

	 for(int i=0;i<12;i++){
			int blockNumber = rg.nextInt(size);
			int blockNumber2 = rg.nextInt(size);
			list1[i]=blockNumber;
			list2[i]=blockNumber2;
	 }
    return 1;
}


public int gridBlockAdd(){
  	int ii=0;
    int jj=0;
	blockMaker();

   for (int i = 0; i < 5; i++){
        for (int j = 0; j < 5; j++){
			if(randomNumbLeftTarget==list1[ii] && randomNumbRightTarget==list2[jj]);
			else if(randomNumbLeftPlayer==list1[ii] && randomNumbRightPlayer==list2[jj]);
            else route[ list1[ii] ][ list2[jj] ] = 69;
            jj++;
        }
        ii++;
    }


   for (int i = 4; i < 5; i++){
        for (int j = 0; j < 5; j++){
			if(randomNumbLeftTarget==list1[ii] && randomNumbRightTarget==list2[jj]);
			else if(randomNumbLeftPlayer==list1[ii] && randomNumbRightPlayer==list2[jj]);
            else route[i][j] = 69;
            jj++;
        }
        ii++;
    }


   for (int i = 5; i <6; i++){
        for (int j = 6; j < 9; j++){
			if(randomNumbLeftTarget==list1[ii] && randomNumbRightTarget==list2[jj]);
			else if(randomNumbLeftPlayer==list1[ii] && randomNumbRightPlayer==list2[jj]);
			else route[i][j] = 69;
			jj++;
        }
        ii++;
    }

for(int i=0;i<size;i++){
	route[i][j]	= 69 ;
	for(int j=0;j<size;j++){
		if(i==0){
		route[i][j]	= 69 ;
		a[i][j]="B";
		}
		if(i==size-1){
		route[i][j]	= 69 ;
		a[i][j]="B";
		}
		if(j==size-1){
			route[i][j]	= 69 ;
			a[i][j]="B";
		}
	}

}
    System.out.println();
    return 1;
}

}
