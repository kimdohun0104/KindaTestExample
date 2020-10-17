package dohun.kim.count

import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_dsl.buildReducer
import dohun.kim.kinda.kinda_dsl.buildSideEffectHandler

class CountViewModel(
    private val countRepository: CountRepository
) : KindaViewModel<CountState, CountEvent, CountSideEffect>(
    initialState = CountState(),
    reducer = buildReducer {
        whenEvent<CountEvent.SetCount> {
            next(copy(count = it.count))
        }

        whenEvent<CountEvent.AttemptRequestCount> {
            dispatch(CountSideEffect.RequestCount)
        }

        whenEvent<CountEvent.Increase> {
            next(copy(count = count + 1))
        }

        whenEvent<CountEvent.Decrease> {
            next(copy(count = count - 1))
        }
    },
    sideEffectHandler = buildSideEffectHandler {
        whenSideEffect<CountSideEffect.RequestCount> {
            CountEvent.SetCount(countRepository.requestCount())
        }
    }
)