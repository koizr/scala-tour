package example

object CollectionMain {
  def main(args: Array[String]): Unit = {
    string()
    range()
    array()
    list()
    set()
    map()
    tuple()
  }

  def string(): Unit = {
    // String は char のコレクション
    "string".foreach(c => println(c))
  }

  /**
    * Range は数値の範囲を表す
    */
  def range(): Unit = {
    // 1 <= 3
    val oneToThree = 1 to 3

    // 1 < 3
    val oneToTwo = 1 until 3

    // by に負数を渡すと逆順になる
    val threeToOne = 1 to 3 by -1

    // to や until はメソッド。中置記法という糖衣構文を使っている
    // + や * といった演算子もメソッドとして定義してある
    val nonSyntaxSugar = 1.to(3).by(-1) // 本来はこう書く
  }

  /**
    * Array は固定長のミュータブルな配列
    * 内部的に Java の配列を利用している
    * ランダムアクセスが高速という特徴がある
    */
  def array(): Unit = {
    // Int を入れられる長さ 3 の配列を初期化
    val array1 = Array[Int](3)

    // 値から推論して初期化することもできる
    val array2 = Array("Hello", "World")

    // インデックスでアクセスできる
    println(array2(0))

    // 要素は可変なので再代入できる
    array2(0) = "GoodBye"
  }

  /**
    * List はイミュータブルな固定長配列
    * 固定長ではあるが連結するメソッドが容易されているので可変長のように扱うことも出来る
    * シーケンシャルアクセスが高速？
    */
  def list(): Unit = {
    // Int を入れられる長さ 3 のリストを初期化
    val list1 = List[Int](3)

    // 値から推論して初期化することもできる
    val list2 = List("Hello", "World")

    // 連結
    // `::` はメソッド。以下の式は中置記法を使っている
    // ただし、名前が `:` で終わるメソッドは中置記法での主体と引数の位置が逆になる
    val list3 = 1 :: List(2, 3)
    // 中置記法を使わない場合 => List(2, 3).::(1)

    // 連結メソッドを利用して要素を列挙して初期化できる
    val list4 = 1 :: 2 :: 3 :: 4 :: Nil
    // 中置記法を使わない場合 => Nil.::(4).::(3).::(2).::(1)

    // `::` はケースクラスでもある。 => どういうこと？
    val list6 = List(2, 3, 4, 5) match {
        // head には 先頭の要素、tail にはその後ろの要素が来る
      case head :: tail if head % 2 == 0 =>
        (head * 5) :: tail
      case head :: tail =>
        head * 3 :: tail
      case Nil => Nil
    }
  }

  /**
    * Set は集合
    * 重複のないデータの集まりを表す
    */
  def set(): Unit = {
    // 初期化。勝手に重複が排除される
    val set1 = Set(0,0,0)

    // List から Set に変換。重複が排除される
    val set2 = List(1,2,2).toSet
  }

  /**
    * Map はキーとそれに紐付いた値からなるデータの集まりを表す
    */
  def map(): Unit ={
    // (key, value) のタプルを渡す
    val map1 = Map((1, "a"), (2, "b"))

    // キーを元に値を取得する
    // 返り値は Option
    val map1value1 = map1.get(1)
    val map1value2 = map1.get(1000) // None
  }

  /**
    * Tuple は複数の値の組を表す
    * 不変なので再代入できない
    */
  def tuple(): Unit ={
    // 値を () で括って初期化
    val tuple1 = (1, "a")

    // 要素が２つのときだけ `->` で初期化できる
    val tuple2 = 1 -> "a"

    // 要素を取り出す場合は書き方がちょっと特殊
    println(tuple1._1) // 1 番目の要素
    println(tuple1._2) // 2 番目の要素

    // タプルのリストはマップに変換できる
    val map = List((1, "a"), (2, "b")).toMap
  }
}
