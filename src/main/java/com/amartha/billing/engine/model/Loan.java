package com.amartha.billing.engine.model;

import java.util.List;

public class Loan {
  private final int loanId;
  private final double principal;
  private final double interestRate;
  private final List<LoanScheduleEntry> schedule;

  public Loan(int loanId, double principal, double interestRate, List<LoanScheduleEntry> schedule) {
    this.loanId = loanId;
    this.principal = principal;
    this.interestRate = interestRate;
    this.schedule = schedule;
  }

  public int getLoanId() {
    return loanId;
  }

  public double getPrincipal() {
    return principal;
  }

  public double getInterestRate() {
    return interestRate;
  }

  public List<LoanScheduleEntry> getSchedule() {
    return schedule;
  }
}
