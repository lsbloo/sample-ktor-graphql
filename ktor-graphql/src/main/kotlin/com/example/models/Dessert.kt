package com.example.models

data class Dessert(override val id: String, var name: String, var description: String, var imageUrl: String, val userId: String) : Model
data class DessertInput(val name: String, val description: String, val imageUrl: String)

data class PageInfo(var count: Int, var pages: Int, var next: Int?, var previous: Int?)

data class DessertsPage(var results: List<Dessert>? = null, var info: PageInfo? = null) {

    companion object {
        fun initialize(block: DessertsPage.() -> Unit): DessertsPage {
            val page = DessertsPage()
            page.block()
            return page
        }
    }
}

class D {
    var name: String = ""
}
fun createDessert(block: D.() -> Unit) {
    val f = D()
    f.block()
}

fun haha() {
    createDessert {
        this.name = "test"
    }
}