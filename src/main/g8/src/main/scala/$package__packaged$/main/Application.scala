package $package$.main

import $package$.HttpServer

import org.http4s.server.ServerApp
import org.zalando.grafter.{Rewriter, StartResult}

import scalaz.concurrent.Task

object Application {
  def main(args: Array[String]): Unit = {
    val config = ApplicationConfig.config
    val components = ApplicationComponents.reader(config).configure(config)

    startServer(components.httpServer) match {
      case Right(()) =>
        println("Http4s Server Started successfully")

      case Left(startResults) =>
        println("Failed to start Http4s server")
        println(startResults.mkString("\n"))
    }
  }

  def startServer(httpServer: HttpServer): Either[List[StartResult], Unit] = {
    val results = Rewriter.start(httpServer).value
    if (results.forall(_.success)) {
      val app = new ServerApp { def server(args: List[String]) = Task.delay(httpServer.server) }

      app.main(Array())

      Right(())
    }
    else
      Left(results)
    }
}
