# 📚 Bayt Al Hekma — Library Management System

A console-based Library Management System built with Java for managing library items, members, borrowing, returns, fines, renewals, payments, and invoices.

## 📖 About the Project

**Bayt Al Hekma** is a small community library management system.

The system manages:

- Library books, magazines, and DVDs
- Library members and membership types
- Borrowing and returning items
- Item availability and lost items
- Loan renewals
- Late-return fines
- Membership waivers
- Administrative charges
- Outstanding balances
- Payments using Visa or Wallet
- Payment history and invoices
- Library reports

The project is implemented as a console application and focuses on applying **Object-Oriented Programming (OOP)** principles and design patterns.

## ✨ Features

### 👤 Member Management

Members have:

- Name
- Unique Membership ID
- Membership Type
- Outstanding Balance
- Currently Borrowed Items

Supported membership types:

- `STUDENT`
- `STAFF`
- `PUBLIC`

Each membership type has its own fine waiver rate.

### 📚 Library Items

The system supports three types of library items:

| Item | Loan Period | Fine | Renewal |
|------|-------------|------|----------|
| Book | 14 days | 5 EGP/day | Up to 2 times |
| Magazine | 7 days | 3 EGP/day, max 30 EGP | Once |
| DVD | 3 days | 15 EGP/day | Not allowed |

All items share the same parent type:

```text
LibraryItem
├── Book
├── Magazine
└── DVD
```

### 📌 Item Status

Every item has one of three statuses:

```text
AVAILABLE
ON_LOAN
LOST
```

A lost item cannot be borrowed.

## 🔄 Borrowing

A member can borrow an item only when:

- The item exists
- The item is `AVAILABLE`
- The member exists
- The member holds fewer than 3 items
- The member's balance does not exceed 100 EGP

When an item is borrowed:

- Its status becomes `ON_LOAN`
- The borrower's membership ID is stored
- The member's held-item count increases
- The renewal counter is reset

## 🔙 Returning Items

When an item is returned, the system:

1. Checks that the item is currently on loan.
2. Validates the overdue days.
3. Finds the borrower using the stored Membership ID.
4. Calculates the base fine.
5. Applies the membership waiver.
6. Adds the administrative charge when applicable.
7. Charges the member.
8. Removes the item from the member's borrowed items.
9. Resets the item's loan information.

Returning an item also resets its renewal count.

## 💰 Fines

### Book

```text
5 EGP × overdue days
```

No maximum fine.

### Magazine

```text
3 EGP × overdue days
```

Maximum fine: `30 EGP`.

### DVD

```text
15 EGP × overdue days
```

No maximum fine.

The membership waiver applies to the base fine. The administrative charge is not waived.

## 💳 Payment System

The project uses the **Strategy Design Pattern** for payments.

```text
PaymentStrategy
├── Visa
└── Wallet
```

The `Payment` class delegates the payment operation to the selected strategy.

Supported payment methods:

- VISA
- WALLET

Payment details are collected in `Main`, while the payment strategies validate the supplied details.

The system rejects:

- Non-positive payments
- Payments greater than the member's balance
- Invalid Visa numbers
- Invalid Wallet numbers

## 🧾 Invoices & Payment History

Successful payments are recorded in an invoice history.

Each payment record contains:

- Payment ID
- Payment amount
- Remaining balance
- Payment type
- Payment date

A member can make multiple partial payments until the outstanding balance is cleared.

## 🔄 Renewals

Renewal capability is represented using the `Renewable` interface.

Books and magazines implement `Renewable`. DVDs do not.

Renewal is allowed only when:

- The item is currently `ON_LOAN`
- The item supports renewal
- The renewal limit has not been reached

## 📊 Library Report

The library report displays:

- Library name
- Catalogue size
- Number of items ever added
- Number of items currently on loan
- Loan rate
- Total outstanding balance
- Projected fines for a 5-day overdue period

## 🖥️ Available Operations

```text
1. View catalogue
2. Register member
3. Borrow item
4. Return item
5. Renew loan
6. Search item by ID
7. View items by status
8. Pay outstanding fines
9. View all members
10. Library report
11. Mark item as lost
0. Exit
```

## 🏗️ Project Structure

```text
src/
├── enums/
│   ├── ItemStatus.java
│   ├── MembershipType.java
│   └── PaymentType.java
│
├── interfaces/
│   └── Renewable.java
│
├── models/
│   ├── Library.java
│   ├── LibraryItem.java
│   ├── Book.java
│   ├── Magazine.java
│   ├── DVD.java
│   ├── Member.java
│   ├── Invoice.java
│   └── PaymentDetails.java
│
├── payments/
│   ├── Payment.java
│   ├── PaymentStrategy.java
│   ├── Visa.java
│   └── Wallet.java
│
└── Main.java
```

## 🧠 OOP Concepts Used

### Encapsulation

Member balance and borrowed-item information are controlled through class methods.

### Inheritance

```text
LibraryItem
├── Book
├── Magazine
└── DVD
```

### Abstraction

`LibraryItem` is an abstract class containing common behavior for all library items.

### Polymorphism

Different item types can be stored together using:

```java
List<LibraryItem>
```

### Interfaces

The `Renewable` interface represents the ability of an item to be renewed.

### Enums

Enums are used for:

- Item status
- Membership type
- Payment type

## 🎯 Design Patterns

### Strategy Pattern

The payment system uses the Strategy Pattern:

```text
             PaymentStrategy
             /             \
          Visa            Wallet
             \             /
                  Payment
```

This allows different payment methods to be added without changing the core payment logic.

## 🧪 Testing

The application can be tested using an input file instead of manually entering values.

```bash
java -cp out Main < testInput.txt
```

This allows repeatable testing of:

- Borrowing
- Returning
- Fines
- Renewals
- Payments
- Partial payments
- Invalid inputs
- Lost items
- Reports
- Payment invoices

## 🚀 Running the Project

### Compile

From the project root:

```bash
rm -rf out
mkdir out
find src -name "*.java" -print0 | xargs -0 javac -d out
```

### Run

```bash
java -cp out Main
```

### Run With Test Input

```bash
java -cp out Main < testInput.txt
```

## 🛠️ Technologies

- Java
- Object-Oriented Programming
- Java Collections
- Enums
- Interfaces
- Abstract Classes
- Strategy Design Pattern
- Java `LocalDate`
- Console I/O

## 🎯 Project Goal

The main goal of this project is to build a complete console-based library management system while practicing:

- Clean OOP design
- Encapsulation
- Inheritance
- Abstraction
- Polymorphism
- Interfaces
- Design Patterns
- Input validation
- Business-rule implementation
- Separation of responsibilities
