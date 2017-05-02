package $package$.main

import $package$.HttpServerConfig

case class ApplicationConfig(httpServer: HttpServerConfig)

object ApplicationConfig {
  def config: ApplicationConfig =
    ApplicationConfig(httpServer)

  def httpServer: HttpServerConfig =
    HttpServerConfig("0.0.0.0", 8080)
}
