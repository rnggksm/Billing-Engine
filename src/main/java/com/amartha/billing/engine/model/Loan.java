package com.amartha.billing.engine.model;

public class Loan {
  private final int loanId;
  private final double principal;
  private final double interestRate;
  private int currentWeek;

  public Loan(int loanId, double principal, double interestRate) {
    this.loanId = loanId;
    this.principal = principal;
    this.interestRate = interestRate;
    this.currentWeek = 0;
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

  public int getCurrentWeek() {
    return currentWeek;
  }

  public void setCurrentWeek(int currentWeek) {
    this.currentWeek = currentWeek;
  }
}
