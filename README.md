# fcitx5-android

[Fcitx5](https://github.com/fcitx/fcitx5) input method framework and engines ported to Android.

## Download

### Latest CI builds

Jenkins: [![build status](https://img.shields.io/jenkins/build.svg?jobUrl=https://jenkins.fcitx-im.org/job/android/job/fcitx5-android/)](https://jenkins.fcitx-im.org/job/android/job/fcitx5-android/)

### Tagged releases

GitHub: [![release version](https://img.shields.io/github/v/release/fcitx5-android/fcitx5-android)](https://github.com/fcitx5-android/fcitx5-android/releases)

[<img src="https://fdroid.gitlab.io/artwork/badge/get-it-on.png" alt="Get it on F-Droid" width="207" height="80">](https://f-droid.org/packages/org.fcitx.fcitx5.android)
[<img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png" width="207" height="80">](https://play.google.com/store/apps/details?id=org.fcitx.fcitx5.android)

## Project status

### Supported Languages

- English (with spell check)
- Chinese (Pinyin, Shuangpin, Wubi, Cangjie and custom tables)
- Vietnamese (Based on UniKey, supports Telex, VNI and VIQR)
- Japanese (via [Anthy Plugin](./plugin/anthy))

### Implemented Features

- Virtual Keyboard (layout not customizable yet)
- Expandable candidate view
- Clipboard management (plain text only)
- Theming (custom color scheme and background image)
- Popup preview on key press
- Long press popup keyboard for convenient symbol input
- Symbol and Emoji picker
- Plugin System for loading addons from other installed apk

### Planned Features

- Customizable keyboard layout
- More input methods (via plugin)

## Screenshots

|拼音, Material Light theme, key border enabled|自然码双拼, Pixel Dark theme, key border disabled|
|:-:|:-:|
|<img src="https://user-images.githubusercontent.com/13914967/202180575-04b6db41-ff24-4bef-899a-8051fc0243f5.png" width="360px">|<img src="https://user-images.githubusercontent.com/13914967/202180709-457e4897-961f-48a6-8fb2-b6560568a122.png" width="360px">|

|Emoji picker, Pixel Light theme, key border enabled|Symbol picker, Material Dark theme, key border disabled|
|:-:|:-:|
|<img src="https://user-images.githubusercontent.com/13914967/202181845-6a5f6bb2-a877-468c-851a-fd7e66e64ed4.png" width="360px">|<img src="https://user-images.githubusercontent.com/13914967/202181861-dd253439-1d5e-4f5f-9535-934f28796a6b.png" width="360px">|

## Get involved

Trello kanban: https://trello.com/b/gftk6ZdV/kanban

Matrix Room: https://matrix.to/#/#fcitx5-android:mozilla.org

Discuss on Telegram: https://t.me/+hci-DrFVWUM3NTUx ([@fcitx5_android](https://t.me/fcitx5_android) originally)

## Build

### Dependencies

- Android SDK Platform & Build-Tools 33.
- Android NDK (Side by side) 25 & CMake 3.22.1, they can be installed using SDK Manager in Android Studio or `sdkmanager` command line. **Note:** NDK 21 & 22 are confirmed not working with this project.
- [KDE/extra-cmake-modules](https://github.com/KDE/extra-cmake-modules)
- GNU Gettext >= 0.20 (for `msgfmt` binary; or install `appstream` if you really have to use gettext <= 0.19.)

### How to set up development environment
<details>
<summary>Prerequisites for Windows</summary>
<ul>
  <li>Enable Developer Mode so that symlinks can be created without administrator privilege.</li>
  <li>Execute <code>git config --global core.symlinks true</code>.</li>
</ul>
</details>
First, clone this repository and fetch all submodules:

```sh
git clone git@github.com:fcitx5-android/fcitx5-android.git
git submodule update --init --recursive
# Need to regenerate symlinks to submodule on Windows
Remove-Item -Recurse app/src/main/assets/usr/share
git checkout -- *
```

Install extra-cmake-modules from your distribution software repository:

```sh
# For Arch Linux (Arch has gettext in it's base meta package)
sudo pacman -S extra-cmake-modules
# For Debian/Ubuntu
sudo apt install extra-cmake-modules gettext
# For macOS
brew install extra-cmake-modules gettext
```
<details>
<summary>For Windows you have to manually install ECM and gettext.</summary>
Do the following steps outside fcitx5-android directory:
<pre>
<code>winget install cmake Ninja-build.Ninja
git clone https://github.com/KDE/extra-cmake-modules
cd extra-cmake-modules
cmake -B build -G Ninja -DCMAKE_INSTALL_PREFIX="$($(Get-Item .).FullName)/dist"
cmake --install build</code>
</pre>
<ul>
  <li>Add environment variable <code>ECM_DIR</code> valued absolute path to <code>extra-cmake-modules/dist/share/ECM/cmake</code></li>
  <li>Create a directory named <code>gettext</code>, download and extract <a href="https://github.com/mlocati/gettext-iconv-windows/releases/download/v0.21-v1.16/gettext0.21-iconv1.16-shared-64.zip">prebuilt gettext</a> into it, then add absolute path to <code>gettext/bin</code> to <code>PATH</code>.</li>
</ul>
</details>

Install Android SDK Platform, Android SDK Build-Tools, Android NDK and cmake via SDK Manager in Android Studio:

<details>
<summary>Detailed steps (screenshots)</summary>

**Note:** These screenshots are for references and the versions in them may be out of date.
The current recommended versions are recorded in [Versions.kt](build-logic/convention/src/main/kotlin/Versions.kt) file.

![Open SDK Manager](https://user-images.githubusercontent.com/13914967/202184493-3ee1546b-0a83-4cc9-9e41-d20b0904a0cf.png)

![Install SDK Platform](https://user-images.githubusercontent.com/13914967/202184534-340a9e7c-7c42-49bd-9cf5-1ec9dcafcf32.png)

![Install SDK Build-Tools](https://user-images.githubusercontent.com/13914967/202185945-0c7a9f39-1fcc-4018-9c81-b3d2bf1c2d3f.png)

![Install NDK](https://user-images.githubusercontent.com/13914967/202185601-0cf877ea-e148-4b88-bd2f-70533189b3d4.png)

![Install CMake](https://user-images.githubusercontent.com/13914967/202184655-3c1ab47c-432f-4bd7-a508-92096482de50.png)

</details>

### Trouble-shooting

- Android Studio indexing takes forever to complete and cosumes a lot of memory.

    Switch to "Project" view in the "Project" tool window (namely the file tree side bar), right click `lib/fcitx5/src/main/cpp/prebuilt` directory, then select "Mark Directory as > Excluded". You may also need to restart the IDE to interrupt ongoing indexing process.

- Gradle error: "No variants found for ':app'. Check build files to ensure at least one variant exists."

    Examine if there are environment variables set such as `_JAVA_OPTIONS` or `JAVA_TOOL_OPTIONS`. You might want to clear them (maybe in the startup script `studio.sh` of Android Studio), as some gradle plugin treats anything in stderr as errors and aborts.

## Nix

Appropriate Android SDK with NDK is available in the development shell.  The `gradlew` should work out-of-the-box, so you can install the app to your phone with `./gradlew installDebug` after applying the patch mentioned above. For development, you may want to install the unstable version of Android Studio, and point the project SDK path to `$ANDROID_SDK_ROOT` defined in the shell. Notice that Android Studio may generate wrong `local.properties` which sets the SDK location to `~/Android/SDK` (installed by SDK Manager). In such case, you need specify `sdk.dir` as the project SDK in that file manually, in case Android Studio sticks to the wrong global SDK.
