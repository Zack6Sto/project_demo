package com.example.project_demo.Constants

class Constants {
    companion object{

        const val TIME_CONNECT = 30L
        const val TIME_INTERVAL = 1000L * 600L
        const val TIME_INTERVAL_UNIT = 1000L

        const val BASE_URL = "http://localhost/phpmyadmin/"
        const val SERVER_KEY = "AAAAsWbZlj4:APA91bF6Q-G_7bhWvLwsea8vao5ncX1gFmdGcvSp-AakZwBOrro1gzxQ49qTzIuRIDZBVuJX8D3qxBbwS_5CFsqN-w7cZUTy9xzhe4soap1Iv5mHLn08a6ZGabk3xpmNA64vhIRMzJaC" // get firebase server key from firebase project setting
        const val CONTENT_TYPE = "application/json"

        const val MESSAGE_NO_INTERNET = "กรุณาตรวจสอบการเชื่อมต่ออินเตอร์เน็ต"

        private const val API_ENDPOINT_SSL = "http://"

        const val URL_PRO = API_ENDPOINT_SSL + "localhost:3000/"

        const val URL_DEV = API_ENDPOINT_SSL + "localhost:3000/"
    }
}