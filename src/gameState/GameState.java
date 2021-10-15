package gameState;
//import Math;
//Author: Emma Bradford
//this class serves as the game board. this is an odject. this holds id, parentid, board array, g(n), f(n), h(n) values
//priority and visited. this also has the fuctions to move up, down, left, and right. there are also fuctions to pring 
//the game state,to find where the empty space is, and to swap two values in the board array
public class GameState {

	//these are all the private variables that are needed to create the game state
    public int stateID;
	int parentStateID;
	int[] board = new int[18];
	int g;
	int h;
	int f;
	boolean visited;//this is not specifically stated in the word doc, but it is used in BFS
	int priority;//this is the value that the proirity queue uses to rank the game states
	
	//this is the consructor of the game state. it sets most of the information to zero. this will be filled in later.
	public GameState() 
	{
		stateID = 0;
		parentStateID = 0;
		g= 0;
		f=0;
		h=0;
	}
	//getters
	//this fucntion returns the ID of the game state
	public int getStateID() 
	{
		return stateID;
	}
	//this fucntion returns the parent ID of the game state
	public int getParentStateID() 
	{
		return parentStateID;
	}
	//this fucntion returns the board array of the game state
	public int[] getBoard() 
	{
		return board;
	}
	//this fucntion returns the g(n) of the game state
	public int getG() 
	{
		return g;
	}
	//this fucntion returns the h(n) of the game state
	public int getH() 
	{
		return h;
	}
	//this fucntion returns the f(n) of the game state
	public int getF() 
	{
		return f;
	}
	//this fucntion returns the visited of the game state
	public boolean getVisited() 
	{
		return visited;
	}
	//this fucntion returns the prioirty of the game state
	public int getPriority() 
	{
		return priority;
	}
	//setters
	//this fucntion set the ID of the game state to the integer inputted
	public void setStateID(int i) 
	{
		stateID = i;
	}
	//this fucntion set the parent ID of the game state to the integer inputted
	public void setParentStateID(int i) 
	{
		parentStateID = i;
	}
	//this fucntion set the board array of the game state to the integer array inputted
	public void setBoard(int[] i) 
	{
		board = i;
	}
	//this fucntion set the g(n) of the game state. the g(n) is calucalted based on where the empty space is.
	public void setG() 
	{
		int x = this.findZero();//this sets x to the empty space on the board
		if (x>=0&&x<=6) //this check to see if it is in positions 0 through 6
		{
			g=1;//g(n) is set to one
		}
		else if (x>=7&&x<=16) //this check to see if it is in positions 7 through 16
		{
			g=3;//g(n) is set to three
		}
		else if(x==17) //this check to see if it is in position 17
		{
			g=15;//g(n) is set to fifiteen
		}
	}
	//this fucntion set the h(n) of the game state. 
	public void setH(int i) 
	{
		h=i;
	}
	//this fucntion set the f(n) of the game state. the f(n) is calucalted based on h(n) and g(n).
	public void setF() 
	{
		f = g+h;//sets f(n) to the sum of g(n) and h(n)
	}
	//this fucntion set the priority of the game state to the integer inputted
	public void setPriority(int i) 
	{
		priority = i;
	}
	//this fucntion set the visited of the game state to the integer inputted
	public void setVisited(boolean i) 
	{
		visited = i;
	}
	//healper functions
	//this is the fuction to change the board for the move where the empty space moves up
	public int[] moveUp() 
	{
		int[] ans=new int[18];//creates a new empty board
		int x = this.findZero();//finds where the empty space is
		if (x==1||x==0||x==2) //if the location is at the top
		{
			int[] y = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			return y;//return an empty list if the move is illigal
		}
		else //if it is a legal move
		{
			for(int j=0; j<18; j++) //loop through the whole board and sets the new board to the old board
			{
				ans[j] = board[j];
			}
			swap(x, x-3,ans);//swaps the necesary values to reflect the move up
			return ans;
		}
		
	}
	//this is the fuction to change the board for the move where the empty space moves up
	public int[] moveDown() 
	{
		int[] ans=new int[18];//creates a new empty board
		int x = this.findZero();//finds where the empty space is
		if (x==17||x==16||x==15) //if the location is at the bottom
		{
			int[] y = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			return y;//return an empty list if the move is illigal
		}
		else //if it is a legal move
		{
			for(int j=0; j<18; j++) //loop through the whole board and sets the new board to the old board
			{
				ans[j] = board[j];
			}
			swap(x, x+3,ans);//swaps the necesary values to reflect the move down
			return ans;
		}
		
	}
	//this is the fuction to change the board for the move where the empty space moves up
	public int[] moveRight() 
	{
		int[] ans=new int[18];//creates a new empty boa
		int x = this.findZero();//finds where the empty space is
		if (x==8||x==5||x==2||x==11||x==14||x==17) //if the location is at a side
		{
			int[] y = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			return y;//return an empty list if the move is illigal
		}
		else //if it is a legal move
		{
			for(int j=0; j<18; j++) //loop through the whole board and sets the new board to the old board
			{
				ans[j] = board[j];
			}
			swap(x, x+1,ans);//swaps the necesary values to reflect the move right
			return ans;
		}
		
	}
	//this is the fuction to change the board for the move where the empty space moves up
	public int[] moveLeft() 
	{
		int[] ans=new int[18];//creates a new empty boa
		int x = this.findZero();//finds where the empty space is
		if (x==0||x==3||x==6||x==9||x==12||x==15) //if the location is at a side
		{
			int[] y = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			return y;//return an empty list if the move is illigal
		}
		else //if it is a legal move
		{
			for(int j=0; j<18; j++) //loop through the whole board and sets the new board to the old board
			{
				ans[j] = board[j];
			}
			swap(x, x-1,ans);//swaps the necesary values to reflect the move left
			return ans;
		}
		
	}
	//this is a helper funtion that swaps two indexes in an array
	public void swap(int i, int j, int[] arr) 
	{
		  int t = arr[i];//save one value
		  arr[i] = arr[j];//set the old value to the new value
		  arr[j] = t;////set new value to old value
	}
	//this finds the empty square in the array
	public int findZero() 
	{
		int ans=0;//ans is the index of the empty square
		for(int x=0; x<18; x++) //loop throught the whole array
		{
			if (board[x]==0) //if the value at x in board is empty
			{
				ans=x;//ans set to that index
			}
		}
		return ans;
	}
	//this method prints out the info about the board.
	public void printGameState() 
	{
		String b = "[";//b is the out put of the board
		for(int x=0; x<18; x++) //loop throught the board array 
		{
			b = b + " " + String.valueOf(board[x]);//add the array values to the b
		}
		b = b + " ]";
		//this is the out put of the board in plain language
		System.out.println("game state " + stateID + " from " + parentStateID + " is " + b + " where g: " + g + " where h: " + h + " where f: " + f);
	}
}
