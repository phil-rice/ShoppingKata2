package org.validoc.cart

case class Product[M: Money](name: String, price: M)

object Product {
  implicit def pricableForProduct[M] = new Pricable[Product[M], M] {
    override def price(p: Product[M])(implicit money: Money[M]) = p.price
  }

  implicit def OrderedForProduct[M] = new Ordering[Product[M]] {
    override def compare(x: Product[M], y: Product[M]) = x.name.compare(y.name)
  }
}
