package org.scassandra.cqlmessages

import org.scalatest.{Matchers, FunSuite}

class ColumnTypesTest extends FunSuite with Matchers {
  test("Serialisation of CqlVarchar") {
    CqlVarchar.writeValue("hello") should equal(Array[Byte](0, 0, 0, 5, 104, 101, 108, 108, 111))
    CqlVarchar.writeValueInCollection("hello") should equal(Array[Byte](0, 5, 104, 101, 108, 108, 111))
    CqlVarchar.writeValue("") should equal(Array[Byte](0, 0, 0, 0))
    CqlVarchar.writeValueInCollection("") should equal(Array[Byte](0, 0))
    CqlVarchar.writeValue(BigDecimal("123.67")) should equal(Array[Byte](0, 0, 0, 6, 49, 50, 51, 46, 54, 55))
    CqlVarchar.writeValueInCollection(BigDecimal("123.67")) should equal(Array[Byte](0, 6, 49, 50, 51, 46, 54, 55))
    CqlVarchar.writeValue(true) should equal(Array[Byte](0, 0, 0, 4, 116, 114, 117, 101))
    CqlVarchar.writeValueInCollection(true) should equal(Array[Byte](0, 4, 116, 114, 117, 101))

    intercept[IllegalArgumentException] {
      CqlVarchar.writeValue(List())
      CqlVarchar.writeValueInCollection(List())
    }
    intercept[IllegalArgumentException] {
      CqlVarchar.writeValue(Map())
      CqlVarchar.writeValueInCollection(Map())
    }
  }

  test("Serialisation of CqlText") {
    CqlText.writeValue("hello") should equal(Array[Byte](0, 0, 0, 5, 104, 101, 108, 108, 111))
    CqlText.writeValueInCollection("hello") should equal(Array[Byte](0, 5, 104, 101, 108, 108, 111))
    CqlText.writeValue("") should equal(Array[Byte](0, 0, 0, 0))
    CqlText.writeValueInCollection("") should equal(Array[Byte](0, 0))
    CqlText.writeValue(BigDecimal("123.67")) should equal(Array[Byte](0, 0, 0, 6, 49, 50, 51, 46, 54, 55))
    CqlText.writeValueInCollection(BigDecimal("123.67")) should equal(Array[Byte](0, 6, 49, 50, 51, 46, 54, 55))
    CqlText.writeValue(true) should equal(Array[Byte](0, 0, 0, 4, 116, 114, 117, 101))
    CqlText.writeValueInCollection(true) should equal(Array[Byte](0, 4, 116, 114, 117, 101))

    intercept[IllegalArgumentException] {
      CqlText.writeValue(List())
      CqlText.writeValueInCollection(List())
    }
    intercept[IllegalArgumentException] {
      CqlText.writeValue(Map())
      CqlText.writeValueInCollection(Map())
    }
  }

  test("Serialisation of CqlAscii") {
    CqlAscii.writeValue("hello") should equal(Array[Byte](0, 0, 0, 5, 104, 101, 108, 108, 111))
    CqlAscii.writeValueInCollection("hello") should equal(Array[Byte](0, 5, 104, 101, 108, 108, 111))
    CqlAscii.writeValue("") should equal(Array[Byte](0, 0, 0, 0))
    CqlAscii.writeValueInCollection("") should equal(Array[Byte](0, 0))
    CqlAscii.writeValue(BigDecimal("123.67")) should equal(Array[Byte](0, 0, 0, 6, 49, 50, 51, 46, 54, 55))
    CqlAscii.writeValueInCollection(BigDecimal("123.67")) should equal(Array[Byte](0, 6, 49, 50, 51, 46, 54, 55))
    CqlAscii.writeValue(true) should equal(Array[Byte](0, 0, 0, 4, 116, 114, 117, 101))
    CqlAscii.writeValueInCollection(true) should equal(Array[Byte](0, 4, 116, 114, 117, 101))

    intercept[IllegalArgumentException] {
      CqlAscii.writeValue(List())
      CqlAscii.writeValueInCollection(List())
    }
    intercept[IllegalArgumentException] {
      CqlAscii.writeValue(Map())
      CqlAscii.writeValueInCollection(Map())
    }
  }

  test("Serialisation of CqlInt") {
    CqlInt.writeValue(BigDecimal("123")) should equal(Array[Byte](0, 0, 0, 4, 0, 0, 0, 123))
    CqlInt.writeValue("123") should equal(Array[Byte](0, 0, 0, 4, 0, 0, 0, 123))

    intercept[IllegalArgumentException] {
      CqlInt.writeValue(BigDecimal("123.67"))
    }
    intercept[IllegalArgumentException] {
      CqlInt.writeValue(BigDecimal("12345678910"))
    }
    intercept[IllegalArgumentException] {
      CqlInt.writeValue("hello")
    }
    intercept[IllegalArgumentException] {
      CqlInt.writeValue(true)
    }
    intercept[IllegalArgumentException] {
      CqlInt.writeValue(false)
    }
    intercept[IllegalArgumentException] {
      CqlInt.writeValue(List())
    }
    intercept[IllegalArgumentException] {
      CqlInt.writeValue(Map())
    }
  }

  test("Serialisation of CqlBigInt") {
    CqlBigint.writeValue(BigDecimal("123000000000")) should equal(Array(0, 0, 0, 8, 0, 0, 0, 28, -93, 95, 14, 0))
    CqlBigint.writeValue("123") should equal(Array[Byte](0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 123))

    intercept[IllegalArgumentException] {
      CqlBigint.writeValue(BigDecimal("123.67"))
    }
    intercept[IllegalArgumentException] {
      CqlBigint.writeValue("hello") should equal(Array[Byte](0, 0, 0, 4, 0, 0, 0, 123))
    }
    intercept[IllegalArgumentException] {
      CqlBigint.writeValue(true) should equal(Array[Byte](0, 0, 0, 4, 0, 0, 0, 123))
    }
    intercept[IllegalArgumentException] {
      CqlBigint.writeValue(false) should equal(Array[Byte](0, 0, 0, 4, 0, 0, 0, 123))
    }
    intercept[IllegalArgumentException] {
      CqlBigint.writeValue(List()) should equal(Array[Byte](0, 0, 0, 4, 0, 0, 0, 123))
    }
    intercept[IllegalArgumentException] {
      CqlBigint.writeValue(Map()) should equal(Array[Byte](0, 0, 0, 4, 0, 0, 0, 123))
    }
  }

  test("Serialisation of CqlVarint") {
    CqlVarint.writeValue(BigDecimal("123000000000")) should equal(Array(0, 0, 0, 5, 28, -93, 95, 14, 0))
    CqlVarint.writeValue("123") should equal(Array(0, 0, 0, 1, 123))

    intercept[IllegalArgumentException] {
      CqlVarint.writeValue(BigDecimal("123.67"))
    }
    intercept[IllegalArgumentException] {
      CqlVarint.writeValue("hello")
    }
    intercept[IllegalArgumentException] {
      CqlVarint.writeValue(true)
    }
    intercept[IllegalArgumentException] {
      CqlVarint.writeValue(false)
    }
    intercept[IllegalArgumentException] {
      CqlVarint.writeValue(List())
    }
    intercept[IllegalArgumentException] {
      CqlVarint.writeValue(Map())
    }
  }

  test("Serialisation of CqlDouble") {
    CqlDouble.writeValue(BigDecimal("123")) should equal(Array(0, 0, 0, 8, 64, 94, -64, 0, 0, 0, 0, 0))
    CqlDouble.writeValue("123") should equal(Array(0, 0, 0, 8, 64, 94, -64, 0, 0, 0, 0, 0))
    CqlDouble.writeValue(BigDecimal("123.67")) should equal(Array(0, 0, 0, 8, 64, 94, -22, -31, 71, -82, 20, 123))

    intercept[IllegalArgumentException] {
      CqlDouble.writeValue("hello")
    }
    intercept[IllegalArgumentException] {
      CqlDouble.writeValue(true)
    }
    intercept[IllegalArgumentException] {
      CqlDouble.writeValue(false)
    }
    intercept[IllegalArgumentException] {
      CqlDouble.writeValue(List())
    }
    intercept[IllegalArgumentException] {
      CqlDouble.writeValue(Map())
    }
  }

  test("Serialisation of CqlFloat") {
    CqlFloat.writeValue(BigDecimal("123")) should equal(Array(0, 0, 0, 4, 66, -10, 0, 0))
    CqlFloat.writeValue("123") should equal(Array(0, 0, 0, 4, 66, -10, 0, 0))
    CqlFloat.writeValue(BigDecimal("123.67")) should equal(Array(0, 0, 0, 4, 66, -9, 87, 10))

    intercept[IllegalArgumentException] {
      CqlFloat.writeValue("hello")
    }
    intercept[IllegalArgumentException] {
      CqlFloat.writeValue(true)
    }
    intercept[IllegalArgumentException] {
      CqlFloat.writeValue(false)
    }
    intercept[IllegalArgumentException] {
      CqlFloat.writeValue(List())
    }
    intercept[IllegalArgumentException] {
      CqlFloat.writeValue(Map())
    }
  }

  test("Serialisation of CqlCounter") {
    CqlCounter.writeValue(BigDecimal("123000000000")) should equal(Array(0, 0, 0, 8, 0, 0, 0, 28, -93, 95, 14, 0))
    CqlCounter.writeValue("123") should equal(Array[Byte](0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 123))

    intercept[IllegalArgumentException] {
      CqlCounter.writeValue(BigDecimal("123.67"))
    }
    intercept[IllegalArgumentException] {
      CqlCounter.writeValue("hello")
    }
    intercept[IllegalArgumentException] {
      CqlCounter.writeValue(true)
    }
    intercept[IllegalArgumentException] {
      CqlCounter.writeValue(false)
    }
    intercept[IllegalArgumentException] {
      CqlCounter.writeValue(List())
    }
    intercept[IllegalArgumentException] {
      CqlCounter.writeValue(Map())
    }
  }

  test("Serialisation of CqlTimeUUID") {
    CqlTimeUUID.writeValue("59ad61d0-c540-11e2-881e-b9e6057626c4") should equal(Array(0, 0, 0, 16, 89, -83, 97, -48, -59, 64, 17, -30, -120, 30, -71, -26, 5, 118, 38, -60))

    intercept[IllegalArgumentException] {
      CqlTimeUUID.writeValue("123")
    }
    intercept[IllegalArgumentException] {
      CqlTimeUUID.writeValue(BigDecimal("123.67"))
    }
    intercept[IllegalArgumentException] {
      CqlTimeUUID.writeValue("hello")
    }
    intercept[IllegalArgumentException] {
      CqlTimeUUID.writeValue(true)
    }
    intercept[IllegalArgumentException] {
      CqlTimeUUID.writeValue(false)
    }
    intercept[IllegalArgumentException] {
      CqlTimeUUID.writeValue(List())
    }
    intercept[IllegalArgumentException] {
      CqlTimeUUID.writeValue(Map())
    }
  }


  test("Serialisation of CqlUUID") {
    CqlUUID.writeValue("59ad61d0-c540-11e2-881e-b9e6057626c4") should equal(Array(0, 0, 0, 16, 89, -83, 97, -48, -59, 64, 17, -30, -120, 30, -71, -26, 5, 118, 38, -60))

    intercept[IllegalArgumentException] {
      CqlUUID.writeValue("123")
    }
    intercept[IllegalArgumentException] {
      CqlUUID.writeValue(BigDecimal("123.67"))
    }
    intercept[IllegalArgumentException] {
      CqlUUID.writeValue("hello")
    }
    intercept[IllegalArgumentException] {
      CqlUUID.writeValue(true)
    }
    intercept[IllegalArgumentException] {
      CqlUUID.writeValue(false)
    }
    intercept[IllegalArgumentException] {
      CqlUUID.writeValue(List())
    }
    intercept[IllegalArgumentException] {
      CqlUUID.writeValue(Map())
    }
  }

  test("Serialisation of CqlBlog") {
    CqlBlob.writeValue("0x01") should equal(Array(0, 0, 0, 1, 1))
    CqlBlob.writeValue("1235") should equal(Array(0, 0, 0, 2, 18, 53))

    intercept[IllegalArgumentException] {
      CqlBlob.writeValue(BigDecimal("123.67"))
    }
    intercept[IllegalArgumentException] {
      CqlBlob.writeValue("hello")
    }
    intercept[IllegalArgumentException] {
      CqlBlob.writeValue(true)
    }
    intercept[IllegalArgumentException] {
      CqlBlob.writeValue(false)
    }
    intercept[IllegalArgumentException] {
      CqlBlob.writeValue(List())
    }
    intercept[IllegalArgumentException] {
      CqlBlob.writeValue(Map())
    }
  }

  test("Serialisation of CqlBoolean") {
    CqlBoolean.writeValue("true") should equal(Array(0, 0, 0, 1, 1))
    CqlBoolean.writeValue("false") should equal(Array(0, 0, 0, 1, 0))
    CqlBoolean.writeValue("TRUE") should equal(Array(0, 0, 0, 1, 1))
    CqlBoolean.writeValue("FALSE") should equal(Array(0, 0, 0, 1, 0))
    CqlBoolean.writeValue(true) should equal(Array(0, 0, 0, 1, 1))
    CqlBoolean.writeValue(false) should equal(Array(0, 0, 0, 1, 0))

    intercept[IllegalArgumentException] {
      CqlBoolean.writeValue("123")
    }
    intercept[IllegalArgumentException] {
      CqlBoolean.writeValue(BigDecimal("123.67"))
    }
    intercept[IllegalArgumentException] {
      CqlBoolean.writeValue("hello")
    }
    intercept[IllegalArgumentException] {
      CqlBoolean.writeValue(List())
    }
    intercept[IllegalArgumentException] {
      CqlUUID.writeValue(Map())
    }
  }


  test("Serialisation of CqlSet - Varchar") {
    val underTest = CqlSet(CqlVarchar)
    underTest.writeValue(List()) should equal(Array[Byte](0,0,0,2,0,0))
    underTest.writeValue(List("one", "two", "three")) should equal(Array[Byte](
        0, 0, 0, 19, // number of bytes
        0, 3,         // number of elements in the set
        0, 3,   111, 110, 101,  // one
        0, 3,   116, 119, 111,  // two
        0, 5,   116, 104, 114, 101, 101 // three

    ))

    intercept[IllegalArgumentException] {
      underTest.writeValue("0x01")
    }
    intercept[IllegalArgumentException] {
      underTest.writeValue("1235") should equal(Array(0, 0, 0, 2, 18, 53))
    }
    intercept[IllegalArgumentException] {
      underTest.writeValue(BigDecimal("123.67"))
    }
    intercept[IllegalArgumentException] {
      underTest.writeValue("hello")
    }
    intercept[IllegalArgumentException] {
      underTest.writeValue(true)
    }
    intercept[IllegalArgumentException] {
      underTest.writeValue(false)
    }
    intercept[IllegalArgumentException] {
      underTest.writeValue(Map())
    }
  }

  test("Serialisation of CqlList - Varchar") {
    val underTest = CqlList(CqlVarchar)
    underTest.writeValue(List()) should equal(Array[Byte](0,0,0,2,0,0))
    underTest.writeValue(List("one", "two", "three")) should equal(Array[Byte](
      0, 0, 0, 19, // number of bytes
      0, 3,         // number of elements in the set
      0, 3,   111, 110, 101,  // one
      0, 3,   116, 119, 111,  // two
      0, 5,   116, 104, 114, 101, 101 // three

    ))

    intercept[IllegalArgumentException] {
      underTest.writeValue("0x01")
    }
    intercept[IllegalArgumentException] {
      underTest.writeValue("1235") should equal(Array(0, 0, 0, 2, 18, 53))
    }
    intercept[IllegalArgumentException] {
      underTest.writeValue(BigDecimal("123.67"))
    }
    intercept[IllegalArgumentException] {
      underTest.writeValue("hello")
    }
    intercept[IllegalArgumentException] {
      underTest.writeValue(true)
    }
    intercept[IllegalArgumentException] {
      underTest.writeValue(false)
    }
    intercept[IllegalArgumentException] {
      underTest.writeValue(Map())
    }
  }
}
