import java.time.LocalDateTime

case class Operation(date: LocalDateTime, amount: Int, balance: Int)

class BankAccount(operations: Seq[Operation]) {

  def deposit(amount: Int): BankAccount = {
    if (amount < 0) {
      throw new IllegalArgumentException()
    }
    new BankAccount(operations :+ Operation(LocalDateTime.now(), amount, getBalance() + amount))
  }

  def withdraw(amount: Int): BankAccount = ???

  def getBalance(): Int = if (operations.isEmpty) 0 else operations.last.balance

  def printStatement(): List[Operation] = ???

}

object BankAccount {
  def apply(): BankAccount = new BankAccount(List())
}
