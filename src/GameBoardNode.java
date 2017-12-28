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
 * Creates a GameBoardNode object which represents the state of the game after a specific move
 * in the GameTree.
 * 
 * @author Naim Youssif Traore
 *
 */
public class GameBoardNode {
	
	private GameBoard gameBoard;
	private GameBoardNode config[] = new GameBoardNode[9];  //this is the same as GameBoardNode config[] ?
	private boolean isEnd;
	private Box currentTurn;
	private Box winner; //If the game ended in a draw, the value of this variable should be Box.EMPTY.
	private Double winProb;
	private Double loseProb;
	private Double drawProb;
	
	/**
	 * Creates a GameBoardNode object, initializing gameBoard to a new GameBoard object and
	 * winner to Box.EMPTY .
	 */
	public GameBoardNode(){
		this.gameBoard = new GameBoard();
		this.winner = Box.EMPTY;
	}
	
	/**
	 * Creates a GameBoardNode object, initializing gameBoard and currentTurn to the passed in 
	 * parameters. Also initializes winner to Box.EMPTY .
	 * @param board
	 * 	The gameBoard to be set.
	 * @param currentTurn
	 * 	The currentTurn to be set.
	 */
	public GameBoardNode(GameBoard board, Box currentTurn){
		
		this.gameBoard = board;
		GameBoard childBoard;
		this.winner = Box.EMPTY;
		
		if(currentTurn == Box.X){
			
			for(int i = 0; i < config.length; i++){
				childBoard = new GameBoard();
				
				if(board.getBoard()[i] == Box.EMPTY){
					childBoard.getBoard()[i] = Box.O;
					config[i] = new GameBoardNode(childBoard, Box.X);
				
				}
				else config[i] = null;
					
			}
			
		}
		else if(currentTurn == Box.O){
			
			for(int i = 0; i < config.length; i++){
				childBoard = new GameBoard();
				
				if(board.getBoard()[i] == Box.EMPTY){
					childBoard.getBoard()[i] = Box.X;
					config[i] = new GameBoardNode(childBoard, Box.O);
				}
				else config[i] = null;
				
			}
		}
		
		
	}
	
	/**
	 * Computes and sets the win, lose, and draw probabilities of this GameBoardNode.
	 */
	public void setProbabilities(){
		
		int totalLeafs = getLeafCount(this);
		int winLeafCount = getWinLeafCount(this);
		int drawLeafCount = getDrawLeafCount(this);
		int loseLeafCount = totalLeafs - (winLeafCount + drawLeafCount);
		
		winProb = (double)winLeafCount/(double)totalLeafs;
		loseProb = (double)loseLeafCount/(double)totalLeafs;
		drawProb = (double)drawLeafCount/(double)totalLeafs;
				
	}
	
	/**
	 * Gets the total leaf count of the passed in GameBoardNode.
	 * @param node
	 * 	The GameBoardNode whose number of leafs is to be counted.
	 * @return
	 * 	Returns an int count.
	 */
	public static int getLeafCount(GameBoardNode node){
		int count = 0;
		
		if(node == null)
			return 0;
		if(node.isLeaf())
			return 1;
		for(int i = 0; i < node.getConfig().length; i++){
			count += getLeafCount(node.getConfig()[i]);
		}
		
		return count;
		
	}
	
	/**
	 * Gets the total win leaf count of the passed in GameBoardNode.
	 * @param node
	 * 	The GameBoardNode whose number of win leafs is to be counted.
	 * @return
	 * 	Returns an int count.
	 */
	public static int getWinLeafCount(GameBoardNode node){
		int count = 0;
		
		if(node == null)
			return 0;
		if(node.isLeaf()){
			Box box = GameTree.checkWin(node);
			if(box == Box.O)
				return 1;
			return 0;		
		}
		
		for(int i = 0; i < node.config.length; i++){
			count += getWinLeafCount(node.config[i]);
		}
		
		return count;
	}
	
	/**
	 * Gets the total draw leaf count of the passed in GameBoardNode.
	 * @param node
	 * 	The GameBoardNode whose number of draw leafs is to be counted.
	 * @return
	 * 	Returns an int count.
	 */
	public static int getDrawLeafCount(GameBoardNode node){
		
		int count = 0;
		
		if(node == null)
			return 0;
		if(node.isLeaf()){
			Box box = GameTree.checkWin(node);
			if(box == Box.EMPTY)
				return 1;
			return 0;		
		}
		
		for(int i = 0; i < node.config.length; i++){
			count += getDrawLeafCount(node.config[i]);
		}
		
		return count;
	}
	
	/**
	 * Creates and returns a String representation of this GameBoardNode object.
	 * @return
	 * 	Returns a String representation of this GameBoardNode object. 	
	 */
	public String toString(){
		
		return gameBoard.toString();
	}

	/**
	 * Gets the gameBoard of this GameBoardNode object.
	 * @return
	 * 	Returns a GameBoard gameBoard.
	 */
	public GameBoard getGameBoard() {
		return gameBoard;
	}

	/**
	 * Gets the config of this GameBoardNode object.
	 * @return
	 * 	Returns the GameBoardNode[] config.
	 */
	public GameBoardNode[] getConfig() {
		return config;
	}

	/**
	 * Gets isEnd of this GameBoardNode. True indicates this GameBoardNode is the end of the game,
	 * false indicates otherwise.
	 * @return
	 */
	public boolean isEnd() {
		return isEnd;
	}

	/**
	 * Sets isEnd to the passed in parameter.
	 * @param isEnd
	 * 	The isEnd to be set.
	 */
	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	/**
	 * Gets the currentTurn of this GameBoardNode.
	 * @return
	 * 	Return a Box currentTurn.
	 */
	public Box getCurrentTurn() {
		return currentTurn;
	}

	/**
	 * Sets the currentTurn to the passed in parameter.
	 * @param currentTurn
	 * 	The currentTurn to be set.
	 */
	public void setCurrentTurn(Box currentTurn) {
		this.currentTurn = currentTurn;
	}

	/**
	 * Gets the winner of this GameBoardNode.
	 * @return
	 * 	Returns the winner of this GameBoardNode.
	 */
	public Box getWinner() {
		return winner;
	}

	/**
	 * Sets winner to the passed in parameter.
	 * @param winner
	 * 	The winner to be set.
	 */
	public void setWinner(Box winner) {
		this.winner = winner;
	}

	/**
	 * Gets the winProb of this GameBoardNode.
	 * @return
	 * 	Returns the winProb of this GameBoardNode.
	 */
	public Double getWinProb() {
		return winProb;
	}

	/**
	 * Sets winProb to the passed in parameter.
	 * @param winProb
	 * 	The winProb to be set.
	 */
	public void setWinProb(Double winProb) {
		this.winProb = winProb;
	}

	/**
	 * Gets the loseProb of this GameBoardNode.
	 * @return
	 * 	Returns the loseProb of this GameBoardNode.
	 */
	public Double getLoseProb() {
		return loseProb;
	}

	/**
	 * Sets loseProb to the passed in parameter.
	 * @param loseProb
	 * 	The loseProb to be set.
	 */
	public void setLoseProb(Double loseProb) {
		this.loseProb = loseProb;
	}

	/**
	 * Gets the drawProb of this GameBoardNode.
	 * @return
	 * 	Returns the drawProb of this GameBoardNode.
	 */
	public Double getDrawProb() {
		return drawProb;
	}

	/**
	 * Sets drawProb to the passed in parameter.
	 * @param drawProb
	 * 	The drawProb to be set.
	 */
	public void setDrawProb(Double drawProb) {
		this.drawProb = drawProb;
	}
	
	/**
	 * Creates and returns a clone of this GameBoardNode.
	 * @return
	 * 	Returns a clone of this GameBoardNode.
	 */
	public Object clone(){
		GameBoardNode clone = new GameBoardNode();
		//clone.gameBoard = this.gameBoard;
		
		for(int i = 0; i < clone.gameBoard.getBoard().length; i++){
			clone.gameBoard.getBoard()[i] = this.gameBoard.getBoard()[i];
		}
		
		return clone;
	}
	
	/**
	 * Checks if this GameBoardNode is a leaf.
	 * @return
	 * 	Returns true if this GameBoardNode is a leaf, false otherwise.
	 */
	public boolean isLeaf(){
		
		for(int i = 0; i < config.length; i++){
			if(config[i] != null)
				return false;
		}
		
		return true;
	}
	
}
