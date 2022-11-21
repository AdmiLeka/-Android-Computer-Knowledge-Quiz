package com.thyevolution.computerknowledgequiz;

import android.provider.BaseColumns;

public final class QuizContract {

    public QuizContract() {}

    public static class QuestionsTable implements BaseColumns{
        public static final String TABLE_NAME1 = "beginnerQnAEng";
        public static final String TABLE_NAME2 = "intermediateQnAEng";
        public static final String TABLE_NAME3 = "advancedQnAEng";
        public static final String TABLE_NAME4= "beginnerQnADe";
        public static final String TABLE_NAME5 = "intermediateQnADe";
        public static final String TABLE_NAME6 = "advancedQnADe";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_ANSWER1 = "answer1";
        public static final String COLUMN_ANSWER2 = "answer2";
        public static final String COLUMN_ANSWER3 = "answer3";
        public static final String COLUMN_ANSWER4 = "answer4";
        public static final String COLUMN_CORRECT_ANSWER_ID = "corrAnswerID";
    }
}
