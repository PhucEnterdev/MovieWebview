package vn.com.enterdev.moviewebview.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import vn.com.enterdev.moviewebview.activities.WebViewActivity
import vn.com.enterdev.moviewebview.adapter.MovieHomeAdapter
import vn.com.enterdev.moviewebview.databinding.FragmentHomeBinding
import vn.com.enterdev.moviewebview.model.Movie
import vn.com.enterdev.moviewebview.utils.Constants

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var movieHomeAdapter: MovieHomeAdapter
    private lateinit var mListMovie: List<Movie>
    private lateinit var tempList: ArrayList<Movie>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        mListMovie = getListMovie()
        tempList = arrayListOf()

        /** fragment -> activity
         * and put url to WebviewActivity*/
        putDataDisPlayWebView()

        movieHomeAdapter.setData(mListMovie)
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rcvHome.layoutManager = gridLayoutManager
        binding.rcvHome.adapter = movieHomeAdapter

        // use seachview to filter by title
        searchView()

        return binding.root
    }

    private fun searchView() {
        binding.searchHome.clearFocus()
        binding.searchHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }


    private fun putDataDisPlayWebView() {
        movieHomeAdapter = MovieHomeAdapter(this, object : MovieHomeAdapter.OnClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@HomeFragment.context, WebViewActivity::class.java)
                intent.putExtra(Constants.KEY_URL_MOVIE, movieHomeAdapter.getData()[position].url)
                startActivity(intent)
            }
        })
    }

    // Filter list movie by title
    @SuppressLint("NotifyDataSetChanged")
    private fun filterList(newText: String?) {
        tempList = arrayListOf()
        for (movie in mListMovie) {
            if (movie.title!!.toString().lowercase().contains(newText!!.lowercase())) {
                tempList.add(movie)
            }
        }

        if (tempList.isEmpty()) {
            movieHomeAdapter.setData(mListMovie)
            movieHomeAdapter.notifyDataSetChanged()
            tempList = mListMovie as ArrayList<Movie>
        } else {
            movieHomeAdapter.setFilterList(tempList)
            movieHomeAdapter.notifyDataSetChanged()
            tempList = mListMovie as ArrayList<Movie>
        }
    }


    // get list movie
    private fun getListMovie(): List<Movie> {
        val list: ArrayList<Movie> = ArrayList()
        val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference: DatabaseReference =
            firebaseDatabase.getReference(Constants.KEY_OBJ_MOVIE)
        databaseReference.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (snap in snapshot.children) {
                    val movie: Movie = snap.getValue(Movie::class.java)!!
                    list.add(movie)
                }
                movieHomeAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }

        })

        return list
    }
}