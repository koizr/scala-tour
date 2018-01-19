package example

object GenericsMain {
  def main(args: Array[String]): Unit = {

    val intBox = new Box[Int](100)
    println(intBox.pop())

    val stringBox = new Box("string") // 型推論される
    println(stringBox.pop())

    // Box[Int].push(newElement: Int) に String を入れようとしてるので type mismatch が発生する
    // intBox.push("string")

    val personBox = new PersonBox[Person]
    val childBox = new PersonBox[Child]
    // T <: Person なので Parent はダメ
    // val parentBox = new PersonBox[Parent]
  }
}

class Box[T](var element: T) {
  def pop(): T = element

  def push(newElement: T): Unit = {
    element = newElement
  }
}

// 型パラメータの親子関係を指定することもできる
// T <: A で、A か A の子クラスのみ
// T >: A で、A か A の親クラスのみ
class PersonBox[T <: Person]

class Parent

class Person extends Parent

class Child extends Person
