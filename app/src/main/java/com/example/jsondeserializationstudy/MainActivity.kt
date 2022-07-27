package com.example.jsondeserializationstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

data class MyJSONDataClass(
    val data1: Int,
    val data2: String,
    val list: List<Int>
)

data class MyJSONNestedDataClass(val nested : MyJSONDataClass)

data class Address(
    val city : String,
    val lat : Double,
    val lon : Double)

data class Person(
    val name : String,
    val age : Int,
    val favorites : List<String>,
    val address : Address)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        
        // 역직렬화
        
        var mapper = jacksonObjectMapper()


        var jsonString = """
            {
                "data1": 1234,
                "data2" : "Hello",
                "list" : [1, 2, 3]
            }
        """.trimIndent()

        val d1 = mapper?.readValue<MyJSONDataClass>(jsonString)
        Log.d("my_tag", d1.toString())

        var jsonString2 = """
            {
                "nested": {
                    "data1": 1234,
                    "data2" : "Hello",
                    "list" : [1, 2, 3]
                }
            }
        """.trimIndent()

        val d2 = mapper?.readValue<MyJSONNestedDataClass>(jsonString2)
        Log.d("my_tag", d2.toString())

        val personString = """
            {
                "name" : "John",
                "age" : 20,
                "favorites":["study", "game"],
                "address" : {
                    "city" : "Seoul",
                    "lat" : 0.0,
                    "lon" : 1.0
                }
            }
        """.trimIndent()

        val d3 = mapper?.readValue<Person>(personString)
        Log.d("my_tag", d3.toString())

    }
}