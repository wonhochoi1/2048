/**
 * Class denoting the test cases and main driver instantiating the different boards from the test cases.
 * @author Wonho Choi
 * @version 1.3
 */
public class _2048Tester
{
    public static void main(String[] args)
    {
        case1();
        case2();
        //case3();
        //case4();
        //case5();
        //undo();
        //gameOverCaseFalse();
        gameOverCaseTrue();
    }

    //Test cases for the board, with one move. 
    private static void case1()
    {
        System.out.println("Test Case 1:");
        int[][] board = {{0,0,0,2}, {4,0,0,0}, {0,0,0,0}, {0,0,0,0}};
        _2048 game = new _2048(board);
        System.out.println(game);
        game.move("up");
        System.out.println(game);
    }

    private static void case2()
    {
        System.out.println("Test Case 2:");
        int[][] board = {{0,2,0,0}, {0,2,0,0}, {0,0,0,0}, {0,4,0,0}};
        _2048 game = new _2048(board);
        System.out.println(game);
        game.move("up");
        System.out.println(game);
    }

    private static void case3()
    {
        System.out.println("Test Case 3:");
        int[][] board = {{0,0,2,0}, {0,0,0,0}, {0,2,0,0}, {0,0,0,0}};
        _2048 game = new _2048(board);
        System.out.println(game);
        game.move("up");
        System.out.println(game);
    }

    private static void case4()
    {
        System.out.println("Test Case 4:");
        int[][] board = {{0,0,0,0}, {0,0,0,2}, {4,0,2,0}, {0,2,2,2}};
        _2048 game = new _2048(board);
        System.out.println(game);
        game.move("up");
        System.out.println(game);
    }

    private static void case5()
    {
        System.out.println("Test Case 5:");
        int[][] board = {{4,0,0,0}, {4,0,0,0}, {0,0,0,0}, {2,2,2,2}};
        _2048 game = new _2048(board);
        System.out.println(game);
        game.move("right");
        System.out.println(game);
    }

    private static void undo()
    {
        System.out.println("Test Undo:");
        int[][] board = {{2,0,0,0}, {2,0,0,0}, {2,0,0,0}, {2,2,2,2}};
        _2048 game = new _2048(board);
        System.out.println(game);
        game.move("left");
        System.out.println(game);
        game.undo();
        System.out.println(game);
    }

    private static void gameOverCaseFalse()
    {
        System.out.println("Test Game Over (False):");
        int[][] board = {{4,16,2,8}, {2,4,8,16}, {16,8,4,2}, {8,2,16,4}};
        _2048 game = new _2048(board);
        System.out.println(game);
        System.out.println("Game Over: " + game.gameOver());
    }

    private static void gameOverCaseTrue()
    {
        System.out.println("Test Game Over (True):");
        int[][] board = {{2,4,8,16}, {4,8,16,2}, {8,16,2,4}, {16,2,4,8}};
        _2048 game = new _2048(board);
        System.out.println(game);
        System.out.println("Game Over: " + game.gameOver());
    }
}
