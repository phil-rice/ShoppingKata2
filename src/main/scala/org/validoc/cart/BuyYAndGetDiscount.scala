package org.validoc.cart

import Money._

class BuyYAndGetDiscount[M: Money](product: Product[M], n: Int, discountedPrice: M) extends (LineItem[Product[M], M] => Option[LineItem[Product[M], M]]) {

  override def apply(lineItem: LineItem[Product[M], M]): Option[LineItem[Product[M], M]] = {
    val timesRelevant = lineItem.count / n
    if (product == lineItem.product && timesRelevant > 0) Some(LineItem(Product[M]("discount", discountedPrice), timesRelevant)) else None
  }
}


class AddOffersToBasket[M: Money](offers: Seq[BuyYAndGetDiscount[M]]) extends (ShoppingBasket[Product[M], M] => ShoppingBasket[Product[M], M]) {
  override def apply(basket: ShoppingBasket[Product[M], M]): ShoppingBasket[Product[M], M] = {
    val addedItems =
      for {
        offer <- offers
        lineItem <- basket.items
      } yield offer(lineItem)
    ShoppingBasket[Product[M], M](basket.items ++ addedItems.flatten)
  }
}