# Watcher
[![](https://jitpack.io/v/xcc3641/watcher.svg)](https://jitpack.io/#xcc3641/watcher)

---

![](http://ww1.sinaimg.cn/large/006y8lVagw1fabk6nf50eg30el05njrd.gif)

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
	compile 'com.github.xcc3641:watcher:0.4'
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

The Watcher will stop itself when your app is in the background