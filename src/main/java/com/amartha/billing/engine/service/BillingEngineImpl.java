package com.amartha.billing.engine.service;


import com.amartha.billing.engine.model.Loan;
import com.amartha.billing.engine.model.LoanScheduleEntry;

import java.util.List;

public class BillingEngineImpl implements BillingEngine{
  @Override
  public double getOutstanding(Loan loan) {
    List<LoanScheduleEntry> unpaid = loan.getSchedule().stream().filter(x -> !x.isPaid()).toList();

    double outStanding = 0;
    for (LoanScheduleEntry loanScheduleEntry : unpaid) {
      outStanding += loanScheduleEntry.getAmount();
    }

    return outStanding;
  }

  @Override
  public boolean isDelinquent(Loan loan, int currentWeek) {
    int delinquentCount = 2;

    for (int i = 0; i < currentWeek; i++) {
      LoanScheduleEntry currentEntry = loan.getSchedule().get(i);

      if (!currentEntry.isPaid()) {
        delinquentCount--;
      }

      if (delinquentCount == 0) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean makePayment(Loan loan, double amount, int currentWeek) {
    LoanScheduleEntry entry = loan.getSchedule().get(currentWeek - 1);

    if (amount != entry.getAmount()) {
      return false;
    }

    entry.setPaid(true);
    return true;
  }
}
