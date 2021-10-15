package algorythms;
//Author: Emma Bradford
import java.util.LinkedList;
import java.util.*;

import gameState.GameState;
import gameState.GameStateComparator;
//this is the code for the BFS algorithm
public class BFS {
	//these are all the variables for the BFS class
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
	
	//this is the constructor for the BFS
	//it gets passed in an array for the start state and the goal state
	public BFS(int[] s, int[] g) 
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
	//this fucntion set the h(n) of the game state. the h(n) is calucalted based on where the empty space is and 
	//where the empty space in the goal board is
	//the inputed value is the index of the empty space of the goal node
	//another inouted value is a gamestate representing where the gamestate is
	public int h(int i, GameState a) 
	{
		int zero=a.findZero();//this finds where the empty space in the inputted gamestate
		
		int calc = zero-i;//thus calcates how far apart the empty spaces are
						  //i decided to go with this method because there is no instruction on how to calculate H(n)
		return Math.abs(calc);//the absolute value of calc is set to h(n) to ensure that the value is positive
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
			w.setH(this.h(goalS.findZero(), w));//passing in the empty space index of the goal node
			w.setF();
			w.setVisited(false);
			w.setParentStateID(a.getStateID());//set the parent id to that of the current game state
			//System.out.println(a.getStateID());
			//w.printGameState();
			w.setPriority(a.getPriority()-1);//the priority is the level of the tree we are at 
			 // the values get more and more negitive becaus that is compatible with the comparator
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
			x.setH(this.h(goalS.findZero(), x));//passing in the empty space index of the goal node
			x.setF();
			x.setParentStateID(a.getStateID());//set the parent id to that of the current game state

			x.setVisited(false);
			//x.printGameState();
			x.setPriority(a.getPriority()-1);//the priority is the level of the tree we are at 
			 // the values get more and more negitive becaus that is compatible with the comparator
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
			y.setH(this.h(goalS.findZero(), y));//passing in the empty space index of the goal node
			y.setF();
			y.setParentStateID(a.stateID);//set the parent id to that of the current game state

			y.setVisited(false);
			//y.printGameState();
			y.setPriority(a.getPriority()-1);//the priority is the level of the tree we are at 
			 // the values get more and more negitive becaus that is compatible with the comparator
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
			z.setH(this.h(goalS.findZero(), z));//passing in the empty space index of the goal node
			z.setF();
			z.setVisited(false);
			z.setParentStateID(a.getStateID());//set the parent id to that of the current game state
			//z.printGameState();
			z.setPriority(a.getPriority()-1);//the priority is the level of the tree we are at 
											 // the values get more and more negitive becaus that is compatible with the comparator
			//count++;
			ans.add(z);//add new gamestate to the linked list of legal moves
		}
		count++;//increment count so all the games states have unique ids
		return ans;
	}
	// this is the meat and potatos of the BFS algorythm
	public void runBFS() 
	{
		//System.out.println("this is the stuff in open");
		LinkedList<GameState> que = new LinkedList<GameState>();//new liked list to hold all the legal moves
		open.add(startS);//add the start node to the open queue so the while loop can start
		countOpen++;
		//int c=1;
		//while the open que is not empty and it has not reached its max then run the algorithm
		//this also prevents the code from running forever if the goal state is not reachable
		while(countOpen<1000 && !open.isEmpty())//&& c<6) 
		{
			GameState curr = open.remove();//set the current node to the beingin of open and remove curr node from open
			//System.out.println(curr);
			//System.out.println(open);
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
				//printOpen(open);
				//System.out.println("this is the stuff in open");
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
				//System.out.println("no more stuff");
				//this is where we check to see if any of the legal moves are already in closed
				for(GameState i: closed) //loop through the closed 
				{
					for(GameState z: que) //loop thorugh the legal moves
					{
					//	
						//if(closed.contains(z))
						if(same(i, z)) //if the two are the same then
						{
							//System.out.println(" in closed:" + z.getStateID());
							z.setVisited(true);//set visited to true so we dont accidentally add it to the open list
							//open.re
							//countOpen--;
						}
					}
				}
				
				//System.out.println("this is the stuff in que");
				for(GameState i: que) ///lets loop though the legal moves again
				{
				//	i.printGameState();
					 if(i.getVisited()!=true )//&& !same(i, z))if the gamestate not in closed
						{
							//System.out.println("visited?:" + i.getVisited());
							//System.out.println("not in closed:" + i.getStateID());
							open.add(i);//add the game state to the open queue
							countOpen++;
						}
				}
			//	System.out.println("this is the stuff in closed");
			//	for(GameState i: closed) 
			//	{
			//		i.printGameState();
			//	}
				
				//printOpen(open);
			//	c++;
			//	System.out.println(open);
			//	System.out.println("END LOOP");
			}
		}
		//printAns(closed);
		//System.out.println("This iuotfo answer");
	}
	//yhid is a healer function to help with debugging where you can see what gamestate are in the open queue
	public void printOpen(PriorityQueue<GameState>z) 
	{
		System.out.println("this is the stuff in open");
		for(GameState i: z) //go through the open queue
		{
			i.printGameState();//print out the gamestate
		}
	//	System.out.println("no more stuff");
	}
	//yhid is a healer function to help with debugging where you can see what gamestate are in the closed linked list
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
