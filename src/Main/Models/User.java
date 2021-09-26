package Main.Models;

import java.util.*;

public class User {
    public String userName;
    public List<Category> categories;
    public List<Income> income;
    public List<Expense> expenses;
    public Map<Integer, Integer> monthlySaving;
    // public List<Carryover> carryOver;

    public void initializeCategories() {
        String[] defaultCategories = new String[] { "Travel", "Shopping", "School", "Eating out", "Clothes", "Salary",
                "Pension" };

        for (int i = 0; i < defaultCategories.length; i++) {
            this.categories.add(new Category(defaultCategories[i]));
        }
    }

    public User(String userName) {
        this.userName = userName;
        categories = new ArrayList<Category>();
        income = new ArrayList<Income>();
        expenses = new ArrayList<Expense>();
        // carryOver = new ArrayList<Carryover>();
        monthlySaving = new HashMap<Integer, Integer>();

        initializeCategories();
    }

    public void printCategories() {
        for (Category c : categories) {
            System.out.println(c.categoryName);
        }
    }

    public void addIncome(int amount, int month, String incomeType) {
        Income in = new Income(month, amount, incomeType);

        boolean result = income.add(in);

        if (result) {
            System.out.println("Income added!");
        }
    }

    // this is an upsert operation
    public void addExpense(int month, int week, int amount, String expense) {
        Expense exp = new Expense(month, week, expense, amount);

        // not updating
        if(exists(exp)) {
            for(Expense e: expenses) {
                if(e.month == exp.month && e.week == exp.week && e.expenseType == exp.expenseType){
                    e.amount = exp.amount;
                    System.out.println("Expense updated!");
                }
            }
        } else {
            boolean result = expenses.add(exp);

            if (result) {
                System.out.println("Expense added!");
            }
        }

        

    }

    public boolean checkValid(String category) {
        for (Category c : categories) {
            if (c.categoryName.equals(category)) {
                return true;
            }
        }
        return false;
    }

    public boolean exists(Expense e) {
        for(Expense exp: expenses) {
            if(exp.month == e.month && exp.week == e.week && exp.expenseType == e.expenseType) {
                return true;
            }
        }

        return false;
    }

    public void addCategory(String name, String desc) {
        if(!checkValid(name)){
            categories.add(new Category(name, desc));
            System.out.println("Added " + name);
        }
    }

    public int displayCarryOver(int month) {

        int totalIncome = 0, totalExpense = 0;

        // total income calculation for month
        for(Income i: income) {
            if(i.month == month) {
                totalIncome += i.amount;
            }
        }
        
        // total expense calculation for month
        for(Expense e: expenses) {
            if(e.month == month) {
                totalExpense += e.amount;
            }
        }

        return totalIncome - totalExpense;
    }



    public void summary(int month) {
        int totalIncome = 0, totalExpense = 0, saving = 0;

        // total income calculation for month
        for(Income i: income) {
            if(i.month == month) {
                totalIncome += i.amount;
            }
        }
        System.out.println("Income : " + totalIncome);
        System.out.println("\n");
        
        // total expense calculation for month
        for(Expense e: expenses) {
            if(e.month == month) {
                totalExpense += e.amount;
                System.out.println(e.expenseType + " : " + e.amount);
            }
        }

        saving = totalIncome - totalExpense;

        for(int i = 1; i <= month; i++){
            saving += monthlySaving.get(i);
        }

        System.out.println("Total Expenses : " + totalExpense);
        System.out.println("Savigs/Debts : " + saving);
    }

    public void calculateSummary() {
        for(int i = 1; i <= 12; i++) {
            monthlySaving.put(i, displayCarryOver(i));
        }

        for(int i = 1; i <= 12; i++) {
            System.out.println("Month : " + i);
            summary(i);   
            System.out.println("--------------------"); 
        }
    }
}
