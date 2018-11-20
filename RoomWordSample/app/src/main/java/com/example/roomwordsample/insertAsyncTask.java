package com.example.roomwordsample;

import android.os.AsyncTask;

class insertAsyncTask extends AsyncTask<Word, Void, Void> {
    private WordDao mAsyncTaskDao;

    public insertAsyncTask(WordDao mWordDao) {
        mAsyncTaskDao = mWordDao;
    }

    @Override
    protected Void doInBackground(final Word... words) {
        mAsyncTaskDao.insert(words[0]);
        return null;
    }
}
