package com.amartha.billing.engine.service;


import com.amartha.billing.engine.model.Loan;

public class BillingEngineImpl implements BillingEngine {
  @Override
  public long getOutstanding(Loan loan) {
    return loan.getOutstanding();
  }

  @Override
  public boolean isDelinquent(Loan loan, int currentWeek) {
    return loan.isDelinquent(currentWeek);
  }

  @Override
  public boolean makePayment(Loan loan, long amount, int targetWeek) {
    return loan.makePayment(amount, targetWeek);
  }
}
