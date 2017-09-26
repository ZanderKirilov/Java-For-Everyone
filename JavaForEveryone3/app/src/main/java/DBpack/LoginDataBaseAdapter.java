package DBpack;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginDataBaseAdapter {
    static final String DATABASE_NAME = "users.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    static final String DATABASE_CREATE = "create table USER"+
            "( userID integer primary key autoincrement,USERNAME  text,PASSWORD text, EMAIL text); ";
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

    public void insertEntry(String username,String password, String email) {
        if (email == null || email.equals("")){
            email = "NOMAIL";
        }
        ContentValues newValues = new ContentValues();
        newValues.put("USERNAME", username);
        newValues.put("PASSWORD",password);
        newValues.put("EMAIL", email);
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
    private void setUpAllAchievements(String userName){
        HashMap<String, String> allAchievements = new HashMap<>();
        allAchievements.put("First Achievement", "Unlocks when you pass the first test");
        allAchievements.put("Level up", "Unlocks after 2 exams");
        allAchievements.put("First of many", "You've made 100points on test");
        allAchievements.put("Code Master", "Unlocks after all exams");
        allAchievements.put("Sticky Fingers", "You need more practice");
        allAchievements.put("You actually did it!", "You're the winner, congrats!");

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