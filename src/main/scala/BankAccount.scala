import java.util.Date

case class Operation(date: Date, amount: Int, balance: Int)

class BankAccount {

  def deposit(amount: Int): Unit = ???

  def withdraw(amount: Int): Unit = ???

  def printStatement(): Seq[Operation] = ???

} 
