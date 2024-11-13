package com.atharok.btremote.common.injections

import android.bluetooth.BluetoothHidDevice
import android.bluetooth.BluetoothHidDeviceAppSdpSettings
import android.bluetooth.BluetoothManager
import android.content.Context
import android.hardware.SensorManager
import android.hardware.display.DisplayManager
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.bluetoothHidDescriptor
import com.atharok.btremote.data.bluetooth.BluetoothHidProfile
import com.atharok.btremote.data.bluetooth.BluetoothInteractions
import com.atharok.btremote.data.dataStore.SettingsDataStore
import com.atharok.btremote.data.repositories.BluetoothHidProfileRepositoryImpl
import com.atharok.btremote.data.repositories.BluetoothRepositoryImpl
import com.atharok.btremote.data.repositories.GyroscopeSensorRepositoryImpl
import com.atharok.btremote.data.repositories.SettingsRepositoryImpl
import com.atharok.btremote.data.sensor.GyroscopeSensor
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.BRAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.CSAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.DEAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.ESAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.ElAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.FRAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.HEAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.PLAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.PTAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.RUAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.TRAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.UKAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.USAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.BRVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.CSVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.DEVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.ESVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.ElVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.FRVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.HEVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.PLVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.PTVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.RUVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.TRVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.UKVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.USVirtualKeyboardLayout
import com.atharok.btremote.domain.repositories.BluetoothHidProfileRepository
import com.atharok.btremote.domain.repositories.BluetoothRepository
import com.atharok.btremote.domain.repositories.GyroscopeSensorRepository
import com.atharok.btremote.domain.repositories.SettingsRepository
import com.atharok.btremote.domain.usecases.BluetoothHidServiceUseCase
import com.atharok.btremote.domain.usecases.BluetoothHidUseCase
import com.atharok.btremote.domain.usecases.BluetoothUseCase
import com.atharok.btremote.domain.usecases.GyroscopeSensorUseCase
import com.atharok.btremote.domain.usecases.SettingsUseCase
import com.atharok.btremote.presentation.viewmodel.BluetoothHidViewModel
import com.atharok.btremote.presentation.viewmodel.BluetoothViewModel
import com.atharok.btremote.presentation.viewmodel.GyroscopeSensorViewModel
import com.atharok.btremote.presentation.viewmodel.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

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

    single { USVirtualKeyboardLayout() }
    single { UKVirtualKeyboardLayout() }
    single { ESVirtualKeyboardLayout() }
    single { FRVirtualKeyboardLayout() }
    single { DEVirtualKeyboardLayout() }
    single { RUVirtualKeyboardLayout() }
    single { CSVirtualKeyboardLayout() }
    single { PLVirtualKeyboardLayout() }
    single { PTVirtualKeyboardLayout() }
    single { BRVirtualKeyboardLayout() }
    single { ElVirtualKeyboardLayout() }
    single { TRVirtualKeyboardLayout() }
    single { HEVirtualKeyboardLayout() }

    single { USAdvancedKeyboardLayout(context = androidContext()) }
    single { UKAdvancedKeyboardLayout(context = androidContext()) }
    single { ESAdvancedKeyboardLayout(context = androidContext()) }
    single { FRAdvancedKeyboardLayout(context = androidContext()) }
    single { DEAdvancedKeyboardLayout(context = androidContext()) }
    single { RUAdvancedKeyboardLayout(context = androidContext()) }
    single { CSAdvancedKeyboardLayout(context = androidContext()) }
    single { PLAdvancedKeyboardLayout(context = androidContext()) }
    single { PTAdvancedKeyboardLayout(context = androidContext()) }
    single { BRAdvancedKeyboardLayout(context = androidContext()) }
    single { ElAdvancedKeyboardLayout(context = androidContext()) }
    single { TRAdvancedKeyboardLayout(context = androidContext()) }
    single { HEAdvancedKeyboardLayout(context = androidContext()) }
}

private val viewModelModule: Module = module {
    viewModel {
        GyroscopeSensorViewModel(
            useCase = get<GyroscopeSensorUseCase>()
        )
    }

    viewModel {
        BluetoothViewModel(
            useCase = get<BluetoothUseCase>()
        )
    }

    viewModel {
        BluetoothHidViewModel(
            useCase = get<BluetoothHidUseCase>()
        )
    }

    viewModel {
        SettingsViewModel(
            useCase = get<SettingsUseCase>()
        )
    }
}

private val useCaseModule: Module = module {
    single {
        GyroscopeSensorUseCase(
            repository = get<GyroscopeSensorRepository>()
        )
    }

    single {
        BluetoothUseCase(
            bluetoothRepository = get<BluetoothRepository>()
        )
    }

    single {
        BluetoothHidServiceUseCase(
            repository = get<BluetoothHidProfileRepository>()
        )
    }

    single {
        BluetoothHidUseCase(
            repository = get<BluetoothHidProfileRepository>()
        )
    }

    single {
        SettingsUseCase(
            repository = get<SettingsRepository>()
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
            bluetoothInteractions = get<BluetoothInteractions>()
        )
    }

    single<BluetoothHidProfileRepository> {
        BluetoothHidProfileRepositoryImpl(
            hidProfile = get<BluetoothHidProfile>()
        )
    }

    single<SettingsRepository> {
        SettingsRepositoryImpl(
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
        BluetoothInteractions(
            context = androidContext(),
            adapter = get<BluetoothManager>().adapter
        )
    }

    single {
        BluetoothHidProfile(
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