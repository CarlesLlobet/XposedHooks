# Xposed Hooks
Set of hooks in java to bypass protections and trace functions with Xposed Framework.

This set of hooks for Xposed Framework aim to improve the process of hooking Android apps, sharing a unique repository of the different hooks implemented by Mobile team, so all of as can use them.

## Usage

Clone this repository:

```
git clone ssh://git@git:1994/carles.llobet/XposedHooks.git
```

Open it with Android Studio, change the packagename in XposedHooks.class for your desired App to hook, and uncomment the hooks you need.

Run the app from Android Studio to install the module, activate it on Xposed Framework in the device, and reboot it.

### Prerequisites

To use this project the following binaries are needed: 
- [Android Studio](https://developer.android.com/studio)
- [Xposed Framework on the device](https://repo.xposed.info/module/de.robv.android.xposed.installer)

You can download them clicking the respective links and following the steps.

## Built With

* [Android SDK](https://developer.android.com/studio/#downloads) -  The process by which new applications are created for devices running the Android operating system.

## Authors

* **Carles Llobet** - *Complete work* - [Github](https://github.com/CarlesLlobet)

See also the list of [contributors](https://git/carles.llobet/XposedHooks/contributors) who participated in this project.