package org.validoc.cart

import org.validoc
import Money._

import scala.reflect.ClassTag

abstract class AbstractMoneySpec[M: Money : ClassTag] extends UnitSpec {

  def moneyZero: M

  def moneyTwo: M

  def moneyThree: M

  def moneyFour: M

  def moneyFive: M

  behavior of s"Money for ${implicitly[ClassTag[M]].runtimeClass.getSimpleName}"

  it should "have a zero" in {
    moneyTwo |+| moneyZero shouldBe moneyTwo
    moneyThree |+| moneyZero shouldBe moneyThree
    moneyZero |+| moneyTwo shouldBe moneyTwo
    moneyZero |+| moneyThree shouldBe moneyThree
  }
  it should "implement addition" in {
    moneyTwo |+| moneyTwo shouldBe moneyFour
    moneyTwo |+| moneyThree shouldBe moneyFive
    moneyThree |+| moneyTwo shouldBe moneyFive
  }

  it should "implement 'multiply by int'" in {
    moneyTwo |*| 1 shouldBe moneyTwo
    moneyTwo |*| 2 shouldBe moneyFour
  }

}

class IntMoney extends AbstractMoneySpec[Int]{
  override def moneyZero = 0

  override def moneyTwo = 2

  override def moneyThree = 3

  override def moneyFour = 4

  override def moneyFive = 5
}
