public class Main {
    private static final int QUEENS_AMOUNT = 8;
    private static  final int BOARD_SIZE = 8;
    private static final int MAX_FITNESS = (int) Math.pow(QUEENS_AMOUNT, 2) - QUEENS_AMOUNT;
    private static final int SOLUTION_LENGTH = BOARD_SIZE * 3;

    // Check whether a character is a queen or not
    private static boolean isQueen(Character character) {
        return character.equals('Q');
    }

    private static int cellToBinary(Character character) {
        return isQueen(character) ? 1 : 0;
    }

    private static String intToBinaryString(int n, int length) {
        String s = Integer.toBinaryString(n);
        return "0".repeat(length - s.length()) + s;
    }

    // Q1: Check whether a character is valid or not
    public static boolean isValidCharacter(Character character) {
        if (character == null) {
            return false;
        }

        return isQueen(character) || character.equals('.');
    }

    // Q2: Checks whether the board is valid
    public static boolean isValidBoard(Character[][] c) {
        if(c == null || c.length != BOARD_SIZE) {
            return false;
        }

        int queenCount = QUEENS_AMOUNT;

        for (Character[] characters : c) {
            if (characters.length != 8) {
                return false;
            }

            for (Character character : characters) {
                if (!isValidCharacter(character)) {
                    return false;
                }

                if (isQueen(character)) {
                    queenCount--;
                }
            }
        }

        return queenCount == 0;
    }

    // Q3: Converts Character[][] to String
    public static String generateBinaryString(Character[][] c) {
        String s = "";

        for (int row = c.length - 1;row >= 0; row--) {
            for (int col = 0; col < c[row].length; col++) {
                if (isQueen(c[row][col])) {
                    s += intToBinaryString(col, 3);
                }
            }
        }

        return s;
    }

    // Q4: Generates a random starting point
    public static String randomStartingPoint() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < SOLUTION_LENGTH; i++) {
            s.append((int) (Math.random() * 2));
        }

        return s.toString();
    }

    // Q5: Fitness Function
    public static int calculateFitness(String solution) {
        int fitness = MAX_FITNESS;

        for (int i = 0; i < solution.length(); i += 3) {
            int row = Integer.parseInt(solution.substring(i, i + 3), 2);

            // Iterate through the rest of the rows
            for (int j = i + 3; j < solution.length(); j += 3) {
                int nextRow = Integer.parseInt(solution.substring(j, j + 3), 2);

                if (row == nextRow || Math.abs(row - nextRow) == Math.abs((i - j) / 3)) {
                    fitness -= 2;
                }
            }
        }

        return fitness;
    }

    // Q6: Small change operator
    public static String flipRandomBit (String s) {
        return intToBinaryString(Integer.parseInt(s, 2) ^ 1 << (int) (Math.random() * s.length()), s.length());
    }


    public static void main(String[] args) {
        String str = randomStartingPoint();
    }

}