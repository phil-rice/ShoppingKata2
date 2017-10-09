package org.validoc.cart

import java.io.InputStream

import scala.io.Source

trait ProductParser[P] extends (String => P)

trait FileParser {
  def apply[P](is: InputStream)(implicit parser: ProductParser[P]) =
    Source.fromInputStream(is, "UTF-8").getLines().map(parser).toList
}

object FileParser extends FileParser

