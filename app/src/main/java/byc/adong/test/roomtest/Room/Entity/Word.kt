package byc.adong.test.roomtest.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
public data class Word(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "word")
    val word: String?,
    @ColumnInfo(name = "word_memo")
    val wordMemo: String?
) {
    constructor(word: String?, wordMemo: String?) : this(id = null, word, wordMemo)
}