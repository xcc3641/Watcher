# Watcher


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
	compile 'com.github.xcc3641:watcher:v1.0'
}
```

### Usage

**Step 1.** Add the ```WatcherService``` in your AndroidManifest
```xml
<service android:name="com.hugo.watcher.WatcherService"/>
```

**Step 2.** Start the Watcher

In your Activity/Application onCreate :

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	Watcher.getInstance().start(this);
}
```

And dont forget to stop it :

```java
@Override
protected void onDestroy() {
	super.onDestroy();
	Watcher.getInstance().stop(this);
}
```
