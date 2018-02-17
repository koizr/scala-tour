package example

object Implicit {
  def main(args: Array[String]): Unit = {
    implicit val greeting = "Hello"
    greet("Bob")

    greet("Tom")("Hi")

    // `endSentence` が、 `String` のメソッドであるかのように使える。
    // これは、存在しないメソッドの呼び出しを行った際に、スコープ内の対応する型の implicit クラスに暗黙的に変換されるからである。
    println("A short sentence".endSentence)
    // 以下と同じ
    println(new StringEx("A short sentence").endSentence)
  }

  /**
    * 引数に implicit をつけると暗黙の引数となる。
    * 暗黙の引数は、 implicit で宣言された対応した型の変数を自動的に受け取る。
    * 明示的に渡すことも可能。
    */
  def greet(name: String)(implicit greeing: String): Unit = {
    println(s"${greeing}, ${name}")
  }

  /**
    * String 拡張
    *
    * @param value
    */
  implicit class StringEx(val value: String) extends AnyVal {
    /**
      * ピリオドを末尾に追加して、文章を終わらせる
      *
      * @return
      */
    def endSentence: String = {
      if (value.endsWith(".")) value else value + "."
    }
  }

}
