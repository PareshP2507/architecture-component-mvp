## Android Archiecture Components

A sample code contains a way how [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html) can be implemented.

**The repo contains following components:**
- **LifeCycle Components**<br/>
A smart component that is tightly bound to the liefecycle of other component such as an Activity or a Fragment. This helps you to produce well-organized, light weight code that is easier to maintain.<br/>
Using LifeCycle Component, you can write lifecycle dependent code _outside of lifecycle methods_, such as `onStart()`, `onPause`, `onResume` etc.

- **Room Persistence Library**<br/>
Its a wrapper over SQLite, that allows you to communicate with database like a champ. This is why Room is recommended:-<br/>
  * Room validate queries at compile time, So - _No runtime failure_.
  * Room overlaps some `raw SQL` queries, So - Not much lengthy boring queries.<br/>
    This is how query looks like - 
      ``` 
      @Insert
      void insertAll(User... users);
      ```
  * Room defines _thread containts_ to avoid drasting effects on App performance.
  
## Simple Setup
  
  **Gradle dependencies:**
  ```
  // LifeCycle components
  implementation "android.arch.lifecycle:extensions:$arch_viewmodel_ver"
  annotationProcessor "android.arch.lifecycle:compiler:$arch_viewmodel_ver"
  // Room
  implementation "android.arch.persistence.room:runtime:$arch_viewmodel_ver"
  annotationProcessor "android.arch.persistence.room:compiler:$arch_viewmodel_ver"
  ```

