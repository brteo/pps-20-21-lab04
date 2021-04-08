package exercise

import org.junit.jupiter.api.{BeforeEach, Test}
import org.junit.jupiter.api.Assertions._
import u04lab.code.Lists.List._

class StudentTest {

  val viroli:Teacher = Teacher("Mirko", "Viroli")
  val ricci:Teacher = Teacher("Alessandro", "Ricci")
  val bravetti:Teacher = Teacher("Mario", "Bravetti")

  val pps:Course = Course("X01", "PPS", viroli)
  val pcd:Course = Course("X02", "PCD",  ricci)
  val lcmc:Course = Course("X03", "LCMC",  bravetti)

  @Test def studentCoursesTest(): Unit ={
    val matteo:Student = Student("Matteo", "Brocca")

    matteo.addCourse(pps)
    matteo.addCourse(pcd)
    matteo.addCourse(lcmc)

    assertEquals(Cons("LCMC", Cons("PCD", Cons("PPS", Nil()))), matteo.coursesName)
    assertEquals(Cons("X03", Cons("X02", Cons("X01", Nil()))), matteo.courses(_.id))
  }

  @Test def studentEnrollingTest(): Unit ={
    val matteo:Student = Student("Matteo", "Brocca")

    matteo.enroll(pps, pcd, lcmc)
    assertEquals(Cons("LCMC", Cons("PCD", Cons("PPS", Nil()))), matteo.coursesName)
  }

  @Test def studentHasTeacherTest(): Unit = {
    val matteo:Student = Student("Matteo", "Brocca")
    matteo.enroll(pps, pcd)

    assertTrue(matteo.hasTeacher(viroli))
    assertFalse(matteo.hasTeacher(Teacher("Alessandro", "Viroli")))
    assertFalse(matteo.hasTeacher(bravetti))
  }
}
