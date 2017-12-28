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
 * Creates a GameTree object of GameBoardNode objects that represent all possible game moves of the game
 * of Tic Tac Toe. 
 * @author ilveronaim
 *
 */
public class GameTree {
	
	private GameBoardNode root;
	private GameBoardNode cursor;
	
	/**
	 * Creates a GameTree object, initializing root to a new GameBoardNode object and cursor to root.
	 */
	public GameTree(){
		this.root = new GameBoardNode();
		cursor = root;
		
	}

	/**
	 * Makes a move in the game tree by setting cursor to the GameBoardNode at the passed in position in
	 * its config.
	 * @param position
	 * 	Position of GameBoardNode to be assigned to cursor from cursor's config.
	 */
	public void makeMove(int position){
		
		if(position <= 0 || position > 9)
			throw new IllegalArgumentException();
		
		if(cursor.getGameBoard().getBoard()[position-1] != Box.EMPTY)
				throw new NullPointerException();
		
		
		cursor = cursor.getConfig()[position-1];
		
		cursor.setProbabilities();
		
	
	}
	
	/**
	 * Build the complete game tree.
	 * @param root
	 * 	The root GameBoardNode from which the tree will be built.
	 * @param turn
	 * 	The turn to be made.
	 * @return
	 * 	Returns the root of the tree.
	 */
	public static GameBoardNode buildTree(GameBoardNode root, Box turn){
		
		//base cases:if the gameboard is full, or win/loss occurs
		
		if(isFull(root)){
			root.setEnd(true);
			return root;
		}
		if(winOccur(root)){
			root.setWinner(checkWin(root));
			root.setEnd(true);
			return root;
		}
			
		//GameBoardNode child = root;
		//set this child's gameBoard to rootsgameboard  
		//set this child's config to roots config
		for(int i = 0; i < root.getConfig().length; i++){
			GameBoardNode childNode = (GameBoardNode)root.clone(); // make a clone of the root
			childNode.setCurrentTurn(turnInverse(turn));
			
			if(childNode.getGameBoard().getBoard()[i] == Box.EMPTY){
				childNode.getGameBoard().getBoard()[i] = turn;
				root.getConfig()[i] = childNode;
				
				buildTree(root.getConfig()[i], root.getConfig()[i].getCurrentTurn());
			}
			else root.getConfig()[i] = null;
				
		}
		
		return root;
			
			
}
	
	/**
	 * Returns the inverse of the passed in turn.
	 * @param turn
	 * 	The turn whose inverse is to be returned.
	 * @return
	 * 	Returns the inverse of the passed in turn.
	 */
	public static Box turnInverse(Box turn){
		if(turn == Box.O)
			return Box.X;
		else return Box.O;
		
	}
		

	/**
	 * Checks if the passed in GameBoardNode is full.
	 * @param root
	 * 	The GameBoardNode to be checked.
	 * @return
	 * 	Returns true if the GameBoardNode's gameBoard is full, false otherwise.
	 */
	public static boolean isFull(GameBoardNode root){
		GameBoard gameBoard = root.getGameBoard();
		
		for(int i = 0; i < gameBoard.getBoard().length; i++){
			
			if(gameBoard.getBoard()[i] == Box.EMPTY){
				return false;
			}		
		}
		
		return true;
	}
	
	/**
	 * Checks if the passed in GameBoardNode has a winner.
	 * @param root
	 * 	The GameBoardNode to be checked.
	 * @return
	 *  Returns true if the GameBoardNode has a winner, false otherwise.
	 */
	public static boolean winOccur(GameBoardNode root){
		GameBoard gameBoard = root.getGameBoard();
		Box[] board = gameBoard.getBoard();
		
		if(board[0] == Box.O && board[1] == Box.O && board[2] == Box.O
				|| board[0] == Box.X && board[1] == Box.X && board[2] == Box.X ){
			return true;
		}
		else if(board[3] == Box.O && board[4] == Box.O && board[5] == Box.O
				|| board[3] == Box.X && board[4] == Box.X && board[5] == Box.X){
			return true;
		}
		else if(board[6] == Box.O && board[7] == Box.O && board[8] == Box.O
				|| board[6] == Box.X && board[7] == Box.X && board[8] == Box.X){
			return true;
		}
		
		
		
		else if(board[0] == Box.O && board[3] == Box.O && board[6] == Box.O
				|| board[0] == Box.X && board[3] == Box.X && board[6] == Box.X){
			return true;
		}
		else if(board[1] == Box.O && board[4] == Box.O && board[7] == Box.O
				|| board[1] == Box.X && board[4] == Box.X && board[7] == Box.X){
			return true;
		}
		else if(board[2] == Box.O && board[5] == Box.O && board[8] == Box.O
				|| board[2] == Box.X && board[5] == Box.X && board[8] == Box.X){
			return true;
		}
		
		
		else if(board[0] == Box.O && board[4] == Box.O && board[8] == Box.O
				|| board[0] == Box.X && board[4] == Box.X && board[8] == Box.X){
			return true;
		}
		else if(board[2] == Box.O && board[4] == Box.O && board[6] == Box.O
				|| board[2] == Box.X && board[4] == Box.X && board[6] == Box.X){
			return true;
		}
		
		return false;
		
	}

	
	/**
	 * Checks and returns the winner of a GameBoardNode.
	 * @param node
	 * 	The GameBoardNode to be checked.
	 * @return
	 * 	Returns the X or O for the winner, EMPTY otherwise. Returns null if the GameBoardNode is not
	 * 	a leaf.
	 */
	public static Box checkWin(GameBoardNode node){
		
		GameBoard gameBoard = node.getGameBoard();
		Box[] board = gameBoard.getBoard();
		
		if(board[0] == Box.O && board[1] == Box.O && board[2] == Box.O
				|| board[0] == Box.X && board[1] == Box.X && board[2] == Box.X ){
			return board[0];
		}
		else if(board[3] == Box.O && board[4] == Box.O && board[5] == Box.O
				|| board[3] == Box.X && board[4] == Box.X && board[5] == Box.X){
			return board[3];
		}
		else if(board[6] == Box.O && board[7] == Box.O && board[8] == Box.O
				|| board[6] == Box.X && board[7] == Box.X && board[8] == Box.X){
			return board[6];
		}
		
		
		
		else if(board[0] == Box.O && board[3] == Box.O && board[6] == Box.O
				|| board[0] == Box.X && board[3] == Box.X && board[6] == Box.X){
			return board[0];
		}
		else if(board[1] == Box.O && board[4] == Box.O && board[7] == Box.O
				|| board[1] == Box.X && board[4] == Box.X && board[7] == Box.X){
			return board[1];
		}
		else if(board[2] == Box.O && board[5] == Box.O && board[8] == Box.O
				|| board[2] == Box.X && board[5] == Box.X && board[8] == Box.X){
			return board[2];
		}
		
		
		else if(board[0] == Box.O && board[4] == Box.O && board[8] == Box.O
				|| board[0] == Box.X && board[4] == Box.X && board[8] == Box.X){
			return board[0];
		}
		else if(board[2] == Box.O && board[4] == Box.O && board[6] == Box.O
				|| board[2] == Box.X && board[4] == Box.X && board[6] == Box.X){
			return board[2];
		}
		
		else if(!(node.isLeaf()))
			return null;
		
		return Box.EMPTY;
		
	}
	
	/**
	 * Gets the root of the gameTree.
	 * @return
	 * 	Returns the root of the gameTree.
	 */
	public GameBoardNode getRoot() {
		return root;
	}

	/**
	 * Sets the root of the gameTree to the passed in GameBoardNode.
	 * @param root
	 * 	The root to be set.
	 */
	public void setRoot(GameBoardNode root) {
		this.root = root;
	}

	/**
	 * Gets the cursor of the gameTree.
	 * @return
	 * 	Returns the cursor of the gameTree.
	 */
	public GameBoardNode getCursor() {
		return cursor;
	}

	/**
	 * Sets the cursor of the gameTree to the passed in GameBoardNode.
	 * @param cursor
	 * 	The cursor to be set.
	 */
	public void setCursor(GameBoardNode cursor) {
		this.cursor = cursor;
	}

	//AFTER THE AI MAKES ITS MOVE
	
	/**
	 * Returns the win probability of the cursor.
	 * @return
	 * 	Returns a double win probability of the cursor.
	 */
	public double cursorProbability(){
		
		return cursor.getWinProb();
		
	}
}
