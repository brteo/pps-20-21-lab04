package exercise

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._
import u04lab.code.Lists._

class StudentTest {

  val viroli:Teacher = Teacher("Mirko", "Viroli")
  val ricci:Teacher = Teacher("Alessandro", "Ricci")
  val bravetti:Teacher = Teacher("Mario", "Bravetti")

  val pps:Course = Course("X01", "PPS", viroli)
  val pcd:Course = Course("X02", "PCD",  ricci)
  val lcmc:Course = Course("X03", "LCMC",  bravetti)
  val pps2:Course = Course("X04", "PPS 2", viroli)

  @Test def studentCoursesTest(): Unit ={
    val matteo:Student = Student("Matteo", "Brocca")

    matteo.addCourse(pps)
    matteo.addCourse(pcd)
    matteo.addCourse(lcmc)

    assertEquals(List.Cons("LCMC", List.Cons("PCD", List.Cons("PPS", List.Nil()))), matteo.coursesName)
    assertEquals(List.Cons("X03", List.Cons("X02", List.Cons("X01", List.Nil()))), matteo.courses(_.id))
  }

  @Test def studentEnrollingTest(): Unit ={
    val matteo:Student = Student("Matteo", "Brocca")

    matteo.enroll(pps, pcd, lcmc)
    assertEquals(List.Cons("LCMC", List.Cons("PCD", List.Cons("PPS", List.Nil()))), matteo.coursesName)
  }

  @Test def studentHasTeacherTest(): Unit = {
    val matteo:Student = Student("Matteo", "Brocca")
    matteo.enroll(pps, pcd)

    assertTrue(matteo.hasTeacher(viroli))
    assertFalse(matteo.hasTeacher(Teacher("Alessandro", "Viroli")))
    assertFalse(matteo.hasTeacher(bravetti))
  }

  // optional 1
  @Test def factoryListTest(): Unit = {
    assertEquals(List.Cons("LCMC", List.Cons("PCD", List.Cons("PPS", List.Nil()))), List("LCMC", "PCD", "PPS"))
  }

  // optional 2
  @Test def sameTeacherTest(): Unit = {
    val c1 = List(pps, pps2)

    c1 match {
      case sameTeacher(t) => { assertTrue(true); assertEquals(t, viroli) }
      case _ => fail()
    }

    val c2 = List(pps, pcd)
    c2 match {
      case sameTeacher(t) => fail()
      case _ => assertTrue(true)
    }

    val c3 = List(
      Course("X01", "PPS", Teacher("Mirko", "Viroli")),
      Course("X02", "PCD", Teacher("Mirko", "Ricci")) // only lastname different
    )

    c3 match {
      case sameTeacher(t) => fail()
      case _ => assertTrue(true)
    }
  }
}
