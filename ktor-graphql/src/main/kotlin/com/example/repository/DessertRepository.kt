package com.example.repository

import com.example.models.Dessert
import com.example.models.DessertsPage
import com.example.models.PageInfo
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import org.litote.kmongo.getCollection

class DessertRepository(client: MongoClient) : RepositoryInterface<Dessert> {

    override var col: MongoCollection<Dessert>

    init {
        val database = client.getDatabase("test")
        col = database.getCollection<Dessert>("Dessert")
    }

    fun getDesertsPage(page: Int, size: Int): DessertsPage {
        try {
            val skips = page * size
            val res = col.find().skip(skips).limit(size)
            val resultsx = res.asIterable().map { it }

            val total = col.estimatedDocumentCount()
            val totalPages = (total / size) + 1
            val nextPages = if (resultsx.isNotEmpty()) page + 1 else null
            val previous = if (page > 0) page - 1 else null


            val pageInfo = PageInfo(total.toInt(), totalPages.toInt(), nextPages, previous)

            return DessertsPage.initialize {
                results = resultsx
                info = pageInfo
            }

        } catch (t: Throwable) {
            throw Exception("Cannot get desserts pages")
        }
    }
}