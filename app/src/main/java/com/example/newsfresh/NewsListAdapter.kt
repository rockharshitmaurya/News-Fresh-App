package com.example.newsfresh
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewViewHolder>() {
    private val items:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
       val viewHolder=NewViewHolder(view)
        view.setOnClickListener{
          listener.noItemClicked(items[viewHolder.adapterPosition])
        }
       return viewHolder
        // return NewViewHolder(view)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
       val currentItem=items[position]
        holder.titleView.text=currentItem.title
        holder.author.text=currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }

    fun updateNews(updatedNews: ArrayList<News>){
            items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }
}
class NewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleView:TextView=itemView.findViewById(R.id.title)
        val image:ImageView=itemView.findViewById(R.id.image)
        val author:TextView=itemView.findViewById(R.id.author)
}
interface NewsItemClicked{
    fun noItemClicked(item:News)
}