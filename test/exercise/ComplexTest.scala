package exercise

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

class ComplexTest {

  val a:Array[Complex] = Array(Complex(10,20), Complex(1,1), Complex(7,0))

  @Test def complexSumTest(): Unit ={
    val c = a(0) + a(1) + a(2)

    assertEquals(Complex(18.0, 21.0),c)
    assertEquals(18.0, c.re)
    assertEquals(21.0, c.im)
  }

  @Test def complexProdTest(): Unit ={
    val c = a(0) * a(1)

    assertEquals(Complex(-10.0,30.0), c)
    assertEquals(-10, c.re)
    assertEquals(30.0, c.im)
  }

  @Test def complexEqualsTest(): Unit ={
    assertEquals(Complex(10.0,20.0), a(0))
    assertTrue(Complex(10.0,20.0)==a(0))
    assertTrue(Complex(10.0,20.0).equals(a(0)))
  }

  @Test def complexToStringTest(): Unit ={
    assertEquals("ComplexImpl(10.0,20.0)", a(0).toString())
  }
}
