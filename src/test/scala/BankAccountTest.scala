import java.time.Instant.parse
import java.time.{Clock, ZoneId}

import org.mockito.MockitoSugar

class BankAccountTest extends org.scalatest.funsuite.AnyFunSuite with MockitoSugar {

  test("Should not deposit negative amount") {
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
    val bankAccount = BankAccount() deposit 100 deposit 200 deposit 50

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
    val bankAccount = BankAccount() deposit 1000 withdraw 100 withdraw 200 withdraw 50

    // Then
    assert(bankAccount.balance == 650)
  }

  test("Should print statement with all operations") {
    // Given
    val clock: Clock = mock[Clock]
    when(clock.getZone).thenReturn(ZoneId.of("UTC"))
    when(clock.instant()).thenReturn(
      parse("2020-01-01T00:00:00.00Z"),
      parse("2020-01-02T00:00:00.00Z")
    )

    val bankAccount = BankAccount(clock) deposit 1000 withdraw 100

    // When
    val statement = bankAccount.printStatement()

    // Then
    assert(statement == Seq(
      "1000€ on 2020-01-01 00:00 | Balance : 1000",
      "-100€ on 2020-01-02 00:00 | Balance : 900",
    ))
  }

}
