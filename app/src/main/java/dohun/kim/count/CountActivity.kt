package dohun.kim.count

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dohun.kim.kinda.kinda_android.KindaActivity

class CountActivity : KindaActivity<CountState, CountEvent, CountSideEffect>() {

    override val viewModel: CountViewModel by lazy {
        ViewModelProvider(this, CountViewModelProvider)[CountViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)
        viewModel.intent(CountEvent.AttemptRequestCount)

        findViewById<Button>(R.id.btn_increase).setOnClickListener {
            viewModel.intent(CountEvent.Increase)
        }

        findViewById<Button>(R.id.btn_decrease).setOnClickListener {
            viewModel.intent(CountEvent.Decrease)
        }
    }

    override fun render(state: CountState) {
        findViewById<TextView>(R.id.tv_count).text = state.count.toString()
    }
}

object CountViewModelProvider : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(CountViewModel::class.java)) {
            CountViewModel(DefaultCountRepository()) as T
        } else {
            throw IllegalStateException()
        }
}