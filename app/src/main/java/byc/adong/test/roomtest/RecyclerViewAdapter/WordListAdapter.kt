package byc.adong.test.roomtest.RecyclerViewAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import byc.adong.test.roomtest.R
import byc.adong.test.roomtest.Room.Entity.Word

class WordListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private var words = emptyList<Word>()

    inner class WordViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val wordItemView: TextView = view.findViewById(R.id.textView)
        val idItem : TextView = view.findViewById(R.id.idText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder = WordViewHolder(inflater.inflate(R.layout.recyclerview_item, parent, false))

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val wordData = words[position]
        holder.wordItemView.text = "${wordData.word}, ${wordData.wordMemo}"
        holder.idItem.text = wordData.id.toString()
    }

    internal fun setWord(words: List<Word>){
        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = words.size
}