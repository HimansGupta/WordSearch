package com.himanshu.wordsearch.Api

import com.himanshu.wordsearch.Model.Words
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

     //https://api.datamuse.com/words?ml=ringing+in+the+ears
    @GET("words")
    fun getwords(@Query("ml") ml : String): Call<Words>
}