package org.validoc.cart

class ApplesAndOrangesSpec extends UnitSpec {

  behavior of "ApplesAndOranges.product parser"

  def parse = ApplesAndOranges.productParser[Int](65, 25) _

  it should "parse apples" in {
    parse("apple") shouldBe Product("apple", 65)
  }
  it should "parse oranges" in {
    parse("orange") shouldBe Product("orange", 25)

  }
  it should "throw an exception if not an apple or an orange" in {
    intercept[UnrecognisedItemException](parse("")).getMessage shouldBe ""
    intercept[UnrecognisedItemException](parse("other")).getMessage shouldBe "other"
  }

}
