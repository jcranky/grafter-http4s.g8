package $package$.main

import $package$.HttpServer

import cats.data.Reader
import org.zalando.grafter.GenericReader._

case class ApplicationComponents(httpServer: HttpServer) {
  def configure(config: ApplicationConfig): ApplicationComponents =
    this
}

object ApplicationComponents {
  implicit def reader: Reader[ApplicationConfig, ApplicationComponents] =
    genericReader
}
