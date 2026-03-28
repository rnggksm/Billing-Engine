package com.amartha.billing.engine.service;


import com.amartha.billing.engine.model.Loan;
import com.amartha.billing.engine.model.LoanScheduleEntry;

import java.util.List;

public class BillingEngineImpl implements BillingEngine{
  @Override
  public long getOutstanding(Loan loan) {
    List<LoanScheduleEntry> unpaid = loan.getSchedule().stream().filter(x -> !x.isPaid()).toList();

    long outStanding = 0;
    for (LoanScheduleEntry loanScheduleEntry : unpaid) {
      outStanding += loanScheduleEntry.getAmount();
    }

    return outStanding;
  }

  @Override
  public boolean isDelinquent(Loan loan, int currentWeek) {
    int delinquentCount = 0;

    for (int i = 0; i < currentWeek; i++) {
      LoanScheduleEntry currentEntry = loan.getSchedule().get(i);

      if (currentEntry.isPaid()) {
        delinquentCount = 0;
        continue;
      }

      delinquentCount++;
      if (delinquentCount == 2) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean makePayment(Loan loan, long amount, int currentWeek) {
    LoanScheduleEntry entry = loan.getSchedule().get(currentWeek - 1);

    if (amount != entry.getAmount()) {
      return false;
    }

    entry.setPaid(true);
    return true;
  }
}
