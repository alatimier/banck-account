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
    assert(bankAccount.getBalance() == 0)
  }

  test("Should update balance after deposits") {
    // When
    val bankAccount = BankAccount()
      .deposit(100)
      .deposit(200)
      .deposit(50)

    // Then
    assert(bankAccount.getBalance() == 350)
  }

}
