package com.mercari.model.data.productcatalogue.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProductCatalogueItemResponse(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("num_comments") val numComments: Int,
    @SerialName("num_likes") val numLikes: Int,
    @SerialName("photo") val photo: String,
    @SerialName("price") val price: Int,
    @SerialName("status")val status: String
)