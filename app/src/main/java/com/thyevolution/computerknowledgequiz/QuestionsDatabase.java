package com.thyevolution.computerknowledgequiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.thyevolution.computerknowledgequiz.QuizContract.*;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class QuestionsDatabase extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "questionsAnswers.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public QuestionsDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //A pre-built SQLite database is used, no need for any code inside the onCreate method.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //A method to get all questions from the database based on user difficulty and language selection.
    @SuppressLint("Range")
    public ArrayList<Question> getAllQuestions(int difficulty) {
        String language = Singleton.getInstance().getLanguage();
        ArrayList<Question> questionsArrayList = new ArrayList<>();
        db = getReadableDatabase();
        String getQuestions = "SELECT * FROM ";
        switch (difficulty) {
            case 1:
                if (language.equals("en")) {
                    getQuestions += QuestionsTable.TABLE_NAME1;
                } else {
                    getQuestions += QuestionsTable.TABLE_NAME4;
                }
                break;
            case 2:
                if (language.equals("en")) {
                    getQuestions += QuestionsTable.TABLE_NAME2;
                } else {
                    getQuestions += QuestionsTable.TABLE_NAME5;
                }
                break;
            default:
                if (language.equals("en")) {
                    getQuestions += QuestionsTable.TABLE_NAME3;
                } else {
                    getQuestions += QuestionsTable.TABLE_NAME6;
                }
                break;
        }

        Cursor c = db.rawQuery(getQuestions, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setAnswer1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER1)));
                question.setAnswer2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER2)));
                question.setAnswer3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER3)));
                question.setAnswer4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER4)));
                question.setCorrectAnswerID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CORRECT_ANSWER_ID)));
                questionsArrayList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionsArrayList;
    }

}
