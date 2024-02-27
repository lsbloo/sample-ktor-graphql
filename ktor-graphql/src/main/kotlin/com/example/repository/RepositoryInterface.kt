package com.example.repository

import com.example.models.Model
import com.mongodb.client.MongoCollection
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.updateOne

interface RepositoryInterface<T> {
    var col: MongoCollection<T>

    fun getById(id: String): T {
        return try {
            col.findOne(Model::id eq id) ?: throw Exception("Error to obtain data with id")
        } catch (t: Throwable) {
            throw Exception("Cannot find data")
        }
        finally {
            println("Urrr")
        }
    }


    fun getAll(): List<T> {
        return try {
            val res = col.find()
            res.asIterable().map { it }
        }catch (t: Throwable) {
            throw Exception("Cannot find data")
        }
    }

    fun deleteById(id: String): Boolean {
        return try {
            col.findOneAndDelete(Model::id eq  id) ?: throw Exception("No data with id exists")
            true
        }catch (t: Throwable) {
            throw Exception("cannot delete data")
        }
    }

    fun add(entry: T): T {
        return try {
            col.insertOne(entry)
            println("Created!")
            entry
        }catch (t: Throwable) {
            throw Exception("cannot add data")
        }
    }
    fun update(entry: Model): T {
        return try {
            col.updateOne(Model::id eq  entry.id, entry) ?: throw Exception("cannot update data")
            col.findOne(Model::id eq entry.id) ?: throw Exception("No data with id exists")
        }catch (t: Throwable) {
            throw Exception("cannot update data")
        }
    }
}