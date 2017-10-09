package org.validoc.cart

import Money._
import Pricable._

case class LineItem[P, M: Money](product: P, count: Int)

object LineItem {
  def apply[P, M: Money](tuple: (P, Int)): LineItem[P, M] = LineItem[P, M](tuple._1, tuple._2)

  implicit def pricableForLineItem[P, M](implicit pricable: Pricable[P, M]) = new Pricable[LineItem[P, M], M] {
    override def price(p: LineItem[P, M])(implicit money: Money[M]) = p.product.price |*| p.count
  }
}

case class ShoppingBasket[P, M](items: Seq[LineItem[P, M]])

object ShoppingBasket {
  implicit def pricableForShoppingBasket[P, M](implicit pricable: Pricable[P, M]) = new Pricable[ShoppingBasket[P, M], M] {
    override def price(shoppingBasket: ShoppingBasket[P, M])(implicit money: Money[M]) =
      shoppingBasket.items.map(_.price).foldLeft(money.zero)(money.add)
  }
}