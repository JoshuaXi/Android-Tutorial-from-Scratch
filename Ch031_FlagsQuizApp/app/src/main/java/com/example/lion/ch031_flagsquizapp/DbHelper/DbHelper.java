package com.example.lion.ch031_flagsquizapp.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.lion.ch031_flagsquizapp.Common.Common;
import com.example.lion.ch031_flagsquizapp.Model.Question;
import com.example.lion.ch031_flagsquizapp.Model.Ranking;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lion on 6/30/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "MyDB.db";
    private static String DB_PATH = "";
    private SQLiteDatabase mDateBase;
    private Context mContext = null;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, 1);
        if(Build.VERSION.SDK_INT >=17){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else{
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }

        openDataBase(); //Add this line to fix db.insert can't insert values
        Log.e("DBPATH", DB_PATH);
        this.mContext = context;
    }

    public void openDataBase(){
        String myPath = DB_PATH + DB_NAME;
        mDateBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void copyDataBase() throws IOException{
        try{
            InputStream myInput = mContext.getAssets().open(DB_NAME);
            String outputFileName = DB_PATH + DB_NAME;
            OutputStream myOutput = new FileOutputStream(outputFileName);

            byte[] buffer = new byte[1024];
            int length;
            while((length=myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase tempDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            tempDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        }
        catch(SQLiteException e){
            e.printStackTrace();
        }
        if(tempDB != null)
            tempDB.close();
        return tempDB!= null?true:false;
    }

    public void createDataBase() throws IOException{
        boolean isDBExists = checkDataBase();
        if(isDBExists){

        }
        else {
            this.getReadableDatabase();
            try{
                copyDataBase();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void close() {
        if(mDateBase != null)
            mDateBase.close();
        super.close();
    }

    public List<Question> getAllQuestion(){
        List<Question> listQuestion = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        try{
            c = db.rawQuery("SELECT * FROM Question ORDER BY Random()", null);
            if(c==null) return null;
            c.moveToFirst();
            do{
                int Id = c.getInt(c.getColumnIndex("ID"));
                String Image = c.getString(c.getColumnIndex("Image"));
                String AnswerA = c.getString(c.getColumnIndex("AnswerA"));
                String AnswerB = c.getString(c.getColumnIndex("AnswerB"));
                String AnswerC = c.getString(c.getColumnIndex("AnswerC"));
                String AnswerD = c.getString(c.getColumnIndex("AnswerD"));
                String CorrectAnswer = c.getString(c.getColumnIndex("CorrectAnswer"));
                Question question = new Question(Id, Image, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer);
                listQuestion.add(question);
            }
            while (c.moveToNext());
            c.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return listQuestion;

    }

    public List<Question> getQuestionMode(String mode){
        List<Question> listQuestion = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        int limit = 0;
        if(mode.equals(Common.MODE.EASY.toString()))
            limit = 30;
        else if(mode.equals(Common.MODE.MEDIUM.toString()))
            limit = 50;
        else if(mode.equals(Common.MODE.HARD.toString()))
            limit = 100;
        else if(mode.equals(Common.MODE.HARDEST.toString()))
            limit = 200;


        try{
            c = db.rawQuery(String.format("SELECT * FROM Question ORDER BY Random() LIMIT %d", limit), null);
            if(c==null) return null;
            c.moveToFirst();
            do{
                int Id = c.getInt(c.getColumnIndex("ID"));
                String Image = c.getString(c.getColumnIndex("Image"));
                String AnswerA = c.getString(c.getColumnIndex("AnswerA"));
                String AnswerB = c.getString(c.getColumnIndex("AnswerB"));
                String AnswerC = c.getString(c.getColumnIndex("AnswerC"));
                String AnswerD = c.getString(c.getColumnIndex("AnswerD"));
                String CorrectAnswer = c.getString(c.getColumnIndex("CorrectAnswer"));
                Question question = new Question(Id, Image, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer);
                listQuestion.add(question);
            }
            while (c.moveToNext());
            c.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return listQuestion;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertScore(int Score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Score", Score);
        db.insert("Ranking", null, contentValues);
    }

    public List<Ranking> getRanking(){
        List<Ranking> listRanking = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        try{
            c = db.rawQuery("SELECT * FROM Ranking ORDER BY Score DESC;", null);
            if(c==null) return null;
            c.moveToNext();
            do{
                int Id = c.getInt(c.getColumnIndex("Id"));
                int Score = c.getInt(c.getColumnIndex("Score"));
                Ranking ranking = new Ranking(Id, Score);
                listRanking.add(ranking);
            }
            while(c.moveToNext());
            c.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return listRanking;
    }
}
