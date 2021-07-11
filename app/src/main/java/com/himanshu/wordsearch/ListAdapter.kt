package com.himanshu.wordsearch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HeaderViewListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_words.view.*
import java.util.ArrayList

class ListAdapter(private val context: Context,  private val mRowLayout: Int) : RecyclerView.Adapter<ListAdapter.QuestionViewHolder>() {
    private var mWords: MutableList<String> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(mRowLayout, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {

        holder.Heading?.text = mWords[position][0].toString()
        holder.Words?.text = mWords[position]


    }

    override fun getItemCount(): Int {
        return mWords.size
    }

    fun updateWords(updatedWords: ArrayList<String>)
    {
        mWords.clear()
        mWords.addAll(updatedWords)
        notifyDataSetChanged()
    }
    class QuestionViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {
        val Heading = containerView.heading;
        val Words = containerView.words;
    }
}
