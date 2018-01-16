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

    // コンストラクタ引数の扱い
    val generator = new scala.util.Random
    case class Bar(i: Int) { // case class のコンストラクタ引数は public な val になる
      val randomValue = generator.nextInt
    }
    val bar = Bar(10) // == Foo.apply(10)
    println(bar.i) // アクセスできる

    // 文字列に変換したときの違い
    class Baz
    val baz = new Baz
    println(baz) // 普通のクラスだとハッシュ値
    println(baz.toString)
    println(bar) // case class だと良い感じにオブジェクトを表示してくれる
    println(bar.toString)

    // コピー時の挙動
    val barCopy = bar.copy()
    println(s"${bar}, i: ${bar.i}, random: ${bar.randomValue}")
    println(s"${barCopy}, i: ${barCopy.i}, random: ${barCopy.randomValue}")

    // パターンマッチが便利
    bar match {
      case Bar(i) => println(s"class Bar, i: ${i}") // マッチしたときにパラメータをキャプチャできる
      case _ => println("not class Bar")
    }
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
