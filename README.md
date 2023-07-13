<br />
<h1 align="center">NiceHash Test Jetpack Compose Sample App</h1>

![MinAPI](https://badgen.net/badge/MinAPI/28/green/)
[![Compose](https://img.shields.io/badge/compose-1.4.8-red.svg?)](https://developer.android.com/jetpack/compose)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.8.22-blue.svg?logo=kotlin)](http://kotlinlang.org)
![Gradle](https://img.shields.io/badge/gradle-8.0-blue.svg?)
[![Android Studio](https://img.shields.io/badge/Android_Sudio-2022.2.1_Patch_2-green.svg?)](https://developer.android.com/studio)

<p align="center">
 <img src="docs/accumulator_screen.png" width="25%"/>
  <img src="docs/passport_screen.png" width="25%"/>
</p>

## Table of Contents

* [About the Project](#about-the-project)
* [Features](#features)
* [Test](#test)
  * [Results](#results)

## About The Project
App made with kotlin using Jetpack Compose.

## Features
- Users can test two algorithms.
- User can change which test data want to use
- User can manually change data

## Test
  - androidTest for both screens
  - unitTest for repository

  ### Results

- Question 1
  - <b>Immediately before any instruction is executed a second time,
    what value is in the accumulator? </b>
  - Result: <b> 1087 </b>
- Question 2
  - <b>Count the number of valid passports - those that have all required fields. Treat cid as optional. In
    your batch file, how many passports are valid? </b>
  - Result: <b> 22 </b>