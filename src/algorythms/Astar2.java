package algorythms;
//Author: Emma Bradford
import java.util.LinkedList;
import java.util.PriorityQueue;

import gameState.GameState;
import gameState.GameStateComparator;
//this is the code for the Astar aglirthm with heuristic 2 algorithm
//Author: Emma Bradford
public class Astar2 {
	//these are all the variables for the Astar class
	int[] goal = new int[18];
	int[] start = new int[18];
	GameState goalS = new GameState();
	GameState startS = new GameState();
	int count = 3; //this is a variable for the ids for the board
	LinkedList<GameState> closed = new LinkedList<GameState>();//this is the closed list
	PriorityQueue<GameState> open = new PriorityQueue<GameState>(1000, new GameStateComparator());//this is the open list
	//the counts for the length of closed and open
	int countOpen = 0;
	int countClosed = 0;
	int[] check = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};//this is a check i use later in the code for validating moves
	
	//this is the constructor for the Astar algorythm
	//it gets passed in an array for the start state and the goal state
	public Astar2(int[] s, int[] g) 
	{
		//sets up the gameState for the foal node with the nessacery set functions
		goal = g;
		start = s;
		goalS.setBoard(goal);
		goalS.setStateID(2);
		goalS.setG();
		goalS.setH(goalS.findZero());
		goalS.setF();
		//goalS.printGameState();
		
		//sets up the gameState for the foal node with the nessacery set functions
		startS.setBoard(start);
		startS.setStateID(1);
		startS.setG();
		startS.setH(goalS.findZero());
		startS.setF();
		startS.setParentStateID(-1);
		startS.setPriority(0);
		//startS.printGameState();
	}
	//this check to see if the current gamestate had the same board as the goal state
	public boolean isGoalState(GameState c, GameState g) 
	{
		for(int x = 0; x<18; x++) //loop through the boards
		{
			if(c.getBoard()[x] != g.getBoard()[x]) //if one value is not the same in the afrra return false
			{
			//	System.out.println("curr: "+c.getBoard()[x]);
			//	System.out.println("goal: "+c.getBoard()[x]);
				return false;
			}
		}
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return true;//if they all are the same return true
	}
	
	//this check to see if two gamestates have the same board
	public boolean same(GameState c, GameState g) 
	{
		for(int x = 0; x<18; x++) //loop through the boards
		{
			if(c.getBoard()[x] != g.getBoard()[x]) //if one value is not the same in the afrra return false
			{
				return false;
			}
		}
		return true;//if they all are the same return true
	}
	//this check to see if two gamestates have the same board
	public boolean same1(int[] c, int[] g) 
	{
		for(int x = 0; x<18; x++) //loop through the boards
		{
			if(c[x] != g[x]) //if one value is not the same in the afrra return false
			{
				return false;
			}
		}
		return true;//if they all are the same return true
	}
	//this fucntion set the h(n) of the game state. the h(n) is calucalted based on the documentation in the homework
	//the inputed value is the board array of the goal node
	//another inouted value is a gamestate representing where the gamestate is
	public int h(int [] i, GameState a) 
	{
		int sum = 0;//the sum of the moves it would take for the current games stae to be the goal state
		int temp;//this is the index of the varibale for the goal state
		for(int r  = 0; r<18; r++) //loop thouth the arrays
		{
			if(a.getBoard()[r] != i[r]) //if the places in the current gamestate is not the same as the place in the goal state then
			{
				temp= r;
				for(int z  = 0; z<18; z++) 
				{
					if(a.getBoard()[temp]==i[z]) {
						sum = sum + Math.abs(temp-z);//add to the count
					}
				}
			}
		}
		return sum;//return the count
	}
	//this generates all the gameboards for the legal moves
	public LinkedList<GameState> successor(GameState a)
	{
		//temperary gamestates that will be used for the legal moves
		GameState w = new GameState();
		GameState x = new GameState();
		GameState y = new GameState();
		GameState z = new GameState();
		//System.out.println("THIS IS THE CURR NODE");
		//a.printGameState();
		//System.out.println(a);
		//create a linked list to return all of the legal moves as game states
		LinkedList<GameState> ans = new LinkedList<GameState>();
		//these are arrays that will be used to populate gamestates above
		int[] d = a.moveDown();
		int[] r = a.moveRight();
		int[] u = a.moveUp();
		int[] l = a.moveLeft();
		//System.out.println("hello");
		//this is for the down move
		//System.out.println("DOWN");
		if(!same1(d,check)) //make sure that moving down is a legall move
		{
			//System.out.println("hello");
			//if it is a legal move poplate the fieds with 
			w.setBoard(d);
			w.setStateID(count);//giving unique id
			w.setG();
			w.setH(this.h(goalS.getBoard(), w));//passing in the empty space index of the goal node
			w.setF();
			w.setVisited(false);
			w.setParentStateID(a.getStateID());//set the parent id to that of the current game state
			//System.out.println(a.getStateID());
			//w.printGameState();
			w.setPriority(w.getF());//the priority is the f(n)
			 
			ans.add(w);//add new gamestate to the linked list of legal moves
		}
		count++;//increment count so all the games states have unique ids
		//this is for the up move
		//System.out.println("UP");
		if(!same1(u,check)) //make sure that moving up is a legall move
		{
			x.setBoard(u);
			x.setStateID(count);//giving unique id
			x.setG();
			x.setH(this.h(goalS.getBoard(), x));//passing in the empty space index of the goal node
			x.setF();
			x.setParentStateID(a.getStateID());//set the parent id to that of the current game state
			x.setVisited(false);
			//x.printGameState();
			x.setPriority(x.getF());//the priority is the f(n)
			ans.add(x);//add new gamestate to the linked list of legal moves
		}
		count++;//increment count so all the games states have unique ids
		//	System.out.println("RIGHT");
		//this is for the right move
		if(!same1(r,check)) //make sure that moving right is a legall move
		{
			//System.out.println("hello");
			y.setBoard(r);
			y.setStateID(count);//giving unique id
			y.setG();
			y.setH(this.h(goalS.getBoard(), y));//passing in the empty space index of the goal node
			y.setF();
			y.setParentStateID(a.stateID);//set the parent id to that of the current game state
			y.setVisited(false);
			//y.printGameState();
			y.setPriority(y.getF());//the priority is the f(n)
			ans.add(y);//add new gamestate to the linked list of legal moves
		}
		count++;//increment count so all the games states have unique ids
		//this is for the left move
		//System.out.println("LEFT");
		if(!same1(l,check)) //make sure that moving left is a legall move
		{
			z.setBoard(l);
			z.setStateID(count);//giving unique id
			z.setG();
			z.setH(this.h(goalS.getBoard(), z));//passing in the empty space index of the goal node
			z.setF();
			z.setVisited(false);
			z.setParentStateID(a.getStateID());//set the parent id to that of the current game state
			//z.printGameState();
			z.setPriority(z.getF());//the priority is the f(n)r
			//count++;
			ans.add(z);//add new gamestate to the linked list of legal moves
		}
		count++;//increment count so all the games states have unique ids
		return ans;
	}
	//this is a healer function to help with debugging where you can see what gamestate are in the open queue
	public void printOpen(PriorityQueue<GameState>z) 
	{
		System.out.println("this is the stuff in open");
		for(GameState i: z) //go through the open queue
		{
			i.printGameState();//print out the gamestate
		}
	//	System.out.println("no more stuff");
	}
	public void runAStar() 
	{
		//System.out.println("this is the stuff in open");
		LinkedList<GameState> que = new LinkedList<GameState>();//new liked list to hold all the legal moves
		open.add(startS);//add the start node to the open queue so the while loop can start
		countOpen++;
		int c = 0;
		//while the open que is not empty and it has not reached its max then run the algorithm
		//this also prevents the code from running forever if the goal state is not reachable
		while(countOpen<1000 && !open.isEmpty())//&& c<6) 
		{
			GameState curr = open.peek();//set the curr node to what we think is the games tate with the loewst F(n)
			//GameState temp = open.remove();
			for(GameState v : open)//this goes through all the gamestates in the open lists 
			{
				if(v.getPriority()<curr.getPriority()) //this checks to see if there are any other gamestates in the open list with a lower f(n)
				{
					curr = v;//if there is set curr to that gamestate
			//		System.out.println("this is the small curr node:");
			//		curr.printGameState();
				}
			}
			open.remove(curr);////set the current node to the beingin of open and remove curr node from open
			//System.out.println("this is the curr node:");
			//curr.printGameState();
			countOpen--;
			closed.add(curr);//add curr node to the closed list
			countClosed++;
			if(isGoalState(curr, goalS)) //check to see if the current games state is the goal
			{
				printAns(closed);//print the order of how it got to the goal state
				break;//get out of the loop
			}
			else //if curr is not the goal state
			{
				que = successor(curr);//find all of the legal moves from the current game state
				//System.out.println("This is the curr");
				//printClosed(que);
				//printOpen(open);
				//System.out.println("This is the closed");
				//printClosed(closed);
				//open.
				boolean bk = false;//this is a booklean to determine if we need to break out of the loop
				for(GameState i: que) //go through all of the legal moves
				{
					if(isGoalState(i, goalS)) //if any of the legal moves is the goal state then
					{
						closed.add(i);//add the legal move to the closed
						countClosed++;
						printAns(closed);//print the order of how it got to the goal state
						bk = true;//set the need to break to true
					}
				}
				if (bk) {break;}//break out of the loop
				//this is where we check to see if any of the legal moves are already in closed
				for(GameState i: closed) //loop through the closed 
				{
					for(GameState z: que) //loop thorugh the legal moves
					{
						if(same(i, z)) //if the two are the same then
						{
							z.setVisited(true);//set visited to true so we dont accidentally add it to the open list
						}
					}
				}
				
				for(GameState i: que) ///lets loop though the legal moves again
				{
					if(i.getVisited()!=true )//&& !same(i, z))if the gamestate not in closed
					{
						open.add(i);//add the game state to the open queue
						countOpen++;
					}
				}
				c++;
			}
		}
	}
	//this is a healer function to help with debugging where you can see what gamestate are in the closed linked list
	public void printClosed(LinkedList<GameState> i) 
	{
		System.out.println("This is the answer");
		for(GameState x: i) //go through the linked list
		{
			x.printGameState();//print out the gamestate
		}
	}
	//this is the fuction where we print out the path the game took to get to the goal state in reverse order
	public void printAns(LinkedList<GameState> i) 
	{
		GameState ans = i.getLast();//pull off the goal state
		LinkedList<GameState> an = new LinkedList<GameState>();//new linked list to loop through
		an.add(ans);//add the goal state to the new linked list
		int check = ans.getParentStateID();//this will hold the value of the parent id of the last gamestate move
		while(check!=-1) {//keep going until we reach the start node
			for(GameState x: i) //loop through closed
			{
		//	i.get()
				if(x.getStateID()==check) //check to see if the state id of a x gamestate is the parent node of the check gamestate
				{
					an.add(x);//add the x gamestate to the answer
					check = x.getParentStateID();//update check value
				}
			}
		}
		printClosed(an);//callprintClosed to printout gamestate infor
	}
}
