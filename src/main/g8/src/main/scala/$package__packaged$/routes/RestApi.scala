package $package$.routes

import $package$.main.ApplicationConfig

import org.http4s.HttpService
import org.http4s.server.syntax._
import org.zalando.grafter.macros.reader

@reader[ApplicationConfig]
case class RestApi(itemsRoutes: ItemsRoutes, usersRoutes: UsersRoutes) {
  def services: HttpService =
    itemsRoutes.service orElse usersRoutes.service
}
