package Main;

import java.util.Scanner;

import Main.Models.User;

public class Home {
    public User user;

    Home(User user) {
        this.user = user;
    }

    public void display() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("\n\nPlease choose any one of the below options to proceed");
            System.out.println(
                    "\n\n1. List out the categories available in the application\n2. Add incomes for a month\n3. Add expenses for a month\n4. Add additional category\n5. Carryover amount for next month\n6. Summary\n7. Exit");

            int input = in.nextInt();

            // Invalid input stops execution
            if (input < 0 || input > 6) {
                System.out.println("Thanks for using our application!");
                break;
            }

            // List out the categories
            if (input == 1) {
                user.printCategories();
            }

            // Add income
            if (input == 2) {
                int month, amount;
                String incomeType;
                Character add;
                boolean addMore = true;

                while (addMore) {
                    System.out.println("Enter details");

                    System.out.print("Month : ");
                    month = in.nextInt();
                    in.nextLine();

                    System.out.print("Income : ");
                    incomeType = in.nextLine();

                    // check for validity of income type
                    if (!user.checkValid(incomeType.trim())) {
                        System.out.println("No category found. Please add category.");
                        continue;
                    }

                    System.out.print("Amount : ");
                    amount = in.nextInt();
                    in.nextLine();

                    user.addIncome(amount, month, incomeType);

                    System.out.print("Add more (Y/N)? ");
                    add = in.next().charAt(0);
                    if (add.equals('n') || add.equals('N')) {
                        addMore = false;
                    }

                }
            }

            // Add expense (weekly)
            if (input == 3) {

                int month, amount, week;
                String expense;
                Character add;
                boolean addMore = true;

                while (addMore) {
                    System.out.println("Enter details");

                    System.out.print("Month : ");
                    month = in.nextInt();
                    in.nextLine();

                    System.out.print("Week : ");
                    week = in.nextInt();
                    in.nextLine();

                    System.out.print("Expense : ");
                    expense = in.nextLine();

                    // check for validity of income type
                    if (!user.checkValid(expense.trim())) {
                        System.out.println("No category found. Please add category.");
                        continue;
                    }

                    System.out.print("Amount : ");
                    amount = in.nextInt();
                    in.nextLine();

                    user.addExpense(month, week, amount, expense);

                    System.out.print("Add more (Y/N)? ");
                    add = in.next().charAt(0);
                    if (add.equals('n') || add.equals('N')) {
                        addMore = false;
                    }

                }
            }

            // Add category
            if (input == 4) {
                String categoryName, desc;
                Character add;
                boolean addMore = true;

                while (addMore) {
                    System.out.println("Enter details");

                    System.out.print("Category Name : ");
                    categoryName = in.nextLine();
                    in.nextLine();

                    System.out.print("Description : ");
                    desc = in.nextLine();

                    // check for duplicates and add
                    user.addCategory(categoryName, desc);

                    System.out.print("Add more (Y/N)? ");
                    add = in.next().charAt(0);
                    if (add.equals('n') || add.equals('N')) {
                        addMore = false;
                    }

                }
            }

            // display carry over
            if (input == 5) {
                int month;

                System.out.println("Enter details");

                System.out.print("Month : ");
                month = in.nextInt();
                in.nextLine();

                System.out.println("Amount : " + user.displayCarryOver(month - 1));

            }

            // display summary
            if (input == 6) {
                user.calculateSummary();
            }
        }

        in.close();
    }
}
