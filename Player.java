/**
 * @author Celine Phan
 * CIS 36B, Lab 5
 */
public class Player { 
    
    private int xPos;
    private int yPos;
    private static int numTurns = 0;

    /**
     * 2-argument constructor for the Player class
     * @param y the y position of the Player on the x-y axis
     * @param x the x position of the Player on the x-y axis
     */
    public Player(int y, int x) {
        this.yPos = y;
        this.xPos = x;
        
    }

    /**
     * Accesses the Player's x position on the x-y axis
     * @return the Player's x position
     */
    public int getxPos() {
        return xPos;
        
    }

    /**
     * Accesses the Player's y position on the x-y axis
     * @return the Player's y position
     */
    public int getyPos() {
        return yPos;
        
    }
    
    /**
     * Accesses the total number of turns taken by the Player
     * @return the number of turns taken by the Player
     */
    public static int getNumTurns() {
        return numTurns;
    }
    
    /**
     * Increments the value of the numTurns variable
     */
    public static void increaseNumTurns() {
       numTurns = numTurns + 1;
    }
    
    /**
     * Increments the value of the xPos variable
     */
    public void increaseX() {
       xPos++;
    }
    
    /**
     * Decrements the value of the xPos variable
     */
    public void decreaseX() {
       xPos--;	
    }

    /**
     * Increments the value of the yPos variable
     */
    public void increaseY() {
        yPos++;
    }
    
    /**
     * Decrements the value of the yPos variable
     */
    public void decreaseY() {
        yPos--;
    }

    /**
     * Returns the String representation
     * of the character (Ö)
     * @return Ö
     */
    @Override
    public String toString() {
        return "" + (char) 214;  
    }
    
}