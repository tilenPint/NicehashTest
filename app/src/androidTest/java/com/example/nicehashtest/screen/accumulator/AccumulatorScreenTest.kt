package com.example.nicehashtest.screen.accumulator

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.example.nicehashtest.data.TestSpec
import com.example.nicehashtest.data.getAccumulatorTitle
import com.example.nicehashtest.data.repository.FakeAccumulatorRepository
import com.example.nicehashtest.data.repository.FakeReadFileRepository
import com.example.nicehashtest.ui.theme.NicehashTestTheme
import io.kotest.matchers.shouldBe
import org.junit.Rule
import org.junit.Test

class AccumulatorScreenTest {
    @get:Rule
    val rule = createComposeRule()

    private val fakeAccumulatorRepository = FakeAccumulatorRepository()
    private val fakeReadFileRepository = FakeReadFileRepository()
    private val viewModel = FakeViewModel(fakeReadFileRepository, fakeAccumulatorRepository)

    @Test
    fun shouldReadFileAndGetResultOnCreation() {
        rule.setContent {
            NicehashTestTheme {
                AccumulatorScreen(viewModel = viewModel) {}
            }
        }

        fakeReadFileRepository.readFile shouldBe true
        fakeAccumulatorRepository.getResult shouldBe true
    }

    @Test
    fun changeDataTitle() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val testData = TestSpec.SHORT_TEST
        rule.setContent {
            NicehashTestTheme {
                AccumulatorScreen(viewModel = viewModel) {}
            }
        }

        rule.onNodeWithTag("title")
            .assertTextContains(context.getString(viewModel.currentState.dataTitleRes))

        viewModel.onTriggerEvent(AccumulatorViewEvent.ChangeTestData(testData))

        rule.onNodeWithTag("title")
            .assertTextContains(context.getString(testData.getAccumulatorTitle()))
    }

    @Test
    fun showExpandableItems() {
        rule.setContent {
            NicehashTestTheme {
                AccumulatorScreen(viewModel = viewModel) {}
            }
        }

        rule.onNodeWithTag("expandable icon").performClick()

        rule.onAllNodesWithTag("expandable items").assertCountEquals(2)
    }
}
