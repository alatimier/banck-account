import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter.ofPattern
import java.time.{Clock, LocalDateTime}

import Operation.FORMATTER

class BankAccount(operations: Seq[Operation])(implicit clock: Clock) {
  def deposit(amount: Int): BankAccount = {
    requirePositiveAmount(amount)
    new BankAccount(registerOperation(amount))
  }

  def withdraw(amount: Int): BankAccount = {
    requirePositiveAmount(amount)
    if (balance < amount) throw new IllegalStateException()
    new BankAccount(registerOperation(-amount))
  }

  def balance: Int = if (operations.isEmpty) 0 else balance(operations.last.date)

  def balance(date: LocalDateTime): Int = operations
    .filter(o => o.date.isBefore(date) || o.date.isEqual(date))
    .foldLeft(0)((b, o) => b + o.amount)

  def printStatement(): Seq[String] = operations.map(o => s"${o.toString} | Balance : ${balance(o.date)}")

  private def registerOperation(amount: Int): Seq[Operation] = operations :+ Operation(now(clock), amount)

  private def requirePositiveAmount(amount: Int): Unit = if (amount < 0) throw new IllegalArgumentException()
}

object BankAccount {
  private implicit val clock: Clock = Clock.systemDefaultZone

  def apply(): BankAccount = new BankAccount(List())

  def apply(clock: Clock): BankAccount = new BankAccount(List())(clock)
}

case class Operation(date: LocalDateTime, amount: Int) {
  override def toString: String = s"${amount}€ on ${FORMATTER.format(date)}"
}

object Operation {
  private val FORMATTER = ofPattern("yyyy-MM-dd HH:mm")
}
