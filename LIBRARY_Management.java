package Projects;

import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String title;
    String author;
    boolean isIssued;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }
}

public class LIBRARY_Management {
    private static ArrayList<Book> library = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("Library Management System");
            System.out.println("1. Add a book");
            System.out.println("2. Issue a book");
            System.out.println("3. Display books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    issueBook();
                    break;
                case 3:
                    displayBooks();
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 4);

        sc.close();
    }

    public static void addBook() {
        sc.nextLine();
        System.out.print("Enter the title of the book: ");
        String title = sc.nextLine();
        System.out.print("Enter the author of the book: ");
        String author = sc.nextLine();
        library.add(new Book(title, author));
        System.out.println("Book added successfully!");
    }

    public static void issueBook() {
        sc.nextLine();
        System.out.print("Enter the title of the book to issue: ");
        String title = sc.nextLine();

        for (Book book : library) {
            if (book.title.equalsIgnoreCase(title)) {
                if (!book.isIssued) {
                    book.isIssued = true;
                    System.out.println("Book '" + book.title + "' issued successfully!");
                    return;
                } else {
                    System.out.println("Book '" + book.title + "' is already issued.");
                    return;
                }
            }
        }

        System.out.println("Book '" + title + "' not found in the library.");
    }

    public static void displayBooks() {
        if (library.isEmpty()) {
            System.out.println("The library is empty.");
        } else {
            System.out.println("List of books in the library:");
            for (Book book : library) {
                System.out.println("Title: " + book.title);
                System.out.println("Author: " + book.author);
                System.out.println("Status: " + (book.isIssued ? "Issued" : "Available") + "\n");
            }
        }
    }
}
