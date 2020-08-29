package com.example.daggerinkotlinproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {

    @Inject @field:Named("network")
    lateinit var repository: Repository
    @Inject @field:Named("database")
    lateinit var repository1: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainActivity_RepositoryComponent.create().injectRepository(this)
        tv_myText.text = repository.myValue + "\n" + repository1.myValue
      //  tv_myText.text = repository.myValue
    }

  /*  class Repository @Inject constructor(){
        var myValue: String = "This is dagger text"
      *//*  fun myText(): String {
           var myValue: String = "This is repository text"
            return myValue
        }*//*
    }*/

    class Repository(val myValue: String)

    @Component(modules = [FirstModule::class, SecondModule::class])
    interface RepositoryComponent {
        fun injectRepository(app:MainActivity)
    }

    @Module
    class FirstModule {
        @Provides @Named("network")
        fun networkCall(): Repository {
          return Repository("This is network call...")
        }
    }

    @Module
    class SecondModule {
        @Provides @Named("database")
       fun databaseCall(): Repository {
           return Repository("This is database call...")
       }
    }
}
