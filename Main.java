package cinema;

import java.util.Scanner;
import java.util.Arrays;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        int seatsBought = 0;
        int revenue = 0;
        double percentage = seatsBought == 0 ? 0 : seatsBought / seats;
        char[][] cinema = generateCinema(rows, seats);


        boolean loopBreaker = false;
        while (!loopBreaker) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int input = scanner.nextInt();

            if (input == 1) {
                printCinema(cinema);
            } else if (input == 2) {
                boolean loopWrecker = false;
                while (!loopWrecker) {
                    System.out.println("Enter a row number:");
                    int rowNumber = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int seatNumber = scanner.nextInt();
                    if (rowNumber > cinema.length || seatNumber > cinema[0].length) {
                        System.out.println("Wrong Input!");
                    } else {
                        if (cinema[rowNumber - 1][seatNumber - 1] == 'S') {
                            cinema[rowNumber - 1][seatNumber - 1] = 'B';
                            seatsBought++;
                            loopWrecker = true;
                            revenue = calculateSeatCost(rowNumber, rows, seats, revenue);
                        } else {
                            System.out.println("That ticket has already been purchased");
                        }
                    }
                }
            } else if (input == 3) {
                System.out.println("Number of purchased tickets: " + seatsBought);
                calculatePercentage(seatsBought, rows, seats);
                System.out.println("Current income: $" + revenue);
                calculateIncome(rows, seats);
            } else if (input == 0) {
                loopBreaker = true;
            } else {
                System.out.println("Please enter valid input");
            }

        }
    }

    public static char[][] generateCinema(int rows, int seats) {


        char[][] cinema = new char[rows][seats];

        for (int i = 0; i < cinema.length; i++) {
            Arrays.fill(cinema[i], 'S');
        }

        return cinema;
    }


    public static void calculateIncome(int rows, int seats) {
        if (rows * seats <= 60) {
            System.out.println("Total income: $" + rows * seats * 10);
        } else {
            int expensiveRows = (int) Math.floor(rows / 2);
            int cheapRows = rows - expensiveRows;
            int totalIncome = expensiveRows * seats * 10 + cheapRows * seats * 8;
            System.out.println("Total income: $" + totalIncome);
        }
    }

    public static int calculateSeatCost(int rowNumber, int rows, int seats, int revenue) {
        if (rows * seats <= 60) {
            System.out.println("Ticket price: $10");
            revenue += 10;
        } else {
            int expensiveRows = (int) Math.floor(rows / 2);
            if (rowNumber <= expensiveRows) {
                System.out.println("Ticket price: $10");
                revenue += 10;
            } else {
                System.out.println("Ticket price: $8");
                revenue += 8;
            }
        }

        return revenue;
    }

    public static void calculatePercentage (int seatsBought, int rows, int seats) {
        double percentage;
        double totalSeats = rows * seats;

        if (seatsBought == 0) {
            percentage = 0.00;
        } else {
            percentage = seatsBought / totalSeats * 100;
        }

        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
    }

    public static void printCinema(char[][] input) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i < input[0].length + 1; i++) {
            if(i < input[0].length) {
                System.out.print(" " + i);
            } else {
                System.out.print(" " + i + "\n");
            }
        }

        for (int i = 0; i < input.length; i++) {
            int row = i + 1;
            System.out.print(row);
            for (int j = 0; j < input[0].length; j++) {
                if (j < input[0].length - 1) {
                    System.out.print(" " + input[i][j]);
                } else {
                    System.out.print(" " + input[i][j] + "\n");
                }
            }
        }
    }
}
