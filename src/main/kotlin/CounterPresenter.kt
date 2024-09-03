import androidx.compose.runtime.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

sealed class CounterAction {
    object Increment : CounterAction()
    object Decrement : CounterAction()
}

data class CounterState(val count: Int)
/*

有哪些remember
有哪些mutablestatof

 */

@Composable
fun CounterPresenter(action: Flow<CounterAction>): CounterState {
    var count by remember { mutableStateOf(0) }
    var input by remember { mutableStateOf("") }
//    MutableStateFlow()
//    MutableSharedFlow()

    LaunchedEffect(action) {
        action.collect {
            when (it) {
                is CounterAction.Increment -> count++
                is CounterAction.Decrement -> count--
            }
        }
    }

    return CounterState(count)
}