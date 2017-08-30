package $package$.services

import $package$.main.ApplicationConfig
import $package$.repository.ItemsRepository

import org.zalando.grafter.macros.reader

@reader
case class ItemsService(repo: ItemsRepository)
