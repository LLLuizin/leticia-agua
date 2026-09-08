plugins { id("com.android.application") }

android {
    namespace = "com.luizin.lembreteagua"
    compileSdk = 35
    defaultConfig { applicationId = "com.luizin.lembreteagua"; minSdk = 23; targetSdk = 35; versionCode = 2; versionName = "1.1" }
    buildTypes { release { isMinifyEnabled = false } }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
}
