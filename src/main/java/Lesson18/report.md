```kotlin

// Определяем типы возможных алертов
enum class AlertDataType {
    SNACKBAR,
    POPUP
}

// Определяем менеджер, который будет управлять алертами
interface AlertManager {

    val alert: StateFlow<AlertData?>

    fun showSuccess(message: String, alertDataType: AlertDataType)
    fun showError(message: String, alertDataType: AlertDataType)
    fun showInfo(message: String, alertDataType: AlertDataType)

    fun showError(exception: Throwable, alertDataType: AlertDataType)
}


// Определяем общий интерфейс для данных, содержащихся в алертах и делаем его изолированным, чтобы знать
// всех наследников на этапе компиляции
sealed interface AlertData {}

// Общий интерфейс для типов Snackbar
sealed interface SnackbarAlertData : AlertData, SnackbarVisuals {
    val containerColor: Color
    val contentColor: Color
    val iconResId: Int
}

data class SuccessSnackbarAlertData(
    override val message: String,
    //...
) : SnackbarAlertData

data class ErrorSnackbarAlertData(
    override val message: String,
    //...
) : SnackbarAlertData


data class InfoSnackbarAlertData(
    override val message: String,
    //...
) : SnackbarAlertData


// Общий интерфейс для типов Popup
sealed interface PopupAlertData : AlertData {
    val message: String

    data class SuccessPopup(override val message: String) : PopupAlertData
    data class ErrorPopup(override val message: String) : PopupAlertData
    data class InfoPopup(override val message: String) : PopupAlertData
}

// Реализация менеджера
class AlertManagerImpl @Inject constructor() : AlertManager {

    private var _alert: MutableStateFlow<AlertData?> = MutableStateFlow(null)
    override val alert: StateFlow<AlertData?> = _alert

    override fun showSuccess(message: String, alertDataType: AlertDataType) {
        when (alertDataType) {
            AlertDataType.SNACKBAR -> _alert.value = SuccessSnackbarAlertData(message)
            AlertDataType.POPUP -> _alert.value = PopupAlertData.SuccessPopup(message)
        }
    }

    override fun showError(message: String, alertDataType: AlertDataType) {
        when (alertDataType) {
            AlertDataType.SNACKBAR -> _alert.value = ErrorSnackbarAlertData(message)
            AlertDataType.POPUP -> _alert.value = PopupAlertData.ErrorPopup(message)
        }
    }

    override fun showError(exception: Throwable, alertDataType: AlertDataType) {
        when (alertDataType) {
            AlertDataType.SNACKBAR -> _alert.value = ErrorSnackbarAlertData(exception.stackTraceToString())
            AlertDataType.POPUP -> _alert.value = PopupAlertData.ErrorPopup(exception.stackTraceToString())
        }
    }

    override fun showInfo(message: String, alertDataType: AlertDataType) {
        when (alertDataType) {
            AlertDataType.SNACKBAR -> _alert.value = InfoSnackbarAlertData(message)
            AlertDataType.POPUP -> _alert.value = PopupAlertData.InfoPopup(message)
        }
    }
}

// Подписываемся на изменения данных AlertManager в главной активити и отображаем popup либо snackbar
class DefaultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val alertManager: AlertManager = AlertManagerImpl()
        lifecycleScope.launch {
            alertManager.alert.collect {
                when (it) {
                    is PopupAlertData -> {
                        showPopup(it)
                    }

                    is SnackbarAlertData -> {
                        showSnackbar(it)
                    }

                    null -> {}
                }
            }
        }
    }
}

// Внутри компонентов экранов просто передаем менеджеру нужную информацию 
class ScreenComponent(private val alertManager: AlertManager) {
    fun showError(errorMessage: String) {
        alertManager.showError(errorMessage, AlertDataType.POPUP)
    }
}
```

Таким образом, вместо того, чтобы на каждом экране приложения писать одинаковый код для отображения всевозможных сообщений
об ошибках, мы создали единый унифицированный интерфейс, который в одном месте хранит все типы возможных сообщений и
управляет ими, причем количество типов всегда строго задано и известно на этапе компиляции.