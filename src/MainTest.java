import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
/* Testing functions:
assertTrue(func,message)
assertFalse(func,message)
assertEquals(expected,func,message)
 */

class Tests {

    public static void printArray(Character[][] board){
        String columnString = "   ";
        for(int k = 0; k < board[0].length;k++){
            columnString += k + "   ";
        }
        System.out.println(columnString);
        for (int i = 0; i < board.length;i++) {
            System.out.print((board.length-i)-1 + ": ");
            for (int j = 0; j < board[i].length;j++) {
                System.out.print(board[i][j] + "   "); // elements
            }
            System.out.print(":" + ((board.length-i)-1)); // right
            System.out.println(); // blank
        }
        System.out.println(columnString); // bottom
    }

    public static int[] splitRepresentation(String s){
        int[] split = new int[s.length()/3];
        for(int i = 0; i < s.length();i+=3){
            split[i/3] = Integer.parseInt(s.substring(i,i+3),2);
        }

        return split;
    }

    static Set<String> set = new HashSet<String>();
    Main main = new Main();
    Character[][] exampleBoard = { // Q = Queen, '.' = No queen in square
            {'.', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', '.','.'},
    };
    Character[][] validIncorrectBoard = { // Q = Queen, '.' = No queen in square
            {'Q', '.', '.', '.', '.', '.', '.','.'},
            {'.', 'Q', '.', '.', '.', '.', '.','.'},
            {'.', '.', 'Q', '.', '.', '.', '.','.'},
            {'.', '.', '.', 'Q', '.', '.', '.','.'},
            {'.', '.', '.', '.', 'Q', '.', '.','.'},
            {'.', '.', '.', '.', '.', 'Q', '.','.'},
            {'.', '.', '.', '.', '.', '.', 'Q','.'},
            {'.', '.', '.', '.', '.', '.', '.','Q'},
    };
    Character[][] validIncorrectSol = {
            {'.', '.', '.', '.', '.', 'Q', '.','.'},
            {'Q', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', '.','Q'},
            {'Q', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', 'Q','.'},
            {'.', '.', '.', '.', 'Q', '.', '.','.'},
            {'Q', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', 'Q', '.', '.', '.','.'},
    };
    Character[][] correctSol = { // Q = Queen, '.' = No queen in square
            {'.', 'Q', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', 'Q', '.','.'},
            {'Q', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', 'Q','.'},
            {'.', '.', '.', 'Q', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', '.','Q'},
            {'.', '.', 'Q', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', 'Q', '.', '.','.'},
    };

    public static void boardInfo(Character[][] board){
        if(board == null){return;}
        final int boardLength = board.length;
        int smallestRow = 0;
        int largestRow = 0;
        int queens = 0;
        for(int i = 0; i < board.length;i++){
            if(board[i].length < smallestRow || smallestRow == 0){
                smallestRow = board[i].length;
            }
            if(board[i].length > smallestRow || largestRow == 0){
                largestRow = board[i].length;
            }
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == null){
                    System.out.println("Null char at "+ i +"," +j);
                    continue;
                }
                final char curr = board[i][j];
                if(curr != 'Q' && curr != '.'){
                    System.out.println("illegal char at "+ i +"," +j);
                }
                if(curr == 'Q'){
                    queens++;
                }
            }
        }
        System.out.println("Number of rows: " +boardLength);
        System.out.println("Smallest number of columns on a row: " + smallestRow);
        System.out.println("Largest number of columns on a row: " + largestRow);
        System.out.println(queens + " Queens");
        System.out.println();
    }

    @BeforeEach
    void setUp(){
        main = new Main();
    }

    @AfterEach
    void Fin(){
        System.out.println(" ");
    }

    @Test
    @DisplayName("Q1) Valid character input Q")
    void isQueen() {
        assertTrue(Main.isValidCharacter('Q'),"Q is valid input");
    }

    @Test
    @DisplayName("Q1) Valid character input . (empty) ")
    void validCharEmpty() {
        assertTrue(Main.isValidCharacter('.'),". is valid input");
    }

    @Test
    @DisplayName("Q1) Invalid character input Null")
    void invalidCharNull() {
        assertFalse(Main.isValidCharacter(null),"null is invalid input");
    }

    @Test
    @DisplayName("Q1) Invalid character input q ")
    void invalidChar1() {
        assertFalse(Main.isValidCharacter('q'),"q is invalid input");
    }

    @Test
    @DisplayName("Q1) Invalid character input x ")
    void invalidChar2() {
        assertFalse(Main.isValidCharacter('x'),"x is invalid input");
    }

    @Test
    @DisplayName("Q1) Invalid character input empty ")
    void invalidCharEmpty() {
        assertFalse(Main.isValidCharacter(' '),"empty is invalid input");
    }

    @Test
    @DisplayName("Q2) Board is valid ")
    void validBoard1() {
        Character[][] validIncorrectBoard = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
        };
        boardInfo(validIncorrectBoard);
        printArray(validIncorrectBoard);
        assertTrue(Main.isValidBoard(validIncorrectBoard),"Board is right size, with valid chars");
    }
    @Test
    @DisplayName("Q2) Board is invalid due to invalid char")
    void invalidBoard1() {
        Character[][] invalidCharBoard = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'R', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
        };
        boardInfo(invalidCharBoard);
        printArray(invalidCharBoard);
        assertFalse(Main.isValidBoard(invalidCharBoard),"Board contains invalid chars; valid otherwise");
    }
    @Test
    @DisplayName("Q2) Board is invalid due to invalid char: Edge case 1")
    void invalidBoard2() {
        Character[][] invalidCharBoard = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','R'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
        };
        boardInfo(invalidCharBoard);
        printArray(invalidCharBoard);
        assertFalse(Main.isValidBoard(invalidCharBoard),"Board contains invalid chars; valid otherwise");
    }
    @Test
    @DisplayName("Q2) Board is invalid due to invalid char: Edge case 2")
    void invalidBoard3() {
        Character[][] invalidCharBoard = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','R'},
        };
        boardInfo(invalidCharBoard);
        printArray(invalidCharBoard);
        assertFalse(Main.isValidBoard(invalidCharBoard),"Board contains invalid chars; valid otherwise");
    }
    @Test
    @DisplayName("Q2) Board is invalid due to invalid char: Edge case 3")
    void invalidBoard4() {
        Character[][] invalidCharBoard = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'R', '.', '.', '.', '.', '.', '.','.'},
        };
        boardInfo(invalidCharBoard);
        printArray(invalidCharBoard);
        assertFalse(Main.isValidBoard(invalidCharBoard),"Board contains invalid chars; valid otherwise");
    }
    @Test
    @DisplayName("Q2) Board is invalid due to invalid char: Edge case 4")
    void invalidBoard5() {
        Character[][] invalidCharBoard = { // Q = Queen, '.' = No queen in square
                {'R', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
        };
        boardInfo(invalidCharBoard);
        printArray(invalidCharBoard);
        assertFalse(Main.isValidBoard(invalidCharBoard),"Board contains invalid chars; valid otherwise");
    }

    @Test
    @DisplayName("Q2) Board is invalid due to null char")
    void boardHasNullChar() {
        Character[][] invalidCharBoard = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', null, '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
        };
        boardInfo(invalidCharBoard);
        printArray(invalidCharBoard);
        assertFalse(Main.isValidBoard(invalidCharBoard),"Board contains null.");
    }

    @Test
    @DisplayName("Q2) Board is invalid due to null char")
    void boardHasNullChar2() {
        Character[][] invalidCharBoard = { // Q = Queen, '.' = No queen in square
                {null, '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
        };
        boardInfo(invalidCharBoard);
        printArray(invalidCharBoard);
        assertFalse(Main.isValidBoard(invalidCharBoard),"Board contains null.");
    }

    @Test
    @DisplayName("Q2) Board is invalid as it is null")
    void invalidBoardNull() {
        assertFalse(Main.isValidBoard(null),"Board contains invalid chars; valid otherwise");
    }
    @Test
    @DisplayName("Q2) Board is invalid as it is has too many queens")
    void invalidBoardTooManyQueens() {
        Character[][] invalidBoardQueens = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','Q'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
        };
        boardInfo(invalidBoardQueens);
        printArray(invalidBoardQueens);
        assertFalse(Main.isValidBoard(invalidBoardQueens),"Board contains too many queens; valid otherwise");
    }
    @Test
    @DisplayName("Q2) Board is invalid as it is has too many queens")
    void invalidBoardTooManyQueens2() {
        Character[][] invalidBoardQueens = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','Q'},
                {'Q', '.', '.', '.', '.', '.', '.','Q'},
        };
        boardInfo(invalidBoardQueens);
        printArray(invalidBoardQueens);
        assertFalse(Main.isValidBoard(invalidBoardQueens),"Board contains too many queens");
    }
    @Test
    @DisplayName("Q2) Board is invalid as it is has too many queens")
    void invalidBoardTooManyQueens3() {
        Character[][] invalidBoardQueens = { // Q = Queen, '.' = No queen in square
                {'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q','Q'},
                {'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q','Q'},
                {'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q','Q'},
                {'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q','Q'},
                {'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q','Q'},
                {'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q','Q'},
                {'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q','Q'},
                {'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q','Q'},
        };
        boardInfo(invalidBoardQueens);
        printArray(invalidBoardQueens);
        assertFalse(Main.isValidBoard(invalidBoardQueens),"Board contains too many queens");
    }
    @Test
    @DisplayName("Q2) Board is invalid as it is has too little queens")
    void invalidBoardTooLittleQueens() {
        Character[][] invalidBoardQueens = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
        };
        boardInfo(invalidBoardQueens);
        printArray(invalidBoardQueens);
        assertFalse(Main.isValidBoard(invalidBoardQueens),"Board contains too little queens");
    }
    @Test
    @DisplayName("Q2) Board is invalid as it is no queens")
    void invalidBoardTooLittleQueens2() {
        Character[][] invalidBoardQueens = { // Q = Queen, '.' = No queen in square
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
        };
        boardInfo(invalidBoardQueens);
        printArray(invalidBoardQueens);
        assertFalse(Main.isValidBoard(invalidBoardQueens),"Board contains too little queens");
    }
    @Test
    @DisplayName("Q2) Board is invalid as it is has too many columns on every row")
    void invalidBoardTooManyCols1() {
        Character[][] invalidBoardColumns= { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q','.'},
        };
        boardInfo(invalidBoardColumns);
        printArray(invalidBoardColumns);
        assertFalse(Main.isValidBoard(invalidBoardColumns),"Board contains too many columns");
    }
    @Test
    @DisplayName("Q2) Board is invalid as it is has too many columns on last row")
    void invalidBoardTooManyCols2() {
        Character[][] invalidBoardColumns = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q','.'},
        };
        boardInfo(invalidBoardColumns);
        printArray(invalidBoardColumns);
        assertFalse(Main.isValidBoard(invalidBoardColumns),"Board contains too many columns");
    }
    @Test
    @DisplayName("Q2) Board is invalid as it is has too many columns on middle row")
    void invalidBoardTooManyCols3() {
        Character[][] invalidBoardColumns = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
        };
        boardInfo(invalidBoardColumns);
        printArray(invalidBoardColumns);
        assertFalse(Main.isValidBoard(invalidBoardColumns),"Board contains too many columns");
    }
    @Test
    @DisplayName("Q2) Board is invalid as it is has too many columns on first row")
    void invalidBoardTooManyCols4() {
        Character[][] invalidBoardColumns = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
        };
        boardInfo(invalidBoardColumns);
        printArray(invalidBoardColumns);
        assertFalse(Main.isValidBoard(invalidBoardColumns),"Board contains too many columns");
    }
    @Test
    @DisplayName("Q2) Board is invalid as it is has too many columns on last row")
    void invalidBoardTooManyColsLast() {
        Character[][] invalidBoardColumns = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q','.'},
        };
        boardInfo(invalidBoardColumns);
        printArray(invalidBoardColumns);
        assertFalse(Main.isValidBoard(invalidBoardColumns),"Board contains too many columns");
    }
    @Test
    @DisplayName("Q2) Board is invalid as it is has too little columns")
    void invalidBoardTooLittleColumns() {
        Character[][] invalidBoardColumns = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.'},
                {'.', 'Q', '.', '.', '.', '.', '.'},
                {'.', '.', 'Q', '.', '.', '.', '.'},
                {'.', '.', '.', 'Q', '.', '.', '.'},
                {'.', '.', '.', '.', 'Q', '.', '.'},
                {'.', '.', '.', '.', '.', 'Q', '.'},
                {'.', '.', '.', '.', '.', '.', 'Q'},
                {'.', '.', '.', '.', '.', 'Q', '.'},
        };
        boardInfo(invalidBoardColumns);
        printArray(invalidBoardColumns);
        assertFalse(Main.isValidBoard(invalidBoardColumns),"Board contains too many columns");
    }
    @Test
    @DisplayName("Q2) Board is invalid as it is has too little rows")
    void invalidBoardTooLittleRows() {
        Character[][] invalidBoardRows = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','Q'},
        };
        boardInfo(invalidBoardRows);
        printArray(invalidBoardRows);
        assertFalse(Main.isValidBoard(invalidBoardRows),"Board contains too little rows");
    }
    @Test
    @DisplayName("Q2) Board is invalid as it is has too little rows")
    void invalidBoardTooLittleRows2() {
        Character[][] invalidBoardRows = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'}
        };
        boardInfo(invalidBoardRows);
        printArray(invalidBoardRows);
        assertFalse(Main.isValidBoard(invalidBoardRows),"Board contains too many columns");
    }

    @Test
    @DisplayName("Q2) Board is invalid as it is has no rows or columns")
    void invalidBoardTooLittleRows3() {
        Character[][] invalidBoardRows = { // Q = Queen, '.' = No queen in square
        };
        boardInfo(invalidBoardRows);
        assertFalse(Main.isValidBoard(invalidBoardRows),"Board contains no rows");
    }

    @Test
    @DisplayName("Q2) Board is invalid as it has too many rows")
    void invalidBoardTooManyRows() {
        Character[][] invalidBoardRows = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
        };
        boardInfo(invalidBoardRows);
        printArray(invalidBoardRows);
        assertFalse(Main.isValidBoard(invalidBoardRows),"Board contains too many rows");
    }
    @Test
    @DisplayName("Q2) Board is invalid as it has too many rows")
    void invalidBoardTooManyRows2() {
        Character[][] invalidBoardRows = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
        };
        boardInfo(invalidBoardRows);
        printArray(invalidBoardRows);
        assertFalse(Main.isValidBoard(invalidBoardRows),"Board contains too many rows");
    }
    @Test
    @DisplayName("Q2) Board is valid")
    void validBoard() {
        Character[][] validBoard = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
        };
        boardInfo(validBoard);
        printArray(validBoard);
        assertTrue(Main.isValidBoard(validBoard),"Board is  valid");
    }
    @Test
    @DisplayName("Q2) Board is valid 2")
    void validBoard2() {
        Character[][] validBoard = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
        };
        boardInfo(validBoard);
        printArray(validBoard);
        assertTrue(Main.isValidBoard(validBoard),"Board is  valid");
    }
    @Test
    @DisplayName("Q2) Board is valid 3")
    void validBoard3() {
        Character[][] validBoard = { // Q = Queen, '.' = No queen in square
                {'.', '.', '.', '.', '.', '.', '.','Q'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
        };
        boardInfo(validBoard);
        printArray(validBoard);
        assertTrue(Main.isValidBoard(validBoard),"Board is  valid");
    }
    @Test
    @DisplayName("Q2) Board is valid 4")
    void validBoard4() {
        Character[][] validBoard = { // Q = Queen, '.' = No queen in square
                {'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q','Q'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
        };
        boardInfo(validBoard);
        printArray(validBoard);
        assertTrue(Main.isValidBoard(validBoard),"Board is  valid");
    }
    @Test
    @DisplayName("Q2) Board is valid 5")
    void validBoard5() {
        Character[][] validBoard = { // Q = Queen, '.' = No queen in square
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','.'},
                {'Q', 'Q', 'Q', 'Q', 'Q', 'Q', 'Q','Q'},
        };
        boardInfo(validBoard);
        printArray(validBoard);
        assertTrue(Main.isValidBoard(validBoard),"Board is  valid");
    }

    @Test
    @DisplayName("Q3) Represent graph in binary")
    void graphToBinary1() {
        printArray(correctSol);
        System.out.println("expected: 100010111011110000101001");
        System.out.println("actual:" + Main.generateBinaryString(correctSol) );
        System.out.println();
        System.out.println("100010111011110000101001");
        System.out.println(Main.generateBinaryString(correctSol));
        assertEquals("100010111011110000101001", Main.generateBinaryString(correctSol), "Board reps should match");
    }
    @Test
    @DisplayName("Q3) Represent graph in binary")
    void graphToBinary2() {
        final String expected = "011000100110000111000101";

        printArray(validIncorrectSol);
        System.out.println("expected: "+expected);
        System.out.println("actual:" + Main.generateBinaryString(validIncorrectSol) );
        System.out.println();
        System.out.println(expected);
        System.out.println(Main.generateBinaryString(validIncorrectSol));

        assertEquals(Main.generateBinaryString(validIncorrectSol),expected,"Board reps should match");
    }
    @Test
    @DisplayName("Q3) Represent graph in binary")
    void graphToBinary3() {

        Character[][] exampleBoard = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
        };
        final String expected = "111110101100011010001000";

        printArray(exampleBoard);
        System.out.println("expected: " + expected);
        System.out.println("actual:" + Main.generateBinaryString(exampleBoard) );
        System.out.println();
        System.out.println(expected);
        System.out.println(Main.generateBinaryString(exampleBoard));

        assertEquals(Main.generateBinaryString(exampleBoard),expected,"Board reps should match");
    }

    @Test
    @DisplayName("Q3) Represent graph in binary")
    void graphToBinary4() {

        Character[][] exampleBoard = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
        };

        final String expected = "001011101110111100010000";

        printArray(exampleBoard);
        System.out.println("expected:" + expected);
        System.out.println("actual:" + Main.generateBinaryString(exampleBoard) );
        System.out.println();
        System.out.println(expected);
        System.out.println(Main.generateBinaryString(exampleBoard));

        assertEquals(Main.generateBinaryString(exampleBoard),expected,"Board reps should match");
    }
    @Test
    @DisplayName("Q3) Represent graph in binary")
    void graphToBinary5() {

        Character[][] exampleBoard = { // Q = Queen, '.' = No queen in square
                {'.', '.', '.', '.', '.', '.', '.','Q'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
        };

        final String expected = "000001110101011010100111";

        printArray(exampleBoard);
        System.out.println("expected:" + expected);
        System.out.println("actual:" + Main.generateBinaryString(exampleBoard) );
        System.out.println();
        System.out.println(expected);
        System.out.println(Main.generateBinaryString(exampleBoard));

        assertEquals(Main.generateBinaryString(exampleBoard),expected,"Board reps should match");
    }
    @Test
    @DisplayName("Q3) Represent graph in binary")
    void graphToBinary6() {

        Character[][] exampleBoard = { // Q = Queen, '.' = No queen in square
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
        };

        final String expected = "110100010111101001000011";

        printArray(exampleBoard);
        System.out.println("expected:" + expected);
        System.out.println("actual:" + Main.generateBinaryString(exampleBoard) );
        System.out.println();
        System.out.println(expected);
        System.out.println(Main.generateBinaryString(exampleBoard));

        assertEquals(Main.generateBinaryString(exampleBoard),expected,"Board reps should match");
    }
    @Test
    @DisplayName("Q3) Represent graph in binary")
    void graphToBinary7() {

        Character[][] exampleBoard = { // Q = Queen, '.' = No queen in square
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', '.', '.', '.','Q'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
        };

        final String expected = "011001000111110101010100";

        printArray(exampleBoard);
        System.out.println("expected:" + expected);
        System.out.println("actual:" + Main.generateBinaryString(exampleBoard) );
        System.out.println();
        System.out.println(expected);
        System.out.println(Main.generateBinaryString(exampleBoard));

        assertEquals(Main.generateBinaryString(exampleBoard),expected,"Board reps should match");
    }

    @Test
    @DisplayName("Q4) valid bin strings do not match")
    void randomBinaryString() {
        String startPoint = main.randomStartingPoint();
        String startPoint2 = main.randomStartingPoint();
        int ones = 0; int zeroes = 0;
        int onesForString2 = 0; int zeroesForString2 = 0;

        for(int i = 0; i < startPoint.length(); i++){
            if((startPoint.charAt(i) + "").equals("1")){
                ones++;
            }
            else if((startPoint.charAt(i) + "").equals("0")){
                zeroes++;
            }
            if((startPoint2.charAt(i) + "").equals("1")){
                onesForString2++;
            }
            else if((startPoint2.charAt(i) + "").equals("0")){
                zeroesForString2++;
            }
        }
        System.out.println("String 1: " +startPoint);
        System.out.println("Ones in string 1: " +ones);
        System.out.println("Zeroes in string 1: " +zeroes);
        System.out.println(" ");
        System.out.println("String 2: " +startPoint2);
        System.out.println("Ones in string 2: " +onesForString2);
        System.out.println("Zeroes in string 2: " +zeroesForString2);

        assertNotSame(startPoint,startPoint2,"Is valid binary string and does not match");
    }
    @Test
    @DisplayName("Q4) Binary string generated from board is of length 24")
    void randomBinaryStringLength() {
        String startPoint = main.randomStartingPoint();
        int ones = 0; int zeroes = 0;

        for(int i = 0; i < startPoint.length(); i++){
            if((startPoint.charAt(i) + "").equals("1")){
                ones++;
            }
            else if((startPoint.charAt(i) + "").equals("0")){
                zeroes++;
            }
        }

        System.out.println("String: " +startPoint);
        System.out.println("Ones in string: " +ones);
        System.out.println("Zeroes in string: " +zeroes);

        assertEquals(24,startPoint.length(),"Binary string length is 24");
    }

    @Test // algo runs at ~22ms without print statements
    @DisplayName("Q5) Fitness of example should be 42")
    void fitnessTest() {
        String bin = "011000100110000111000101";
        System.out.println(bin);
        System.out.println(Arrays.toString(splitRepresentation(bin)));
        assertEquals(42, Main.calculateFitness(bin),"Score should equal 42");
    }
    @Test
    @DisplayName("Q5) Fitness of perfect example should be 56")
    void fitnessTest1() {
        String bin = "100010111011110000101001";
        System.out.println(bin);
        System.out.println(Arrays.toString(splitRepresentation(bin)));
        assertEquals(56, Main.calculateFitness(bin),"Perfect result, should score 56");
    }
    @Test
    @DisplayName("Q5) Fitness of example should be: 0")
    void fitnessTest2() {
        Character[][] exampleBoard = { // Q = Queen, '.' = No queen in square
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
        };
        printArray(exampleBoard);
        String bin = Main.generateBinaryString(exampleBoard);
        System.out.println(bin);
        System.out.println(Arrays.toString(splitRepresentation(bin)));
        assertEquals(0, Main.calculateFitness(bin),"Should score: ");
    }
    @Test
    @DisplayName("Q5) Fitness of example should be: 40")
    void fitnessTest3() {
        Character[][] exampleBoard = { // Q = Queen, '.' = No queen in square
                {'.', '.', '.', '.', '.', '.', '.','Q'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
        };
        printArray(exampleBoard);
        String bin = Main.generateBinaryString(exampleBoard);
        System.out.println(bin);
        System.out.println(Arrays.toString(splitRepresentation(bin)));
        assertEquals(40, Main.calculateFitness(bin),"Should score: 40");
    }
    @Test
    @DisplayName("Q5) Fitness of example should be: 48")
    void fitnessTest4() {
        Character[][] exampleBoard = { // Q = Queen, '.' = No queen in square
                {'.', '.', '.', '.', '.', '.', '.','Q'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'.', '.', '.', '.', 'Q', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
        };
        printArray(exampleBoard);
        String bin = Main.generateBinaryString(exampleBoard);
        System.out.println(bin);
        System.out.println(Arrays.toString(splitRepresentation(bin)));
        assertEquals(48, Main.calculateFitness(bin),"Should score: 48");
    }
    @Test
    @DisplayName("Q5) Fitness of example should be: 44")
    void fitnessTest5() {
        Character[][] exampleBoard = { // Q = Queen, '.' = No queen in square
                {'.', '.', '.', '.', '.', '.', 'Q','.'},
                {'Q', '.', '.', '.', '.', '.', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', '.', '.', 'Q', '.','.'},
                {'.', 'Q', '.', '.', '.', '.', '.','.'},
                {'.', '.', '.', 'Q', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
                {'.', '.', 'Q', '.', '.', '.', '.','.'},
        };
        printArray(exampleBoard);
        String bin = Main.generateBinaryString(exampleBoard);
        System.out.println(bin);
        System.out.println(Arrays.toString(splitRepresentation(bin)));
        assertEquals(44, Main.calculateFitness(bin),"Should score: 44");
    }

    @Test
    @DisplayName("Q6) String is not the same after small change")
    void SmallChangeIsNotEqual() {
        String start = Main.randomStartingPoint();
        System.out.println("Start string: " +start);

        String smallChanged = Main.flipRandomBit(start);
        System.out.println("Start with small change applied: " +smallChanged);

        for(int i = 0; i < smallChanged.length();i++){
            if(smallChanged.charAt(i) != start.charAt(i)){
                System.out.println("change detected in string at index: "+i);
            }
        }
        assertNotEquals(start, smallChanged);
    }
    @Test
    @DisplayName("Q6) String is not the same after small change, 100 times")
    void SmallChangeIsNotEqualOneHundredTimes() {

        boolean flag = false;

        for(int i = 0; i < 100; i++){
            String start = Main.randomStartingPoint();

            String smallChanged = Main.flipRandomBit(start);

            boolean localFlag = false;
            for(int j = 0; j < smallChanged.length();j++){
                if(smallChanged.charAt(j) != start.charAt(j)){
                    localFlag = true;
                }
            }
            if(!localFlag){
                flag = true;
            }
        }

        assertFalse(flag);
    }

    @Test
    @DisplayName("Q6) Small change covers all possible permutations")
    void smallChangePermutationCover() {
        Set<Integer> set = new HashSet<>();
        for(int i = 1; i <= 24; i++){set.add(i);}

        String start = Main.randomStartingPoint();
        System.out.println("Start string: " +start);

        for(int i = 0; i < 1000 ; i++){
            System.out.println(set);
            if(set.isEmpty()){break;}
            String smallChanged = Main.flipRandomBit(start);
            for(int j = 0; j < smallChanged.length();j++){
                if(smallChanged.charAt(j) != start.charAt(j)){
                    if(j+1 > 24){fail("Attempted to flip out of bounds");}
                    set.remove(j+1);
                }
            }
        }
        assertTrue(set.isEmpty());
    }

    @Test
    @DisplayName("Q7: Test when RMHC gets stuck in local optima on average using bit-flip small change (Average across 1000 runs of RMHC with 10,000 iterations)")
    void localOptimaTestAverageFlipped() {

        int localOptimas = 0;
        int averageFitness = 0;

        for(int j = 0; j < 10000 ; j++){
            String bestString = Main.randomStartingPoint();
            int bestFitness = Main.calculateFitness(bestString);
            int localOptimaStuck = 0;

            for(int i = 0 ; i < 1000; i++){
                String temp = Main.flipRandomBit(bestString); // will store the best solution with a small change applied.
                int tempFitness = Main.calculateFitness(temp);
                if(tempFitness > bestFitness){
                    bestString = temp;
                    bestFitness = tempFitness;
                    localOptimaStuck = i;
                }
            }
            localOptimas += localOptimaStuck;
            averageFitness += bestFitness;
        }

        System.out.println("RMHC ran 1000 times for 10000 iterations and got stuck at local optima on average at : " + localOptimas / 1000);
        System.out.println("On average, fitness achieved was: " +averageFitness / 1000);
        assertTrue(true,"");
    }

    @Test
    @DisplayName("(Average Tests) Testing averages of permutation RRHC")
    void findAllAverages() {
        System.out.println("All tests ran 10000 times");
        System.out.println();

        Q7._findAverages(10);
        Q7._findAverages(100);
        Q7._findAverages(250);
        Q7._findAverages(500);
        Q7._findAverages(1000);
        Q7._findAverages(2500);
        Q7._findAverages(7500);
        Q7._findAverages(10000);
        return;
    }






    /*
    Character[][] exampleBoard = { // Q = Queen, '.' = No queen in square
            {'.', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', '.','.'},
            {'.', '.', '.', '.', '.', '.', '.','.'},
    };
     */














}