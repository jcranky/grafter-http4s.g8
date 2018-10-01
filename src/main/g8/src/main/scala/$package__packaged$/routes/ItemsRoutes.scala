package $package$.routes

import $package$.services.ItemsService

import cats.effect.Effect

import io.circe._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._

import org.zalando.grafter.macros.reader

@reader
case class ItemsRoutes[F[_]](
  itemsService: ItemsService
)(implicit val m: Effect[F]) extends Http4sDsl[F] {

  val service: HttpService[F] = {
    HttpService[F] {
      case GET -> Root =>
        Ok(Json.obj("message" -> Json.fromString("Hello")))

      case GET -> Root / name =>      
        Ok(Json.obj("message" -> Json.fromString(s"Hello, \${name}")))
    }
  }
}
