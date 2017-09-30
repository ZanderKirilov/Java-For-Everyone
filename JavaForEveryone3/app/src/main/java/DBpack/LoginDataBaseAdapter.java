package DBpack;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginDataBaseAdapter {
    static final String DATABASE_NAME = "users.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    static final String DATABASE_CREATE = "create table USER"+
            "( userID integer primary key autoincrement,USERNAME  text,PASSWORD text, EMAIL text, CURRENT_STAGE text, ALL_POINTS integer); ";
    static final String DATABASE_ACH_CREATE = "create table Achievement"+
            "( achievementID integer primary key autoincrement,TITLE text, INFO text, USERNAME text, POINTS integer, ACHIEVED boolean);";

    public  SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public  LoginDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  LoginDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String username,String password, String email, String current_achievement, int points) {
        if (email == null || email.equals("")){
            email = "NOMAIL";
        }
        ContentValues newValues = new ContentValues();
        newValues.put("USERNAME", username);
        newValues.put("PASSWORD",password);
        newValues.put("EMAIL", email);
        newValues.put("CURRENT_STAGE", current_achievement);
        newValues.put("ALL_POINTS", points);
        this.setUpAllAchievements(username);

        db.insert("USER", null, newValues);
        ///Toast.makeText(context, "USER added in DB", Toast.LENGTH_LONG).show();
    }

    public int deleteEntry(String UserName) {
        //String id=String.valueOf(userID);
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("USER", where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    public String getUserPassword(String userName) {
        Cursor cursor=db.query("USER", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1){ // UserName Not Exist

            cursor.close();
            return "USER DOES NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }
    public String getUserStage(String userName){
        Cursor cursor=db.query("USER", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1){ // UserName Not Exist
            cursor.close();
            return "USER DOES NOT EXIST";
        }
        cursor.moveToFirst();
        String stage = cursor.getString(cursor.getColumnIndex("CURRENT_STAGE"));
        cursor.close();
        return stage;
    }
    public String getUserEmail(String userName){
        Cursor cursor=db.query("USER", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1){ // UserName Not Exist
            cursor.close();
            return "USER DOES NOT EXIST";
        }
        cursor.moveToFirst();
        String e_mail = cursor.getString(cursor.getColumnIndex("EMAIL"));
        cursor.close();
        return e_mail;
    }
    public int getUserPoints(String userName){
        Cursor cursor=db.query("USER", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1){ // UserName Not Exist
            cursor.close();
            return -1;
        }
        cursor.moveToFirst();
        int points = cursor.getInt(cursor.getColumnIndex("ALL_POINTS"));
        cursor.close();
        return points;
    }

    public boolean isUsernameAvailable(String username){
        Cursor cursor = db.query("USER", null, " USERNAME=?", new String[]{username}, null, null,null);
        if (cursor.getCount() < 1){
            cursor.close();
            return true;
        }else{
            cursor.close();
            return false;
        }
    }
    public void  updateEntry(String userName,String password) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD",password);

        String where="USERNAME = ?";
        db.update("USER",updatedValues, where, new String[]{userName});
    }
    public void updateUserStage(String userName, String stage){
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("USERNAME", userName);
        updatedValues.put("CURRENT_STAGE", stage);

        String where="USERNAME = ?";
        db.update("USER",updatedValues, where, new String[]{userName});
    }




    public void updateAchievements(String username, String achievementTitle, Boolean achieved){
        //TODO - to be tested!
        ContentValues updatedAch = new ContentValues();
        updatedAch.put("USERNAME", username);
        updatedAch.put("TITLE", achievementTitle);
        updatedAch.put("ACHIEVED", achieved);

        String where="username = ?";
        db.update("Achievement", updatedAch, where, new String[]{username});

    }
    private void setUpAllAchievements(String userName){
        HashMap<String, String> allAchievements = new HashMap<>();
        allAchievements.put("Първо Постижение", "Отключва се когато си вземете първият тест");
        allAchievements.put("По - Високо ниво!", "Отключва се при два взети теста");
        allAchievements.put("Пъривят от Много", "Поздравления! Ти си получил 100 от 100 на тест");
        allAchievements.put("Влез в Матрицата!", "Отключва се при взимане на всички тестове");
        allAchievements.put("Непослушни Пръсти", "Трябва ти повече упражнение за по - добър резултат");
        allAchievements.put("Ти Всъщност Се Справи!", "Поздравления, ти взе всички постижения!");

        for (Map.Entry<String, String> achTemp : allAchievements.entrySet()) {
            ContentValues newValues = new ContentValues();
            // Assign values for each row.
            newValues.put("TITLE", achTemp.getKey());
            newValues.put("INFO",achTemp.getValue());
            newValues.put("username", userName);
            newValues.put("POINTS", 10);
            newValues.put("achieved", false);

            db.insert("Achievement", null, newValues);
        }
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    public HashMap<String, ArrayList<String>> getAllAchievements(String username){

        Cursor cursor=db.query("Achievement", null, " username=?", new String[]{username}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        String achievement = "";
        String achievementInfo = "";
        String achievementPoints = "";
        String achievementIsAchieved = "";

        HashMap<String, ArrayList<String>> achievements = new HashMap<>();

        for (int i = 0; i < 6; i++){
            achievement += cursor.getString(cursor.getColumnIndex("TITLE")) + "\n";
            achievementInfo += cursor.getString(cursor.getColumnIndex("INFO")) + "\n";
            achievementPoints += cursor.getString(cursor.getColumnIndex("POINTS")) + "\n";
            achievementIsAchieved += cursor.getString(cursor.getColumnIndex("ACHIEVED")) + "\n";
            achievements.put(achievement, new ArrayList());
            achievements.get(achievement).add(achievementInfo);
            achievements.get(achievement).add(achievementPoints);
            achievements.get(achievement).add(achievementIsAchieved);
            cursor.moveToNext();
        }
        cursor.close();
        return achievements;
    }
}