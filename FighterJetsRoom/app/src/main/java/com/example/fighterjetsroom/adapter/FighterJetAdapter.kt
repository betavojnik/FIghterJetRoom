package com.example.fighterjetsroom.adapter
import com.bumptech.glide.Glide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fighterjetsroom.R
import com.example.fighterjetsroom.databinding.FighterJetViewBinding
import com.example.fighterjetsroom.model.FighterJet

class FighterJetAdapter : RecyclerView.Adapter<FighterJetAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    lateinit var binding: FighterJetViewBinding


    private val differcallback = object: DiffUtil.ItemCallback<FighterJet>() {
        override fun areItemsTheSame(oldItem: FighterJet, newItem: FighterJet): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FighterJet, newItem: FighterJet): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differcallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fighter_jet_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            var image = holder.itemView.findViewById<ImageView>(R.id.ivArticleImage)
            Glide.with(this).load(article.urlImage).into(image)
            var description = holder.itemView.findViewById<TextView>(R.id.tvDescription)
            description.text = article.description
            var name = holder.itemView.findViewById<TextView>(R.id.tvName)
            name.text = article.name
            setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((FighterJet) -> Unit)? = null

    fun setOnItemClickListener(listener: (FighterJet) -> Unit) {
        onItemClickListener = listener
    }
}