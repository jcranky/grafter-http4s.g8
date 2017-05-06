package $package$.main

import $package$.HttpServerConfig
import org.zalando.grafter.macros.readers

@readers
case class ApplicationConfig(httpServerConfig: HttpServerConfig)

object ApplicationConfig {
  def config: ApplicationConfig =
    ApplicationConfig(httpServerConfig)

  def httpServerConfig: HttpServerConfig =
    HttpServerConfig("0.0.0.0", 8080)
}
