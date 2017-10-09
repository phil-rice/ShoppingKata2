package org.validoc.cart

object Arrows {

  implicit class AnyPimper[T](t: T) {
    def ~>[T1](fn: T => T1): T1 = fn(t)
  }

  implicit class SeqPimper[T](seq: Seq[T]) {
    def ~~>[T1](fn: T => T1): Seq[T1] = seq.map(fn)
  }


}

