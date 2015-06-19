package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by angoh on 6/17/15.
 */
public class Menu {
    private Biblioteca biblioteca;
    private BufferedReader reader;
    private PrintStream printStream;
    private boolean stillAlive;

    public Menu(PrintStream printStream, Biblioteca biblioteca, BufferedReader reader){
        this.printStream = printStream;
        this.biblioteca = biblioteca;
        this.reader = reader;
        this.stillAlive = true;
    }

    public boolean isStillAlive() {
        return stillAlive;
    }

    public void displayMenu() {
        printStream.println("MAIN MENU");
        printStream.println("- List Books");
        printStream.println("- List Movies");
        printStream.println("- Checkout Book [book title]");
        printStream.println("- Checkout Movie [movie title]");
        printStream.println("- Return [book title]");
        printStream.println("- Quit");
        printStream.println("Enter your selection");
    }

    public String getUserInput() {
        String userSelection = " ";
        try {
            userSelection = reader.readLine();
        }
        catch (IOException e) {
            printStream.println(e.getMessage());
        }
        return userSelection;
    }

    public void selectOption(String selection) {
        selection = selection.toLowerCase();
        if (selection.contains("list books")) {
            biblioteca.listBooks();
        }
        else if (selection.contains("checkout book")){
            boolean checkout = biblioteca.checkoutBooks(selection.replace("checkout book", "").trim());
            if(checkout) {
                printStream.println("Success! Enjoy your book.");
            }else{
                printStream.println("Could not check out book with that title.");
            }
        }
        else if (selection.contains("list movies")){
            biblioteca.listMovies();
        }
        else if (selection.contains("checkout movie")){
            boolean checkout = biblioteca.checkoutMovie(selection.replace("checkout movie", "").trim());
            if(checkout) {
                printStream.println("Success! Enjoy your movie.");
            }else{
                printStream.println("Could not check out movie with that title.");
            }
        }
        else if (selection.contains("return")) {
            boolean bool = biblioteca.returnBook(selection.replace("return","").trim());
            if(bool){
                printStream.println("You have successfully returned this book");
            }else{
                printStream.println("Sorry, could not return that book.");
            }
        }
        else if (selection.contains("quit")) {
            stillAlive = false;
        }
        else{
            printStream.println("That's not a valid option");
        }
    }
}
