package com.karam.easymvi.features.authentication.useCases

import com.karam.easymvi.features.authentication.data.apis.AuthenticationDataSource
import com.karam.easymvi.features.authentication.data.model.LoginResponse
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.powermock.api.mockito.PowerMockito

internal class LoginUseCaseTest {
    private val fakeAuthDataSource = PowerMockito.mock(AuthenticationDataSource::class.java)
    private val useCase = LoginUseCase(fakeAuthDataSource)

    @Test
    fun `userLogin is returning valid user object when auth data source succeeded`() =
        runBlockingTest {
            // Given
            val loginResponse = LoginResponse(
                accessToken = "token",
                tokenType = "some type",
                userName = "karam"
            )
            PowerMockito.`when`(
                fakeAuthDataSource.userLogin(eq("karam"), any())
            ).thenReturn(
                loginResponse
            )

            // When
            val realLoginResponse = useCase.userLogin(
                "karam", "123456"
            )

            // Then
            Assert.assertEquals(
                "token", realLoginResponse.accessToken
            )
            Assert.assertEquals(
                "some type", realLoginResponse.tokenType
            )
            Assert.assertEquals(
                "karam", realLoginResponse.userName
            )

        }

    @Test
    fun `userLogin is throwing Exception when userName is empty`() =  runBlockingTest {
        // Given

        // When
        val exception = Assert.assertThrows(Exception::class.java) {
            runBlocking {
                useCase.userLogin(
                    "", "123456"
                )
            }
        }

        // Then
        Assert.assertEquals("Username is empty", exception.message)
    }

    @Test
    fun `userLogin is throwing Exception when password is empty`() =  runBlockingTest {
        // Given

        // When
        val exception = Assert.assertThrows(Exception::class.java) {
            runBlocking {
                useCase.userLogin("karam", "")
            }
        }
        // Then
        Assert.assertEquals("Password is empty", exception.message)
    }
}