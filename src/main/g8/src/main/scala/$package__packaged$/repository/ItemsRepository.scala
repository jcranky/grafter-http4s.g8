package $package$.repository

import $package$.main.ApplicationConfig
import org.zalando.grafter.macros.reader

@reader[ApplicationConfig]
case class ItemsRepository()
