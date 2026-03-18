public class Bill {
    private int billId;
    private double amount;
    private boolean isPaid;

    public Bill(int billId, double amount) {
        this.billId = billId;
        this.amount = amount;
        this.isPaid = false;
    }

    public void payBill() {
        if (isPaid) {
            System.out.println("Bill already paid.");
        } else {
            isPaid = true;
            System.out.println("Bill paid successfully.");
        }
    }

    public void addCharge(double extra) {
        if (!isPaid) {
            amount += extra;
        }
    }

    public void displayBill() {
        System.out.println("Bill ID: " + billId);
        System.out.println("Amount: " + amount);
        System.out.println("Paid: " + isPaid);
    }

    public double getAmount() {
        return amount;
    }
}