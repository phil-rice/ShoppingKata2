package org.validoc.money


case class LineItem[P, M: Money](product: P, count: Int)

case class ShoppingBasket[P, M](items: Seq[LineItem[P, M]])

