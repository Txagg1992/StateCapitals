package com.curiousca.statecapitals.DataClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.curiousca.statecapitals.DataClasses.QuizContract.CategoriesTable;
import static com.curiousca.statecapitals.DataClasses.QuizContract.QuestionsTable;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CapitalQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase database;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context){
        if (instance == null){
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
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
        Category c2 = new Category("Americas");
        addCategory(c2);
        Category c3 = new Category("Africa");
        addCategory(c3);
        Category c4 = new Category("Europe");
        addCategory(c4);
        Category c5 = new Category("Asia/Australia");
        addCategory(c5);
        Category c6 = new Category("Middle East");
        addCategory(c6);
    }

    private void addCategory(Category category){
        ContentValues cValues = new ContentValues();
        cValues.put(CategoriesTable.COLUMN_REGION, category.getName());
        database.insert(CategoriesTable.TABLE_NAME, null, cValues);
    }

    private void fillQuestionsTable(){

        //Start US state capitals
        Question q1 = new Question("The capital of North Carolina is...?", "Wilmington",
                "Charlotte", "Asheville","Raleigh", 4, Category.US);
        addQuestion(q1);
        Question q2 = new Question("Michigan's capital is...?", "Lansing",
                "Grand Rapids", "Kalamazoo","Detroit", 1, Category.US);
        addQuestion(q2);
        Question q3 = new Question("The capital of North Dakota is...?", "Fargo",
                "Minot", "Bismarck","Williston", 3, Category.US);
        addQuestion(q3);
        Question q4 = new Question("Texas' state capital is:", "San Antonio",
                "Houston", "Dallas","Austin", 4, Category.US);
        addQuestion(q4);
        Question q5 = new Question("Georgia's capital is which city?", "Savanna",
                "Atlanta", "Perry","Augusta", 2, Category.US);
        addQuestion(q5);
        Question q6 = new Question("Nevada's capital is which city?", "Las Vegas",
                "Carson City", "Reno","Fallon", 2, Category.US);
        addQuestion(q6);
        Question q7 = new Question("The capital of Washington is...?", "Spokane",
                "Tacoma", "Olympia","Seattle", 3, Category.US);
        addQuestion(q7);
        Question q8 = new Question("Oregon's capital is...?", "Salem",
                "Eugene", "Portland","Medford", 1, Category.US);
        addQuestion(q8);
        Question q9 = new Question("The capital of Idaho is...?", "Boise",
                "Idaho Falls", "Twin Falls","Pocatello", 1, Category.US);
        addQuestion(q9);
        Question q10 = new Question("Montana's state capital is:", "Helena",
                "Missoula", "Butte","Billings", 1, Category.US);
        addQuestion(q10);
        Question q11 = new Question("Wyoming's capital is which city?", "Casper",
                "Cheyenne", "Rock Springs","Gillette", 2, Category.US);
        addQuestion(q11);
        Question q12 = new Question("California's capital is which city?", "Los Angeles",
                "Sacramento", "Redding","San Francisco", 2, Category.US);
        addQuestion(q12);
        Question q13 = new Question("The capital of Utah is...?", "Provo",
                "Ogden", "Salt Lake City","St. George", 3, Category.US);
        addQuestion(q13);
        Question q14 = new Question("Colorado's state capital is:", "Colorado Springs",
                "Fort Collins", "Denver","Grand Junction", 3, Category.US);
        addQuestion(q14);
        Question q15 = new Question("Arizona's capital is which city?", "Scottsdale",
                "Flagstaff", "Tucson","Phoenix", 4, Category.US);
        addQuestion(q15);
        Question q16 = new Question("New Mexico's capital is which city?", "Albuquerque",
                "Las Cruces", "Roswell","Santa Fe", 4, Category.US);
        addQuestion(q16);
        Question q17 = new Question("The capital of Oklahoma is...?", "Oklahoma City",
                "Tulsa", "Norman","Lawton", 1, Category.US);
        addQuestion(q17);
        Question q18 = new Question("Kansas' capital is...?", "Topeka",
                "Kansas City", "Dodge City","Wichita", 1, Category.US);
        addQuestion(q18);
        Question q19 = new Question("The capital of Nebraska is...?", "Omaha",
                "Lincoln", "Ogallala","Grand Island", 2, Category.US);
        addQuestion(q19);
        Question q20 = new Question("South Dakota's state capital is:", "Sioux Falls",
                "Pierre", "Stugis","Yankton", 2, Category.US);
        addQuestion(q20);
        Question q21 = new Question("Minnesota's capital is which city?", "St. Cloud",
                "Duluth", "St. Paul","Minneapolis", 3, Category.US);
        addQuestion(q21);
        Question q22 = new Question("Wisconsin's capital is which city?", "Green Bay",
                "Milwaukee", "Madison","Racine", 3, Category.US);
        addQuestion(q22);
        Question q23 = new Question("The capital of Iowa is...?", "Iowa City",
                "Sioux City", "Waterloo","Des Moines", 4, Category.US);
        addQuestion(q23);
        Question q24 = new Question("Missouri's capital is...?", "Columbia",
                "St. Louis", "Branson","Jefferson City", 4, Category.US);
        addQuestion(q24);
        Question q25 = new Question("The capital of Illinois is...?", "Springfield",
                "Metropolis", "Chicago","Peoria", 1, Category.US);
        addQuestion(q25);
        Question q26 = new Question("Arkansas's state capital is:", "Little Rock",
                "Fayetteville", "Jonesboro","Hot Springs", 1, Category.US);
        addQuestion(q26);
        Question q27 = new Question("Louisiana's capital is which city?", "Shreveport",
                "Baton Rouge", "New Orleans","Monroe", 2, Category.US);
        addQuestion(q27);
        Question q28 = new Question("Mississippi's capital is which city?", "Starkville",
                "Jackson", "Hattiesburg","Biloxi", 2, Category.US);
        addQuestion(q28);
        Question q29 = new Question("The capital of Alabama is...?", "Mobile",
                "Birmingham", "Montgomery","Dothan", 3, Category.US);
        addQuestion(q29);
        Question q30 = new Question("Tennessee's state capital is:", "Memphis",
                "Chattanooga", "Nashville","Knoxville", 3, Category.US);
        addQuestion(q30);
        Question q31 = new Question("Kentucky's capital is which city?", "Bowling Green",
                "Lexington", "Louisville","Frankfort", 4, Category.US);
        addQuestion(q31);
        Question q32 = new Question("Indiana's capital is which city?", "Fort Wayne",
                "South Bend", "Muncie","Indianapolis", 4, Category.US);
        addQuestion(q32);
        Question q33 = new Question("The capital of Ohio is...?", "Columbus",
                "Toledo", "Cleveland","Cincinnati", 1, Category.US);
        addQuestion(q33);
        Question q34 = new Question("Pennsylvania's state capital is:", "Harrisburg",
                "Pittsburgh", "Gettysburg","Philadelphia", 1, Category.US);
        addQuestion(q34);
        Question q35 = new Question("New York's capital is which city?", "Syracuse",
                "Albany", "Rochester","New York City", 2, Category.US);
        addQuestion(q35);
        Question q36 = new Question("West Virginia's capital is which city?", "Morgantown",
                "Charleston", "Wheeling","Huntington", 2, Category.US);
        addQuestion(q36);
        Question q37 = new Question("The capital of Virginia is...?", "Charlottesville",
                "Roanoke", "Richmond","Norfolk", 3, Category.US);
        addQuestion(q37);
        Question q38 = new Question("Delaware's state capital is:", "Smyrna",
                "Wilmington", "Dover","Camden", 3, Category.US);
        addQuestion(q38);
        Question q39 = new Question("New Jersey's capital is which city?", "Bridgewater",
                "Edison", "Atlantic City","Trenton", 4, Category.US);
        addQuestion(q39);
        Question q40 = new Question("South Carolina's capital is which city?", "Charleston",
                "Florence", "Greenville","Columbia", 4, Category.US);
        addQuestion(q40);
        Question q41 = new Question("The capital of Florida is...?", "Tallahassee",
                "Miami", "Orlando","Pensacola", 1, Category.US);
        addQuestion(q41);
        Question q42 = new Question("Connecticut's state capital is:", "Hartford",
                "New Haven", "Bridgeport","Waterbury", 1, Category.US);
        addQuestion(q42);
        Question q43 = new Question(" Massachusetts' capital is which city?", "Springfield",
                "Boston", "Plymouth","Worcester", 2, Category.US);
        addQuestion(q43);
        Question q44 = new Question("Vermont's capital is which city?", "Waterbury",
                "Montpelier", "Woodstock","Rutland", 2, Category.US);
        addQuestion(q44);
        Question q45 = new Question("The capital of New Hampshire is...?", "Manchester",
                "Portsmouth", "Concord","Nashua", 3, Category.US);
        addQuestion(q45);
        Question q46 = new Question("Maine's state capital is:", "Bangor",
                "Portland", "Augusta","Brunswick", 3, Category.US);
        addQuestion(q46);
        Question q47 = new Question(" Hawaii's capital is which city?", "Kaneohe",
                "O'ahu", "Koloa","Honolulu", 4, Category.US);
        addQuestion(q47);
        Question q48 = new Question("Alaska's capital is which city?", "Anchorage",
                "Kodiak", "Fairbanks","Juneau", 4, Category.US);
        addQuestion(q48);
        Question q49 = new Question("Maryland's state capital is:", "Annapolis",
                "Baltimore", "Alexandria","Washington DC", 1, Category.US);
        addQuestion(q49);
        Question q50 = new Question(" Rhode Island' capital is which city?", "Pawtucket",
                "Providence", "Warwick","Narragansett", 2, Category.US);
        addQuestion(q50);


        //
        //Start Americas country capitals
        Question q57 = new Question("Canada's capital is which city?", "Winnipeg",
                "Ottawa", "Toronto","Montreal", 2, Category.AMERICAS);
        addQuestion(q57);

        //Start Europe country capitals
        Question q88 = new Question("Frances's capital is which city?", "Paris",
                "Marseille", "Nice","Toulouse", 1, Category.EUROPE);
        addQuestion(q88);

        //StartAfrica country capitals
        Question q109 = new Question("Libya's capital is which city?", "Tripoli",
                "Misrata", "Benghazi","Awbari", 1, Category.AFRICA);
        addQuestion(q109);

        //Start Asia Capitals
        Question q210 = new Question("Burma's capital is which city?", "Mandalay",
                "Sittwe", "Naypyitaw","Yangon", 3, Category.ASIA_AUSTRALIA);
        addQuestion(q210);
        Question q211 = new Question("Australia's capital is which city?", "Sydney",
                "Brisbane", "Canberra","Perth", 3, Category.ASIA_AUSTRALIA);
        addQuestion(q211);

        //Start middle East capitals
        Question q312 = new Question("The capital of the United Arab Emirates is?", "Dubai",
                "Al Ain", "Sharjah","Abu Dhabi", 4, Category.MIDDLE_EAST);
        addQuestion(q312);

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

    public List<Category> getAllCategories(){
        List<Category> categoryList = new ArrayList<>();
        database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);
        if (c.moveToFirst()){
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_REGION)));
                categoryList.add(category);
            }while (c.moveToNext());
        }
        c.close();
        return categoryList;
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

    public ArrayList<Question> getQuestions(int categoryID){
        ArrayList<Question> questionList  = new ArrayList<>();
        database = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID)};

        Cursor cursor = database.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
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
