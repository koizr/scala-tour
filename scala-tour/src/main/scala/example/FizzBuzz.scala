package example

object FizzBuzz {

  /**
    * entry point
    *
    * @param args
    */
  def main(args: Array[String]): Unit = {
    val n = 20 // val は定数
    fizzbuzz(n)
    fizzbuzzAgain(n)
    fizzBuzzAgainAgain(n)
  }

  /**
    * FizzBuzz
    *
    * @param args
    */
  @scala.annotation.tailrec // このアノテーションをつけておくと末尾最適化されてないとコンパイルエラーを出してくれる
  def fizzbuzz(n: Int, i: Int = 1): Unit = { // println の戻り値は Unit
    i match {
      case x if x % 15 == 0 => println("FizzBuzz")
      case x if x % 3 == 0 => println("Fizz")
      case x if x % 5 == 0 => println("Buzz")
      case x => println(x)
    }
    // scala では最後に実行された式が評価された値がブロックの値となる

    // 末尾再帰最適化 ってのがあるので最後に評価される式が再帰じゃないと最適化されない
    if (i < n) fizzbuzz(n, i + 1)
  }

  def fizzbuzzAgain(n: Int): Unit = {
    // args => return value の書き方でラムダ式になる
    // Scala の場合、メソッドと関数は明確に別れており、関数は第一級の値であるがメソッドは第一級ではない
    (1 to n).foreach(i => i match {
      case x if x % 15 == 0 => println("FizzBuzz")
      case x if x % 3 == 0 => println("Fizz")
      case x if x % 5 == 0 => println("Buzz")
      case x => println(x.toString)
    })
  }

  def fizzBuzzAgainAgain(n: Int): Unit = {
    val printFizzBuzz = (i: Int) => i match {
      case x if x % 15 == 0 => println("FizzBuzz")
      case x if x % 3 == 0 => println("Fizz")
      case x if x % 5 == 0 => println("Buzz")
      case x => println(x.toString)
    }

    // 中置記法
    1 to n foreach printFizzBuzz
  }
}
