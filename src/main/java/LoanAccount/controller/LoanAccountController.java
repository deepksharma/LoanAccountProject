package LoanAccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import LoanAccount.model.LoanResponse;
import LoanAccount.service.LoanAccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
public class LoanAccountController {

    private static final Logger logger = LoggerFactory.getLogger(LoanAccountController.class);
    @Autowired
    private final LoanAccountService loanAccountService;

    @GetMapping("/{loanAccountNumber}")
    public ResponseEntity<LoanResponse> getLoanAccount(@PathVariable String loanAccountNumber) {
        logger.info("Received request for loan account: {}", loanAccountNumber);
        return ResponseEntity.ok(loanAccountService.processLoanAccount(loanAccountNumber));
    }
}
