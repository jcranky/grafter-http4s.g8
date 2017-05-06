package $package$

import java.util.concurrent.{ExecutorService, Executors}

import $package$.main.ApplicationConfig
import $package$.routes.RestApi

import cats.Eval
import cats.data.Reader
import org.http4s.server.Server
import org.http4s.server.blaze.BlazeBuilder
import org.zalando.grafter.macros.reader
import org.zalando.grafter.{Start, StartResult, Stop, StopResult}

@reader[ApplicationConfig]
case class HttpServer(restApi: RestApi, config: HttpServerConfig) extends Start with Stop {
  val pool : ExecutorService  = Executors.newCachedThreadPool()

  lazy val server: Server =
    BlazeBuilder
      .bindHttp(config.port, config.ip)
      .mountService(restApi.services)
      .withServiceExecutor(pool)
      .start
      .unsafePerformSync

  def start: Eval[StartResult] =
    StartResult.eval("HttpServer")(server)

  def stop: Eval[StopResult] =
    StopResult.eval("HttpServer")(server.shutdownNow())
}

case class HttpServerConfig(ip: String, port: Int)
