class BankAccountTest extends org.scalatest.funsuite.AnyFunSuite {

  test("Should not depose negative amount") {
    assertThrows[IllegalArgumentException] {
      BankAccount().deposit(-1)
    }
  }

  test("Should be empty at creation") {
    // When
    val bankAccount = BankAccount()

    // Then
    assert(bankAccount.balance == 0)
  }

  test("Should update balance after deposits") {
    // When
    val bankAccount = BankAccount()
      .deposit(100)
      .deposit(200)
      .deposit(50)

    // Then
    assert(bankAccount.balance == 350)
  }

  test("Should not withdraw negative amount") {
    assertThrows[IllegalArgumentException] {
      BankAccount().withdraw(-1)
    }
  }

  test("Should not have negative balance") {
    assertThrows[IllegalStateException] {
      BankAccount().withdraw(100)
    }
  }

  test("Should update balance after withdrawal") {
    // When
    val bankAccount = BankAccount()
      .deposit(1000)
      .withdraw(100)
      .withdraw(200)
      .withdraw(50)

    // Then
    assert(bankAccount.balance == 650)
  }

}
