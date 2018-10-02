package $package$.routes

import cats.effect.Sync
import org.http4s.HttpRoutes
import org.http4s.dsl._

import org.http4s.server.Router
import org.http4s.server.syntax._
import org.zalando.grafter.macros.reader

@reader
case class RestApi[F[_]](
  itemsRoutes: ItemsRoutes[F],
  usersRoutes: UsersRoutes[F]
)(implicit val m: Sync[F]) {
  
  def services: HttpRoutes[F] = 
    Router[F](
      "/items" -> itemsRoutes.service,
      "/users" -> usersRoutes.service,
    )

}
