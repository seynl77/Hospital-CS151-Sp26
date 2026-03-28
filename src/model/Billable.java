package src.model;

import src.exceptions.InvalidAmountException;
import src.exceptions.InvalidOperationException;

public interface Billable {
    void addCharge(double amount) throws InvalidAmountException, InvalidOperationException;
    void applyDiscount(double percent) throws InvalidAmountException;
    void payBill() throws InvalidOperationException;
    boolean hasBalance();
}