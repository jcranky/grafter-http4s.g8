package $package$.main

import org.zalando.grafter.macros.reader
import org.zalando.grafter.syntax.rewriter._

import cats.effect.Effect

@reader
case class ApplicationComponents[F[_]](
  httpServer: HttpServer[F]
)(implicit val m: Effect[F]) {
  def configure(config: ApplicationConfig): ApplicationComponents[F] =
    this
      .singletons
}

