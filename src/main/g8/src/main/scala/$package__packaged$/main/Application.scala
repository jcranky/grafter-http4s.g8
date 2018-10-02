package $package$.main

import $package$.HttpServer

import org.http4s.server.ServerApp
import org.zalando.grafter.{Rewriter, StartResult}

import scalaz.concurrent.Task

object Application {
  def main(args: Array[String]): Unit = {
    val config = ApplicationConfig.config
    val components = ApplicationComponents
      .reader[ApplicationConfig]
      .apply(config)
      .configure(config)

    startServer(components) match {
      case Right(()) =>
        println("Http4s Server Started successfully")

      case Left(startResults) =>
        println("Failed to start Http4s server")
        println(startResults.mkString("\n"))
    }
  }

  def startServer(components: ApplicationComponents): Either[List[StartResult], Unit] = {

    val results = Rewriter.startAll(components).value
    if (results.forall(_.success)) {
      val app = new ServerApp { 
        def server(args: List[String]) = Task.delay(components.httpServer.server) 
      }

      app.main(Array())

      Right(())
    }
    else
      Left(results)
    }
}
