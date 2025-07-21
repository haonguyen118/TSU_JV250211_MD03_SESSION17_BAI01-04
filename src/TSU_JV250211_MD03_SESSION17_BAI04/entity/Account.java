package TSU_JV250211_MD03_SESSION17_BAI04.entity;

public class Account {
   private int id;
   private double balance;

    public Account() {
    }

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id +
                ", balance=" + balance + '}';
    }
}
