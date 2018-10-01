package $package$.routes

import cats.effect.Effect
import org.http4s.HttpService
import org.http4s.dsl._

import org.http4s.server.Router
import org.http4s.server.syntax._
import org.zalando.grafter.macros.reader

@reader
case class RestApi[F[_]](
  itemsRoutes: ItemsRoutes[F],
  usersRoutes: UsersRoutes[F]
)(implicit val m: Effect[F]) {
  
  def services: HttpService[F] = 
    Router[F](
      "/items" -> itemsRoutes.service,
      "/users" -> usersRoutes.service,
    )

}
