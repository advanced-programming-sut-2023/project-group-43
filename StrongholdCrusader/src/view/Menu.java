package view;

import model.DataBase;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    final protected static Scanner scanner = new Scanner(System.in);
    protected void run() {}

    protected static Matcher getMatcher(String input, String regex) {
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher(input);
    }

    public static Scanner getScanner() {
        return scanner;
    }
}