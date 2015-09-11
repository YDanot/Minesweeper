package fr.arolla.minesweeper.game;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {

    private BufferedReader reader;

    public ConsoleReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public int readInt(String name, int minValue, int maxValue) {
        int readedInt = 0;
        boolean validValueReaded;

        do {
            System.out.print("Entrez " + name + " : ");
            try {
                String line = reader.readLine();
                readedInt = Integer.valueOf(line);
                validValueReaded = isValidValueReaded(minValue, maxValue, readedInt);
                if (!validValueReaded) {
                    printErrorMessage(name, minValue, maxValue);
                }
            } catch (NumberFormatException e) {
                validValueReaded = false;
                printErrorMessage(name, minValue, maxValue);
            } catch (IOException e) {
                validValueReaded = false;
                e.printStackTrace();
                System.exit(0);
            }
        } while (!validValueReaded);

        return readedInt;
    }

    private boolean isValidValueReaded(int minValue, int maxValue, int value) {
        return value >= minValue && value <= maxValue;
    }

    private void printErrorMessage(String name, int minValue, int maxValue) {
        System.out.println("\n" + name + " doit être un chiffre compris entre " + minValue + " et " + maxValue);
    }
}
