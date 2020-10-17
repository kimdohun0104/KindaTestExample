package dohun.kim.count

import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_android_test.KindaViewModelTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class CountViewModelTest : KindaViewModelTest<CountState, CountEvent, CountSideEffect>() {

    @Mock
    private lateinit var countRepository: CountRepository

    override fun buildViewModel(): KindaViewModel<CountState, CountEvent, CountSideEffect> =
        CountViewModel(countRepository)

    @Test
    fun `Increase, from count=0 to count=1`() {
        CountEvent.Increase expectState { state ->
            assertEquals(1, state.count)
        }
    }

    @Test
    fun `Increase and Decrease, maintain count=0`() {
        listOf(CountEvent.Increase, CountEvent.Decrease) expectState { state ->
            assertEquals(0, state.count)
        }
    }

    @Test
    fun `AttemptRequestCount, receive count=400`() = runBlocking {
        `when`(countRepository.requestCount())
            .thenReturn(400)

        CountEvent.AttemptRequestCount expectState { state ->
            assertEquals(400, state.count)
        }
    }
}