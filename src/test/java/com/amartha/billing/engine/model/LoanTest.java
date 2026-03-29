package com.amartha.billing.engine.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoanTest {
  private Loan loan;

  @BeforeEach
  void setUp() {
    loan = new Loan();
  }

  // test loanId()

  @Test
  void loanIdIncreased_returnLoanId() {
    Loan loan1 = new Loan();
    Loan loan2 = new Loan();

    Assertions.assertNotEquals(loan1.getLoanId(), loan2.getLoanId());
  }

  // test getOutstanding() method

  @Test
  void loanWithNoPayment_getOutstanding_returnFullAmount() {
    Assertions.assertEquals(5_500_000L, loan.getOutstanding());
  }

  @Test
  void loanWithOnePayment_getOutstanding_returnSubtractWithFirstInstallment() {
    long installmentAmount = loan.getInstallments().getFirst().getAmount();
    loan.makePayment(installmentAmount, 1);

    long totalLoanAmount = (long) (loan.getPrincipal() * (1 + loan.getInterestRate()));
    Assertions.assertEquals(totalLoanAmount - installmentAmount, loan.getOutstanding());

  }

  @Test
  void loanWithFullPayment_getOutstanding_returnZero() {
    long installmentAmount = loan.getInstallments().getFirst().getAmount();

    for (int week = 1; week <= 50; week++) {
      loan.makePayment(installmentAmount, week);
    }

    Assertions.assertEquals(0, loan.getOutstanding());
  }

  // test makePayment() method


  @Test
  void loanMakePaymentWithInvalidWeek_makePayment_returnFalse() {
    long installmentAmount = loan.getInstallments().getFirst().getAmount();
    boolean result = loan.makePayment(installmentAmount, 0);

    Assertions.assertFalse(result);

    result = loan.makePayment(installmentAmount, 99);
    Assertions.assertFalse(result);
  }

  @Test
  void loanMakePaymentWithRightAmount_makePayment_returnTrue() {
    long installmentAmount = loan.getInstallments().getFirst().getAmount();
    boolean result = loan.makePayment(installmentAmount, 1);

    Assertions.assertTrue(result);
    Assertions.assertTrue(loan.getInstallments().getFirst().isPaid());
  }

  @Test
  void loanMakePaymentWithWrongAmount_makePayment_returnFalse() {
    long installmentAmount = 99_000;
    boolean result = loan.makePayment(installmentAmount, 1);

    Assertions.assertFalse(result);
    Assertions.assertFalse(loan.getInstallments().getFirst().isPaid());
  }

  @Test
  void loanMakePaymentWhenAlreadyPaid_makePayment_returnFalse() {
    long installmentAmount = loan.getInstallments().getFirst().getAmount();
    loan.makePayment(installmentAmount, 1);
    boolean result = loan.makePayment(installmentAmount, 1);

    Assertions.assertFalse(result);
    Assertions.assertTrue(loan.getInstallments().getFirst().isPaid());

  }

  // test isDelinquent() method

  @Test
  void loanInvalidWeek_isDelinquent_returnFalse() {
    boolean result = loan.isDelinquent(99);
    Assertions.assertFalse(result);
  }

  @Test
  void loanNoPaymentTillFirstWeek_isDelinquent_returnFalse() {
    boolean result = loan.isDelinquent(1);
    Assertions.assertFalse(result);
  }

  @Test
  void loanNoPaymentTillSecondWeek_isDelinquent_returnTrue() {
    boolean result = loan.isDelinquent(2);
    Assertions.assertTrue(result);
  }

  @Test
  void loanNoPaymentTillSecondWeekAndMakePayment_isDelinquent_returnFalse() {
    boolean result = loan.isDelinquent(2);
    Assertions.assertTrue(result);

    long installmentAmount = loan.getInstallments().getFirst().getAmount();
    loan.makePayment(installmentAmount, 1);
    loan.makePayment(installmentAmount, 2);

    result = loan.isDelinquent(2);
    Assertions.assertFalse(result);
  }

}
