package src.main.java.com.LoanAccount.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LoanResponse {

    @Id
    private String loanAccountNumber;
    private String dueDate;
    private int emiAmount;

    // Getters and setters

    public String getLoanAccountNumber() {
        return loanAccountNumber;
    }

    public void setLoanAccountNumber(String loanAccountNumber) {
        this.loanAccountNumber = loanAccountNumber;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(int emiAmount) {
        this.emiAmount = emiAmount;
    }
}
