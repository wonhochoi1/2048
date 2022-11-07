import java.util.ArrayList;
import java.util.Collections;

/**
 * Class setting the methods and construction of the 2048 game. 
 * 2048 is a single-player arithmetic game where the user is able to slide numbered tiles on a 4x4 grid to combine them and
 * create a tile with the number 2048.  The score increases by an amount equal to the combination
 * of the two tiles.  The game ends when the board is full and no tiles can be combined.
 * @author Wonho Choi
 * @version 1.3
 */
public class _2048
{
    private final int rows = 4;
    private final int cols = 4;
    private int[][] board;
    private int[][] previousBoard;
    private int score;
    private int previousScore;

    /**
     * Initializes board and previousBoard using rows and cols.
     * Uses the generateTile method to add two random tiles to board.
     */
    public _2048()
    {
        board = new int[rows][cols];
        previousBoard = new int[4][4];
        generateTile();
        generateTile();
        score = 0;
        previousScore = 0;
    }

    /**
     * Initializes the board of this object using the specified board.
     * Initializes previousBoard using rows and cols.
     *
     * Precondition: the specified board is a 4x4 2D Array.
     *
     * @param board
     */
    public _2048(int[][] board)
    {
        this.board = board;
        previousBoard = new int[rows][cols];
        score = 0;
        previousScore = 0;
    }

    /**
     * Generates a tile and add it to an empty spot on the board.
     * 80% chance to generate a 2
     * 20% chance to generate a 4
     * Does nothing if the board is full.
     */
    private void generateTile()
    {
        int percentChance = (int) (Math.random() * 101);         //creates the percent chances out of 100
        int row = (int) (Math.random() * 4);
        int column = (int) (Math.random() * 4);

        while (board[row][column] != 0) {
            row = (int) (Math.random() * 4);
            column = (int) (Math.random() * 4);
        }

        if (percentChance <= 80)                                //20% chance of 4 and 80% chance of 2
            board[row][column] = 2;
        else board[row][column] = 4;
    }

    /**
     * Returns false if the board contains a 0, true otherwise.
     * @return
     */
    private boolean full()
    {
        for (int i = 0; i < cols; i++)
            for (int j = 0; j < rows; j++)
                if (board[i][j] == 0)
                    return false;

        return true;
    }

    /**
     * Returns the board.
     * @return
     */
    public int[][] getBoard()
    {
        return board;
    }

    /**
     * Returns the score.
     * @return
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Saves board into previousBoard and score into previousScore
     * then performs a move based on the specified direction:
     *
     * Valid directions (not case sensitive):
     *  up
     *  down
     *  left
     *  right
     *
     * Adds a new tile to the board using the generateTile method.
     *
     * @param direction String denoting which direction to take in.
     */
    public void move(String direction)
    {
        direction = direction.toLowerCase();            //makes sure directions is same so not case sensitive
        previousScore = score;                          //new score from previous score

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                previousBoard[i][j] = board[i][j];      //new board from previous board

        if (direction.equals("down"))
            moveDown();
        else if (direction.equals("up"))
            moveUp();
        else if (direction.equals("right"))
            moveRight();
        else moveLeft();

        generateTile();
    }

    /**
     * Shifts all the tiles up, combines like tiles that collide.
     */
    private void moveUp()
    {
        ArrayList<Integer> columnUp = new ArrayList<>();

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
                columnUp.add(board[j][i]);

            columnUp.removeAll(Collections.singleton(0));  //removes 0s in the ArrayList

            for (int j = 0; j < 4; j++)
                while (columnUp.size() < 4)
                    columnUp.add(0);                     //Moves elements up by adding 0s at the end of the ArrayList

            for (int j = 0; j < 3; j++)
                if (columnUp.get(j).equals(columnUp.get(j + 1)))    //If 2 elements are the same, add together and remove one of them
                {
                    columnUp.remove(j + 1);
                    columnUp.add(0);
                    columnUp.set(j, columnUp.get(j) * 2);       //Because numbers are the same, multiply by 2 to combine
                    score += columnUp.get(j);                   //Add the number that was combined to score
                }

            for (int x = 0; x < 4; x++)                   //Adds elements back to board
                board[x][i] = columnUp.get(x);

            columnUp.clear();                            //Column is cleared for next column
        }
    }

    /**
     * Shifts all the tiles down, combines like tiles that collide.
     */
    private void moveDown()
    {
        ArrayList<Integer> columnDown = new ArrayList<>();

        for (int i = 0; i < rows; i++)
        {
            for (int j = 3; j > -1; j--)
                columnDown.add(board[j][i]);

            columnDown.removeAll(Collections.singleton(0));     //removes 0s in the ArrayList

            for (int j = 0; j < 4; j++)
                while (columnDown.size() < 4)
                    columnDown.add(0);              //Moves elements down by adding 0s to the end of the ArrayList

            for (int j = 0; j < 3; j++)
                if (columnDown.get(j).equals(columnDown.get(j + 1)))   //If 2 elements are the same, add together and remove one of them
                {
                    columnDown.remove(j + 1);
                    columnDown.add(0);
                    columnDown.set(j, columnDown.get(j) * 2);       //Because numbers are the same, multiply by 2 to combine
                    score += columnDown.get(j);                     //Add the number that was combined to score
                }

            int y = 0;

            for (int x = 3; x > -1; x--)                //Adds elements back to board
            {
                board[x][i] = columnDown.get(y);
                y++;
            }

            columnDown.clear();                     //Column is cleared for next column
        }
    }

    /**
     * Shifts all the tiles left, combines like tiles that collide.
     */
    private void moveLeft()
    {
        ArrayList<Integer> rowLeft = new ArrayList<>();

        for (int i = 0; i < cols; i++)
        {
            for (int j = 0; j < rows; j++)
                rowLeft.add(board[i][j]);

            rowLeft.removeAll(Collections.singleton(0));

            for (int j = 0; j < 4; j++)
                while (rowLeft.size() < 4)
                    rowLeft.add(0);                     //Moves elements left by adding 0s to the end of the ArrayList

            for (int j = 0; j < 3; j++)
                if (rowLeft.get(j).equals(rowLeft.get(j + 1)))
                {
                    rowLeft.remove(j + 1);
                    rowLeft.add(0);
                    rowLeft.set(j, rowLeft.get(j) * 2);      //Because numbers are the same, multiply by 2 to combine
                    score += rowLeft.get(j);                 //Add the number that was combined to score
                }

            for (int x = 0; x < 4; x++)             //Added back to board
                board[i][x] = rowLeft.get(x);

            rowLeft.clear();                    //Row is cleared for next Row
        }
    }

    /**
     * Shifts all the tiles right, combines like tiles that collide.
     */
    private void moveRight()
    {
        ArrayList<Integer> rowRight = new ArrayList<>();

        for (int i = 0; i < rows; i++)
        {
            for (int j = 3; j > -1; j--)
                rowRight.add(board[i][j]);

            rowRight.removeAll(Collections.singleton(0));

            for (int j = 0; j < 4; j++)
                while (rowRight.size() < 4)
                    rowRight.add(0);            //Moves elements right by adding 0s to the end of the ArrayList

            for (int j = 0; j < 3; j++)
                if (rowRight.get(j).equals(rowRight.get(j + 1)))
                {
                    rowRight.remove(j + 1);
                    rowRight.add(0);
                    rowRight.set(j, rowRight.get(j) * 2);
                    score += rowRight.get(j);
                }

            int y = 0;

            for (int x = 3; x > -1; x--)        //Adds elements to board
            {
                board[i][x] = rowRight.get(y);
                y++;
            }

            rowRight.clear();           //Row is cleared for next row
        }
    }

    /**
     * Sets board to previousBoard and score to previousScore
     */
    public void undo()
    {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                board[i][j] = previousBoard[i][j];      //Changes board to previous board

        score = previousScore;      //Changes score back to previous score
    }

    /**
     * Returns true if the game is over, false otherwise.
     * @return
     */
    public boolean gameOver()
    {

        ArrayList<Integer> adjacent = new ArrayList<>();
        if (!full())
            return false;
        else for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                adjacent.add(board[i][j]);

        for (int j = 0; j < adjacent.size() - 2; j++)   //Checks for same elements to the left and right, if so, a move can be made
            if (adjacent.get(j).equals(adjacent.get(j + 1)))
                return false;

        adjacent.clear();

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                adjacent.add(board[j][i]);

        for (int i = 0; i < adjacent.size() - 2; i++)    //Checks for same elements above or below, if so, a move can be made
            if (adjacent.get(i).equals(adjacent.get(i + 1)))
                return false;

        return true;
    }

    /**
     * Returns a String representation of this object.
     */
    public String toString()
    {
        String rtn = "";

        for(int[] row : board)
        {
            rtn += "|";
            for(int num : row)
                if(num != 0) {
                    String str = num + "";
                    while(str.length() < 4)
                        if (str.length() + 1 < 4) {
                            str = str + " ";
                        } else {
                            str = " " + str;
                        }
                    rtn += str;
                } else
                    rtn += "    ";
            rtn += "|\n";
        }

        rtn += "Score: " + score + "\n";

        return rtn;
    }
}
