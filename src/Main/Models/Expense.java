package Main.Models;

public class Expense {
    public int month;
    public int week;
    public String expenseType;
    public int amount;

    public Expense(int month, int week, String expense, int amount) {
        this.month = month;
        this.week = week;
        this.expenseType = expense;
        this.amount = amount;
    }
}
