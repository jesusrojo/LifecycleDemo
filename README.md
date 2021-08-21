#### LifecycleDemo

Android playground project to learn the
[Activity lifecycle](https://developer.android.com/guide/components/activities/activity-lifecycle)
and the [Fragments lifecycle](https://developer.android.com/guide/fragments/lifecycle).

The app display the logs from the lifecycle in a `TextView`.

The `MainActivity` is the container for the  `MainFragment` and `SecondFragment`.

Also you can navigate to a `SecondActivity` and a `ForResultActivity`.

The `Presenter` implements [`LifecycleObserver`](https://developer.android.com/reference/androidx/lifecycle/LifecycleObserver)

Use:
- [Kotlin](https://kotlinlang.org/docs/home.html)
- [LifecycleObserver](https://developer.android.com/topic/libraries/architecture/lifecycle)
- [Navigation](https://developer.android.com/guide/navigation/)
- [Leak Canary](https://github.com/square/leakcanary)
- [Timber](https://github.com/JakeWharton/timber)
