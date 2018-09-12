package com.irmansyah.myfootball

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.espresso.matcher.ViewMatchers.*
import com.irmansyah.myfootball.R.id.*
import com.irmansyah.myfootball.ui.main.MainActivity
import com.irmansyah.myfootball.utils.EspressoIdlingResource
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppBehaviour() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource().idlingResource)

        onView(withId(match_spinner)).check(matches(isDisplayed()))
        onView(withId(match_spinner)).perform(click())
        onView(withText("Spanish La Liga")).perform(click())

        onView(withId(navigation_bottom)).check(matches(isDisplayed()))
        onView(withId(next_bottom)).perform(click())

        onView(withId(club_spinner)).check(matches(isDisplayed()))
        onView(withId(club_spinner)).perform(click())
        onView(withText("German Bundesliga")).perform(click())

        onView(withId(navigation_bottom)).check(matches(isDisplayed()))
        onView(withId(favorite_bottom)).perform(click())
    }
}