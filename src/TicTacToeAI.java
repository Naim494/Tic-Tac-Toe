/**
 * Naim Youssif Traore
 * 111256860
 * Homework Assignment Number 5
 * CSE 214 Recitation Section 08
 * Michael Rizzo  
 * Timothy Zang 
 * 
*/

import java.util.*;
import java.text.*;

/**
 * Simulates the game of Tic Tac Toe.
 * @author Naim Youssif Traore.
 *
 */
public class TicTacToeAI {

	private static Scanner in = new Scanner(System.in);

	/**
	 * Creates and initializes a new GameTree object. Also initializes player symbols.
	 * @param args
	 * 	main method arguments(are not used).
	 */
	public static void main(String[] args) {

		GameTree gameTree = new GameTree();
		Box user = Box.X;
		Box ai = Box.O;

		playGame(gameTree);

		in.close();

	}

	/**
	 * Simulates the play by play of the Tic Tac Toe game between a user and an AI.
	 * @param gameTree
	 * 	The gameTree from which the game is simulated.
	 */
	public static void playGame(GameTree gameTree) {
		DecimalFormat df = new DecimalFormat("0.00");

		gameTree.setRoot(GameTree.buildTree(gameTree.getRoot(), Box.X));

		boolean go = true;

		System.out.println("Welcome to Tic Tac Toe!\nThis is the board:\n\n" + GameBoard.boardRepresentation());

		int userMove = 0;
		GameBoardNode gameTreeCursor;
		double maxWinProb = 0;
		int maxWinMove;
		Box[] cursorBoard;
		boolean makeBlockMove;
		boolean makeWinMove;

		while (go) {
			
			makeBlockMove = false;
			makeWinMove = false;
			System.out.println("Please make a move: ");
			
	
			try {
				userMove = in.nextInt();
				maxWinMove = 0;
				gameTree.makeMove(userMove);
				gameTreeCursor = gameTree.getCursor();

				// set the probabilities of all the possible moves the ai can
				// make
				if (!(gameTreeCursor.isLeaf())) {
					for (int i = 0; i < gameTreeCursor.getConfig().length; i++) {
						if (gameTreeCursor.getConfig()[i] != null) {
							gameTreeCursor.getConfig()[i].setProbabilities();

							// get the best move for the ai
							if (gameTreeCursor.getConfig()[i].getWinProb() > maxWinProb) {
								maxWinProb = gameTreeCursor.getConfig()[i].getWinProb();
								maxWinMove = i + 1;
							}
							
						}
					}
					
					if(maxWinMove == 0){
						for(int i = 0;i < gameTreeCursor.getGameBoard().getBoard().length; i++){
							if(gameTreeCursor.getGameBoard().getBoard()[i] == Box.EMPTY){
								maxWinMove = i+1;
								break;
							}
						}
					}
						
					GameBoardNode temp = (GameBoardNode)gameTreeCursor.clone();
					cursorBoard = temp.getGameBoard().getBoard();
					
					for(int i = 0; i < cursorBoard.length; i++){
						if(cursorBoard[i] == Box.EMPTY){
							cursorBoard[i] = Box.O;
							if(cursorBoard[0] == Box.O && cursorBoard[1] == Box.O && cursorBoard[2] == Box.O ||
									cursorBoard[3] == Box.O && cursorBoard[4] == Box.O && cursorBoard[5] == Box.O ||
									cursorBoard[6] == Box.O && cursorBoard[7] == Box.O && cursorBoard[8] == Box.O ||
									cursorBoard[0] == Box.O && cursorBoard[3] == Box.O && cursorBoard[6] == Box.O ||
									cursorBoard[1] == Box.O && cursorBoard[4] == Box.O && cursorBoard[7] == Box.O ||
									cursorBoard[2] == Box.O && cursorBoard[5] == Box.O && cursorBoard[8] == Box.O ||
									cursorBoard[0] == Box.O && cursorBoard[4] == Box.O && cursorBoard[8] == Box.O ||
									cursorBoard[2] == Box.O && cursorBoard[4] == Box.O && cursorBoard[6] == Box.O){
								gameTree.makeMove(i+1);
								makeWinMove = true;
								break;
							}
							temp = (GameBoardNode)gameTreeCursor.clone();
							cursorBoard = temp.getGameBoard().getBoard();
						}
					}
					
					if(!(makeWinMove)){
						for(int i = 0; i < cursorBoard.length; i++){
						
							if(cursorBoard[i] == Box.EMPTY){
								cursorBoard[i] = Box.X;
								if(cursorBoard[0] == Box.X && cursorBoard[1] == Box.X && cursorBoard[2] == Box.X ||
										cursorBoard[3] == Box.X && cursorBoard[4] == Box.X && cursorBoard[5] == Box.X ||
										cursorBoard[6] == Box.X && cursorBoard[7] == Box.X && cursorBoard[8] == Box.X ||
										cursorBoard[0] == Box.X && cursorBoard[3] == Box.X && cursorBoard[6] == Box.X ||
										cursorBoard[1] == Box.X && cursorBoard[4] == Box.X && cursorBoard[7] == Box.X ||
										cursorBoard[2] == Box.X && cursorBoard[5] == Box.X && cursorBoard[8] == Box.X ||
										cursorBoard[0] == Box.X && cursorBoard[4] == Box.X && cursorBoard[8] == Box.X ||
										cursorBoard[2] == Box.X && cursorBoard[4] == Box.X && cursorBoard[6] == Box.X){
									gameTree.makeMove(i+1);
									makeBlockMove = true;
									break;
								}
								temp = (GameBoardNode)gameTreeCursor.clone();
								cursorBoard = temp.getGameBoard().getBoard();
							}
						
								
						}
					}
					
					
					if(!(makeBlockMove) && !(makeWinMove))
						gameTree.makeMove(maxWinMove);
					
				}

				System.out.println(gameTree.getCursor().getGameBoard().toString());

				double userDrawProb = gameTree.getCursor().getDrawProb();
				double userWinProb = 1 - (gameTree.getCursor().getWinProb() + userDrawProb);
				double userLossProb = 1 - (gameTree.getCursor().getLoseProb() + userDrawProb);

				System.out.println("The probability of a win is: " + df.format(userWinProb));
				System.out.println("The probability of a draw is: " + df.format(userDrawProb));
				System.out.println("The probability of a loss is: " + df.format(userLossProb));

				if (gameTree.getCursor().isLeaf()) {
					go = false;
					System.out.println();
					if (GameTree.checkWin(gameTree.getCursor()) == Box.EMPTY)
						System.out.println("The game is a draw.");
					else
						System.out.println("The winner is :" + GameTree.checkWin(gameTree.getCursor()));

				}
				System.out.println();

			} catch (IllegalArgumentException e) {
				System.out.println("Invalid position");
				continue;
			} catch (NullPointerException e) {
				System.out.println("Move already played.");
				continue;
			} catch (InputMismatchException e){
				System.out.println("Invalid input");
				in.nextLine();
				continue;
			}

		}

	}

}
