# GoogleBooks

### **Overview - About this project**
This is a simple project **Using Google Books API** to fetch data. I used the Android Jetpack components to make this a modern project.

### 📷Images
<img src="http://www.projectconnect.com.br/github_imagens/image_202004170959.gif" width="30%"></img>

### 💻Technology
- [Kotlin](https://kotlinlang.org/)
- [Material](https://material.io/)
- [Coil](https://coil-kt.github.io/coil/)
- [OkHttp](https://square.github.io/okhttp/)
- [GSON](https://github.com/google/gson)

### Libraries

```bash
    ext {
        kotlin_version = '1.3.71'
        appcompat_version = '1.1.0'
        ktx_version = '1.2.0'
        constraintlayout_version = '1.1.3'
        lifecycle_version = '2.2.0'
        room_version = '2.2.3'
    }

    //material design items
    implementation 'com.google.android.material:material:1.1.0'
    //viewpager2
    implementation 'androidx.viewpager2:viewpager2:1.0.0'

    //make requests to network
    implementation 'com.squareup.okhttp3:okhttp:4.3.1'

    //map class from Json to objetcs
    implementation 'com.google.code.gson:gson:2.8.6'

    //load images
    implementation "io.coil-kt:coil:0.9.1"

    //coroutines
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"

    //viewmodel
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    //allow coroutines inside viewmodel
    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
    //add livedata
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    //jarvis alert library
    implementation 'com.github.gilsonjuniorpro:Jarvis:0.2.1'

    //Room
    implementation "androidx.room:room-runtime:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
    kapt "androidx.room:room-compiler:$room_version"

    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
    ```
