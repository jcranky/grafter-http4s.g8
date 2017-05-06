package $package$.main

import $package$.HttpServer

import org.zalando.grafter.macros.reader

@reader[ApplicationConfig]
case class ApplicationComponents(httpServer: HttpServer) {
  def configure(config: ApplicationConfig): ApplicationComponents =
    this
}
