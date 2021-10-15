package mainAlgorithms;
import gameState.GameState;
import gameState.GameStateComparator;
import algorythms.BFS;
import java.util.Scanner;
import algorythms.Astar;
import algorythms.Astar2;
//Author: Emma Bradford
public class Display {
	
	public static void main(String[] args) 
	{
		//this is the part of the code that askes the user for the inputs of the goal and start state
		Scanner myObj = new Scanner(System.in);
		int[] start1 = new int[18];//the array to hold inputed start state atrray
		int[] goal1 = new int[18];//the array to hold inputed goal state atrray
		System.out.println("enter the numbers for the start node");
		//ask the user to input the values for each of teh indexes for start state
		for(int i = 0; i<18;i++) {
			System.out.println("Enter number at index " + i + ":");
			int test = myObj.nextInt();//get the input from the user
			start1[i] = test;//add input to the array
		}
		//ask the user to input the values for each of teh indexes for start state
		System.out.println("enter the numbers for the goal node");
		for(int k = 0; k<18;k++) {
			System.out.println("Enter number at index " + k + ":");
			int test1 = myObj.nextInt();//get the input from the user
			goal1[k] = test1;////add input to the array
		}
		//set start1 to start and goal1 to goal
		int[] start = start1;//{1,  13,  3,  5,  17,  9,  11,  0,  8,  12,  14,  10,  7,  16,  15,  4,  6,  2};
		int[] goal = goal1;//{5,  1,  3,  13,  17,  9,  11,  0,  8, 12,  14,  10,  7,  16,  15,  4,  6,  2};
		//GameState s = new GameState();
		//GameState g = new GameState();
		//s.setBoard(start);
		//s.printGameState();
		//g.setBoard(goal);
		//g.printGameState();
		//int[]f=s.moveDown();
		//s.setBoard(f);
		//s.printGameState();
		System.out.println("breadth first search algorithm");
		BFS bfs = new BFS(start, goal);//create new BFS algoithm and pass inn the start and goal state atrrays
		bfs.runBFS();//run the BFS algorythm!!!!!!!!!!!!!!!!!!!!!!!
		System.out.println("A* search algorithm h1 heuristic");
		Astar a1 = new Astar(start, goal);//create new A* algoithm and pass inn the start and goal state atrrays
		a1.runAStar();//run the A* algorythm!!!!!!!!!!!!!!!!!!!!!!!
		System.out.println("A* search algorithm h2 heuristic");
		Astar2 a2 = new Astar2(start, goal);//create new A* algoithm and pass inn the start and goal state atrrays
		a2.runAStar();//run the A* algorythm!!!!!!!!!!!!!!!!!!!!!!!
		//System.out.println(bfs.isGoalState(s, g));
		//System.out.println(bfs.successor(s));
	}
}
