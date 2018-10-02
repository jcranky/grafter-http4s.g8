package $package$.repository

import $package$.main.ApplicationConfig
import org.zalando.grafter.macros.readerOf

@readerOf[ApplicationConfig]
case class ItemsRepository()
