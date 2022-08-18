

# Architecture With MVI using Kotlin, Coroutines, Retrofit and Unit test
MVI (Model-View-Intent) streamlines the process of creating and developing applications by introducing a reactive approach  
  
This repository contains a sample app that implements MVI architecture using Kotlin, ViewModel, Retrofit, Coroutines and etc to make login screen and receive the results.  

#### How does the MVI work?

User does an action which will be an Intent → Intent is a state which is an input to model → Model stores state and send the requested state to the View → View Loads the state from Model → Displays to the user. If we observe, the data will always flow from the user and end with the user through intent. It cannot be the other way, Hence it's called Unidirectional architecture. If the user does one more action the same cycle is repeated, hence it is Cyclic.

![MVI Cycle Description](https://s3.ap-south-1.amazonaws.com/mindorks-server-uploads/mvi_cyclic-49d9f8c2d3fe26b7.png)
  
#### The app has the following packages:  
1. **core**: It contains (Dependency injection modules, Constants class, and Main App class).  
2. **Features**: It contains packages of screens we have in the application each screen package contains  
 - **data**: it contains APIs and modules related to that screen  
 - **UI**: it contains intents, states, and UI classes like activity, fragments..etc, and view models  
 - **use-cases**: it contains the use cases classes responsible for the business and logic of the data we received from the models.  
3. **helpers**: Helper classes.  
  
**Unit Test**: Unit-test for LoginUseCase class done as a sample you can use to have a good step with the real implementation.  
 1. [LoginUseCase class Code](app/src/test/java/com/karam/easymvi/features/authentication/useCases/LoginUseCaseTest.kt)
 
 
**Note**:

> The API link is only as an example for the explanation but it does not work You can use your own link, you can set your API link in [Build.gradle](https://github.com/AhmedKaram2/Easy-Architecture-With-MVI/blob/main/app/build.gradle)
> 

  ### Reference resources:
1. Coroutines: [Check here](https://blog.mindorks.com/mastering-kotlin-coroutines-in-android-step-by-step-guide)
2. Retrofit: [Check here](https://square.github.io/retrofit/)
3. Hilt: [Check here](https://medium.com/androiddevelopers/dependency-injection-on-android-with-hilt-67b6031e62d)
4. MVI: [Check here](https://blog.mindorks.com/mvi-architecture-android-tutorial-for-beginners-step-by-step-guide)
