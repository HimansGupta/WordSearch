package com.himanshu.wordsearch


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.himanshu.wordsearch.Api.ApiInterface
import com.himanshu.wordsearch.Api.Client
import com.himanshu.wordsearch.Model.Words
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mApiService: ApiInterface? = null

    private var mAdapter: ListAdapter?= null;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener {
            val query = edit.editableText.toString()

            fetchWordList(query)
        }
        mApiService = Client.client.create(ApiInterface::class.java)

        listRecyclerView!!.layoutManager = LinearLayoutManager(this)

        mAdapter = ListAdapter(this,  R.layout.item_words)
        listRecyclerView!!.adapter = mAdapter



    }
    private fun fetchWordList( query : String) {
        val call = mApiService!!.getwords(query);
        call.enqueue(object : Callback<Words> {

            override fun onResponse(call: Call<Words>, response: Response<Words>) {

                Log.d(TAG, "Total Questions: " + response.body()!!.size)
                val words = response.body()
                if (words != null) {
                    val map = mutableMapOf<String,String>()
                     var list: MutableList<String> = ArrayList()
                    for (q in words)
                    {   val k = q.word[0]
                        if(map.containsKey(k.toString()))
                        {
                            map[k.toString()] = map.getValue(k.toString())+" "+q.word
                        }
                        else
                        {
                            map[k.toString()] = q.word
                        }
                    }
                    val sorted = map.toSortedMap()
                    for( v in sorted.values)
                    {
                        list.add(v)
                    }
                    mAdapter?.updateWords(list as ArrayList<String>)
                }
            }

            override fun onFailure(call: Call<Words>, t: Throwable) {
                Log.e(TAG, "Got error : " + t.localizedMessage)
            }
        })
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

}