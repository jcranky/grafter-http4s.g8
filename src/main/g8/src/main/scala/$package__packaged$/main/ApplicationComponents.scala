package $package$.main

import $package$.HttpServer

import org.zalando.grafter.macros.reader
import org.zalando.grafter.syntax.rewriter._

@reader[ApplicationConfig]
case class ApplicationComponents(httpServer: HttpServer) {
  def configure(config: ApplicationConfig): ApplicationComponents =
    this
      .singletons
}
