package com.mercari.model.presentation.shared

sealed interface HomeCatalogueType {
    object ManCatalogue : HomeCatalogueType {
        override fun toString(): String = javaClass.simpleName
    }
    object WomenCatalogue : HomeCatalogueType {
        override fun toString(): String = javaClass.simpleName
    }
    object AllCatalogue : HomeCatalogueType {
        override fun toString(): String = javaClass.simpleName
    }
}