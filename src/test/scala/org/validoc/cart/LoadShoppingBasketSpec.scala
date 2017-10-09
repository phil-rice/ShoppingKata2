package org.validoc.cart

class LoadShoppingBasketSpec extends UnitSpec {

  import BasketFixture._

  behavior of "LoadShoppingBasket"

  implicit val parser = ApplesAndOranges.productParser(65, 25)

  it should "load products from a file" in {
    LoadShoppingBasket[Int].apply(getClass.getResourceAsStream("/sample.txt")) shouldBe ShoppingBasket(apple, 1, orange, 2)
  }
}
