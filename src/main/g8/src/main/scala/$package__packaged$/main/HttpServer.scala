package $package$.main

import $package$.routes.RestApi

import fs2.Stream
import fs2.StreamApp.ExitCode

import java.util.concurrent.{ExecutorService, Executors}
import scala.concurrent.ExecutionContext

import cats.effect.Effect
import cats.Eval
import cats.data.Reader
import org.http4s.server.Server
import org.http4s.server.blaze.BlazeBuilder
import org.zalando.grafter.macros.reader

@reader
case class HttpServer[F[_]](
  restApi: RestApi[F], 
  config: HttpServerConfig
)(implicit val m: Effect[F]) {

  val pool: ExecutorService = Executors.newFixedThreadPool(10)
  implicit val workers = ExecutionContext.fromExecutor(pool)

  def stream: Stream[F, ExitCode] =
    BlazeBuilder[F]
      .bindHttp(config.port, config.host)
      .mountService(restApi.services, "/")
      .serve
}

case class HttpServerConfig(host: String, port: Int)
