package example

// 小文字の future も存在するが非推奨
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success
import scala.util.Failure

object FutureMain {
  def main(args: Array[String]): Unit = {
    // Future を使うと非同期に実行される
    // ノンブロッキング
    val f1 = Future {
      Thread.sleep(5)
      println("completed f1")
      1
    }
    val f2 = Future {
      Thread.sleep(1)
      println("completed f2")
      10 / 0 // 例外を出してみる
    }

    // Future は、処理が成功したら長さ 1, 失敗したら長さ 0 のコレクションのように振る舞う
    for {
      result1 <- f1
      result2 <- f2
    } {
      println(result1 + result2)
    }

    // Future は成功していたら Success, 失敗していたら Failure
    // onComplete では Future 完了時のコールバックを設定できる
    f1.zip(f2).onComplete {
      case Success(result) => println(result._1 + result._2)
      case Failure(error) => println(error.getMessage)
    }

    println("bottom of code")
  }
  // 非同期に処理しつつブロックするなら Await を使うといい
}
