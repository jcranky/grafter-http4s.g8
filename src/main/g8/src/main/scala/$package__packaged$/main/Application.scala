package $package$.main

import cats.implicits._
import cats.effect.{ConcurrentEffect, Effect, ExitCode, IO, IOApp}

import org.zalando.grafter.{Rewriter, StartResult}

object Application extends IOApp {

  val config = ApplicationConfig.config

  val components = ApplicationComponents.reader[ApplicationConfig, IO]
    .apply(config)
    .configure(config)

  def run(args: List[String]): IO[ExitCode] = {
    val results = Rewriter.startAll(components).value

    if (results.forall(_.success)) {
      IO(println("Http4s Server Started successfully")) *>
      components.httpServer
        .stream
        .compile
        .drain
        .as(ExitCode.Success)
    } else {
      IO(println(s"Failed to start Http4s server\n\${results.mkString("\n")}")).as(ExitCode.Success)
    }
  }
}
