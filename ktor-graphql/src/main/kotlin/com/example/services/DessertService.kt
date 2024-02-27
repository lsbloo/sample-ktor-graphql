package com.example.services

import com.example.models.Dessert
import com.example.models.DessertInput
import com.example.models.DessertsPage
import com.example.repository.DessertRepository
import com.mongodb.client.MongoClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class DessertService : KoinComponent {
    private val client: MongoClient by inject()
    private val dessertRepository = DessertRepository(client)

    fun getDessert(id: String) = dessertRepository.getById(id)

    fun createDessert(dessertInput: DessertInput, userId: String): Dessert {
            val uid = UUID.randomUUID().toString()
            val dessert = Dessert(
                id = uid,
                userId = userId,
                name = dessertInput.name,
                description = dessertInput.description,
                imageUrl = dessertInput.imageUrl
            )
            return dessertRepository.add(dessert)
    }

    fun updateDessert(userId: String, dessertId: String, dessertInput: DessertInput) : Dessert {
        val dessert = dessertRepository.getById(dessertId)

        if(dessert.userId.equals(userId)) {
            val updates = Dessert(
                id = dessertId,
                userId = userId,
                name = dessertInput.name,
                description = dessertInput.description,
                imageUrl = dessertInput.imageUrl
            )
            return dessertRepository.update(updates)
        }
        error("Cannot update dessert")
    }

    fun deleteDessert(userId: String, dessertId: String) : Boolean {
        val dessert = dessertRepository.getById(dessertId)

        if(dessert.userId == userId) {
            return dessertRepository.deleteById(dessertId)
        }
        error("Cannot delete dessert")
    }

    fun getAllDesserts() : List<Dessert> {
        return dessertRepository.getAll()
    }

    fun getDessertsPage(page: Int, size: Int): DessertsPage {
        return dessertRepository.getDesertsPage(page,size)
    }
}