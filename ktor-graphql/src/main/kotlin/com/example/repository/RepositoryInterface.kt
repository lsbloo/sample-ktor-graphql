package com.example.repository

interface RepositoryInterface<T> {
    fun getById(id: String): T
    fun getAll(): List<T>
    fun deleteById(id: String): Boolean
    fun add(entry: T): T
    fun update(entry: T): T
}