package org.validoc.money

trait Pricable[P, M] {
  def price(p: P)(implicit money: Money[M]): M
}

object Pricable {

  implicit class PricablePimper[P, M](p: P)(implicit pricable: Pricable[P, M]) {
    def price(implicit money: Money[M]) = pricable.price(p)
  }

}