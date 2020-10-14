import java.time.LocalDateTime

case class Operation(date: LocalDateTime, amount: Int, balance: Int)

class BankAccount(operations: Seq[Operation]) {

  def deposit(amount: Int): BankAccount = {
    requirePositive(amount)
    new BankAccount(operations :+ Operation(LocalDateTime.now(), amount, balance + amount))
  }

  def withdraw(amount: Int): BankAccount = {
    requirePositive(amount)
    if (balance < amount) {
      throw new IllegalStateException()
    }
    new BankAccount(operations :+ Operation(LocalDateTime.now(), amount, balance - amount))
  }

  def balance: Int = if (operations.isEmpty) 0 else operations.last.balance

  def printStatement(): List[Operation] = ???

  private def requirePositive(amount: Int): Unit = if (amount < 0) throw new IllegalArgumentException()

}

object BankAccount {
  def apply(): BankAccount = new BankAccount(List())
}
