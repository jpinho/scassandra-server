package org.scassandra.server.priming.prepared

import org.scalatest.{BeforeAndAfter, Matchers, FunSuite}
import org.scassandra.server.cqlmessages.{TWO, ONE}
import org.scassandra.server.cqlmessages.types.CqlText
import org.scassandra.server.priming.{WriteRequestTimeoutResult, ReadRequestTimeoutResult, SuccessResult}
import org.scassandra.server.priming.json.{WriteTimeout, ReadTimeout, Success}
import org.scassandra.server.priming.query.{Prime, PrimeMatch}

import org.scalatest.OptionValues._

// todo generalise all the prepared stores, very little difference
class PrimePreparedMultiStoreTest extends FunSuite with Matchers with BeforeAndAfter {

  var underTest: PrimePreparedMultiStore = _

  before {
    underTest = new PrimePreparedMultiStore
  }

  test("Match on variable type - success") {
    val variableTypes = List(CqlText)
    val thenDo: ThenPreparedMulti = ThenPreparedMulti(Some(variableTypes), List(
      Outcome(Criteria(List(VariableMatch(Some("Chris")))), Action(Some(List()), result = Some(Success)))))
    val queryText = "Some query"
    underTest.record(PrimePreparedMulti(WhenPrepared(Some(queryText)), thenDo))

    val preparedPrime = underTest.findPrime(PrimeMatch(queryText, ONE))

    preparedPrime.value.variableTypes should equal(variableTypes)
    preparedPrime.value.getPrime(List(Some("Chris"))) should equal(Prime(rows = List(), result = SuccessResult))
  }

  test("Match on variable type - failure") {
    val variableTypes = List(CqlText)
    val thenDo: ThenPreparedMulti = ThenPreparedMulti(Some(variableTypes), List(
      Outcome(Criteria(List(VariableMatch(Some("Chris")))), Action(Some(List()), result = Some(ReadTimeout)))))
    val queryText = "Some query"
    underTest.record(PrimePreparedMulti(WhenPrepared(Some(queryText)), thenDo))

    val preparedPrime = underTest.findPrime(PrimeMatch(queryText, ONE))

    preparedPrime.value.variableTypes should equal(variableTypes)
    preparedPrime.value.getPrime(List(Some("Chris"))) should equal(Prime(result = ReadRequestTimeoutResult()))
  }

  test("Match on variable type - multiple options") {
    val variableTypes = List(CqlText)
    val thenDo: ThenPreparedMulti = ThenPreparedMulti(Some(variableTypes), List(
      Outcome(Criteria(List(VariableMatch(Some("Chris")))), Action(Some(List()), result = Some(ReadTimeout))),
      Outcome(Criteria(List(VariableMatch(Some("Daniel")))), Action(Some(List()), result = Some(WriteTimeout)))
    ))
    val queryText = "Some query"
    underTest.record(PrimePreparedMulti(WhenPrepared(Some(queryText)), thenDo))

    val preparedPrime = underTest.findPrime(PrimeMatch(queryText, ONE))

    preparedPrime.value.variableTypes should equal(variableTypes)
    preparedPrime.value.getPrime(List(Some("Daniel"))) should equal(Prime(result = WriteRequestTimeoutResult()))
  }

  test("Match on consistency") {
    val variableTypes = List(CqlText)
    val thenDo: ThenPreparedMulti = ThenPreparedMulti(Some(variableTypes), List(
      Outcome(Criteria(List(VariableMatch(Some("Daniel")))), Action(Some(List()), result = Some(WriteTimeout)))
    ))
    val queryText = "Some query"
    val when: WhenPrepared = WhenPrepared(Some(queryText), consistency = Some(List(TWO)))
    underTest.record(PrimePreparedMulti(when, thenDo))

    val preparedPrime = underTest.findPrime(PrimeMatch(queryText, ONE))

    preparedPrime should equal(None)
  }

  test("Stores rows for prime") {
    val variableTypes = List(CqlText)
    val rows = List(
      Map("name" -> "Chris")
    )
    val thenDo: ThenPreparedMulti = ThenPreparedMulti(Some(variableTypes), List(
      Outcome(Criteria(List(VariableMatch(Some("Daniel")))), Action(Some(rows)))
    ))
    val queryText = "Some query"
    val when: WhenPrepared = WhenPrepared(Some(queryText))
    underTest.record(PrimePreparedMulti(when, thenDo))

    val preparedPrime = underTest.findPrime(PrimeMatch(queryText, ONE))

    preparedPrime.value.getPrime(List(Some("Daniel"))).rows should equal(rows)
  }

  // return th delay

}