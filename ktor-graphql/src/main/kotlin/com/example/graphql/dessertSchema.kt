package com.example.graphql

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.example.models.Dessert
import com.example.models.DessertInput
import com.example.repository.DessertRepository
import com.example.services.DessertService


fun SchemaBuilder.dessertSchema(dessertService: DessertService) {



    query("dessert") {
        resolver { dessertId: String ->
            dessertService.getDessert(dessertId)
        }
    }

    query("desserts") {
        resolver {
            ->
            dessertService.getAllDesserts()
        }
    }

    query("dessertsPage") {
        resolver {
            page: Int?, size:Int? ->
            dessertService.getDessertsPage(page ?: 0,size ?: 10)
        }
    }

    mutation("createDessert") {
        description = "Create a new dessert"
        resolver { dessertInput: DessertInput ->

            try {
                val userId = "abc"
                dessertService.createDessert(dessertInput, userId)
            } catch (e: Exception) {
                null
            }
        }
    }

    mutation("updateDessert") {
        description = "Update a dessert"
        resolver { dessertId: String,dessert: DessertInput ->

            try {
                val userId = "abc"
                val dessertUp =  dessertService.updateDessert(userId, dessertId = dessertId, dessert)
                dessertUp
            } catch (e: Exception) {
                null
            }

        }
    }

    mutation("deleteDessert") {
        description = "Delete a dessert"
        resolver { dessertId: String ->
            val userId = "abc"
            dessertService.deleteDessert(userId,dessertId)
        }
    }

    type<Dessert>() {
        description = "Dessert object with attributes name,description and imageUrl"
    }


    inputType<DessertInput>() {
        description = "The input of the dessert without identifier"
    }
}