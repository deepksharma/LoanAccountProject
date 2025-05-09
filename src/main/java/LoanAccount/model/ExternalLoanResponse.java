package LoanAccount.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ExternalLoanResponse {

    @JsonProperty("loanAccountNumber")
    private String loanAccountNumber;

    @JsonProperty("emiDetails")
    private List<EmiDetail> emiDetails;

    // Getters and setters

    public String getLoanAccountNumber() {
        return loanAccountNumber;
    }

    public void setLoanAccountNumber(String loanAccountNumber) {
        this.loanAccountNumber = loanAccountNumber;
    }

    public List<EmiDetail> getEmiDetails() {
        return emiDetails;
    }

    public void setEmiDetails(List<EmiDetail> emiDetails) {
        this.emiDetails = emiDetails;
    }

    public static class EmiDetail {
        private String month;
        private int emiAmount;
        private boolean paidStatus;
        private boolean dueStatus;

        // Getters and setters

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public int getEmiAmount() {
            return emiAmount;
        }

        public void setEmiAmount(int emiAmount) {
            this.emiAmount = emiAmount;
        }

        public boolean isPaidStatus() {
            return paidStatus;
        }

        public void setPaidStatus(boolean paidStatus) {
            this.paidStatus = paidStatus;
        }

        public boolean isDueStatus() {
            return dueStatus;
        }

        public void setDueStatus(boolean dueStatus) {
            this.dueStatus = dueStatus;
        }
    }
}
