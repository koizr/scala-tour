import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    // libraryDependencies に指定したライブラリを勝手に落としてきてくれる
    // シーケンスで渡すと複数一気に指定できる
    libraryDependencies ++= Seq(
      // グループ % ライブラリ % バージョン
      // Scala のライブラリの場合は %%
      "org.json4s" %% "json4s-jackson" % "3.5.3",
      "org.scala-sbt" %% "io" % "1.1.0",
      // テスト時にのみ使うライブラリは最後に % Test をつける
      scalaTest % Test // これがどこに定義されているのか不明
    )
  )
