package com.atharok.btremote.common.injections

import android.bluetooth.BluetoothHidDevice
import android.bluetooth.BluetoothHidDeviceAppSdpSettings
import android.bluetooth.BluetoothManager
import android.content.Context
import android.hardware.SensorManager
import android.hardware.display.DisplayManager
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.bluetoothHidDescriptor
import com.atharok.btremote.data.bluetooth.BluetoothHidCore
import com.atharok.btremote.data.bluetooth.BluetoothLocalData
import com.atharok.btremote.data.bluetooth.BluetoothScanner
import com.atharok.btremote.data.bluetooth.BluetoothStatusChecker
import com.atharok.btremote.data.dataStore.SettingsDataStore
import com.atharok.btremote.data.repositories.BluetoothRepositoryImpl
import com.atharok.btremote.data.repositories.DataStoreRepositoryImpl
import com.atharok.btremote.data.repositories.GyroscopeSensorRepositoryImpl
import com.atharok.btremote.data.sensor.GyroscopeSensor
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.ANSIEnglishUSAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.BulgarianAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.CzechAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.EnglishUKAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.EnglishUSAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.FrenchAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.GermanAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.GreekAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.HebrewAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.PersianAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.PolishAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.PortugueseAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.PortugueseBRAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.RussianAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.SpanishAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.TurkishAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.advancedKeyboard.UkrainianAdvancedKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.ANSIEnglishUSVirtualKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.BulgarianVirtualKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.CzechVirtualKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.EnglishUKVirtualKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.EnglishUSVirtualKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.FrenchVirtualKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.GermanVirtualKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.GreekVirtualKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.HebrewVirtualKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.PersianVirtualKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.PolishVirtualKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.PortugueseBRVirtualKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.PortugueseVirtualKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.RussianVirtualKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.SpanishVirtualKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.TurkishVirtualKeyboardLayout
import com.atharok.btremote.domain.entities.remoteInput.keyboard.virtualKeyboard.UkrainianVirtualKeyboardLayout
import com.atharok.btremote.domain.repositories.BluetoothRepository
import com.atharok.btremote.domain.repositories.DataStoreRepository
import com.atharok.btremote.domain.repositories.GyroscopeSensorRepository
import com.atharok.btremote.domain.usecases.AppScopeUseCase
import com.atharok.btremote.domain.usecases.BluetoothActivationUseCase
import com.atharok.btremote.domain.usecases.BluetoothHidServiceUseCase
import com.atharok.btremote.domain.usecases.DeviceDiscoveryUseCase
import com.atharok.btremote.domain.usecases.DeviceSelectionUseCase
import com.atharok.btremote.domain.usecases.GyroscopeSensorUseCase
import com.atharok.btremote.domain.usecases.RemoteUseCase
import com.atharok.btremote.domain.usecases.SettingsUseCase
import com.atharok.btremote.presentation.viewmodel.AppScopeViewModel
import com.atharok.btremote.presentation.viewmodel.BluetoothActivationViewModel
import com.atharok.btremote.presentation.viewmodel.DeviceDiscoveryViewModel
import com.atharok.btremote.presentation.viewmodel.DeviceSelectionViewModel
import com.atharok.btremote.presentation.viewmodel.GyroscopeSensorViewModel
import com.atharok.btremote.presentation.viewmodel.RemoteViewModel
import com.atharok.btremote.presentation.viewmodel.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import java.util.Locale

val appModules by lazy {
    listOf<Module>(androidModule, viewModelModule, useCaseModule, repositoryModule, dataModule)
}

private val androidModule: Module = module {
    single<SensorManager> {
        androidContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    single<DisplayManager> {
        androidContext().getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
    }

    single<BluetoothManager> {
        androidContext().getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    }

    single<BluetoothHidDeviceAppSdpSettings> {
        BluetoothHidDeviceAppSdpSettings(
            androidContext().getString(R.string.app_name),
            "Bluetooth HID Device",
            "Atharok",
            BluetoothHidDevice.SUBCLASS2_UNCATEGORIZED,
            bluetoothHidDescriptor
        )
    }

    single { ANSIEnglishUSVirtualKeyboardLayout() }
    single { EnglishUSVirtualKeyboardLayout() }
    single { EnglishUKVirtualKeyboardLayout() }
    single { SpanishVirtualKeyboardLayout() }
    single { FrenchVirtualKeyboardLayout() }
    single { GermanVirtualKeyboardLayout() }
    single { RussianVirtualKeyboardLayout() }
    single { CzechVirtualKeyboardLayout() }
    single { PolishVirtualKeyboardLayout() }
    single { PortugueseVirtualKeyboardLayout() }
    single { PortugueseBRVirtualKeyboardLayout() }
    single { GreekVirtualKeyboardLayout() }
    single { TurkishVirtualKeyboardLayout() }
    single { HebrewVirtualKeyboardLayout() }
    single { BulgarianVirtualKeyboardLayout() }
    single { UkrainianVirtualKeyboardLayout() }
    single { PersianVirtualKeyboardLayout() }

    single { ANSIEnglishUSAdvancedKeyboardLayout(context = androidContext()) }
    single { EnglishUSAdvancedKeyboardLayout(context = androidContext()) }
    single { EnglishUKAdvancedKeyboardLayout(context = androidContext()) }
    single { SpanishAdvancedKeyboardLayout(context = androidContext()) }
    single { FrenchAdvancedKeyboardLayout(context = androidContext()) }
    single { GermanAdvancedKeyboardLayout(context = androidContext()) }
    single { RussianAdvancedKeyboardLayout(context = androidContext()) }
    single { CzechAdvancedKeyboardLayout(context = androidContext()) }
    single { PolishAdvancedKeyboardLayout(context = androidContext()) }
    single { PortugueseAdvancedKeyboardLayout(context = androidContext()) }
    single { PortugueseBRAdvancedKeyboardLayout(context = androidContext()) }
    single { GreekAdvancedKeyboardLayout(context = androidContext()) }
    single { TurkishAdvancedKeyboardLayout(context = androidContext()) }
    single { HebrewAdvancedKeyboardLayout(context = androidContext()) }
    single { BulgarianAdvancedKeyboardLayout(context = androidContext()) }
    single { UkrainianAdvancedKeyboardLayout(context = androidContext()) }
    single { PersianAdvancedKeyboardLayout(context = androidContext()) }

    factory<Locale> {
        androidContext().resources.configuration.locales[0]
    }
}

private val viewModelModule: Module = module {
    viewModel {
        AppScopeViewModel(
            useCase = get<AppScopeUseCase>()
        )
    }

    viewModel {
        GyroscopeSensorViewModel(
            useCase = get<GyroscopeSensorUseCase>()
        )
    }

    viewModel {
        SettingsViewModel(
            useCase = get<SettingsUseCase>()
        )
    }

    viewModel {
        BluetoothActivationViewModel(
            useCase = get<BluetoothActivationUseCase>()
        )
    }

    viewModel {
        DeviceSelectionViewModel(
            useCase = get<DeviceSelectionUseCase>()
        )
    }

    viewModel {
        DeviceDiscoveryViewModel(
            useCase = get<DeviceDiscoveryUseCase>()
        )
    }

    viewModel {
        RemoteViewModel(
            useCase = get<RemoteUseCase>()
        )
    }
}

private val useCaseModule: Module = module {
    single {
        AppScopeUseCase(
            bluetoothRepository = get<BluetoothRepository>(),
            dataStoreRepository = get<DataStoreRepository>()
        )
    }

    single {
        GyroscopeSensorUseCase(
            repository = get<GyroscopeSensorRepository>()
        )
    }

    single {
        BluetoothHidServiceUseCase(
            bluetoothRepository = get<BluetoothRepository>(),
            dataStoreRepository = get<DataStoreRepository>()
        )
    }

    single {
        SettingsUseCase(
            dataStoreRepository = get<DataStoreRepository>()
        )
    }

    single {
        BluetoothActivationUseCase(
            dataStoreRepository = get<DataStoreRepository>()
        )
    }

    single {
        DeviceSelectionUseCase(
            bluetoothRepository = get<BluetoothRepository>(),
            dataStoreRepository = get<DataStoreRepository>()
        )
    }

    single {
        DeviceDiscoveryUseCase(
            bluetoothRepository = get<BluetoothRepository>()
        )
    }

    single {
        RemoteUseCase(
            bluetoothRepository = get<BluetoothRepository>(),
            dataStoreRepository = get<DataStoreRepository>()
        )
    }
}

private val repositoryModule: Module = module {
    single<GyroscopeSensorRepository> {
        GyroscopeSensorRepositoryImpl(
            sensor = get<GyroscopeSensor>()
        )
    }

    single<BluetoothRepository> {
        BluetoothRepositoryImpl(
            bluetoothStatusChecker = get<BluetoothStatusChecker>(),
            bluetoothScanner = get<BluetoothScanner>(),
            bluetoothLocalData = get<BluetoothLocalData>(),
            bluetoothHidCore = get<BluetoothHidCore>()
        )
    }

    single<DataStoreRepository> {
        DataStoreRepositoryImpl(
            settingsDataStore = get<SettingsDataStore>()
        )
    }
}

private val dataModule: Module = module {
    single {
        GyroscopeSensor(
            sensorManager = get<SensorManager>(),
            displayManager = get<DisplayManager>()
        )
    }

    single {
        BluetoothStatusChecker(
            context = androidContext(),
            adapter = get<BluetoothManager>().adapter
        )
    }

    single {
        BluetoothScanner(
            context = androidContext(),
            adapter = get<BluetoothManager>().adapter
        )
    }

    single {
        BluetoothLocalData(
            context = androidContext(),
            adapter = get<BluetoothManager>().adapter
        )
    }

    single {
        BluetoothHidCore(
            context = androidContext(),
            adapter = get<BluetoothManager>().adapter,
            hidSettings = get<BluetoothHidDeviceAppSdpSettings>()
        )
    }

    single {
        SettingsDataStore(
            context = androidContext()
        )
    }
}