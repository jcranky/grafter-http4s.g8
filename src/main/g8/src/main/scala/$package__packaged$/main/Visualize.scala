package $package$.main

import org.zalando.grafter.syntax.visualize._

object Visualize extends App {
  val config = ApplicationConfig.config
  val components = ApplicationComponents.reader[ApplicationConfig].apply(config).configure(config)

  // http://www.webgraphviz.com/
  println("Copy the graph schematics below, without the dashes, to the following website to plot a visualization: http://www.webgraphviz.com/")
  println("-" * 50)

  println(components.asDotString)

  println("-" * 50)
}
