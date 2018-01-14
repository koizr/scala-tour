package example

object PolygonMain {
  def main(args: Array[String]): Unit = {
    val triangle = new Triangle(List(3, 4, 5))
    println(triangle.area)
  }
}

/**
  * 多角形を表す抽象クラス
  *
  * @param edges 各辺の長さのリスト
  */
// scala には明示的なコンストラクタは存在しない
// 必要であればクラス名に続けてコンストラクタ引数を定義することができる
abstract class Polygon(edges: List[Int]) {
  // メンバ。コンストラクタも兼ねる
  val n = edges.length // n 角形
  val area: Double // 面積
}

/**
  * 三角形を表すクラス
  *
  * @param edges 各辺の長さのリスト
  */
// 継承は extends キーワードをつけて行う。このときにコンストラクタ引数も忘れず渡す
class Triangle(edges: List[Int]) extends Polygon(edges) {
  val a = edges(0)
  val b = edges(1)
  val c = edges(2)

  val area = {
    val s = (a + b + c) / 2.0
    math.sqrt(s * (s - a) * (s - b) * (s - c))
  }
}

/**
  * 四角形を表すクラス
  *
  * @param edges 各辺の長さのリスト
  */
class Square(edges: List[Int]) extends Polygon(edges) {
  val a = edges(0)
  val b = edges(1)
  val c = edges(2)
  val d = edges(3)

  val area = {
    val s = (a + b + c + d) / 2.0
    // 対角の和が 180 度であると過程
    math.sqrt((s - a) * (s - b) * (s - c) * (s - b))
  }
}
