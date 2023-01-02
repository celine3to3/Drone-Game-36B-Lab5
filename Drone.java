/**
 * @author Celine Phan
 * CIS 36B, Lab 5
 */
import java.util.Random; 

public class Drone {
    private int xPos;
    private int yPos;
    private int droneNum;
    private int fieldSize; //used to check if Drone goes out of bounds
    private static int droneCount = 0;
    
    /**
     * One-argument constructor for the Drone class
     * Uses the Random class to assign a random position
     * within the walls of the game field, given the 
     * size of the field as input
     * Assigns a random value within the field to xPos
     * and yPos, assigns fieldSize and assigns DroneNum
     * the next value of droneCount after incrementing it
     * @param fieldSize the size of the field
     */
    public Drone(int fieldSize) {
        Random random = new Random();
        xPos = random.nextInt(fieldSize - 2) + 1;
        yPos = random.nextInt(fieldSize - 2) + 1; 
        droneCount++; 
        this.fieldSize = fieldSize; 
        droneNum = droneCount; 
    }
    
    
    /**
     * Copy constructor for the Drone class
     * @param d another Drone to copy
     * assigns xPos and yPos to be the same
     * as d's position, assigns fieldSize to
     * be the same as d's fieldSize, *but*
     * assigns a unique droneNum to this Drone
     * after incrementing droneCount
     */
    public Drone(Drone d) {
        if (d != null) {
              this.xPos = d.xPos; 
              this.yPos = d.yPos; 
              this.fieldSize = d.fieldSize; 
              droneCount++; 
               
              this.droneNum = droneCount;
        }
    }

    /**
     * Accesses the Drone's x position on the x-y axis
     * @return the Drone's x position
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * Accesses the Drone's y position on the x-y axis
     * @return the Drone's y position
     */
    public int getyPos() {
        return yPos;
    }
    
    /**
     * Accesses the Drone's DroneNum 
     * @return the DroneNum
     */
    public int getDroneNum() {
        return droneNum;
    }
    
    /**
     * Randomly selects a number from 0-3 using
     * the Random class. 0 means increase the xPos,
     * 1 means decrease the xPos, 2 means increase
     * the yPos, 3 means decrease the yPos
     * However, if this random movement would cause
     * the Drone to fly into a wall around the field,
     * the Drone will jump to a random location within
     * the walls of the field.
     */
    public void move() {
        Random random = new Random(); 
        int num = random.nextInt(4); 
        if (num == 0) {
            xPos += 1; 
        } 
        else if (num == 1) {	
            xPos -= 1; 
        }
        else if (num == 2) {
            yPos += 1;
        } 
        else if (num == 3) {
            yPos -= 1; 
        }
        
        // loop while the new position is out of bounds
        while (xPos == 0 || xPos == fieldSize - 1 || yPos == 0 || yPos == fieldSize - 1) {
                xPos = random.nextInt(fieldSize - 1);
                yPos = random.nextInt(fieldSize - 1); 
                
        } // end while
    }
    
    /**
     * Returns the String representation
     * of the Drone
     * @return the Drone as a String
     */
    @Override
    public String toString() {
        char c = 164;
        return "" + c;
    }
}