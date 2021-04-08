package exercise

trait Complex {
  def re: Double
  def im: Double
  def +(c: Complex): Complex // should implement the sum of two complex numbers..
  def *(c: Complex): Complex // should implement the product of two complex numbers
}

object Complex {
  def apply(re: Double, im: Double): Complex = ComplexImpl(re, im)

  private case class ComplexImpl(override val re: Double, override val im: Double) extends Complex {
    //(a+ib) + (c+id) = (a+c) + i(b+d)
    override def +(c: Complex): Complex = Complex(re+c.re, im+c.im)

    //(a+ib) * (c+id) = (ac-bd) + i(bc+ad)
    override def *(c: Complex): Complex = Complex((re*c.re) - (im*c.im), (im*c.re) + (re*c.im))
  }
}