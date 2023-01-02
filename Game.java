
/**
 * @author Celine Phan
 * CIS 36B, Lab 5
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	private String[][] field;
	private Player p = new Player(1, 1);
	private ArrayList<Drone> drones = new ArrayList<Drone>();

	private int fieldSize;
	private static int smallField = 10;
	private static int bigField = 20;
	private static boolean gameOver = false;

	/**
	 * Assigns values to each row and column in the 2D field. Assigns - to the top
	 * and bottom edges of the field and | to the side edges to represent the walls
	 * around the field For all other (row, col), it assigns a blank space
	 * 
	 * @param size the size of the field
	 */
	public void initializeField(int size) {
		// hint: use a nested for loop
		field = new String[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == 0) {
					field[i][j] = "-";
				} else if (i == size - 1) {
					field[i][j] = "-";
				} else if (j == 0) {
					field[i][j] = "|";
				} else if (j == size - 1) {
					field[i][j] = "|";
				} else {
					field[i][j] = " ";

				}
			}
		}
	}

	/**
	 * Displays the 2D array to the console using the formula provided in class
	 */
	public void drawField() {
		// hint: use a nested for loop
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				System.out.print(field[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * Functions similar to a constructor Initializes fieldSize, calls the
	 * intializeField Inserts fs/2 Drones into the Drones ArrayList Inserts the
	 * chars representing the Player and each Drone into the correct position on the
	 * field ArrayList.
	 * 
	 * @param fs the size of the field
	 */
	public void gameSetUp(int fs) {
		initializeField(fs); // set up field and add Drones to field
		fieldSize = fs;
		field[p.getyPos()][p.getxPos()] = p.toString();// hint: call toString() for Player

		for (int i = 0; i < (fs / 2); i++) {
			Drone drone = new Drone(fs);
			drones.add(drone);
		}

	}

	/**
	 * Moves player based on wasd keys
	 * @return true if player successfully moved, false if 
	 * player tried to move out of bounds
	 */
	public boolean movePlayer(String direction) {
		field[p.getyPos()][p.getxPos()] = " ";
		if (direction.equals("a")) {
			if (!(p.getxPos() == 1)) {
				p.decreaseX();
				field[p.getyPos()][p.getxPos()] = p.toString();

				return true;
			}
		} else if (direction.equals("d")) {
			p.increaseX();
			field[p.getyPos()][p.getxPos()] = p.toString();

			return true;
		} else if (direction.equals("w")) {
			if (!(p.getyPos() == 1)) {
				p.decreaseY();
				field[p.getyPos()][p.getxPos()] = p.toString();

				return true;
			}
		} else if (direction.equals("s")) {
			p.increaseY();
			field[p.getyPos()][p.getxPos()] = p.toString();

			return true;
		}
		field[p.getyPos()][p.getxPos()] = p.toString();
		return false;

	}

	/**
	 * Calls the move() method from the Drone class for each Drone and also places a
	 * blank space where the Drone use to be located on the field.
	 */
	public void moveDrones() {
		// hint: use a single for loop
		for (int i = 0; i < drones.size(); i++) {
			field[drones.get(i).getyPos()][drones.get(i).getxPos()] = " ";
			drones.get(i).move();
		}
	}

	/**
	 * Inserts the chars representing each Drone into the correct position on the
	 * field ArrayList.
	 */
	public void placeDrones() {
		// hint: use a single for loop
		for (int i = 0; i < drones.size(); i++) {
			field[drones.get(i).getyPos()][drones.get(i).getxPos()] = "¤";
		}
	}

	/**
	 * Spawns a new Drone at each odd index in the ArrayList by calling the Drone
	 * copy constructor
	 */
	public void spawnDrones() {
		// hint: use a single for loop
		int length = drones.size();
		for (int i = 0; i < length; i++) {
			if (i % 2 != 0) {
				Drone drone = new Drone(drones.get(i));
				drones.add(drone);
			}
		}
	}

	/**
	 * Determines whether the Player is at the same location on the field as any
	 * Drone
	 * 
	 * @return the droneNum of the Drone collided with Player
	 */
	public int checkCollisionPlayerDrone() {
		// hint: use a single for loop
		for (int i = 0; i < drones.size(); i++) {
			if (field[p.getyPos()][p.getxPos()] == field[drones.get(i).getyPos()][drones.get(i).getxPos()]) {
				int num = drones.get(i).getDroneNum();
				return num;
			}
		}

		return -1;
	}

	/**
	 * Replaces collision point with asterisk at end of game
	 */
	public void endGame() {
		field[p.getyPos()][p.getxPos()] = "*";
	}

	/**
	 * Check if player won by reaching opposite side and ends the game after
	 * displaying final field
	 * @return true if player won, false if not
	 */
	public boolean checkWin() {
		if (p.getxPos() == fieldSize - 2) {
			field[p.getyPos()][p.getxPos()] = " ";
			field[p.getyPos()][p.getxPos()] = p.toString();
			System.out.println("\nCongratulations! You win the game!");
			placeDrones();
			drawField();

			gameOver = true;
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Game g = new Game();
		Scanner input = new Scanner(System.in);
		boolean moved;

		System.out.println("Welcome to Game of Drones!");
		System.out.println("\nPlease select from one of the following options:");
		System.out.println("\n1. Play on a small field");
		System.out.println("2. Play on a big field");
		System.out.println("Or, press any other key + return to quit");
		System.out.print("\nEnter your choice: ");

		String choice = input.next();

		if (choice.equals("1")) {
			g.gameSetUp(smallField);
			g.placeDrones();
		} else if (choice.equals("2")) {
			g.gameSetUp(bigField);
			g.placeDrones();

		} else {
			System.out.println("\nGoodbye!");
			input.close();
			return;
		}

		System.out.println(); 
		g.drawField();

		while (!gameOver) {
			Player.increaseNumTurns();

			System.out.print("Enter your move (w/a/s/d): ");
			String move = input.next();
			moved = g.movePlayer(move);
			if (moved == false) {
				System.out.println("\nTaking a break while we keep droning on?");
			}
			
			// spawn every other turn
			if (Player.getNumTurns() % 2 == 0) {
				g.spawnDrones();
			}
			g.moveDrones();

			int check = g.checkCollisionPlayerDrone();
			
			// if there was a collision, end game
			if (check != -1) {
				System.out.println(
						"\nGame over! You just got knocked out by Drone Number " + check + ". Better luck next time!");
				g.placeDrones();
				g.endGame(); // inserting asterisk at collision point
				g.drawField();
				break;
			}
			
			// finish game if player won
			if (g.checkWin()) {
				break;
			}

			g.placeDrones();
			g.drawField();
		}
		input.close();

	}

}