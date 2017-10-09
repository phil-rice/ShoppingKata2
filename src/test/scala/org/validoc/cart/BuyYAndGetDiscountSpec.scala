package org.validoc.cart

import BasketFixture._

class BuyYAndGetDiscountSpec extends UnitSpec {

  val threeOrangesForThePriceOfTwo = new BuyYAndGetDiscount[Int](orange, 3, orange.price)
  val buyTwoApplesGetOneFree = new BuyYAndGetDiscount[Int](apple, 2, apple.price)

  val addOffersToBasket = new AddOffersToBasket[Int](Seq(buyTwoApplesGetOneFree, threeOrangesForThePriceOfTwo))

  behavior of "AddOffersToBasket"
  it should "Not add any offers if the offers don't match" in {
    addOffersToBasket(ShoppingBasket(apple, 1, orange, 1)) shouldBe (ShoppingBasket(apple, 1, orange, 1))
    addOffersToBasket(ShoppingBasket(apple, 1, orange, 2)) shouldBe (ShoppingBasket(apple, 1, orange, 2))
  }

  it should "add suitable offers" in {
    addOffersToBasket(ShoppingBasket(apple, 1, orange, 3)) shouldBe (ShoppingBasket(apple, 1, orange, 3, Product("discount", 25), 1))
    addOffersToBasket(ShoppingBasket(apple, 2, orange, 2)) shouldBe (ShoppingBasket(apple, 2, orange, 2, Product("discount", 65), 1))
    addOffersToBasket(ShoppingBasket(apple, 2, orange, 3)) shouldBe (ShoppingBasket(apple, 2, orange, 3, Product("discount", 65), 1, Product("discount", 25), 1))
  }
}