# Easy-Architecture-With-MVI
MVI (Model-View-Intent) streamlines the process of creating and developing applications by introducing a reactive approach

This repository contains a sample app that implements MVI architecture using Kotlin, ViewModel, Retrofit, Coroutines and etc to make login screen and receive the results.

#### The app has following packages:
1. **core**: It contains (Dependency injection modules , Constants class , Main App class).
2. **Features**: It contains packages of screens we have in the application each screen package contains
(
  - **data**: it contains APIs and modules related to that screen
   - **UI**: it contains intents, states, and UI classes like activity, fragments..etc, and view models
   - **use-cases**: it contains the use cases classes responsible for the business and logic of the data we received from the models.
)
3. **helpers**: Helper classes.

**Unit Test:** Unit-test for LoginUseCase class done as a sample you can use to have a good step with real implementation.
 1. [LoginUseCase class Code](app/src/test/java/com/karam/easymvi/features/authentication/useCases/LoginUseCaseTest.kt)



