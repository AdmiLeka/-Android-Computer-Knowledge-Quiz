# Computer-Knowledge-Quiz-Android-Studio-
An android trivia game regarding computer knowledge.


This are the problems I encountered and how I solved them: 

In order to transport the pre-built SQLite database from the assets folder to the device's internal storage, I used this method: 
```
private void copyDataBase(String dbname) throws IOException {
        // Opening local database as the input stream
        InputStream myInput = this.getAssets().open(dbname);
        // Path to the just created empty database
        File outFileName = this.getDatabasePath(dbname);
        // Opening the empty database as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        // Transferring bytes from the input file to the output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        // Closing the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
```

To mix up the answers in the buttons, first I made an ArrayList of Button and then I did it like this: 

```
Collections.shuffle(buttonList);
            int answerId = currentQuestion.getCorrectAnswerID();
            switch (answerId) {
                case 1:
                    correctButton = buttonList.get(0);
                    break;
                case 2:
                    correctButton = buttonList.get(1);
                    break;
                case 3:
                    correctButton = buttonList.get(2);
                    break;
                default:
                    correctButton = buttonList.get(3);
                    break;
            }
                    
```

In order to get the appropriate database table based on user difficulty and language selection, I used this method, which allows for a more dynamic approach and avoids boilerplate code:
```
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
```    


The game looks like this:

![image 2](https://user-images.githubusercontent.com/95366089/193774789-f3125755-be31-4861-b922-d4e7acfc36df.png)
![image 3](https://user-images.githubusercontent.com/95366089/193774804-d1e87ca1-b97d-4ff7-b60c-7e591e227408.png)
![image 4](https://user-images.githubusercontent.com/95366089/193774824-97cb1747-9023-4528-a504-b8515fbcd922.png)


