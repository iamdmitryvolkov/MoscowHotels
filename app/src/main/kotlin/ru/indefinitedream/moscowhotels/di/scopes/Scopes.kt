package ru.indefinitedream.moscowhotels.di.scopes

import javax.inject.Scope

/**
 * Scope for objects, existing every time
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

/**
 * Scope for [ru.indefinitedream.moscowhotels.ui.HotelsActivity]
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class HotelsScope

/**
 * Scope for [ru.indefinitedream.moscowhotels.ui.DetailsActivity]
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class DetailsScope