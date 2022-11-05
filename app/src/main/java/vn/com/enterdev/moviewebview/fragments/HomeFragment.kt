package vn.com.enterdev.moviewebview.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import vn.com.enterdev.moviewebview.adapter.MovieHomeAdapter
import vn.com.enterdev.moviewebview.databinding.FragmentHomeBinding
import vn.com.enterdev.moviewebview.model.Movie
import vn.com.enterdev.moviewebview.utils.Constants

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var movieHomeAdapter: MovieHomeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        movieHomeAdapter = MovieHomeAdapter(this)
        movieHomeAdapter.setData(getListMovie())
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rcvHome.layoutManager = gridLayoutManager
        binding.rcvHome.adapter = movieHomeAdapter

        return binding.root
    }

    private fun getListMovie(): List<Movie> {
        var list: ArrayList<Movie> = ArrayList()
        val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference: DatabaseReference = firebaseDatabase.getReference(Constants.KEY_OBJ_MOVIE)
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (snap in snapshot.children){
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