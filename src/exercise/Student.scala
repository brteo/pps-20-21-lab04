package exercise

import u04lab.code.Lists._

case class Teacher(name: String, lastname: String)
case class Course(id:String, name:String, teacher:Teacher)

trait Student {
  def name: String
  def lastname: String
  def addCourse(c: Course)
  def enroll(courses: Course*)
  def courses[A](f: Course => A): List[A]
  def coursesName: List[String]
  def hasTeacher(t: Teacher): Boolean
}

object Student {
  def apply(name:String, lastName:String):Student = StudentImpl(name, lastName)

  private case class StudentImpl(override val name: String, override val lastname: String) extends Student {
    assert(name != null && lastname != null)

    private var _courses:List[Course] = List.Nil()

    override def addCourse(c: Course):Unit = { _courses = List.Cons(c,_courses) }// add one course

    override def enroll(courses: Course*):Unit = { courses foreach { c => _courses = List.Cons(c,_courses) }} // add many courses

    override def courses[A](f: Course => A): List[A] = List.map(_courses)(f) // get course's list with free map

    override def coursesName:List[String] = courses(_.name) // get course's list with name

    override def hasTeacher(t: Teacher):Boolean = List.contains(courses(_.teacher))( teacher => teacher.name==t.name && teacher.lastname==t.lastname )
  }
}
