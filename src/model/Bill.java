package src.model;

import src.exceptions.InvalidAmountException;
import src.exceptions.InvalidOperationException;
import src.exceptions.MaxCapacityException;

public class Bill implements Billable {
    public static final int MAX_BILLS = 100;

    private static int billCount = 0;
    private int billId;
    private double amount;
    private boolean isPaid;
    private boolean isOverdue;

    public Bill(int billId, double amount) throws MaxCapacityException, InvalidAmountException { 
        if (billCount >= MAX_BILLS) {
            throw new MaxCapacityException("Cannot create more than 100 bills.");
        }
        if (billId <= 0) {
            throw new InvalidAmountException("Bill ID must be greater than 0.");
        } else {
            this.billId = billId;
        }
        if (amount < 0) {
            throw new InvalidAmountException("Amount cannot be negative.");
        } else {
            this.amount = amount;
        }
            this.isPaid = (amount == 0);
            this.isOverdue = false;
            billCount++;
        }
        public void payBill() throws InvalidOperationException {
            if (isPaid) {
                throw new InvalidOperationException("Bill is already fully paid.");
            } else {
            isPaid = true;
            amount = 0;
            isOverdue = false;
            System.out.println("Bill paid successfully.");
        }
    }

    public void addCharge(double extra) throws InvalidAmountException, InvalidOperationException {
        if (extra <= 0) {
            throw new InvalidAmountException("Charge must be greater than 0.");
        }

        if (isPaid) {
            throw new InvalidOperationException("Cannot add charge to a fully paid bill.");
        }
        amount += extra;
        System.out.println("Charge added successfully.");
    }

    public void applyDiscount(double percent) throws InvalidAmountException {
        if (percent < 0 || percent > 100) {
            throw new InvalidAmountException("Discount must be between 0 and 100.");
        }
            double discountAmount = amount * (percent / 100.0);
            amount -= discountAmount;
        if (amount <= 0) {
            amount = 0;
            isPaid = true;
            isOverdue = false;
        }
        System.out.println("Discount applied. New balance: $" + amount);
    }

    public void markAsOverdue() {
        if (isPaid) {
            System.out.println("Paid bill cannot be marked overdue.");
        } else {
            isOverdue = true;
            System.out.println("Bill marked as overdue.");
        }
    }

    public boolean hasBalance() {
        return amount > 0;
    }

    public void clearOverdueStatus() {
        if (isPaid) {
            isOverdue = false;
            System.out.println("Overdue status cleared.");
        } else {
            System.out.println("Bill must be paid before clearing overdue status.");
        }
    }

    public int getBillId() {
        return billId;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public boolean isOverdue() {
        return isOverdue;
    }

    public static int getBillCount() {
        return billCount;
    }

    public void setBillId(int billId) {
        if (billId > 0) {
            this.billId = billId;
        } else {
            System.out.println("Invalid bill ID.");
        }
    }

    public void setAmount(double amount) {
        if (amount >= 0) {
            this.amount = amount;
            if (amount > 0) {
                isPaid = false;
            }
        } else {
            System.out.println("Amount cannot be negative.");
        }
    }

    public void setPaid(boolean paid) {
        this.isPaid = paid;
        if (paid) {
            this.amount = 0;
            this.isOverdue = false;
        }
    }

    public void setOverdue(boolean overdue) {
        if (isPaid && overdue) {
            System.out.println("Paid bill cannot be overdue.");
        } else {
            isOverdue = overdue;
        }
    }

    public void displayBill() {
        System.out.println("Bill ID: " + billId);
        System.out.println("Amount: $" + amount);
        System.out.println("Paid: " + isPaid);
        System.out.println("Overdue: " + isOverdue);
    }

    @Override
    public String toString() {
        return "Bill ID: " + billId + "\n" +
            "Amount: $" + amount + "\n" +
            "Paid: " + isPaid + "\n" +
            "Overdue: " + isOverdue + "\n";
    }
}