import java.util.*;

public class Tic {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        String[] row1 = { "1", "2", "3" };
        String[] row2 = { "4", "5", "6" };
        String[] row3 = { "7", "8", "9" };
        String[][] Board = { { row1[0], "|", row1[1], "|", row1[2] }, { "-", "|", "-", "|", "-" },
                { row2[0], "|", row2[1], "|", row2[2] }, { "-", "|", "-", "|", "-" },
                { row3[0], "|", row3[1], "|", row3[2] }, { "-", "|", "-", "|", "-" }, };

        printBoard(Board);

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("Where would you like to place your mark? (Select 1-9)");
            int mark = input.nextInt();
            while (playerPositions.contains(mark) || cpuPositions.contains(mark)) {
                System.out.println("Oooo, a postion stealer. NOT ALLOWED!!!");
                mark = input.nextInt();
            }

            System.out.println(mark);

            placePiece(row1, row2, row3, Board, mark, "player");

            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);

            }

            Random rand = new Random();
            int cpuMark = rand.nextInt(9) + 1;
            while (cpuPositions.contains(cpuMark) || playerPositions.contains(cpuMark)) {
                cpuMark = rand.nextInt(9) + 1;
            }
            placePiece(row1, row2, row3, Board, cpuMark, "cpu");

            Board[0][0] = row1[0];
            Board[0][2] = row1[1];
            Board[0][4] = row1[2];
            Board[2][0] = row2[0];
            Board[2][2] = row2[1];
            Board[2][4] = row2[2];
            Board[4][0] = row3[0];
            Board[4][2] = row3[1];
            Board[4][4] = row3[2];

            printBoard(Board);

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
            }

        }

    }

    public static void printBoard(String[][] Board) {
        for (String[] r : Board) {
            for (String c : r) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(String row1[], String row2[], String row3[], String Board[][], int mark,
            String user) {

        String symbol = " ";

        if (user.equalsIgnoreCase("Player")) {
            symbol = "X";
            playerPositions.add(mark);
        } else if (user.equalsIgnoreCase("CPU")) {
            symbol = "O";
            cpuPositions.add(mark);
        }

        switch (mark) {

            case 1:
                row1[0] = symbol;
                break;
            case 2:
                row1[1] = symbol;
                break;
            case 3:
                row1[2] = symbol;
                break;
            case 4:
                row2[0] = symbol;
                break;
            case 5:
                row2[1] = symbol;
                break;
            case 6:
                row2[2] = symbol;
                break;
            case 7:
                row3[0] = symbol;
                break;
            case 8:
                row3[1] = symbol;
                break;
            case 9:
                row3[2] = symbol;
                break;

        }

    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List leftDiag = Arrays.asList(1, 5, 9);
        List rightDiag = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(leftDiag);
        winning.add(rightDiag);

        for (List l : winning) {
            if (playerPositions.containsAll(l)) {
                return "Congratz my friend, you have won.";
            } else if (cpuPositions.containsAll(l)) {
                return "Robot uprising next?";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "...You're both idiots...how?";
            }
        }

        return " ";
    }
}