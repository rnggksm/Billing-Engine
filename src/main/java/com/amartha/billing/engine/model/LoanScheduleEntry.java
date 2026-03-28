package com.amartha.billing.engine.model;

public class LoanScheduleEntry {
  private final int weekNumber;
  private final double amount;
  private boolean paid;

  public LoanScheduleEntry(int weekNumber, double amount) {
    this.weekNumber = weekNumber;
    this.amount = amount;
    this.paid = false;
  }

  public int getWeekNumber() {
    return weekNumber;
  }

  public double getAmount() {
    return amount;
  }

  public boolean isPaid() {
    return paid;
  }

  public void setPaid(boolean paid) {
    this.paid = paid;
  }
}
