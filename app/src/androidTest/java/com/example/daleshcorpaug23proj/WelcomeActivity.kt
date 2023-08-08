package com.example.daleshcorpaug23proj

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.daleshcorpaug23proj.news.view.welcome.WelcomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WelcomeActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<WelcomeActivity> =
        ActivityScenarioRule(WelcomeActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.welcome_text)).check(matches(withText("WELCOME")))
        onView(withId(R.id.inform_text)).check(matches(withText("We will inform you with all the right stuff")))
        onView(withId(R.id.subscribe_button)).check(matches(withText("Subscribe")))
    }

    @Test
    fun test_clickSubscribeButton() {
        onView(withId(R.id.subscribe_button)).perform(click())
        // Add checks for expected results after clicking the button
    }
}
