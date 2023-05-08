package com.mercari.homecatalogue.viewmodel

import com.mercari.domain.productcatalogue.usecase.GetProductCatalogueUseCase
import com.mercari.homecatalogue.failureResultNoDataFound
import com.mercari.homecatalogue.noDataFoundErrorData
import com.mercari.homecatalogue.productCatalogueItemsList
import com.mercari.model.domain.productcatalogue.ProductCatalogueItem
import com.mercari.model.domain.shared.error.FailureResult
import com.mercari.model.domain.shared.result.ResultData
import com.mercari.model.presentation.shared.ErrorData
import com.mercari.presentation.shared.transformations.Transformations
import com.utils.testutils.coroutines.base.BaseUnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class HomeCatalogueViewModelTest : BaseUnitTest() {

    private lateinit var viewModel: HomeCatalogueViewModel
    private lateinit var getManCatalogueUseCase: GetProductCatalogueUseCase
    private lateinit var getWomenCatalogueUseCase: GetProductCatalogueUseCase
    private lateinit var getAllCatalogueUseCase: GetProductCatalogueUseCase
    private lateinit var errorTransformations: Transformations<FailureResult, ErrorData>

    @Before
    override fun setup() {
        super.setup()
        getManCatalogueUseCase = mock()
        getWomenCatalogueUseCase = mock()
        getAllCatalogueUseCase = mock()
        errorTransformations = mock()

        viewModel = HomeCatalogueViewModel(
            dispatchersProvider = dispatchersProvider,
            getManProductCatalogueUseCase = getManCatalogueUseCase,
            getWomenProductCatalogueUseCase = getWomenCatalogueUseCase,
            getAllProductCatalogueUseCase = getAllCatalogueUseCase,
            errorDataTransformations = errorTransformations
        )
    }

    @Test
    fun `default state should be catalogue loading`() {
        val state = viewModel.state.value
        val expectedState = HomeCatalogueContract.State.CatalogueLoading

        assertEquals(expectedState, state)
    }

    @Test
    fun `should load man catalogue when processing load man catalogue intent`() = runTest {
        val intent = HomeCatalogueContract.Intent.LoadManCatalogue
        val spyViewModel = spy(viewModel)

        whenever(getManCatalogueUseCase.invoke())
            .thenReturn(ResultData.Success(emptyList()))

        // https://github.com/mockito/mockito-kotlin/issues/350#issuecomment-508396848
        whenever(spyViewModel.fetchProductCatalogue(getManCatalogueUseCase::invoke))
            .thenReturn(Unit)

        spyViewModel.processIntent(intent)

        launch {
            verify(getManCatalogueUseCase).invoke()
            verify(spyViewModel).fetchProductCatalogue(getManCatalogueUseCase::invoke)
        }
    }

    @Test
    fun `should load women catalogue when processing load women catalogue intent`() = runTest {
        val intent = HomeCatalogueContract.Intent.LoadWomenCatalogue
        val spyViewModel = spy(viewModel)

        whenever(getWomenCatalogueUseCase.invoke())
            .thenReturn(ResultData.Success(emptyList()))

        whenever(spyViewModel.fetchProductCatalogue(getWomenCatalogueUseCase::invoke))
            .thenReturn(Unit)

        spyViewModel.processIntent(intent)

        launch {
            verify(getWomenCatalogueUseCase).invoke()
            verify(spyViewModel).fetchProductCatalogue(getWomenCatalogueUseCase::invoke)
        }
    }

    @Test
    fun `should load all catalogue when processing load all catalogue intent`() = runTest {
        val intent = HomeCatalogueContract.Intent.LoadAllCatalogue
        val spyViewModel = spy(viewModel)

        whenever(getAllCatalogueUseCase.invoke())
            .thenReturn(ResultData.Success(emptyList()))

        whenever(spyViewModel.fetchProductCatalogue(getAllCatalogueUseCase::invoke))
            .thenReturn(Unit)

        spyViewModel.processIntent(intent)

        launch {
            verify(getAllCatalogueUseCase).invoke()
            verify(spyViewModel).fetchProductCatalogue(getAllCatalogueUseCase::invoke)
        }
    }

    @Test
    fun `should update state to catalogue loaded when fetch result for man catalogue is success`() = runTest {
        val expectedState = HomeCatalogueContract.State.CatalogueLoaded(productCatalogueItemsList)
        val result = ResultData.Success(productCatalogueItemsList)

        whenever(getManCatalogueUseCase.invoke())
            .thenReturn(result)

        viewModel.fetchProductCatalogue(getManCatalogueUseCase::invoke)

        launch {
            verify(getManCatalogueUseCase).invoke()
            val resultState = viewModel.state.first()
            assertEquals(expectedState, resultState)
        }
    }

    @Test
    fun `should update state to catalogue load error when fetch result for man catalogue is failure`() = runTest {
        val expectedState = HomeCatalogueContract.State.CatalogueLoadError(noDataFoundErrorData)
        val result = ResultData.Failure<List<ProductCatalogueItem>>(failureResultNoDataFound)

        whenever(errorTransformations.transformTo(any()))
            .thenReturn(noDataFoundErrorData)
        whenever(getManCatalogueUseCase.invoke())
            .thenReturn(result)

        viewModel.fetchProductCatalogue(getManCatalogueUseCase::invoke)

        launch {
            verify(getManCatalogueUseCase).invoke()
            verify(errorTransformations).transformTo(any())
            val resultState = viewModel.state.first()
            assertEquals(expectedState, resultState)
        }
    }

    @Test
    fun `should update state to catalogue loaded when fetch result for women catalogue is success`() = runTest {
        val expectedState = HomeCatalogueContract.State.CatalogueLoaded(productCatalogueItemsList)
        val result = ResultData.Success(productCatalogueItemsList)

        whenever(getWomenCatalogueUseCase.invoke())
            .thenReturn(result)

        viewModel.fetchProductCatalogue(getWomenCatalogueUseCase::invoke)

        launch {
            verify(getWomenCatalogueUseCase).invoke()
            val resultState = viewModel.state.first()
            assertEquals(expectedState, resultState)
        }
    }

    @Test
    fun `should update state to catalogue load error when fetch result for women catalogue is failure`() = runTest {
        val expectedState = HomeCatalogueContract.State.CatalogueLoadError(noDataFoundErrorData)
        val result = ResultData.Failure<List<ProductCatalogueItem>>(failureResultNoDataFound)

        whenever(errorTransformations.transformTo(any()))
            .thenReturn(noDataFoundErrorData)
        whenever(getWomenCatalogueUseCase.invoke())
            .thenReturn(result)

        viewModel.fetchProductCatalogue(getWomenCatalogueUseCase::invoke)

        launch {
            verify(getWomenCatalogueUseCase).invoke()
            verify(errorTransformations).transformTo(any())
            val resultState = viewModel.state.first()
            assertEquals(expectedState, resultState)
        }
    }

    @Test
    fun `should update state to catalogue loaded when fetch result for all catalogue is success`() = runTest {
        val expectedState = HomeCatalogueContract.State.CatalogueLoaded(productCatalogueItemsList)
        val result = ResultData.Success(productCatalogueItemsList)

        whenever(getAllCatalogueUseCase.invoke())
            .thenReturn(result)

        viewModel.fetchProductCatalogue(getAllCatalogueUseCase::invoke)

        launch {
            verify(getAllCatalogueUseCase).invoke()
            val resultState = viewModel.state.first()
            assertEquals(expectedState, resultState)
        }
    }

    @Test
    fun `should update state to catalogue load error when fetch result for all catalogue is failure`() = runTest {
        val expectedState = HomeCatalogueContract.State.CatalogueLoadError(noDataFoundErrorData)
        val result = ResultData.Failure<List<ProductCatalogueItem>>(failureResultNoDataFound)

        whenever(errorTransformations.transformTo(any()))
            .thenReturn(noDataFoundErrorData)
        whenever(getAllCatalogueUseCase.invoke())
            .thenReturn(result)

        viewModel.fetchProductCatalogue(getAllCatalogueUseCase::invoke)

        launch {
            verify(getAllCatalogueUseCase).invoke()
            verify(errorTransformations).transformTo(any())
            val resultState = viewModel.state.first()
            assertEquals(expectedState, resultState)
        }
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(
            getManCatalogueUseCase,
            getWomenCatalogueUseCase,
            getAllCatalogueUseCase,
            errorTransformations
        )
    }
}
