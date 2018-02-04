package example

import sbt.io.IO
import java.io.File
import org.json4s.jackson.Serialization.{read, write}

object FizzBuzzWithLibraryMain {
  def main(args: Array[String]): Unit = {
    val json = createSourceJSON(20, "json/sample.json")
    fizzbuzzFromJSON(json, "json/modified.json")
  }

  /**
    * 読み込み用の 1 ~ n の連続した数列のファイルを作成する
    *
    * @param n
    * @param fileName
    * @return 作成したファイルの File インスタンス
    */
  def createSourceJSON(n: Int, fileName: String): File = {
    // 条件を満たさなかったときに `IllegalArgumentException` を発生させる
    // 引数をチェックするのに使う
    require(n >= 1)

    val sourceFile = new File(fileName)
    // `"""` で囲むとクォーテーションや改行をエスケープしなくても大丈夫
    IO.write(sourceFile, s"""{"intArray": [${(1 to n).mkString(",")}]}""")
    sourceFile
  }

  /**
    * FizzBuzz の結果を JSON ファイルに出力する
    *
    * @param jsonFile
    * @param outputFileName
    * @return 出力したファイルの File インスタンス
    */
  def fizzbuzzFromJSON(jsonFile: File, outputFileName: String): File = {
    // implicit は暗黙的な宣言
    // 同一スコープ内の implicit な同じ型の引数に自動的に渡される
    // ここはで read と write 関数の引数として渡されている
    // コンテキストの定義に使うと良さそう
    implicit val formats = org.json4s.DefaultFormats

    val rawJSON = IO.read(jsonFile)
    val intArrayHolder = read[IntArrayHolder](rawJSON)

    val fizzbuzz = intArrayHolder.intArray.map(
      i => i match {
        case x if x % 15 == 0 => "FizzBuzz"
        case x if x % 3 == 0 => "Fizz"
        case x if x % 5 == 0 => "Buzz"
        case x => x.toString
      })
    val fizzbuzzHolder = FizzBuzzHolder(fizzbuzz)

    // FizzBuzz の結果を書き出す
    val destinationFile = new File(outputFileName)
    IO.write(destinationFile, write(fizzbuzzHolder))
    destinationFile
  }
}

case class IntArrayHolder(intArray: Array[Int])

case class FizzBuzzHolder(fizzbuzz: Array[String])
