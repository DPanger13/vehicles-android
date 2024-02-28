package com.dpanger.vehicles.home

import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HomeViewModelUnitTest {
    private val useCase: LoadManufacturersUseCase = mockk()
    private lateinit var viewModel: HomeViewModel

    @BeforeEach
    fun setUp() {
        val dispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun whenErrorLoadingData_thenUiStateIsError() =
        runTest {
            coEvery { useCase.invoke() } returns HomeUiState.Error

            viewModel = HomeViewModel(useCase)

            viewModel.uiState.value shouldBe HomeUiState.Error
        }

    @Test
    fun whenSuccessfullyLoadedData_thenUiStateIsSuccess() =
        runTest {
            val manufacturers = emptyList<UiManufacturer>().toImmutableList()
            coEvery { useCase.invoke() } returns HomeUiState.Success(manufacturers)

            viewModel = HomeViewModel(useCase)

            viewModel.uiState.value shouldBe HomeUiState.Success(manufacturers)
        }
}