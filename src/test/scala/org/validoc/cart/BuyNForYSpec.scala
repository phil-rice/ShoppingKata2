package org.validoc.cart

class BuyNForYSpec extends UnitSpec {

  import Pricable._
  import Money._
  import BasketFixture._

  behavior of "three oranges for the price of two"

  val threeOrangesForThePriceOfTwo = new BuyNForYOffer[Int](orange, 3, orange.price)

  it should "return None when applied to a line item and the product doesn't match" in {
    threeOrangesForThePriceOfTwo(LineItem(apple, 1)) shouldBe None
    threeOrangesForThePriceOfTwo(LineItem(apple, 2)) shouldBe None
  }

  it should "return None if there aren't enough products to trigger the offer" in {
    threeOrangesForThePriceOfTwo(LineItem(orange, 1)) shouldBe None
    threeOrangesForThePriceOfTwo(LineItem(orange, 2)) shouldBe None
  }

  it should "return a discount if the offer is triggered" in {
    threeOrangesForThePriceOfTwo(LineItem(orange, 3)) shouldBe Some(LineItem(Product("discount", 25), 1))
    threeOrangesForThePriceOfTwo(LineItem(orange, 5)) shouldBe Some(LineItem(Product("discount", 25), 1))
    threeOrangesForThePriceOfTwo(LineItem(orange, 6)) shouldBe Some(LineItem(Product("discount", 25), 2))
  }

  behavior of "buy two apples get one free"

  val buyTwoApplesGetOneFree = new BuyNForYOffer[Int](apple, 2, apple.price)
  it should "return None when applied to a line item and the product doesn't match" in {
    buyTwoApplesGetOneFree(LineItem(orange, 1)) shouldBe None
    buyTwoApplesGetOneFree(LineItem(orange, 2)) shouldBe None
  }

  it should "return None if there aren't enough products to trigger the offer" in {
    buyTwoApplesGetOneFree(LineItem(apple, 1)) shouldBe None
  }

  it should "return a discount if the offer is triggered" in {
    buyTwoApplesGetOneFree(LineItem(apple, 2)) shouldBe Some(LineItem(Product("discount", 65), 1))
    buyTwoApplesGetOneFree(LineItem(apple, 3)) shouldBe Some(LineItem(Product("discount", 65), 1))
    buyTwoApplesGetOneFree(LineItem(apple, 4)) shouldBe Some(LineItem(Product("discount", 65), 2))
  }
}
