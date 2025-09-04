package kmp.multimodule.sample.common.demo

object DemoApi {
    fun greet(name: String): String = "Hello, $name from shared KMP"
    fun add(a: Int, b: Int): Int = a + b
}
