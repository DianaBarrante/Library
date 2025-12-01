package com.ibero.diana.library_proyect.repositories.loan;

import com.ibero.diana.library_proyect.entities.Loan;
import com.ibero.diana.library_proyect.enums.loan.LoanState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findByStateAndDateEndBefore(LoanState state, Date date);
}
