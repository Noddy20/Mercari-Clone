package com.utils.testutils.coroutines.base

import androidx.annotation.CallSuper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.utils.multithreading.coroutines.DispatchersProvider
import com.utils.testutils.coroutines.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseUnitTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    protected lateinit var dispatchersProvider: DispatchersProvider

    @Before
    @CallSuper
    open fun setup() {
        setupDispatcherProvider()
    }

    private fun setupDispatcherProvider() {
        dispatchersProvider = mock()

        whenever(dispatchersProvider.io)
            .thenReturn(mainCoroutineRule.testDispatcher)
    }
}