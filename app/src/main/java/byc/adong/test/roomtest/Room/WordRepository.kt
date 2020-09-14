package byc.adong.test.roomtest.Room

import androidx.lifecycle.LiveData
import byc.adong.test.roomtest.Room.Dao.WordDao
import byc.adong.test.roomtest.Room.Entity.Word

class WordRepository(private val wordDao: WordDao) {
    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Word){
        wordDao.insert(word)
    }
}