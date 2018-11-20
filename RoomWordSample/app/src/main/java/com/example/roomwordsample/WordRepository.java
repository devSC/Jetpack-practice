package com.example.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public void insert(Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }
}
