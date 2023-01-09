package com.cleanarch.codewars.demo.integration

import com.cleanarch.codewars.demo.data.network.retrofit.CodeWarsApi
import com.cleanarch.codewars.demo.data.network.response.NetworkNotFoundResponse
import com.cleanarch.codewars.demo.data.network.response.NetworkSuccessResponse
import com.cleanarch.codewars.demo.data.network.response.GetUserResponse
import com.cleanarch.codewars.demo.integration.setup.NetworkTestComponent
import com.cleanarch.codewars.demo.integration.setup.BaseNetworkTest
import junit.framework.Assert.*
import org.junit.Test
import javax.inject.Inject

class UserNetworkTest: BaseNetworkTest() {

    override fun injectTest(component: NetworkTestComponent)
        = component.inject(this)

    @Inject
    lateinit var api: CodeWarsApi

    @Test
    fun test_get_existing_user() {
        val username = "g964"
        val response = api.getUsers(username) as NetworkSuccessResponse<GetUserResponse>
        assertEquals(username, response.body.username)
    }

    @Test
    fun test_get_unexisting_user() {
        val username = "unexisting_user"
        val response = api.getUsers(username)
        assertTrue(response is NetworkNotFoundResponse<GetUserResponse>)
    }
}