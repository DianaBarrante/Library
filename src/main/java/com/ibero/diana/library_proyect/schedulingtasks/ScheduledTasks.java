package com.ibero.diana.library_proyect.schedulingtasks;

import com.ibero.diana.library_proyect.entities.Loan;
import com.ibero.diana.library_proyect.enums.loan.LoanState;
import com.ibero.diana.library_proyect.repositories.loan.LoanRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    private final LoanRepository loanRepository;

    public ScheduledTasks(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }


    @Scheduled(fixedRate = 60000)
    public void updateLoansState(){


        Date now = new Date();
        List<Loan> loans = loanRepository.findByStateAndDateEndBefore(LoanState.ACTIVE, now);

        loans.forEach(loan -> {
            loan.setState(LoanState.LATE);
        });
        loanRepository.saveAll(loans);

    }
}
