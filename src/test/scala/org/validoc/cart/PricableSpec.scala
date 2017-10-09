package org.validoc.cart

import scala.reflect.ClassTag
import Pricable._

abstract class AbstractPriceableSpec[P: ClassTag](implicit pricable: Pricable[P, Int]) extends UnitSpec {

  def pricableWithMoneyOne: P

  def pricableWithMoneyTwo: P

  behavior of s"Pricable for ${implicitly[ClassTag[P]].runtimeClass.getSimpleName}"

  it should "allow the price to be extracted from it" in {
    pricableWithMoneyOne.price shouldBe 1
    pricableWithMoneyTwo.price shouldBe 2
  }


}


class ProductPriceableSpec extends AbstractPriceableSpec[Product[Int]] {
  override def pricableWithMoneyOne = Product("apples", 1)

  override def pricableWithMoneyTwo = Product("oranges", 2)
}