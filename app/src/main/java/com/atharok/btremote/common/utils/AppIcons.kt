package com.atharok.btremote.common.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.Backspace
import androidx.compose.material.icons.automirrored.rounded.BluetoothSearching
import androidx.compose.material.icons.automirrored.rounded.HelpOutline
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.rounded.KeyboardReturn
import androidx.compose.material.icons.automirrored.rounded.KeyboardTab
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.automirrored.rounded.VolumeDown
import androidx.compose.material.icons.automirrored.rounded.VolumeOff
import androidx.compose.material.icons.automirrored.rounded.VolumeUp
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material.icons.rounded.Bluetooth
import androidx.compose.material.icons.rounded.BluetoothDisabled
import androidx.compose.material.icons.rounded.BrightnessHigh
import androidx.compose.material.icons.rounded.BrightnessLow
import androidx.compose.material.icons.rounded.ClosedCaption
import androidx.compose.material.icons.rounded.Computer
import androidx.compose.material.icons.rounded.ControlCamera
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.DeviceUnknown
import androidx.compose.material.icons.rounded.Dialpad
import androidx.compose.material.icons.rounded.HealthAndSafety
import androidx.compose.material.icons.rounded.HelpOutline
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Key
import androidx.compose.material.icons.rounded.Keyboard
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material.icons.rounded.LinkOff
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Mouse
import androidx.compose.material.icons.rounded.MusicVideo
import androidx.compose.material.icons.rounded.OpenInBrowser
import androidx.compose.material.icons.rounded.OpenWith
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.PowerSettingsNew
import androidx.compose.material.icons.rounded.Print
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.Router
import androidx.compose.material.icons.rounded.ScreenshotMonitor
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.SkipNext
import androidx.compose.material.icons.rounded.SkipPrevious
import androidx.compose.material.icons.rounded.Smartphone
import androidx.compose.material.icons.rounded.SpaceBar
import androidx.compose.material.icons.rounded.Toys
import androidx.compose.material.icons.rounded.Usb
import androidx.compose.material.icons.rounded.ViewCompact
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VolumeDown
import androidx.compose.material.icons.rounded.VolumeOff
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.material.icons.rounded.Watch
import androidx.compose.ui.graphics.vector.ImageVector
import org.koin.core.component.KoinComponent
import java.util.Locale

object AppIcons: KoinComponent {

    private val localLanguage: String get() = getKoin().get<Locale>().language
    private val hebrewLanguage: String by lazy { Locale("he").language }

    private fun isLanguageHebrew(): Boolean = localLanguage == hebrewLanguage

    // Some icons should not be mirrored in Hebrew.
    private val HelpIcon: ImageVector get() = if(!isLanguageHebrew()) Icons.AutoMirrored.Rounded.HelpOutline else @Suppress("DEPRECATION") Icons.Rounded.HelpOutline
    private val MuteIcon: ImageVector get() = if(!isLanguageHebrew()) Icons.AutoMirrored.Rounded.VolumeOff else @Suppress("DEPRECATION") Icons.Rounded.VolumeOff
    private val VolumeIncreaseIcon: ImageVector get() = if(!isLanguageHebrew()) Icons.AutoMirrored.Rounded.VolumeUp else @Suppress("DEPRECATION") Icons.Rounded.VolumeUp
    private val VolumeDecreaseIcon: ImageVector get() = if(!isLanguageHebrew()) Icons.AutoMirrored.Rounded.VolumeDown else @Suppress("DEPRECATION") Icons.Rounded.VolumeDown

    // ---- UI ----

    val Back get() = Icons.AutoMirrored.Rounded.ArrowBack
    val Help get() = HelpIcon
    val Settings get() = Icons.Rounded.Settings
    val Info get() = Icons.Outlined.Info
    val Refresh get() = Icons.Rounded.Refresh
    val MoreVert get() = Icons.Rounded.MoreVert
    val Visibility get() = Icons.Rounded.Visibility
    //val Done get() = Icons.Rounded.Done
    val Appearance get() = Icons.Rounded.Palette
    val UserInterface get() = Icons.Rounded.ViewCompact
    val OpenInBrowser get() = Icons.Rounded.OpenInBrowser
    val Lock get() = Icons.Rounded.Lock
    val Key get() = Icons.Rounded.Key

    // ---- Remote ----

    val Home get() = Icons.Rounded.Home
    val Menu get() = Icons.AutoMirrored.Rounded.List
    val Power get() = Icons.Rounded.PowerSettingsNew
    val ClosedCaption get() = Icons.Rounded.ClosedCaption
    val TVChannel get() = Icons.Rounded.Dialpad
    val TVChannelIncrease get() = Icons.Rounded.Add
    val TVChannelDecrease get() = Icons.Rounded.Remove
    val Mute get() = MuteIcon
    val VolumeIncrease get() = VolumeIncreaseIcon
    val VolumeDecrease get() = VolumeDecreaseIcon
    val BrightnessIncrease get() = Icons.Rounded.BrightnessHigh
    val BrightnessDecrease get() = Icons.Rounded.BrightnessLow
    val MultimediaPrevious get() = Icons.Rounded.SkipPrevious
    val MultimediaNext get() = Icons.Rounded.SkipNext
    val MultimediaPlay get() = Icons.Rounded.PlayArrow
    val MultimediaPause get() = Icons.Rounded.Pause
    val Up get() = Icons.Rounded.KeyboardArrowUp
    val Left get() = Icons.AutoMirrored.Rounded.KeyboardArrowLeft
    val Right get() = Icons.AutoMirrored.Rounded.KeyboardArrowRight
    val Down get() = Icons.Rounded.KeyboardArrowDown
    val Pick get() = Icons.Outlined.Circle

    val Controller get() = Icons.Rounded.ControlCamera
    val Gesture get() = Icons.Rounded.OpenWith
    val Disconnect get() = Icons.Rounded.LinkOff

    // ---- Bluetooth ----

    val Bluetooth get() = Icons.Rounded.Bluetooth
    val BluetoothDisabled get() = Icons.Rounded.BluetoothDisabled
    val BluetoothPairing get() = Icons.AutoMirrored.Rounded.BluetoothSearching
    val BluetoothUnpair get() = Icons.Rounded.Delete
    val EnabledAutoConnect get() = Icons.Rounded.Link

    // ---- BT Category ----

    val Computer get() = Icons.Rounded.Computer
    val Phone get() = Icons.Rounded.Smartphone
    val Networking get() = Icons.Rounded.Router
    val AudioVideo get() = Icons.Rounded.MusicVideo
    val Peripheral get() = Icons.Rounded.Usb
    val Imaging get() = Icons.Rounded.Print
    val Wearable get() = Icons.Rounded.Watch
    val Toy get() = Icons.Rounded.Toys
    val Health get() = Icons.Rounded.HealthAndSafety
    val Uncategorized get() = Icons.Rounded.DeviceUnknown

    // ---- Keyboard ----

    val Keyboard get() = Icons.Rounded.Keyboard
    val KeyboardTab get() = Icons.AutoMirrored.Rounded.KeyboardTab
    val KeyboardScreenshot get() = Icons.Rounded.ScreenshotMonitor
    val KeyboardBackspace get() = Icons.AutoMirrored.Rounded.Backspace
    val KeyboardEnter get() = Icons.AutoMirrored.Rounded.KeyboardReturn
    val SpaceBar get() = Icons.Rounded.SpaceBar
    val KeyboardArrowUp get() = Icons.Rounded.KeyboardArrowUp
    val KeyboardArrowLeft get() = Icons.AutoMirrored.Rounded.KeyboardArrowLeft
    val KeyboardArrowDown get() = Icons.Rounded.KeyboardArrowDown
    val KeyboardArrowRight get() = Icons.AutoMirrored.Rounded.KeyboardArrowRight

    val Send get() = Icons.AutoMirrored.Rounded.Send

    // ---- Mouse ----

    val Mouse get() = Icons.Rounded.Mouse
    val MouseScrollUp get() = Icons.Rounded.ArrowUpward
    val MouseScrollDown get() = Icons.Rounded.ArrowDownward
}