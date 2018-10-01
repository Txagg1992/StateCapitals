package com.curiousca.statecapitals.DataClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.curiousca.statecapitals.DataClasses.QuizContract.*;
import static com.curiousca.statecapitals.DataClasses.QuizContract.QuestionsTable;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CapitalQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase database;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.database = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + " ( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_REGION + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable(){
        Category c1 = new Category("US");
        addCategory(c1);
        Category c2 = new Category("AMERICAS");
        addCategory(c2);
        Category c3 = new Category("AFRICA");
        addCategory(c3);
        Category c4 = new Category("EUROPE");
        addCategory(c4);
        Category c5 = new Category("ASIA");
        addCategory(c5);
        Category c6 = new Category("AUSTRALIA");
        addCategory(c6);
    }

    private void addCategory(Category category){
        ContentValues cValues = new ContentValues();
        cValues.put(CategoriesTable.COLUMN_REGION, category.getName());
        database.insert(CategoriesTable.TABLE_NAME, null, cValues);
    }

    private void fillQuestionsTable(){
        Question q1 = new Question("The capital of North Carolina is...?", "Wilmington",
                "Charlotte", "Asheville","Raleigh", 4, 1);
        addQuestion(q1);
        Question q2 = new Question("Michigan's capital is...?", "Lansing",
                "Grand Rapids", "Kalamazoo","Detroit", 1, 1);
        addQuestion(q2);
        Question q3 = new Question("The capital of North Dakota is...?", "Fargo",
                "Minot", "Bismark","Williston", 3, 1);
        addQuestion(q3);
        Question q4 = new Question("Texas' state capital is:", "San Antonio",
                "Houston", "Dallas","Austin", 4, 1);
        addQuestion(q4);
        Question q5 = new Question("Georgia's capital is which city?", "Savanna",
                "Atlanta", "Perry","Augusta", 2, 1);
        addQuestion(q5);
        Question q6 = new Question("Nevada's capital is which city?", "Las Vegas",
                "Carson City", "Reno","Fallon", 2, 1);
        addQuestion(q6);
        Question q7 = new Question("Canada's capital is which city?", "Winnipeg",
                "Ottawa", "Toronto","Montreal", 2, 2);
        addQuestion(q7);
        Question q8 = new Question("Frances's capital is which city?", "Paris",
                "Marseille", "Nice","Toulouse", 1, 4);
        addQuestion(q8);
        Question q9 = new Question("Libya's capital is which city?", "Tripoli",
                "Misrata", "Benghazi","Awbari", 1, 3);
        addQuestion(q9);
        Question q10 = new Question("Burma's capital is which city?", "Mandalay",
                "Sittwe", "Naypyitaw","Yangon", 3, 5);
        addQuestion(q10);
        Question q11 = new Question("Australia capital is which city?", "Sydney",
                "Brisbane", "Canberra","Perth", 3, 6);
        addQuestion(q11);

    }

    private void addQuestion(Question question){
        ContentValues contentValues = new ContentValues();
        contentValues.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        contentValues.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        contentValues.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        contentValues.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        contentValues.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        contentValues.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        contentValues.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        database.insert(QuestionsTable.TABLE_NAME, null, contentValues);
    }

    public ArrayList<Question> getAllQuestions(){

        ArrayList<Question> questionList = new ArrayList<>();
        database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (cursor.moveToFirst()){
            do {
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setCategoryID(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }
}
