package com.kotlin.firstkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    val value: String?= "fffff"

    var a: Int = 8
    var b: Int = 7
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gotoStar.setOnClickListener {
            startActivity(Intent(this,StarRepoActivity::class.java))
        }
    /*    //// Rx/////////////
        // just operator take sequential of data and not change behveoir of your data
        val dataStream = Observable.just(10, 20, 30, 40, 50)
        val dataObserver = object : Observer<Int> {
            override fun onComplete() {
                println("all data recived......")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Int) {
                println("new data" + t)
            }

            override fun onError(e: Throwable) {
            }

        }
        dataStream.subscribe(dataObserver)

        createFromArray().subscribe {
            println("arrray" + Arrays.toString(it))
        }
        //////////////////////

        val x = getMax(a, b)
        print(x)


        val item = listOf<String>("asd", "dddd", "ddddddd")

        var a = arrayOf(1, 1, 2, 3, 3)
        for (a in a)
            print(a)

        for (it in item)
            print(it)


        for (x in 1..10 step 2)
            print(x)

        val per = Person1("mohammed", 12)


        val map = TreeMap<String, String>()

        map["user"] = "mohammed"
        map["user1"] = "mohammed1"
        map["user2"] = "mohammed2"

        for ((key, value) in map) {
            print("$key -> $value")
        }

        //extensionFunction
        var spider = Hero()
        spider.setHero("spiderMan")
        spider.getHero()

        var myString = "mohammedSabour"
        myString.removeFirst()
        print(myString)

        //infix call function
        spider createPyramid 4


        //////inhertiance/////
        val extend = Extend()
        extend.createPyramid(5)*/
    }

    fun String.removeFirst(): String = this.substring(1, this.length - 1)

    fun Hero.setHero(p: String) {
        name = p
    }

    fun Hero.getHero() {
        print(name)
    }

    private fun getMax(a: Int, b: Int): Int =
        if (a > b)
            a
        else
            b


    class Person(name: String?) {
        init {
            print("somethin here kotlin")
        }

    }

    class Person1(val name: String) {
        var children: MutableList<Person1> = mutableListOf()

        constructor(name: String, age: Int) : this(name) {
            print("secondery one constructor")
        }


    }


    private fun createFromArray(): Observable<Array<Int>> {
        return Observable.fromArray(arrayOf(1, 2, 3, 4))
        // Observable.range(1,20)
    }

    private fun createFromRange(): Observable<Int> {
        return Observable.range(1, 10).repeat(4)
    }

    private fun createFilter(): Observable<Int> {
        return Observable.just(1, 10,34,56,44,6).filter {
            it >= 10
        }
    }

    private fun createDistincut(): Observable<Int> {
        return Observable.just(1, 10,10,1,44,6).distinct()
    }

    private fun createMerge(): Observable<Int> {
        val first=Observable.just(1,2,3,4)
        val second=Observable.just(5,6,7,8)
        // not order
        return first.mergeWith(second)
        //order
      //  return first.concatWith (second)
     }

  /*  private fun createZip(): Observable<String> {
        val first=Observable.just("dd","ddd")
        val second=Observable.just("dd","Ddd")
       return first.zipWith(second, BiFunction{
             fir,las ->
           first+ "" +second).toString()
      })
    }*/

    private fun createFromInterval(): Observable<Long> {

        return Observable.interval(1, TimeUnit.SECONDS).takeWhile {
            it <= 20
        }

    }
}
