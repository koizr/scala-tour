package example

object TryCatchMain {
  def main(args: Array[String]): Unit = {
    // 例外は try-catch-finally で処理できる。
    // catch か finally のどちらかがあれば良い。
    // try か catch の最後に評価された値が返る。
    val message = try {
      "Hello, world"
    } catch {
      case e: java.lang.ArithmeticException =>
        s"Invalid arithmetics (${e.getMessage})"
      case e: Throwable =>
        "Unknown error"
    } finally {
      // finally は戻り値とは無関係
      println("completed")
    }
    println(message)
  }
}
