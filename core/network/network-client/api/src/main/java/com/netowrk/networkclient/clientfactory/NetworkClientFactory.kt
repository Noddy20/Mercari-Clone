package com.netowrk.networkclient.clientfactory

interface NetworkClientFactory {
    fun <T> create(clientService: Class<T>): T
}