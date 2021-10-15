package gameState;
import java.util.*;
import java.util.Comparator; 
//Author: Emma Bradford
public class GameStateComparator implements Comparator<GameState> {
	//this is a class that is used to complare the game states
	public int compare(GameState a1, GameState a2) 
	{
		if(a1.priority < a2.priority)//check to see if the a2 is bigger than a1
		{
			//System.out.println("this is a1 smaller than a2 ");
			//System.out.println("this is a1: " + a1.getStateID() + " priotity " + a1.priority);
			//System.out.println("this is a2: " + a2.getStateID() + " priotity " + a2.priority);
			return 1;
		} 
		else if(a2.priority > a1.priority) //check to see if the a1 is bigger than a2
		{
			//System.out.println("this is a2 smaller than a1 ");
			//System.out.println("this is a1: " + a1.getStateID() + " priotity " + a1.priority);
			//System.out.println("this is a2: " + a2.getStateID() + " priotity " + a2.priority);
			return -1;
		}	
		else 
		{	
			return 0;//check to see if the prioirties are the same
		}
	}

	private void println(String string) {
		// TODO Auto-generated method stub
		
	}
}
