package example

/*
 自動でいろいろ実装してくれる case class というものがある。
 case class にできて class にできないものはないが、便利なので覚えておくべき
 */

object CaseClass {
  def main(args: Array[String]): Unit = {

      // apply メソッドは
    val foo = new Foo
    // foo() // 引数なしなのに () つけて呼び出してるからかコンパイルエラー
    println(Foo("argument"))
  }
}

class Foo {
  def apply: Unit = println("this is instance method `apply`.")
}

object Foo {
  def apply(s: String): Int = {
    println("this is class method `apply`.")
    println("args and return value have no restriction.")
    s.length
  }
}