package $package$.routes

import $package$.services.ItemsService

import io.circe._
import org.http4s.HttpService
import org.http4s.circe._
import org.http4s.dsl._

case class ItemsRoutes(itemsService: ItemsService) {

  val service = HttpService {
    case GET -> Root / "items" =>
      Ok(Json.obj("message" -> Json.fromString(s"Hello")))

    case GET -> Root / "items" / name =>
      Ok(Json.obj("message" -> Json.fromString(s"Hello, \$name")))
  }
}
