package org.validoc.cart

import Pricable._

object BasketFixture {
  val orange = Product("orange", 25)
  val apple = Product("apple", 65)

  implicit def tupleToLineItem(t: (Product[Int], Int)) = LineItem(t._1, t._2)

  implicit def lineItemToListOfLineItems(t: (Product[Int], Int)) = List(LineItem(t._1, t._2))

  implicit def tupleToListOfLineItems(t: (Product[Int], Int, Product[Int], Int)) = t match {
    case ((p1, n1, p2, n2)) => List(LineItem(p1, n1), LineItem(p2, n2))
  }

}

import BasketFixture._

class LineItemSpec extends UnitSpec {
  behavior of "LineItem"

  it should "be pricable, with the natural definition: the price of the product inside it, times the count of the number of items" in {
    LineItem(orange, 0).price shouldBe 0
    LineItem(orange, 1).price shouldBe 25
    LineItem(orange, 3).price shouldBe 75
  }
}

class ShoppingBasketSpec extends UnitSpec {

  behavior of "Shopping Basket"

  it should "add up the prices of the things inside it for single items" in {
    ShoppingBasket(orange, 1).price shouldBe 25
    ShoppingBasket(apple, 1).price shouldBe 65
  }

  it should "add up the prices of the things inside it for multiple instances of same item " in {
    ShoppingBasket(orange, 2).price shouldBe 50
    ShoppingBasket(apple, 2).price shouldBe 130
  }

  it should "add up prices of things inside it for multiple instances of different times" in {
    ShoppingBasket(orange, 1, apple, 1).price shouldBe 90
    ShoppingBasket(orange, 1, apple, 2).price shouldBe 155
  }

}
