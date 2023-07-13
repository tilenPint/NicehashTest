package com.example.nicehashtest.screen.passport

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.example.nicehashtest.data.TestSpec
import com.example.nicehashtest.data.getPassportTitle
import com.example.nicehashtest.data.repository.FakePassportRepository
import com.example.nicehashtest.data.repository.FakeReadFileRepository
import com.example.nicehashtest.ui.theme.NicehashTestTheme
import io.kotest.matchers.shouldBe
import org.junit.Rule
import org.junit.Test

class AccumulatorScreenTest {
    @get:Rule
    val rule = createComposeRule()

    private val fakeAccumulatorRepository = FakePassportRepository()
    private val fakeReadFileRepository = FakeReadFileRepository()
    private val viewModel = PassportViewModel(fakeReadFileRepository, fakeAccumulatorRepository)

    @Test
    fun shouldReadFileAndGetResultOnCreation() {
        rule.setContent {
            NicehashTestTheme {
                PassportScreen(viewModel = viewModel) {}
            }
        }

        fakeReadFileRepository.readFile shouldBe true
        fakeAccumulatorRepository.getSingleResult shouldBe true
        fakeAccumulatorRepository.getMultiResult shouldBe true
    }

    @Test
    fun changeDataTitle() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val testData = TestSpec.SHORT_TEST
        rule.setContent {
            NicehashTestTheme {
                PassportScreen(viewModel = viewModel) {}
            }
        }

        // GIVEN
        rule.onNodeWithTag("title")
            .assertTextContains(context.getString(viewModel.currentState.dataTitleRes))

        // WHEN
        viewModel.onTriggerEvent(PassportViewEvent.ChangeTestData(testData))

        // THEN
        rule.onNodeWithTag("title")
            .assertTextContains(context.getString(testData.getPassportTitle()))
    }

    @Test
    fun showExpandableItems() {
        rule.setContent {
            NicehashTestTheme {
                PassportScreen(viewModel = viewModel) {}
            }
        }

        // GIVEN
        rule.onAllNodesWithTag("expandable items").assertCountEquals(0)

        // WHEN
        rule.onNodeWithTag("expandable icon").performClick()

        // THEN
        rule.onAllNodesWithTag("expandable items").assertCountEquals(3)
    }
}
