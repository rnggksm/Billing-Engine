package com.amartha.billing.engine.factory;

import com.amartha.billing.engine.model.Loan;
import com.amartha.billing.engine.model.LoanScheduleEntry;

import java.util.ArrayList;
import java.util.List;

public class LoanFactory {
  private static int idCounter = 0;
  private static final double PRINCIPAL = 5_000_000;
  private static final double INTEREST_RATE = 0.10;
  private static final int TOTAL_WEEKS = 50;

  public static Loan createLoan() {
    idCounter++;

    List<LoanScheduleEntry> schedule = new ArrayList<>();
    for (int week = 1; week <= TOTAL_WEEKS; week++) {
      double amount = PRINCIPAL * (1 + INTEREST_RATE) / TOTAL_WEEKS;

      schedule.add(new LoanScheduleEntry(week, amount));
    }

    return new Loan(idCounter, PRINCIPAL, INTEREST_RATE, schedule);
  }
}
