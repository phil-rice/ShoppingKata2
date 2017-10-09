package org.validoc.cart

import java.io.InputStream

import scala.io.Source

trait ProductParser[P] extends (String => P)

class FileParser[P](implicit parser: ProductParser[P]) extends (InputStream => List[P]) {
  def apply(is: InputStream): List[P] =
    Source.fromInputStream(is, "UTF-8").getLines().map(parser).toList
}


object LoadShoppingBasket {
  def apply[M: Money](implicit productParser: ProductParser[Product[M]]) = new LoadShoppingBasket[M](new FileParser[Product[M]])
}

class LoadShoppingBasket[M: Money](fileParser: InputStream => List[Product[M]])(implicit productParser: ProductParser[Product[M]]) {

  import Arrows._

  private def aggregate = { seq: List[Product[M]] => seq.groupBy(x => x).toList.sortBy(_._1).map { case (k, v) => (k, v.size) } }

  private def toLineItems = { seq: List[(Product[M], Int)] => seq.map { case (product, count) => LineItem[Product[M], M](product, count) } }

  def apply(is: InputStream): ShoppingBasket[Product[M], M] =
    is ~> fileParser ~> aggregate ~> toLineItems ~> ShoppingBasket.apply

}