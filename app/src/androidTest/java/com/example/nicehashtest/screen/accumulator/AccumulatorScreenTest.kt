package com.example.nicehashtest.screen.accumulator

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.nicehashtest.data.AccumulatorTest
import com.example.nicehashtest.ui.theme.NicehashTestTheme
import org.junit.Rule
import org.junit.Test

class AccumulatorScreenTest {
    @get:Rule
    val rule = createComposeRule()

    private val viewModel = FakeViewModel()

    @Test
    fun showDataLong() {
        rule.setContent {
            NicehashTestTheme {
                AccumulatorScreen(viewModel = viewModel) {}
            }
        }

        viewModel.onTriggerEvent(AccumulatorViewEvent.ChangeTestData(AccumulatorTest.LONG_TEST))

        rule.onNodeWithTag("result").assertTextContains("1087")
    }

    @Test
    fun showDataShort() {
        rule.setContent {
            NicehashTestTheme {
                AccumulatorScreen(viewModel = viewModel) {}
            }
        }

        viewModel.onTriggerEvent(AccumulatorViewEvent.ChangeTestData(AccumulatorTest.SHORT_TEST))

        rule.onNodeWithTag("result").assertTextContains("5")
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
