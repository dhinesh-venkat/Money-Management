package Main.Models;

public class Income {
    public int month;
    public String incomeType;
    public int amount;

    public Income(int month, int amount, String incomeType) {
        this.month = month;
        this.amount = amount;
        this.incomeType = incomeType;
    }
}
