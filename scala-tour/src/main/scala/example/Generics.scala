package example

object GenericsMain {
  def main(args: Array[String]): Unit = {

    val intBox = new Box[Int](100)
    println(intBox.pop())

    val stringBox = new Box("string") // 型推論される
    println(stringBox.pop())

    // Box[Int].push(newElement: Int) に String を入れようとしてるので type mismatch が発生する
    // intBox.push("string")
  }
}

class Box[T](var element: T) {
  def pop(): T = element

  def push(newElement: T): Unit = {
    element = newElement
  }
}
