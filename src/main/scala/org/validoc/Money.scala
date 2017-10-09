package org.validoc

trait Money[M] {
  def add(m1: M, m2: M): M

  def zero: M

  def times(m: M, n: Int): M
}

object Money {

  implicit object MoneyForInt extends Money[Int] {
    override def add(m1: Int, m2: Int) = m1 + m2

    override def zero = 0

    override def times(m: Int, n: Int) = m * n
  }


  implicit class MoneyPimper[M](m1: M)(implicit money: Money[M]) {
    def |+|(m2: M) = money.add(m1, m2)

    def |*|(n: Int) = money.times(m1,n)
  }

}
