package example

object FizzBuzz {
  /**
    * FizzBuzz
    * @param args
    */
  def main(args: Array[String]): Unit = { // println の戻り値は Unit
    val n = 20 // val は定数
    for { i <- 1 to n } {
      i match {
        case x if x % 15 == 0 => println("FizzBuzz")
        case x if x % 3 == 0 => println("Fizz")
        case x if x % 5 == 0 => println("Buzz")
        case x => println(x)
      }
    }
    // scala では最後に実行された式が評価された値がブロックの値となる
  }
}
