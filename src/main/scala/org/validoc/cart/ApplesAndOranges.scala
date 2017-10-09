package org.validoc.cart

class UnrecognisedItemException(name: String) extends Exception(name)



object ApplesAndOranges {
   def productParser[M: Money](priceForApples: M, priceForOranges: M) = new ProductParser[Product[M]] {
    def apply(s: String) = s match {
      case "orange" => Product("orange", priceForOranges)
      case "apple" => Product("apple", priceForApples)
      case _ => throw new UnrecognisedItemException(s)
    }
  }
}