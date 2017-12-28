/**
 * Naim Youssif Traore
 * 111256860
 * Homework Assignment Number 5
 * CSE 214 Recitation Section 08
 * Michael Rizzo   
 * Timothy Zang 
 * 
*/

/**
 * Creates a GameBoard object that represents the current state of the game.
 * @author Naim Youssif Traore
 *
 */
public class GameBoard {
	
	private Box[] board;
	private final int boardSize = 9;
	
	/**
	 * Creates a GameBoard object initializing board to a new board of size boardSize. Sets all
	 * the boxes to Box.EMPTY.
	 */
	public GameBoard(){
		this.board = new Box[boardSize];
		
		for(int i = 0; i < board.length; i++){
			board[i] = Box.EMPTY;
		}
	}
	
	/**
	 * Gets this game board's board.
	 * @return
	 * 	Returns an array of Box board.
	 */
	public Box[] getBoard() {
		return board;
	}

	/**
	 * Creates and returns a String representation of this GameBoard object.
	 * @return
	 * 	Returns a String representation of this GameBoard object.
	 * 	
	 */
	public String toString(){
		
		return "|" + printBox(board[0]) + "|" + printBox(board[1]) + "|" + printBox(board[2]) + "|\n|"
				+ printBox(board[3]) + "|" + printBox(board[4]) + "|" + printBox(board[5]) + "|\n|"
				+ printBox(board[6]) + "|" + printBox(board[7]) + "|" + printBox(board[8]) + "|\n";
	}
	
	/**
	 * Creates and returns a String representation of a number labeled game board.
	 * @return
	 * 	Returns a String representation of a number labeled game board.
	 */
	public static String boardRepresentation(){
		
		return "|" + 1 + "|" + 2 + "|" + 3 + "|\n|"
				+ 4 + "|" + 5 + "|" + 6 + "|\n|"
				+ 7 + "|" + 8 + "|" + 9 + "|\n";
	}
	
	/**
	 * Returns a String representation of a Box object.
	 * @param box
	 * 	The Box object whose String representation is to be returned.
	 * @return
	 * 	Returns a String representation of a Box object.
	 */
	public String printBox(Box box){
		if(box == Box.EMPTY)
			return "_";
		else if(box == Box.O)
			return "O";
		else return "X";
	}
}
