import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        List<List<String>> listWord = new ArrayList<>();

        String path = pathValidate(scanner,listWord);

        for (; ; ) {
            showMenu();
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("End")) {
                printEmptyLines();
                System.out.println("The program is closing...");
                break;
            } else if (command.equalsIgnoreCase("SwapWords")) {
                swapWord(scanner,listWord,path);
            } else if (command.equalsIgnoreCase("SwapRows")) {
                swapRow(scanner,listWord,path);
            } else {
                printEmptyLines();
                System.out.println("You enter an invalid command.");
                System.out.println("Try again!");
            }
        }


    }


    public static void showMenu() {
        System.out.println("----------------------");
        System.out.println("| Command Information|");
        System.out.println("----------------------");
        System.out.println("SwapRows (Swap two Rows)");
        System.out.println("SwapWords (Swap two Words)");
        System.out.println("End (Stop the program)");
        System.out.print("Type your command here:");

    }

    public static void printEmptyLines() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public static void swapWord(Scanner scanner,List<List<String>> listWord, String path) throws IOException {

        System.out.println("Enter the number of row");
        int firstRowNumber = Integer.parseInt(scanner.nextLine());
        firstRowNumber+=1;

        System.out.println("Enter the number of word");
        int firstWordNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the number of row");
        int secondRowNumber = Integer.parseInt(scanner.nextLine());
        secondRowNumber+=1;

        System.out.println("Enter the number of word");
        int secondWordNumber = Integer.parseInt(scanner.nextLine());

        if (firstRowNumber > listWord.get(0).size() - 1 || secondRowNumber > listWord.get(0).size() - 1||firstRowNumber<=0||secondRowNumber<=0) {
            printEmptyLines();
            System.out.println("Invalid number of row!");
        } else {
            for (int i = 0; i < listWord.get(0).size(); i++) {
                List<String> listOFWords = new ArrayList<>();
                String[] arr = listWord.get(0).get(i).split("\\s+");
                Collections.addAll(listOFWords, arr);
                listWord.add(listOFWords);
            }

            if (firstWordNumber > listWord.get(firstRowNumber).size() - 1 || secondWordNumber > listWord.get(secondRowNumber).size() - 1||firstWordNumber<0||secondWordNumber<0) {
                printEmptyLines();
                System.out.println("Invalid number of word!");
            } else {

                String firstWord = listWord.get(firstRowNumber).get(firstWordNumber);
                String secondWord = listWord.get(secondRowNumber).get(secondWordNumber);

                listWord.get(firstRowNumber).set(firstWordNumber, secondWord);
                listWord.get(secondRowNumber).set(secondWordNumber, firstWord);

                writeInFile("word", path,listWord);
                printEmptyLines();
                System.out.println("The command is done.");

            }
        }
    }

    public static void swapRow(Scanner scanner,List<List<String>> listWord,String path) throws IOException {

        System.out.println("Enter the number of row");
        int firstRowNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the number of row");
        int secondRowNumber = Integer.parseInt(scanner.nextLine());

        if (firstRowNumber > listWord.get(0).size() - 1 || secondRowNumber > listWord.get(0).size() - 1||firstRowNumber<0||secondRowNumber<0) {
            printEmptyLines();
            System.out.println("Invalid number of row!");
        } else {

            String firstRow = listWord.get(0).get(firstRowNumber);
            String secondRow = listWord.get(0).get(secondRowNumber);

            listWord.get(0).set(firstRowNumber, secondRow);
            listWord.get(0).set(secondRowNumber, firstRow);

            writeInFile("row", path,listWord);
            printEmptyLines();
            System.out.println("The command is done.");

        }
    }

    public static void writeInFile(String checker, String path,List<List<String>> listWord) throws IOException {
        FileWriter writer = new FileWriter(path);
        if (checker.equalsIgnoreCase("row")) {
            for (int i = 0; i < listWord.get(0).size(); i++) {
                writer.write(listWord.get(0).get(i) + System.lineSeparator());
            }
        } else if (checker.equalsIgnoreCase("word")) {
            for (int i = 1; i <= listWord.get(0).size(); i++) {
                for (int j = 0; j < listWord.get(i).size(); j++) {
                    writer.write(listWord.get(i).get(j) + " ");
                }
                writer.write(System.lineSeparator());
            }
        }
        writer.close();
    }

    public static String pathValidate (Scanner scanner,List<List<String>> listWord){
        for(;;) {
            System.out.println("Enter the path of file.");
            String path = scanner.nextLine();
            try {
                listWord.add(Files.readAllLines(Paths.get(path)));
                return path;
            } catch (IOException ex) {
                printEmptyLines();
                System.out.println("The file path is incorrect!");
                System.out.println("Try again.");
            }
        }
    }
}