package example

object FizzBuzz {
  /**
    * FizzBuzz
    * @param args
    */
  def main(args: Array[String]): Unit = { // println の戻り値は Unit
    val n = 20 // val は定数
    for { i <- 1 to n } {
      if (i % 15 == 0) {
        println("FizzBuzz")
      } else if (i % 3 == 0) {
        println("Fizz")
      } else if (i % 5 == 0) {
        println("Buzz")
      } else {
        println(i)
      }
    }
    // scala では最後に実行された式が評価された値がブロックの値となる
  }
}
