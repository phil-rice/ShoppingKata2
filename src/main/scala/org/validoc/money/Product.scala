package org.validoc.money

case class Product[M: Money](name: String, price: M)

object Product {
  implicit def pricableForProduct[M] = new Pricable[Product[M], M] {
    override def price(p: Product[M])(implicit money: Money[M]) = p.price
  }
}
