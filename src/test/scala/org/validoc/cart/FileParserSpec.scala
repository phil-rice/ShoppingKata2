package org.validoc.cart

class FileParserSpec extends UnitSpec {
  behavior of "FileParser"


  implicit val parser = ApplesAndOranges.productParser(65, 25)
  it should "read a file in, and pass it through the parser" in {
    new FileParser[Product[Int]].apply(getClass.getResourceAsStream("/sample.txt")) shouldBe List(Product("orange", 25), Product("apple", 65), Product("orange", 25))
  }

}
