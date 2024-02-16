package com.example.repository

import com.example.data.desserts
import com.example.models.Dessert
import java.lang.Exception

class DessertRepository : RepositoryInterface<Dessert> {

    override fun getById(id: String): Dessert {
        return try {
            desserts.find { it.id == id } ?: throw Exception("ID not exists")
        }catch (e: Exception) {
            throw Exception("cannot find the dessert")
        }
    }

    override fun getAll(): List<Dessert> {
        return desserts
    }

    override fun deleteById(id: String): Boolean {
        return try {
            val dessert = desserts.find { it.id == id } ?: throw Exception("ID not exists")
            desserts.remove(dessert)
            true
        }catch (e: Throwable) {
            throw Exception("cannot find the dessert")
        }
    }

    override fun update(entry: Dessert): Dessert {
        return try {
            val dessert = desserts.find { it.id == entry.id }.apply {
                this?.name = entry.name
            } ?: throw Exception("ID Not existis")
            dessert
        }catch (e: Exception) {
            throw Exception("cannot find the dessert")
        }
    }

    override fun add(entry: Dessert): Dessert {
        desserts.add(entry)
        return entry
    }


}