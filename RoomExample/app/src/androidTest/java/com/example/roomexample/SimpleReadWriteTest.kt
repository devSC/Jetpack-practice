package com.example.roomexample

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class SimpleReadWriteTest {
    private lateinit var personDao: PersonDao
    private lateinit var carDao: CarDao
    private lateinit var dataBase: AppDataBase

    @Before
    fun createDB() {
        val appContext = InstrumentationRegistry.getTargetContext()
        dataBase = Room.inMemoryDatabaseBuilder(appContext, AppDataBase::class.java).build()
        personDao = dataBase.personDao()
        carDao = dataBase.carDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        dataBase.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndSelect() {
        val person = Person("H001","小王",27,"1")
        val person3 = Person("H002","小明",24,"1")
        val person4 = Person("H003","张慧",28,"0")
        val person5 = Person("H004","李小燕",35,"0")
        val person6 = Person("H005","王大拿",29,"1")
        val person7 = Person("H006","周强",36,"1")

        val car = Car("C001","BMW",65.99,"H001")
        val car2 = Car("C002","nZ",75.99,"H002")
        val car3 = Car("C003","oda",23.99,"H001")
        val car4 = Car("C004","ugeot",20.99,"H003")
        val car5 = Car("C005","rsche",295.99,"H004")
        val car6 = Car("C006","nda",24.99,"H005")
        val car7 = Car("C007","yota",27.99,"H006")
        val car8 = Car("C008","a",18.99,"H002")
        val car9 = Car("C009","ntley",309.99, "H005")

        val personList = arrayListOf(person, person3, person4,person5,person6,person7)
        val carList = arrayListOf(car, car2, car3, car4,car5, car6, car7,car8, car9)
        //insert to
        personDao.insertAll(personList)
        carDao.insertAll(carList)

        try {
            val car10 = Car("C009","ntley",309.99, "H009")
            carDao.insertAll(arrayListOf(car10))
            assertFalse("must in catch bloak", true)
        } catch (e: Exception) {
            assertNotNull(e)
        }

        assertEquals(personList, personDao.getAll())
        assertEquals(carList, carDao.getAll())
        //select
        assertEquals(arrayListOf(car, car3), carDao.findCarsBy("H001"))
        assertEquals(arrayListOf(car2, car8), carDao.findCarsBy("H002"))

        personDao.delete(person)
        assertEquals(personList.filter { it.id != person.id }, personDao.getAll())
        assertEquals(carList.filter { it.hid != person.id }, carDao.getAll())
    }
}