package byc.adong.test.roomtest.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import byc.adong.test.roomtest.Room.Dao.WordDao
import byc.adong.test.roomtest.Room.Entity.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Word::class), version = 2, exportSchema = false)
public abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao


    private class WordDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.wordDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            // Add sample words.
            var word = Word("Hello", "hello")
            wordDao.insert(word)
            word = Word("World!", "hello")
            wordDao.insert(word)

            // TODO: Add your own words!
        }
    }

    companion object {
        private const val WORD_DATABASE_TITLE: String = "word_database"

        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("Drop Table word_table")
                database.execSQL("Create Table word_table (id INTEGER primary key autoincrement, word TEXT, word_memo TEXT)")
            }
        }

        fun getDatabase(context: Context, scope: CoroutineScope): WordRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    WORD_DATABASE_TITLE
                )
                    .addMigrations(MIGRATION_1_2)
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}