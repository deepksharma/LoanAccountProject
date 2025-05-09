package src.main.java.com.LoanAccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.main.java.com.LoanAccount.model.LoanResponse;

public interface LoanAccountRepository extends JpaRepository<src.main.java.com.LoanAccount.model.LoanResponse, String> {
}
