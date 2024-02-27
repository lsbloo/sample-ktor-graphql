package com.example

import com.apurebase.kgraphql.GraphQL
import com.example.di.mainModule
import com.example.graphql.dessertSchema
import com.example.services.DessertService
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.context.startKoin

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    startKoin {
        modules(mainModule)
    }

    install(GraphQL) {
        playground = true
        schema {
            dessertSchema(DessertService())
        }
    }
}