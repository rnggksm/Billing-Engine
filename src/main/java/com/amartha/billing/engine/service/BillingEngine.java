package com.amartha.billing.engine.service;


import com.amartha.billing.engine.model.Loan;

public interface BillingEngine {
  double getOutstanding(Loan loan);
  boolean isDelinquent(Loan loan, int currentWeek);
  boolean makePayment(Loan loan, double amount,  int currentWeek);
}
