//package com.LoanAccount.service;
//
//import com.LoanAccount.model.LoanAccountResponse;
//import com.LoanAccount.repository.LoanAccountRepository;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//@RequiredArgsConstructor
//public class LoanAccountService {
//
//    private static final Logger logger = LoggerFactory.getLogger(LoanAccountService.class);
//    private final LoanAccountRepository repository;
//    private final RestTemplate restTemplate = new RestTemplate();
//    private final String API_URL = "http://demo9993930.mockable.io/loanaccount/";
//
//    public LoanAccountResponse processLoanAccount(String loanAccountNumber) {
//        String url = API_URL + loanAccountNumber;
//        logger.debug("Calling external API: {}", url);
//
//        try {
//            LoanAccountResponse response = restTemplate.getForObject(url, LoanAccountResponse.class);
//            if (response != null) {
//                response.setLoanAccountNumber(loanAccountNumber);
//                repository.save(response);
//                logger.info("Saved loan account response for: {}", loanAccountNumber);
//                return response;
//            } else {
//                throw new RuntimeException("No data returned from API.");
//            }
//        } catch (Exception e) {
//            logger.error("Failed to fetch or save loan account: {}", e.getMessage());
//            throw new RuntimeException("Failed to process loan account: " + loanAccountNumber);
//        }
//    }
//}
package LoanAccount.service;

import LoanAccount.model.ExternalLoanResponse;
import LoanAccount.model.LoanResponse;
import LoanAccount.repository.LoanAccountRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class LoanAccountService {

    private static final Logger logger = LoggerFactory.getLogger(LoanAccountService.class);
    private final LoanAccountRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "http://demo9993930.mockable.io/loanaccount/";

    public LoanResponse processLoanAccount(String loanAccountNumber) {
        String url = API_URL + loanAccountNumber;
        logger.debug("Calling external API: {}", url);

        try {
            // Deserialize the full external API structure
            ExternalLoanResponse externalResponse = restTemplate.getForObject(url, ExternalLoanResponse.class);

            if (externalResponse == null || externalResponse.getEmiDetails() == null) {
                throw new RuntimeException("No data returned from external API.");
            }

            ExternalLoanResponse.EmiDetail dueEmi = externalResponse.getEmiDetails().stream()
                    .filter(emi -> emi.isDueStatus())
                    .findFirst()
                    .orElse(null);


            if (dueEmi == null) {
                throw new RuntimeException("No due EMI found for loan account: " + loanAccountNumber);
            }

            // Map to your DB entity
            LoanResponse response = new LoanResponse();
            response.setLoanAccountNumber(externalResponse.getLoanAccountNumber());
            response.setDueDate(dueEmi.getMonth());
            response.setEmiAmount(dueEmi.getEmiAmount());

            repository.save(response);
            logger.info("Saved loan account response for: {}", loanAccountNumber);

            return response;

        } catch (Exception e) {
            logger.error("Failed to process loan account {}: {}", loanAccountNumber, e.getMessage());
            throw new RuntimeException("Failed to process loan account: " + loanAccountNumber);
        }
    }
}
