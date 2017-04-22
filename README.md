# Watcher
[![](https://jitpack.io/v/xcc3641/watcher.svg)](https://jitpack.io/#xcc3641/watcher) [![Build Status](https://travis-ci.org/xcc3641/Watcher.svg?branch=master)](https://travis-ci.org/xcc3641/Watcher)
---

![](https://ww4.sinaimg.cn/large/006tKfTcgy1fevepnbf5ig30gz0aowev.gif)

### Installation

**Step 1.** Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

**Step 2.** Add the dependency
```gradle
dependencies {
	compile 'com.github.xcc3641:watcher:0.5'
}
```

### Usage

**Step 1.** Add the ```WatcherService``` in your AndroidManifest
```xml
<service android:name="com.hugo.watcher.WatcherService"/>
```

**Step 2.** Start the Watcher

In your Application onCreate :

```java
@Override
public void onCreate() {
	super.onCreate();
	// default
	Watcher.getInstance().start(this);
}
```

**Update at 17.4.22**

Watcher has used ``TYPE_PHONE`` instead of ``TYPE_TOAST`` .
Make sure enable **"Draw over other apps permission"**.

[Prevent apps to overlay other apps via toast windows](https://android.googlesource.com/platform/frameworks/base/+/dc24f93)

#### More configuration

```java
// custom
WatcherConfig watcherConfig = new WatcherConfig();
watcherConfig.enableFps = false;
// ....
Watcher.getInstance().setWatcherConfig(watcherConfig).start(this);
```

**Step 3.** Enjoy it

The Watcher will stop itself back to the background