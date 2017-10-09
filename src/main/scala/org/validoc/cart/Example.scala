package org.validoc.cart

import java.io.StringBufferInputStream
import Pricable._
import Money._

object Example extends App {
  val orange = Product("orange", 25)
  val apple = Product("apple", 65)

  implicit val parser = ApplesAndOranges.productParser(apple.price, orange.price)

  val threeOrangesForThePriceOfTwo = new BuyNForYOffer[Int](orange, 3, orange.price)
  val buyTwoApplesGetOneFree = new BuyNForYOffer[Int](apple, 2, apple.price)

  val basket = LoadShoppingBasket[Int].apply(getClass.getResourceAsStream("/sample.txt"))
  val addOffersToBasket = new AddOffersToBasket[Int](Seq(buyTwoApplesGetOneFree, threeOrangesForThePriceOfTwo))
  val finalBasket = addOffersToBasket(basket)
  println(s"Final basket is $finalBasket")
  println(s"Price is ${finalBasket.price}")

}
