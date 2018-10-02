package $package$.main

import fs2.{Stream, StreamApp}
import fs2.StreamApp.ExitCode
import cats.effect.{Effect, IO}

import org.zalando.grafter.{Rewriter, StartResult}

object Application extends StreamApp[IO] {

  val config = ApplicationConfig.config

  val components = ApplicationComponents.reader[ApplicationConfig, IO]
    .apply(config)
    .configure(config)

  def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, ExitCode] = {
    val results = Rewriter.startAll(components).value

    if (results.forall(_.success)) {
      println("Http4s Server Started successfully")
      components.httpServer.stream
    } else {
      println("Failed to start Http4s server")
      println(results.mkString("\n"))
      Stream.eval(
        requestShutdown
          .map(_ => ExitCode.Success)
      )
    }
  }
}
