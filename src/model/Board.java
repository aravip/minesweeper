package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Board class contains the representation of the game board
 * and also the logic for processing moves 
 *
 */
public class Board {
	
	//Grid for the game board	
	protected static Node[] nodes;		
	
	/**
	 * Initializes a board using the specified dimension
	 * 1/8 of the board is allocated as mines
	 * @param width
	 * @param height
	 * @param rows
	 * @param cols
	 */
	public Board(int width, int height, int rows, int cols){
		
		ArrayList<Integer> mines = new ArrayList<Integer>();
		nodes = new Node[rows*cols];	
		
		for (int pos = 0; pos < rows*cols; pos++){
			nodes[pos] = new Node(pos);
		}
		
		//Randomly generates mine locations until 1/8 of the board is mines
		while (mines.isEmpty() || mines.size() < (rows*cols)/8){
			int pos = new Random().nextInt(rows*cols);
			if (!mines.contains(pos)) {
				mines.add(pos);
				nodes[pos].setMine();
			}
		}
		setup(rows,cols);	
	}
	
	/**
	 * Defines the board as a directed graph and adds edges
	 * @param rows
	 * @param cols
	 */
	public void setup(int rows, int cols){
		for (int y = 0; y < rows; y++){
			for (int x = 0; x < cols; x++){
				int pos = y*cols+x;					
				if (x < cols - 1) nodes[pos].addConnection(pos + 1);
				if (x > 0) nodes[pos].addConnection(pos - 1);
				if (y > 0) nodes[pos].addConnection(pos - cols);
				if (y < rows - 1) nodes[pos].addConnection(pos + cols);
				if (x < cols - 1 && y < rows - 1) nodes[pos].addConnection(pos + cols + 1);	//Down & right
				if (x < rows - 1 && y > 0) nodes[pos].addConnection(pos - cols + 1);		//Up & right
				if (x > 0 && y < cols - 1) nodes[pos].addConnection(pos + cols - 1);		//Down & left
				if (x > 0 && y > 0) nodes[pos].addConnection(pos - cols - 1);				//Up & left
			}
		}
	}
	
	/**
	 * Accessor method for the nodes on the board
	 * @param x
	 * @return
	 */
	public static Node getNode(int x){
		return nodes[x];
	}
	
}
