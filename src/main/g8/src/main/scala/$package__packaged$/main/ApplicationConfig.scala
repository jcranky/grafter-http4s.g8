package $package$.main

import $package$.HttpServerConfig

case class ApplicationConfig(httpServerConfig: HttpServerConfig)

object ApplicationConfig {
  def config: ApplicationConfig =
    ApplicationConfig(httpServerConfig)

  def httpServerConfig: HttpServerConfig =
    HttpServerConfig("0.0.0.0", 8080)
}
