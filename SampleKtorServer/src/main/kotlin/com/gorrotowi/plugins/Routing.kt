package com.gorrotowi.plugins

import com.gorrotowi.models.Product
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureRouting() {

    val products = mutableListOf<Product>()
    products.add(Product(1, "Jam", 3.44, "Delicious strawberry jam"))
    products.add(Product(2, "Bread", 2.10, "Handmade bread"))
    products.add(Product(3, "Soda", 1.20, "Not a healthy beverage but hey! It's ok for me"))
    products.add(Product(4, "Beer", 5.10, "Not not alcoholic beer, u know what I mean"))
    products.add(Product(5, "Milk", 6.00, "Just for strong bones with less milk and more MALK"))
    products.add(Product(6, "Orange Juice", 2.99, "Ok, we know, but it's not only paint ok?"))

    routing {
        get("/") {
            call.respondText("Hello Android!")
        }

        get("/allProducts") {
            call.respond(products)
        }

        post("/addProduct") {
            val newProduct = call.receive<Product>()
            val lastId = (products.last().id ?: 0) + 1
            val newProductToAdd = newProduct.copy(id = lastId)
            products.add(newProductToAdd)
            call.respond(newProductToAdd)
        }

        delete("/product") {
            val idToDelete = call.request.queryParameters["id"]?.toIntOrNull()
            if (idToDelete != null) {
                val product = products.find { it.id == idToDelete }
                if (product != null) {
                    products.remove(product)
                    call.respond(products)
                } else {
                    call.respond(HttpStatusCode.NotFound, mapOf("error" to "The id provided was not related to any product"))
                }
            } else {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to "The id provided was not related to any product"))
            }
        }

        post("/updateProduct") {
            val idToUpdate = call.request.queryParameters["id"]?.toIntOrNull()
            if (idToUpdate != null) {
                val productToUpdate = call.receive<Product>()
                val localProduct = products.find { it.id == idToUpdate }
                if (localProduct!=null) {
                    val productUpdated = localProduct?.copy(
                        id = idToUpdate,
                        name = productToUpdate.name,
                        price = productToUpdate.price,
                        description = productToUpdate.description
                    )
                    val indexToUpdate = products.indexOf(localProduct)
                    productUpdated?.let { newProduct -> products.set(indexToUpdate, newProduct) }
                    call.respond(products)
                } else {
                    call.respond(mapOf("error" to "The id provided was not related to any product"))
                }
            } else {
                call.respond(mapOf("error" to "The id provided was not related to any product"))
            }
        }

    }
}
