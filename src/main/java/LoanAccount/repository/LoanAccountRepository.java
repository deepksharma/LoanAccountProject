package LoanAccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import LoanAccount.model.LoanResponse;

public interface LoanAccountRepository extends JpaRepository<LoanResponse, String> {
}
