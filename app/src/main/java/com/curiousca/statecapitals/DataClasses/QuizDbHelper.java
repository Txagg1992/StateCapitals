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
        Question q43 = new Question("Massachusetts' capital is which city?", "Springfield",
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
        Question q50 = new Question(" Rhode Island's capital is which city?", "Pawtucket",
                "Providence", "Warwick","Narragansett", 2, Category.US);
        addQuestion(q50);


        //
        //Start Americas country capitals
        Question q101 = new Question("Canada's capital is which city?", "Winnipeg",
                "Ottawa", "Toronto","Montreal", 2, Category.AMERICAS);
        addQuestion(q101);
        Question q102 = new Question("The capital of the United States is...?", "Washington D.C.",
                "New York", "Philadelphia","Boston", 1, Category.AMERICAS);
        addQuestion(q102);
        Question q103 = new Question("Brazil's capital is:", "Brasilia",
                "Rio de Janeiro", "Sao Paulo","Fortaleza", 1, Category.AMERICAS);
        addQuestion(q103);
        Question q104 = new Question(" Mexico's capital is which city?", "Tijuana",
                "Mexico City", "Acapulco","Puerto Vallarta", 2, Category.AMERICAS);
        addQuestion(q104);
        Question q105 = new Question("Belize's capital is which city?", "Belize City",
                "Belmopan", "Placencia","Punta Gorda", 2, Category.AMERICAS);
        addQuestion(q105);
        Question q106 = new Question("The capital of Guatemala is...?", "Puerto Barrios",
                "Quetzaltenango", "Guatemala City","Escuintla", 3, Category.AMERICAS);
        addQuestion(q106);
        Question q107 = new Question("Honduras' capital is:", "San Pedro Sula",
                "Catacamas", "Tegucigalpa","La Ceiba", 3, Category.AMERICAS);
        addQuestion(q107);
        Question q108 = new Question(" El Salvador's capital is which city?", "San Miguel",
                "Santa Ana", "Santa Tecla","San Salvador", 4, Category.AMERICAS);
        addQuestion(q108);
        Question q109 = new Question("Nicaragua's capital is which city?", "Chinandega",
                "Leon", "Esteli","Managua", 4, Category.AMERICAS);
        addQuestion(q109);
        Question q110 = new Question("Antigua & Barbuda's capital is:", "St. John's",
                "Jolly Harbour", "English Harbour","Willikies", 1, Category.AMERICAS);
        addQuestion(q110);
        Question q111 = new Question(" Argentina's capital is which city?", "Buenos Aires",
                "Cordoba", "Mar del Plata","Rosario", 1, Category.AMERICAS);
        addQuestion(q111);
        Question q112 = new Question("The Bahamas's capital is which city?", "Congo Town",
                "Nassau", "Andros Town","Nicholls Town", 2, Category.AMERICAS);
        addQuestion(q112);
        Question q113 = new Question("Barbados' capital is which city?", "Speightstown",
                "Bridgetown", "Diamond Valley","Belleplaine", 2, Category.AMERICAS);
        addQuestion(q113);
        Question q114 = new Question("The capital of Bolivia is...?", "La Paz",
                "Cochabamba", "Sucre","Santa Cruz de la Sierra", 3, Category.AMERICAS);
        addQuestion(q114);
        Question q115 = new Question("Chile's capital is:", "Valparaiso",
                "Concepcion", "Santiago","Puerto Montt", 3, Category.AMERICAS);
        addQuestion(q115);
        Question q116 = new Question(" Colombia's capital is which city?", "Cali",
                "Medellin", "Cartagena","Bogota", 4, Category.AMERICAS);
        addQuestion(q116);
        Question q117 = new Question("Costa Rica's capital is which city?", "Limon",
                "Liberia", "La Fortuna","San Jose", 4, Category.AMERICAS);
        addQuestion(q117);
        Question q118 = new Question("Cuba's capital is:", "Havana",
                "Guantanamo", "Santa Clara","Pinar del Rio", 1, Category.AMERICAS);
        addQuestion(q118);
        Question q119 = new Question(" Dominica's capital is which city?", "Roseau",
                "Rosalie", "Portsmouth","Penville", 1, Category.AMERICAS);
        addQuestion(q119);
        Question q120 = new Question("Dominican Republic's capital is which city?", "Puerto Plata",
                "Santo Domingo", "La Vega","Santiago De Los Caballeros", 2, Category.AMERICAS);
        addQuestion(q120);
        Question q121 = new Question("Ecuador's capital is which city?", "Guayaquil",
                "Quito", "Cuenca","Santo Domingo", 2, Category.AMERICAS);
        addQuestion(q121);
        Question q122 = new Question("The capital of Grenada is...?", "Morne Rouge",
                "Blaize", "St. George's","Sauteurs", 3, Category.AMERICAS);
        addQuestion(q122);
        Question q123 = new Question("Guyana's capital is:", "Bartica",
                "New Amsterdam", "Georgetown","Kwakwani", 3, Category.AMERICAS);
        addQuestion(q123);
        Question q124 = new Question(" Haiti's capital is which city?", "Porte-de-Paix",
                "Jacmel", "Les Cayes","Port-Au-Prince", 4, Category.AMERICAS);
        addQuestion(q124);
        Question q125 = new Question("Jamaica's capital is which city?", "Port Antonio",
                "Montego Bay", "Mandeville","Kingston", 4, Category.AMERICAS);
        addQuestion(q125);
        Question q126 = new Question("Panama's capital is:", "Panama City",
                "Colon", "Las Tablas","David", 1, Category.AMERICAS);
        addQuestion(q126);
        Question q127 = new Question(" Paraguay's capital is which city?", "Asuncion",
                "San Jorge", "Concepcion","Encarnacion", 1, Category.AMERICAS);
        addQuestion(q127);
        Question q128 = new Question("Peru's capital is which city?", "Cusco",
                "Lima", "Ayacucho","Piura", 2, Category.AMERICAS);
        addQuestion(q128);
        Question q129 = new Question("St Lucia's capital is which city?", "Fond Doux",
                "Castries", "Vieux Fort","Gros Islet", 2, Category.AMERICAS);
        addQuestion(q129);
        Question q130 = new Question("The capital of St. Kitts & Nevis is...?", "New Castle",
                "Cayon", "Basseterre","Charlestown", 3, Category.AMERICAS);
        addQuestion(q130);
        Question q131 = new Question("St Vincent & the Grenadines's capital is:", "Port Elizabeth",
                "Brighton", "Kingstown","Laylou", 3, Category.AMERICAS);
        addQuestion(q131);
        Question q132 = new Question("Suriname's capital is which city?", "Kabalebo",
                "Wageningen", "Brownsweg","Paramaribo", 4, Category.AMERICAS);
        addQuestion(q132);
        Question q133 = new Question("Trinidad & Tobago's capital is which city?", "Arima",
                "Diego Martin", "San Fernando","Port of Spain", 4, Category.AMERICAS);
        addQuestion(q133);
        Question q134 = new Question("Uruguay's capital is:", "Montevideo",
                "Punta del Este", "Punta del Diablo","Melo", 1, Category.AMERICAS);
        addQuestion(q134);
        Question q135 = new Question(" Venezuela's capital is which city?", "Caracas",
                "Maracaibo", "Valencia","Barquisimeto", 1, Category.AMERICAS);
        addQuestion(q135);


        //Start Europe country capitals
        Question q201 = new Question("Andorra's capital is which city?", "La Massana",
                "Andorra La Vella", "Soldeu","El Pas de la Casa", 2, Category.EUROPE);
        addQuestion(q201);
        Question q202 = new Question("The capital of Austria is...?", "Salzburg",
                "Klagenfurt", "Vienna","Graz", 3, Category.EUROPE);
        addQuestion(q202);
        Question q203 = new Question("Belarus' capital is:", "Mogilev",
                "Gomel", "Minsk","Grodno", 3, Category.EUROPE);
        addQuestion(q203);
        Question q204 = new Question("Belgium's capital is which city?", "Antwerp",
                "Ghent", "Charleroi","Brussels", 4, Category.EUROPE);
        addQuestion(q204);
        Question q205 = new Question("Bosnia's capital is which city?", "Mostar",
                "Zenica", "Banja Luka","Sarajevo", 4, Category.EUROPE);
        addQuestion(q205);
        Question q206 = new Question("Bulgaria's capital is:", "Sofia",
                "Plovdiv", "Varna","Montana", 1, Category.EUROPE);
        addQuestion(q206);
        Question q207 = new Question(" Croatia's capital is which city?", "Zagreb",
                "Zadar", "Rijeka","Velika Gorica", 1, Category.EUROPE);
        addQuestion(q207);
        Question q208 = new Question("Czech Republic's capital is which city?", "Ostrava",
                "Prague", "Brno","Pilsen", 2, Category.EUROPE);
        addQuestion(q208);
        Question q209 = new Question("Denmark's capital is which city?", "Aarhus",
                "Copenhagen", "Odense","Aalborg", 2, Category.EUROPE);
        addQuestion(q209);
        Question q210 = new Question("The capital of Estonia is...?", "Tartu",
                "Parnu", "Tallinn","Viljandi", 3, Category.EUROPE);
        addQuestion(q210);
        Question q211 = new Question("Finland's capital is:", "Tampere",
                "Turku", "Helsinki","Oulu", 3, Category.EUROPE);
        addQuestion(q211);
        Question q212 = new Question("France's capital is which city?", "Marseille",
                "Toulouse", "La Rochelle","Paris", 4, Category.EUROPE);
        addQuestion(q212);
        Question q213 = new Question("Germany's capital is which city?", "Stuttgart",
                "Hamburg", "Frankfort","Berlin", 4, Category.EUROPE);
        addQuestion(q213);
        Question q214 = new Question("Greece's capital is:", "Athens",
                "Sparta", "Thessaloniki","Kalabaka", 1, Category.EUROPE);
        addQuestion(q214);
        Question q215 = new Question(" Hungary's capital is which city?", "Budapest",
                "Szeged", "Debrecen","Miskolc", 1, Category.EUROPE);
        addQuestion(q215);
        Question q216 = new Question("Iceland's capital is which city?", "Hellnar",
                "Reykjavik", "Laugar","Selfoss", 2, Category.EUROPE);
        addQuestion(q216);
        Question q217 = new Question("Ireland's capital is which city?", "Belfast",
                "Dublin", "Galway","Limerick", 2, Category.EUROPE);
        addQuestion(q217);
        Question q218 = new Question("The capital of Italy is...?", "Milan",
                "Venice", "Rome","Florence", 3, Category.EUROPE);
        addQuestion(q218);
        Question q219 = new Question("Latvia's capital is:", "Daugavpils",
                "Valmiera", "Riga","Saldus", 3, Category.EUROPE);
        addQuestion(q219);
        Question q220 = new Question("Liechtenstein's capital is which city?", "Falkins",
                "Triesenberg", "Schaanwald","Vaduz", 4, Category.EUROPE);
        addQuestion(q220);
        Question q221 = new Question("Lithuania's capital is which city?", "Kaunas",
                "Klaipeda", "Kaliningrad","Vilnius", 4, Category.EUROPE);
        addQuestion(q221);
        Question q222 = new Question("Luxembourg's capital is:", "Luxembourg",
                "Mersch", "Bettembourg","Niederanven", 1, Category.EUROPE);
        addQuestion(q222);
        Question q223 = new Question(" Macedonia's capital is which city?", "Skopje",
                "Bitola", "Ohrid","Gostivar", 1, Category.EUROPE);
        addQuestion(q223);
        Question q224 = new Question("Malta's capital is which city?", "Luqa",
                "Valletta", "Mellieha","Rabat", 2, Category.EUROPE);
        addQuestion(q224);
        Question q225 = new Question("Albania's capital is which city?", "Durres",
                "Tirana", "Pogradec","Vlore", 2, Category.EUROPE);
        addQuestion(q225);
        Question q226 = new Question("The capital of Moldova is...?", "Tiraspol",
                "Comrat", "Chisinau","Bender", 3, Category.EUROPE);
        addQuestion(q226);
        Question q227 = new Question("Monaco's capital is:", "Monte Carlo",
                "Les Salines", "Monaco","La Rousse", 3, Category.EUROPE);
        addQuestion(q227);
        Question q228 = new Question("Montenegro's capital is which city?", "Budva",
                "Berane", "Kolasin","Podgorica", 4, Category.EUROPE);
        addQuestion(q228);
        Question q229 = new Question("Netherlands's capital is which city?", "Rotterdam",
                "The Hague", "Groningen","Amsterdam", 4, Category.EUROPE);
        addQuestion(q229);
        Question q230 = new Question("Norway's capital is:", "Oslo",
                "Trondheim", "Bergen","Stavenger", 1, Category.EUROPE);
        addQuestion(q230);
        Question q231 = new Question(" Poland's capital is which city?", "Warsaw",
                "Wroclaw", "Lublin","Katowice", 1, Category.EUROPE);
        addQuestion(q231);
        Question q232 = new Question("Portugal's capital is which city?", "Porto",
                "Lisbon", "Coimbra","Faro", 2, Category.EUROPE);
        addQuestion(q232);
        Question q233 = new Question("Romania's capital is which city?", "Slatina",
                "Bucharest", "Cluj-Napoca","Timisoara", 2, Category.EUROPE);
        addQuestion(q233);
        Question q234 = new Question("Russia's capital is which city?", "Stalingrad",
                "Moscow", "Volgograd","St. Petersburg", 2, Category.EUROPE);
        addQuestion(q234);
        Question q235 = new Question("San Marino's capital is which city?", "Fiorentino",
                "San Marino", "Acquaviva","Domagnano", 2, Category.EUROPE);
        addQuestion(q235);
        Question q236 = new Question("The capital of Serbia is...?", "Novi Sad",
                "Subotica", "Belgrade","Sabac", 3, Category.EUROPE);
        addQuestion(q236);
        Question q237 = new Question("Kosovo's capital is:", "Prizren",
                "Peja", "Pristina","Krusevac", 3, Category.EUROPE);
        addQuestion(q237);
        Question q238 = new Question("Slovakia's capital is which city?", "Kosice",
                "Nitra", "Budapest","Bratislava", 4, Category.EUROPE);
        addQuestion(q238);
        Question q239 = new Question("Slovenia's capital is which city?", "Velenja",
                "Novo Mesto", "Celje","Ljubljana", 4, Category.EUROPE);
        addQuestion(q239);
        Question q240 = new Question("Spain's capital is:", "Madrid",
                "Seville", "Valencia","Cordoba", 1, Category.EUROPE);
        addQuestion(q240);
        Question q241 = new Question(" Sweden's capital is which city?", "Stockholm",
                "Gothenburg", "Uppsala","Sundsvall", 1, Category.EUROPE);
        addQuestion(q241);
        Question q242 = new Question("Switzerland's capital is which city?", "Zurich",
                "Bern", "Lausanne","Basel", 2, Category.EUROPE);
        addQuestion(q242);
        Question q243 = new Question("Ukraine's capital is which city?", "Odesa",
                "Kiev", "Kharkiv","Lviv", 2, Category.EUROPE);
        addQuestion(q243);
        Question q244 = new Question("United Kingdom's capital is which city?", "Edinburgh",
                "London", "Manchester","Oxford", 2, Category.EUROPE);
        addQuestion(q244);


        //StartAfrica country capitals
        Question q301 = new Question("Algeria's capital is:", "Algiers",
                "Abalessa", "Ouargla","Bordj El Haouas", 1, Category.AFRICA);
        addQuestion(q301);
        Question q302 = new Question(" Angola's capital is which city?", "Luanda",
                "Lobito", "Namibe","Malanje", 1, Category.AFRICA);
        addQuestion(q302);
        Question q303 = new Question("Benin's capital is which city?", "Cotonou",
                "Porto-Novo", "Djougou","Natitingou", 2, Category.AFRICA);
        addQuestion(q303);
        Question q304 = new Question("Botswana's capital is which city?", "Maun",
                "Gaborone", "Kanye","Francistown", 2, Category.AFRICA);
        addQuestion(q304);
        Question q305 = new Question("The capital of Burkina Faso is...?", "Bobo-Dioulasso",
                "Kaya", "Ouagadougou","Koudougou", 3, Category.AFRICA);
        addQuestion(q305);
        Question q306 = new Question("Burundi's capital is:", "Mutumba",
                "Kayogoro", "Bujumbura","Bubanza", 3, Category.AFRICA);
        addQuestion(q306);
        Question q307 = new Question("Cabo Verde's capital is which city?", "Mindelo",
                "Sao Filipe", "Santa Maria","Praia", 4, Category.AFRICA);
        addQuestion(q307);
        Question q308 = new Question("Cameroon's capital is which city?", "Douala",
                "Bertoua", "Garoua","Yaounde", 4, Category.AFRICA);
        addQuestion(q308);
        Question q309 = new Question("Central African Republic's capital is:", "Bangiu",
                "Bambari", "Bangassou","Bria", 1, Category.AFRICA);
        addQuestion(q309);
        Question q310 = new Question(" Chad's capital is which city?", "N'Djamena",
                "Moundou", "Faya-Largeau","Fada", 1, Category.AFRICA);
        addQuestion(q310);
        Question q311 = new Question("Comoros' capital is which city?", "Bouenindi",
                "Moroni", "Mutsamudu","Fomboni", 2, Category.AFRICA);
        addQuestion(q311);
        Question q312 = new Question("The Congo Republic's capital is which city?", "Pointe-Noire",
                "Brazzaville", "Owando","Loubomo", 2, Category.AFRICA);
        addQuestion(q312);
        Question q313 = new Question("The capital of The Democratic Republic of the Congo is...?", "Kisangani",
                "Kananga", "Kinshasa","Kitwit", 3, Category.AFRICA);
        addQuestion(q313);
        Question q314 = new Question("Cote d'Ivoire's capital is:", "Abidjan",
                "Korhogo", "Yamoussoukro","San-Pedro", 3, Category.AFRICA);
        addQuestion(q314);
        Question q315 = new Question("Djibouti's capital is which city?", "Tew'o",
                "Egahlou", "Balho","Djibouti City", 4, Category.AFRICA);
        addQuestion(q315);
        Question q316 = new Question("Egypt's capital is which city?", "Alexanderia",
                "Asyut", "Port Said","Cairo", 4, Category.AFRICA);
        addQuestion(q316);
        Question q317 = new Question("Equatorial Guinea's capital is:", "Malabo",
                "Bata", "Aconibe","Cogo", 1, Category.AFRICA);
        addQuestion(q317);
        Question q318 = new Question(" Eritrea's capital is which city?", "Asmara",
                "Keren", "Barentu","Massawa", 1, Category.AFRICA);
        addQuestion(q318);
        Question q319 = new Question("Ethiopia's capital is which city?", "Finchawa",
                "Addis Ababa", "Dessie","Mek'ele", 2, Category.AFRICA);
        addQuestion(q319);
        Question q320 = new Question("Gabon's capital is which city?", "Franceville",
                "Libreville", "Booue","Port-Gentil", 2, Category.AFRICA);
        addQuestion(q320);
        Question q321 = new Question("The capital of The Gambia is...?", "Serrekunda",
                "Gunjur", "Banjul","Bansang", 3, Category.AFRICA);
        addQuestion(q321);
        Question q322 = new Question("Ghana's capital is:", "Kumasi",
                "Tamale", "Accra","Takoradi", 3, Category.AFRICA);
        addQuestion(q322);
        Question q323 = new Question("Guinea's capital is which city?", "Kankan",
                "Boke", "Kindia","Conakry", 4, Category.AFRICA);
        addQuestion(q323);
        Question q324 = new Question("Guinea-Bissau's capital is which city?", "Buba",
                "Gabu", "Catio","Bissau", 4, Category.AFRICA);
        addQuestion(q324);
        Question q325 = new Question("Kenya's capital is:", "Nairobi",
                "Mombasa", "Nakuru","Marsabit", 1, Category.AFRICA);
        addQuestion(q325);
        Question q326 = new Question(" Lesotho's capital is which city?", "Maseru",
                "Durban", "Mafeteng","Patlong", 1, Category.AFRICA);
        addQuestion(q326);
        Question q327 = new Question("Liberia's capital is which city?", "Greenville",
                "Monrovia", "Gbarnga","Buchanan", 2, Category.AFRICA);
        addQuestion(q327);
        Question q328 = new Question("Libya's capital is which city?", "Misrata",
                "Tripoli", "Benghazi","Awbari", 2, Category.AFRICA);
        addQuestion(q328);
        Question q329 = new Question("The capital of Madagascar is...?", "Mahajanga",
                "Manakara", "Antananarivo","Toamasina", 3, Category.AFRICA);
        addQuestion(q329);
        Question q330 = new Question("Malawi's capital is:", "Zomba",
                "Blantyre", "Lilongwe","Mzuzu", 3, Category.AFRICA);
        addQuestion(q330);
        Question q331 = new Question("Mali's capital is which city?", "Bourem",
                "Kayes", "Mopti","Bamako", 4, Category.AFRICA);
        addQuestion(q331);
        Question q332 = new Question("Mauritania's capital is which city?", "Kiffa",
                "Nouadhibou", "Atar","Nouakchott", 4, Category.AFRICA);
        addQuestion(q332);
        Question q333 = new Question("Mauritius's capital is:", "Port Louis",
                "Flic en Flac", "Grand Baie","Mahenbourg", 1, Category.AFRICA);
        addQuestion(q333);
        Question q334 = new Question(" Morocco's capital is which city?", "Rabat",
                "Casablanca", "Marrakesh","Agadir", 1, Category.AFRICA);
        addQuestion(q334);
        Question q335 = new Question("Mozambique's capital is which city?", "Nampula",
                "Maputo", "Montepuez","Chimoio", 2, Category.AFRICA);
        addQuestion(q335);
        Question q336 = new Question("Namibia's capital is which city?", "Swakopmund",
                "Windhoek", "Keetmanshoop","Grootfontein", 2, Category.AFRICA);
        addQuestion(q336);
        Question q337 = new Question("The capital of Niger is...?", "Agadez",
                "Madama", "Niamey","Bouza", 3, Category.AFRICA);
        addQuestion(q337);
        Question q338 = new Question("Nigeria's capital is:", "Kano",
                "Lagos", "Abuja","Port Harcourt", 3, Category.AFRICA);
        addQuestion(q338);
        Question q339 = new Question("Rwanda's capital is which city?", "Butare",
                "Ruhengeri", "Nyagatare","Kigali", 4, Category.AFRICA);
        addQuestion(q339);
        Question q340 = new Question("Sao Tome & Principe's capital is which city?", "Porto Alegre",
                "Neves", "Trindade","Sao Tome", 4, Category.AFRICA);
        addQuestion(q340);
        Question q341 = new Question("Senegal's capital is:", "Dakar",
                "Tambacounda", "Touba","St. Louis", 1, Category.AFRICA);
        addQuestion(q341);
        Question q342 = new Question(" Seychelles's capital is which city?", "Victoria",
                "Anse Royale", "Baie Ste Anne","Grand Anse", 1, Category.AFRICA);
        addQuestion(q342);
        Question q343 = new Question("Sierra Leone's capital is which city?", "Kenema",
                "Freetown", "Makeni","Port Loko", 2, Category.AFRICA);
        addQuestion(q343);
        Question q344 = new Question("Somalia's capital is which city?", "Berbera",
                "Mogadishu", "Jilib","Banderadley", 2, Category.AFRICA);
        addQuestion(q344);
        Question q345 = new Question("Which city is NOT considered a capital of South Africa?", "Pretoria",
                "Cape Town", "Port Elizabeth","Bloemfontein", 3, Category.AFRICA);
        addQuestion(q345);
        Question q346 = new Question("South Sudan's capital is:", "Malakal",
                "Wau", "Juba","Mundri West", 3, Category.AFRICA);
        addQuestion(q346);
        Question q347 = new Question("Sudan's capital is which city?", "Kassala",
                "Al Goled", "Nyala","Khartoum", 4, Category.AFRICA);
        addQuestion(q347);
        Question q348 = new Question("Swaziland's capital is which city?", "Maputo",
                "Nelspruit", "Lobamba","Mbabane", 4, Category.AFRICA);
        addQuestion(q348);
        Question q349 = new Question("Tanzania's capital is:", "Dodoma",
                "Dar es Salaam", "Arusha","Kigoma", 1, Category.AFRICA);
        addQuestion(q349);
        Question q350 = new Question(" Togo's capital is which city?", "Lome",
                "Kara", "Lagos","Ibadan", 1, Category.AFRICA);
        addQuestion(q350);
        Question q351 = new Question("Tunisia's capital is which city?", "Sfax",
                "Tunis", "Sousse","El Kef", 2, Category.AFRICA);
        addQuestion(q351);
        Question q352 = new Question("Uganda's capital is which city?", "Gulu",
                "Kampala", "Entebbe","Arua", 2, Category.AFRICA);
        addQuestion(q352);
        Question q353 = new Question("Zambia's capital is:", "Kitwe",
                "Mazabuka", "Lusaka","Kalomo", 3, Category.AFRICA);
        addQuestion(q353);
        Question q354 = new Question("Zimbabwe's capital is which city?", "Bulawayo",
                "Victoria Falls", "Harare","Mutare", 3, Category.AFRICA);
        addQuestion(q354);

        //Start Asia_Australia Capitals
        Question q401 = new Question("Australia's capital is which city?", "Sydney",
                "Brisbane", "Canberra","Perth", 3, Category.ASIA_AUSTRALIA);
        addQuestion(q401);
        Question q402 = new Question("Bangladesh's capital is which city?", "Chittagong",
                "Rajshahi", "Madaripur","Dhaka", 4, Category.ASIA_AUSTRALIA);
        addQuestion(q402);
        Question q403 = new Question("Bhutan's capital is which city?", "Paro",
                "Trashigang", "Punakha","Thimphu", 4, Category.ASIA_AUSTRALIA);
        addQuestion(q403);
        Question q404 = new Question("Brunei's capital is:", "Bandar Seri Begawan",
                "Sandakan", "Singapore","Balikpapan", 1, Category.ASIA_AUSTRALIA);
        addQuestion(q404);
        Question q405 = new Question(" Cambodia's capital is which city?", "Phnom Penh",
                "Bangkok", "Krong Siem Reap","Krong Battambang", 1, Category.ASIA_AUSTRALIA);
        addQuestion(q405);
        Question q406 = new Question("China's capital is which city?", "Hong Kong",
                "Beijing", "Chongqing","Hangzhou", 2, Category.ASIA_AUSTRALIA);
        addQuestion(q406);
        Question q407 = new Question("Micronesia's capital is which city?", "Kolonia",
                "Palikir", "Entebbe","Arua", 2, Category.ASIA_AUSTRALIA);
        addQuestion(q407);
        Question q408 = new Question("Fiji's capital is:", "Nadi",
                "Apia", "Suva","Tonga", 3, Category.ASIA_AUSTRALIA);
        addQuestion(q408);
        Question q409 = new Question("India's capital is which city?", "Mumbai",
                "Ahmedabad", "New Delhi","Terlingua", 3, Category.ASIA_AUSTRALIA);
        addQuestion(q409);
        Question q410 = new Question("Indonesia's capital is which city?", "Makassar",
                "Madan", "Manila","Jakarta", 4, Category.ASIA_AUSTRALIA);
        addQuestion(q410);
        Question q411 = new Question("Japan's capital is which city?", "Osaka",
                "Hiroshima", "Nagasaki","Tokyo", 4, Category.ASIA_AUSTRALIA);
        addQuestion(q411);
        Question q412 = new Question("Laos' capital is:", "Vientiane",
                "Hanoi", "Hai Phong","Singapore", 1, Category.ASIA_AUSTRALIA);
        addQuestion(q412);
        Question q413 = new Question(" Malaysia's capital is which city?", "Kuala Lumpur",
                "Ho Chi Minh", "Medan","Panang", 1, Category.ASIA_AUSTRALIA);
        addQuestion(q413);
        Question q414 = new Question("Maldives's capital is which city?", "Mysuru",
                "Male", "Colombo","Kochi", 2, Category.ASIA_AUSTRALIA);
        addQuestion(q414);
        Question q415 = new Question("Marshall Island's capital is which city?", "Enubirr",
                "Majuro", "Wotje","Bairiki", 2, Category.ASIA_AUSTRALIA);
        addQuestion(q415);
        Question q416 = new Question("Mongolia's capital is:", "Beijing",
                "Mandalgovi", "Ulaanbaater","Urgamal", 3, Category.ASIA_AUSTRALIA);
        addQuestion(q416);
        Question q417 = new Question("Nauru's capital is which city?", "Solomon",
                "Bairiki", "Yaren District","Port Moresby", 3, Category.ASIA_AUSTRALIA);
        addQuestion(q417);
        Question q418 = new Question("Myanmar(Burma)'s capital is which city?", "Mandalay",
                "Sittwe", "Naypyitaw","Yangon", 3, Category.ASIA_AUSTRALIA);
        addQuestion(q418);
        Question q419 = new Question("Nepal's capital is which city?", "Jaipur",
                "Nagpur", "New Delhi","Kathmandu", 4, Category.ASIA_AUSTRALIA);
        addQuestion(q419);
        Question q420 = new Question("New Zealand's capital is which city?", "Auckland",
                "Tauranga", "Dunedin","Wellington", 4, Category.ASIA_AUSTRALIA);
        addQuestion(q420);
        Question q421 = new Question("North Korea's capital is:", "Pyongyang",
                "Hanoi", "Seoul","Shanghai", 1, Category.ASIA_AUSTRALIA);
        addQuestion(q421);
        Question q422 = new Question(" Papua New Guinea's capital is which city?", "Port Moresby",
                "Solomon", "Lae","Santa Isabel", 1, Category.ASIA_AUSTRALIA);
        addQuestion(q422);
        Question q423 = new Question("Palau's capital is which city?", "Klouklubed",
                "Ngerulmud", "Koror","Ollei", 2, Category.ASIA_AUSTRALIA);
        addQuestion(q423);
        Question q424 = new Question("Philippines's capital is which city?", "Cebu",
                "Manila", "Davao","Negros", 2, Category.ASIA_AUSTRALIA);
        addQuestion(q424);
        Question q425 = new Question("Singapore's capital is:", "Jakarta",
                "Medan", "Singapore","Bangkok", 3, Category.ASIA_AUSTRALIA);
        addQuestion(q425);
        Question q426 = new Question("Samoa's capital is which city?", "Lalomanu",
                "Fagamalo", "Apia","Suva", 3, Category.ASIA_AUSTRALIA);
        addQuestion(q426);
        Question q427 = new Question("Solomon Island's capital is which city?", "Buka Town",
                "Port Vila", "Port Moresby","Honiara", 4, Category.ASIA_AUSTRALIA);
        addQuestion(q427);
        Question q428 = new Question("South Korea's capital is which city?", "Osaka",
                "Tianjin", "Pyongyang","Seoul", 4, Category.ASIA_AUSTRALIA);
        addQuestion(q428);
        Question q429 = new Question("Sri Lanka's capital is:", "Colombo",
                "Bengaluru", "Galle","Kochi", 1, Category.ASIA_AUSTRALIA);
        addQuestion(q429);
        Question q430 = new Question("Thailand's capital is which city?", "Bangkok",
                "Chiang Mai", "Rayong","Yangon", 1, Category.ASIA_AUSTRALIA);
        addQuestion(q430);
        Question q431 = new Question("Timor-Leste's capital is which city?", "Darwin",
                "Dili", "Atambua","Tutuala", 2, Category.ASIA_AUSTRALIA);
        addQuestion(q431);
        Question q432 = new Question("Tonga's capital is which city?", "Pangai",
                "Nuku'Alofa", "Suva","Muitoa", 2, Category.ASIA_AUSTRALIA);
        addQuestion(q432);
        Question q433 = new Question("Tuvalu's capital is:", "Tonga",
                "Solomon", "Funafuti","Bangkok", 3, Category.ASIA_AUSTRALIA);
        addQuestion(q433);
        Question q434 = new Question("Vanuatu's capital is which city?", "Noumea",
                "Suva", "Port Vila","San Cristobal", 3, Category.ASIA_AUSTRALIA);
        addQuestion(q434);
        Question q435 = new Question("Vietnam's capital is which city?", "Hong Kong",
                "Hai Phong", "Ho Chi Minh","Hanoi", 4, Category.ASIA_AUSTRALIA);
        addQuestion(q435);
        Question q436 = new Question("Kiribati's capital is which city?", "Auckland",
                "Honolulu", "Apia","South Tarawa", 4, Category.ASIA_AUSTRALIA);
        addQuestion(q436);


        //Start middle East capitals
        Question q501 = new Question("Afghanistan's capital is which city?", "Kandahar",
                "Mazari Sharif", "Anar Dara","Kabul", 4, Category.MIDDLE_EAST);
        addQuestion(q501);
        Question q502 = new Question("Armenia's capital is which city?", "Gyumri",
                "Sevan", "Tatev","Yerevan", 4, Category.MIDDLE_EAST);
        addQuestion(q502);
        Question q503 = new Question("Azerbaijan's capital is:", "Baku",
                "Ganja", "Sheki","Shivran", 1, Category.MIDDLE_EAST);
        addQuestion(q503);
        Question q504 = new Question(" Bahrain's capital is which city?", "Manama",
                "Dammam", "Riffa","Saar", 1, Category.MIDDLE_EAST);
        addQuestion(q504);
        Question q505 = new Question("Georgia's capital is which city?", "Batumi",
                "Tbilisi", "Kutaisi","Borjomi", 2, Category.MIDDLE_EAST);
        addQuestion(q505);
        Question q506 = new Question("Iran's capital is which city?", "Shiraz",
                "Tehran", "Mashhad","Tabriz", 2, Category.MIDDLE_EAST);
        addQuestion(q506);
        Question q507 = new Question("Iraq's capital is:", "Basrah",
                "Erbil", "Baghdad","Karbala", 3, Category.MIDDLE_EAST);
        addQuestion(q507);
        Question q508 = new Question("Jordan's capital is which city?", "Irbid",
                "Ramadi", "Amman","Al Bukamal", 3, Category.MIDDLE_EAST);
        addQuestion(q508);
        Question q509 = new Question("Israel's capital is which city?", "Nazareth",
                "Netanya", "Tel Aviv","Jerusalem", 4, Category.MIDDLE_EAST);
        addQuestion(q509);
        Question q510 = new Question("Kazakhstan's capital is which city?", "Shymkent",
                "Almaty", "Karagandy","Astana", 4, Category.MIDDLE_EAST);
        addQuestion(q510);
        Question q511 = new Question("Kuwait's capital is:", "Kuwait City",
                "Khafji", "Basrah","Shiraz", 1, Category.MIDDLE_EAST);
        addQuestion(q511);
        Question q512 = new Question(" Kyrgyzstan's capital is which city?", "Bishkek",
                "Osh", "Andijan","Karakol", 1, Category.MIDDLE_EAST);
        addQuestion(q512);
        Question q513 = new Question("Lebanon's capital is which city?", "Homs",
                "Beirut", "Tyre","Damascus", 2, Category.MIDDLE_EAST);
        addQuestion(q513);
        Question q514 = new Question("Oman's capital is which city?", "Nizwa",
                "Muscat", "Salalah","Sur", 2, Category.MIDDLE_EAST);
        addQuestion(q514);
        Question q515 = new Question("Pakistan's capital is:", "Lahore",
                "Karachi", "Islamabad","Kabul", 3, Category.MIDDLE_EAST);
        addQuestion(q515);
        Question q516 = new Question("State of Palestine's capital is which city?", "Tabuk",
                "Suez", "Ramallah","Karbala", 3, Category.MIDDLE_EAST);
        addQuestion(q516);
        Question q517 = new Question("Qatar's capital is which city?", "Al Khor",
                "Al Hofuf", "Dammam","Doha", 4, Category.MIDDLE_EAST);
        addQuestion(q517);
        Question q518 = new Question("Saudi Arabia's capital is which city?", "Dammam",
                "Mecca", "Medina","Riyadh", 4, Category.MIDDLE_EAST);
        addQuestion(q518);
        Question q519 = new Question("Syria's capital is:", "Damascus",
                "Aleppo", "Gaza","Konya", 1, Category.MIDDLE_EAST);
        addQuestion(q519);
        Question q520 = new Question(" Tajikistan's capital is which city?", "Dushanbe",
                "Samarkand", "Mazari Sharif","Kulob", 1, Category.MIDDLE_EAST);
        addQuestion(q520);
        Question q521 = new Question("Turkey's capital is which city?", "Istanbul",
                "Ankara", "Izmir","Bursa", 2, Category.MIDDLE_EAST);
        addQuestion(q521);
        Question q522 = new Question("Turkmenistan's capital is which city?", "Turkmenabat",
                "Ashgabat", "Mary","Mashhad", 2, Category.MIDDLE_EAST);
        addQuestion(q522);
        Question q523 = new Question("The capital of the United Arab Emirates is?", "Dubai",
                "Al Ain", "Sharjah","Abu Dhabi", 4, Category.MIDDLE_EAST);
        addQuestion(q523);
        Question q524 = new Question("Uzbekistan's capital is:", "Samarkand",
                "Nukus", "Tashkent","Qarshi", 3, Category.MIDDLE_EAST);
        addQuestion(q524);
        Question q525 = new Question("Yemen's capital is which city?", "Jeddah",
                "Aden", "Sana'a","Bisha", 3, Category.MIDDLE_EAST);
        addQuestion(q525);
        Question q526 = new Question("Cyprus's capital is which city?", "Nicosia",
                "Beirut", "Mersin","Limassol", 1, Category.MIDDLE_EAST);
        addQuestion(q526);


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
