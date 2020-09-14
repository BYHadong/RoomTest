package byc.adong.test.roomtest.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
public data class Word(
    @PrimaryKey
    @ColumnInfo(name = "word")
    val word: String)