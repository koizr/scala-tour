package example

/*
  「scala 列挙」で検索すると Enumeration クラスについて出てくることが多いが
  より利便性を高めた列挙の実装を行う
 */

sealed abstract class Animal(val cryJP: String, val cryEN: String) // sealed は同じファイル内でのみ継承できるアクセス修飾子
// これ以上継承されない Animal を親としたオブジェクトを作成することで列挙のように扱うことができる
object Cat extends Animal("にゃー", "Meow")

object Dog extends Animal("わんわん", "Bowwow")

object Lion extends Animal("がおー", "Roar")

object Enum {
  def main(args: Array[String]): Unit = {
    for {animal <- List(Lion, Cat, Dog)} {
      animal match {
        case Cat =>
          println(s"Cat ${animal.cryJP} ${animal.cryEN}")
        case Dog =>
          println(s"Dog ${animal.cryJP} ${animal.cryEN}")
        case Lion =>
          println(s"Lion ${animal.cryJP} ${animal.cryEN}")
      }
    }
  }
}
