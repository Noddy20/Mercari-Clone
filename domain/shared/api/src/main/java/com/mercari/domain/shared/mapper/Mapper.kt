package com.mercari.domain.shared.mapper

interface Mapper<FROM, TO> {
    fun mapTo(from: FROM): TO
    fun mapFrom(to: TO): FROM {
        throw IllegalAccessError("mapFrom is not implemented for the mapper!")
    }
}