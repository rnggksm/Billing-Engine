package com.amartha.billing.engine.service;

import com.amartha.billing.engine.model.Loan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BillingEngineImplTest {
  private final BillingEngine billingEngine = new BillingEngineImpl();
  private Loan loan;

  @BeforeEach
  void setUp() {
    loan = new Loan();
  }

  @Test
  void givenLoan_withNoPayment_returnOutstanding() {
    Assertions.assertEquals(5_500_000L, billingEngine.getOutstanding(loan));
  }

  @Test
  void givenLoan_makePayment_returnTrue() {
    long installmentAmount = loan.getInstallments().getFirst().getAmount();
    Assertions.assertTrue(billingEngine.makePayment(loan, installmentAmount, 1));
  }

  @Test
  void givenLoan_withTwoConsecutiveMissedPayments_isDelinquent() {
    // week 1: miss, week 2: miss
    Assertions.assertTrue(billingEngine.isDelinquent(loan, 2));
  }

  @Test
  void givenLoan_afterCatchingUp_isNotDelinquent() {
    billingEngine.makePayment(loan, 110_000L, 1);
    billingEngine.makePayment(loan, 110_000L, 2);
    Assertions.assertFalse(billingEngine.isDelinquent(loan, 2));
  }

  @Test
  void givenLoan_withOneMissedPayment_isNotDelinquent() {
    // only 1 consecutive miss — not yet delinquent
    Assertions.assertFalse(billingEngine.isDelinquent(loan, 1));
  }

  @Test
  void givenLoan_outstandingDecreasesAfterPayment() {
    billingEngine.makePayment(loan, 110_000L, 1);
    Assertions.assertEquals(5_390_000L, billingEngine.getOutstanding(loan));
  }

  @Test
  void givenLoan_makePayment_withWrongAmount_returnFalse() {
    Assertions.assertFalse(billingEngine.makePayment(loan, 100_000L, 1));
  }

  @Test
  void givenLoan_makePayment_duplicate_returnFalse() {
    billingEngine.makePayment(loan, 110_000L, 1);
    Assertions.assertFalse(billingEngine.makePayment(loan, 110_000L, 1));
  }

  @Test
  void givenLoan_makePayment_withInvalidWeek_returnFalse() {
    Assertions.assertFalse(billingEngine.makePayment(loan, 110_000L, 0));
    Assertions.assertFalse(billingEngine.makePayment(loan, 110_000L, 51));
  }
}
