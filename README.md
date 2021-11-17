# Missing class cast warning

## Run the example

Use `./gradlew run` or similar invocation to run the main function.

## Explanation

Given a function (`ExceptionInterface.handleException`) which accepts a 
parameter of type `ExceptionInterface`,

We find we can write a cast which should not work, and a warning is not always
issued, depending on the code structure.

In particular,

* Main.thisSeesClassCastProblem    -- warning issued
* Main.analysisSeesWrongClass      -- no warning
* Main.alsoMissesClassCastProblem  -- no warning
