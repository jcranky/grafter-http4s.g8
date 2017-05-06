package $package$.routes

import $package$.main.ApplicationConfig

import org.http4s.HttpService
import org.zalando.grafter.macros.reader

@reader[ApplicationConfig]
case class RestApi(itemsRoutes: ItemsRoutes) {
  def services: HttpService =
    itemsRoutes.service // orElse other routes.services
}
