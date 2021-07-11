package dev.plesa.data.common

interface DomainMapper<T : Any> {
    fun mapToDomain(): T
}