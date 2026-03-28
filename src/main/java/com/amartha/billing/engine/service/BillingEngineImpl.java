package com.amartha.billing.engine.service;


import com.amartha.billing.engine.model.Loan;

public class BillingEngineImpl implements BillingEngine{
  @Override
  public double getOutstanding(Loan loan) {
    return 0;
  }

  @Override
  public boolean isDelinquent(Loan loan, int currentWeek) {
    return false;
  }

  @Override
  public boolean makePayment(Loan loan, double amount, int currentWeek) {
    return false;
  }
}
