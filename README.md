# Billing Engine

A billing engine implementation for a loan management system.

## Features

- **Loan Schedule**: Generates a 50-week repayment schedule for a loan
- **Outstanding Balance**: Tracks the remaining unpaid amount at any point in time
- **Delinquency Check**: Detects borrowers who have missed 2 or more consecutive weekly payments
- **Payment Processing**: Accepts exact weekly payments and rejects invalid ones

## Loan Terms

| Parameter | Value |
|---|---|
| Principal | Rp 5,000,000 |
| Interest Rate | 10% per annum (flat) |
| Total Amount | Rp 5,500,000 |
| Duration | 50 weeks |
| Weekly Payment | Rp 110,000 |

## Prerequisites

- Java 21
- Gradle (or use the included Gradle wrapper `./gradlew`)

## How to Run

**Run the simulation:**
```bash
./gradlew run
```

Or via IntelliJ: run `Main.java` directly.

**Run tests:**
```bash
./gradlew test
```
