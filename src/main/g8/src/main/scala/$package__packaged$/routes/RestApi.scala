package $package$.routes

import org.http4s.HttpService

case class RestApi(itemsRoutes: ItemsRoutes) {
  def services: HttpService =
    itemsRoutes.service // orElse other routes.services
}
