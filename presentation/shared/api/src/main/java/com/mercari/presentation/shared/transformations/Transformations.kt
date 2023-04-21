package com.mercari.presentation.shared.transformations

/**
 *   Mapper for presentation layer
 */

interface Transformations<FROM, TO> {
    fun transformTo(from: FROM): TO
    fun transformFrom(to: TO): FROM {
        throw IllegalAccessError("transformFrom is not implemented for the Transformations!")
    }
}