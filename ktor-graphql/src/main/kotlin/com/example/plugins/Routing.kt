package com.example.plugins

import com.apurebase.kgraphql.GraphQL
import io.ktor.server.application.*

fun Application.configureRouting() {
    install(GraphQL) {
        playground = true
        schema {
            query("hello") {
                resolver {
                    -> "Hello World"
                }
            }
        }
    }
}
