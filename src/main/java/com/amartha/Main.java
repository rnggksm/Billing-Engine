package com.amartha;

import com.amartha.billing.engine.model.Loan;
import com.amartha.billing.engine.service.BillingEngine;
import com.amartha.billing.engine.service.BillingEngineImpl;

public class Main {
  public static void main(String[] args) {
    // Simulation:
    Loan loan1 = new Loan();
    BillingEngine billingEngine = new BillingEngineImpl();

    // show initial outstanding
    System.out.println("Week 0 - Outstanding: " + billingEngine.getOutstanding(loan1)); // 5,500,000

    // Week 1: pay on time
    billingEngine.makePayment(loan1, 110_000L, 1);
    // Week 2: skip payment
    // Week 3: skip payment
    System.out.println("Week 3 - Delinquent: " + billingEngine.isDelinquent(loan1, 3)); // true

    // repayment week 2 & 3
    billingEngine.makePayment(loan1, 110_000L, 2);
    billingEngine.makePayment(loan1, 110_000L, 3);

    System.out.println("Week 3 - Delinquent: " + billingEngine.isDelinquent(loan1, 3)); // false
    System.out.println("Week 3 - Outstanding: " + billingEngine.getOutstanding(loan1)); // 5,170,000

    // show loan schedule
    System.out.println("\n--- Loan Schedule ---");
    loan1.getInstallments().forEach(i ->
        System.out.println("Week " + i.getWeekNumber() + ": " + i.getAmount() + " | Paid: " + i.isPaid())
    );
  }
}
