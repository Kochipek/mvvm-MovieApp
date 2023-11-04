import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.kochipek.moviesmvvm.databinding.ListRowBinding
import com.kochipek.moviesmvvm.data.model.Movie
import com.kochipek.moviesmvvm.utils.downloadFromUrl
import com.kochipek.moviesmvvm.utils.placeholderProgressBar
import com.kochipek.moviesmvvm.view.FeedFragmentDirections

class MovieAdapter(val movieList: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val binding: ListRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Movie) {
            binding.movieTitle.text = model.title
            binding.movieReleaseDate.text = model.release_date
            binding.movieRating.text = model.vote_average
            binding.imageView.downloadFromUrl("https://image.tmdb.org/t/p/w500${model.poster_path}", placeholderProgressBar(binding.root.context))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListRowBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToMovieFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    // update when swipe screen
    fun updateWhenSwipe(newMovieList: List<Movie>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }

}
