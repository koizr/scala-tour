package example

/*
  「scala 列挙」で検索すると Enumeration クラスについて出てくることが多いが
  より利便性を高めた列挙の実装を行う
 */

sealed abstract class Animal(val cryJP: String, val cryEN: String) // sealed は同じファイル内でのみ継承できるアクセス修飾子
// これ以上継承されない Animal を親としたオブジェクトを作成することで列挙のように扱うことができる
case object Cat extends Animal("にゃー", "Meow")

case object Dog extends Animal("わんわん", "Bowwow")

case object Lion extends Animal("がおー", "Roar")

object Enum {
  def main(args: Array[String]): Unit = {
    for {animal <- List(Lion, Cat, Dog)} {
      // 列挙を case object で作ることによって良い感じに名前が表示される
      println(s"${animal}, ${animal.cryJP} ${animal.cryEN}")
    }
  }
}
