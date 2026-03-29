package com.amartha.billing.engine.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Loan {
  private static int idCounter = 1;
  private static final long PRINCIPAL = 5_000_000;
  private static final double INTEREST_RATE = 0.10;
  private static final int TOTAL_WEEKS = 50;

  private final int loanId;
  private final long principal;
  private final double interestRate;
  private final List<Installment> installments;

  public Loan() {
    this.loanId = idCounter++;
    this.principal = PRINCIPAL;
    this.interestRate = INTEREST_RATE;

    long totalAmount = (long) (principal * (1 + INTEREST_RATE));
    long weeklyAmount = totalAmount / TOTAL_WEEKS;

    this.installments = new ArrayList<>();
    for (int week = 1; week <= TOTAL_WEEKS; week++) {
      installments.add(new Installment(week, weeklyAmount));
    }
  }

  public int getLoanId() {
    return loanId;
  }

  public long getPrincipal() {
    return principal;
  }

  public double getInterestRate() {
    return interestRate;
  }

  public List<Installment> getInstallments() {
    return Collections.unmodifiableList(installments);
  }

  public long getOutstanding() {
    return installments.stream().filter(i -> !i.isPaid()).mapToLong(Installment::getAmount).sum();
  }

  /**
   * Checks whether the borrower is delinquent as of the end of {@code currentWeek}.
   * A borrower is considered delinquent if they have missed 2 or more consecutive
   * weekly payments up to and including {@code currentWeek}.
   *
   * @param currentWeek the week number to evaluate (1-based), and the value (currentWeek) itself included
   * @return true if 2 or more consecutive installments are unpaid
   */
  public boolean isDelinquent(int currentWeek) {
    // reject invalid week input
    if (isWeekInvalid(currentWeek)) {
      return false;
    }

    int delinquentCount = 0;

    for (int i = 0; i < currentWeek; i++) {
      Installment currentEntry = installments.get(i);

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

  public boolean makePayment(long amount, int targetWeek) {
    // reject invalid week input
    if (isWeekInvalid(targetWeek)) {
      return false;
    }

    Installment entry = installments.get(targetWeek - 1);

    // reject if the payment amount is different with weekly amount
    if (amount != entry.getAmount()) {
      return false;
    }

    // reject for multiple payment in the same week
    if (entry.isPaid()) {
      return false;
    }

    entry.markPaid();
    return true;
  }

  private boolean isWeekInvalid(int currentWeek) {
    return currentWeek < 1 || currentWeek > installments.size();
  }
}
