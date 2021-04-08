package u04lab.code

import Optionals._
import Lists._
import Streams._

trait PowerIterator[A] {
  def next(): Option[A]
  def allSoFar(): List[A]
  def reversed(): PowerIterator[A]
}

object PowerIterator {
  def apply[A](iterator: Stream[A]): PowerIterator[A] = new PowerIteratorImpl(iterator)

  private class PowerIteratorImpl[A](var iterator: Stream[A]) extends PowerIterator[A] {

    private var pastList: List[A] = List.Nil()

    override def next(): Option[A] = iterator match {
      case Stream.Cons(h, t) => {
        pastList = List.append(pastList, List.Cons[A](h(), List.Nil())) // generate next and add to private list - used append for correct items order
        iterator = t() // refresh iterator with tail
        Option.of(h()) // return
      }
      case _ => Option.empty
    }

    override def allSoFar(): List[A] = pastList

    override def reversed(): PowerIterator[A] = PowerIterator[A](List.toStream(List.reverse(pastList)))
  }
}

trait PowerIteratorsFactory {
  def incremental(start: Int, successive: Int => Int): PowerIterator[Int]
  def fromList[A](list: List[A]): PowerIterator[A] // edit return Unit with PowerIterator
  def randomBooleans(size: Int): PowerIterator[Boolean]
}

class PowerIteratorsFactoryImpl extends PowerIteratorsFactory {

  override def incremental(start: Int, successive: Int => Int): PowerIterator[Int] = PowerIterator[Int](Stream.iterate(start)(successive))

  override def fromList[A](list: List[A]): PowerIterator[A] = PowerIterator[A](List.toStream(list))

  override def randomBooleans(size: Int): PowerIterator[Boolean] = PowerIterator[Boolean](Stream.take(Stream.generate(scala.util.Random.nextBoolean()))(size))
}
