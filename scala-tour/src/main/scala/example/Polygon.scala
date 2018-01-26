package example

object PolygonMain {
  def main(args: Array[String]): Unit = {
    val triangle = Polygon.fromEdges(List(3, 4, 5))
    triangle match {
      case Some(t) => println(t.n)
      case None => println("None")
    }
    // `Some[T]` は `T` のインスタンスをひとつだけもったコレクションのように振る舞う
    triangle.foreach(t => println(t.n))

    val errorPolygon = Polygon.fromEdges(List(1, 1, 1, 1, 1, 1))
    errorPolygon match {
      case Some(p) => println(p.n)
      case None => println("error polygon is None")
    }
    // `None` は長さ 0 のコレクションのように振る舞う
    // 長さ 0 なので何も出力されない
    errorPolygon.foreach(p => println(p.n))

    val greenTriangle = new ColoredTriangle(List(6, 8, 10))
    println(s"R: ${greenTriangle.R} G: ${greenTriangle.G} B: ${greenTriangle.B}")
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
  * Polygon クラスのコンパニオンオブジェクト
  */
// クラスと同じ名前のオブジェクトを同一ファイル・同一パッケージ内に宣言することで `コンパニオンオブジェクト` となる
// 主にクラスに静的メソッドを定義する場合に用いる（ Scala には static キーワードがない = クラスに静的メソッドを定義できない）
object Polygon {
  /**
    * 辺の数に応じた多角形を生成して返す
    *
    * @param edges 各辺の長さのリスト
    * @return 多角形
    */
  def fromEdges(edges: List[Int]): Option[Polygon] = {
    // Some, None は Option のサブクラス
    // Some は値があることを、 None は値がないことを表す
    edges.length match {
      case 3 => Triangle.fromEdges(edges)
      case 4 => Square.fromEdges(edges)
      case _ => None
      // case _ => ??? // `???` の実態は `NotImplementedError` 。未実装を表すことができる
      // 変数 `_` は利用しない値を受け取る場合に用いる。この変数を参照することはできない
    }
  }
}

/**
  * 三角形を表すクラス
  *
  * @param edges 各辺の長さのリスト
  */
// 継承は extends キーワードをつけて行う。このときにコンストラクタ引数も忘れず渡す
// コンストラクタのアクセス修飾子をつけられる
class Triangle protected(edges: List[Int]) extends Polygon(edges) {
  val a = edges(0)
  val b = edges(1)
  val c = edges(2)

  val area = {
    val s = (a + b + c) / 2.0
    math.sqrt(s * (s - a) * (s - b) * (s - c))
  }
}

object Triangle {
  /**
    * 辺の数と図形としての整合性が正しければ Triangle を生成して返す
    *
    * @param edges
    * @return
    */
  def fromEdges(edges: List[Int]): Option[Triangle] = {
    if (edges.length == 3
      && edges(0) + edges(1) > edges(2)
      && edges(1) + edges(2) > edges(0)
      && edges(0) + edges(2) > edges(1)) {
      Some(new Triangle(edges))
    } else {
      None
    }
  }
}

/**
  * 四角形を表すクラス
  *
  * @param edges 各辺の長さのリスト
  */
class Square private(edges: List[Int]) extends Polygon(edges) {
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

object Square {
  def fromEdges(edges: List[Int]): Option[Square] = {
    if (edges.length == 4
      && edges(2) - edges(1) - edges(0) < edges(3)
      && edges(3) < edges(2) + edges(1) + edges(0)) {
      Some(new Square(edges))
    } else {
      None
    }
  }
}

/**
  * 色を表す trait
  */
trait Color {
  val R: Int
  val G: Int
  val B: Int
}

trait Red extends Color {
  override val R = 255
  override val G = 0
  override val B = 0
}

trait Green extends Color {
  override val R = 0
  override val G = 255
  override val B = 0
}

/**
  * 色が塗られた三角形を表す
  */
// trait は継承できる。2つ目以降は with を用いる
// trait の多重継承のことを mixin と呼ぶ
class ColoredTriangle(edges: List[Int])
  extends Triangle(edges) with Red with Green // 継承は後勝ちなので Green が有効

