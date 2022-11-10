package vn.com.enterdev.moviewebview.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import vn.com.enterdev.moviewebview.R
import vn.com.enterdev.moviewebview.model.Movie

class MovieHomeAdapter(private val fragment: Fragment,
                       private val onClickListener: OnClickListener):
    RecyclerView.Adapter<MovieHomeAdapter.MovieHomeViewHolder>(){
    private var mListMovie: List<Movie> = ArrayList()

    // interface click item in recyclerview
    interface OnClickListener{
        fun onItemClick(position: Int)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Movie>){
        this.mListMovie = list
        this.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFilterList(listItem: List<Movie>){
        this.mListMovie = listItem
        this.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData():List<Movie>{
        return this.mListMovie
    }
    class MovieHomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgMovie: ImageView = itemView.findViewById(R.id.img_movie)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHomeViewHolder {
        return MovieHomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_home, parent, false))
    }

    override fun onBindViewHolder(holder: MovieHomeViewHolder, position: Int) {
        val movie = mListMovie[position]
        Glide.with(fragment).load(movie.image).into(holder.imgMovie)
        holder.tvTitle.text = movie.title
        holder.itemView.setOnClickListener {
            onClickListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return mListMovie.size
    }
}