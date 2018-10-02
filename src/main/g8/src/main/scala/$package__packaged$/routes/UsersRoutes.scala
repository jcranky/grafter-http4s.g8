package $package$.routes

import $package$.services.ItemsService

import cats.effect.Sync

import io.circe._
import org.http4s.HttpRoutes
import org.http4s.circe._
import org.http4s.dsl._
import org.zalando.grafter.macros.reader

@reader
case class UsersRoutes[F[_]](
  itemsService: ItemsService
)(implicit val m: Sync[F]) extends Http4sDsl[F]  {

  val service: HttpRoutes[F] = {
    HttpRoutes.of[F] {
      case GET -> Root =>
        Ok(Json.obj("message" -> Json.fromString(s"Hello")))
    }
  }
}


