package $package$.main

import org.zalando.grafter.syntax.visualize._

import cats.effect.{IO, IOApp, ExitCode}

object Visualize extends IOApp {

  def run(args: List[String]): IO[ExitCode] = {

    val config = ApplicationConfig.config
    val components = ApplicationComponents
      .reader[ApplicationConfig, IO]
      .apply(config)
      .configure(config)

    // http://www.webgraphviz.com/
    println("Copy the graph schematics below, without the dashes, to the following website to plot a visualization: http://www.webgraphviz.com/")
    println("-" * 50)

    println(components.asDotString)

    println("-" * 50)

    IO(ExitCode.Success)
  }
}
