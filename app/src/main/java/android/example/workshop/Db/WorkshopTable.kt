package android.example.workshop.Db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.example.workshop.Workshop
import android.util.Log

object WorkshopTable{
    val TABLE_NAME="workshops"

    object Columns {
        val ID="id"
        val NAME="name"
        val DES="des"
    }
    val CMD_CREATE_TABLE="""
        CREATE TABLE IF NOT EXISTS $TABLE_NAME
        (
        ${Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${Columns.NAME} TEXT,
        ${Columns.DES} TEXT
        );
         """.trimMargin()

    fun insertWS(db:SQLiteDatabase) {
        //1st workshop
        val row1=ContentValues()
        row1.put(Columns.NAME,"Public Speaking")
        row1.put(Columns.DES,"Learn to speak confidently in public")
        val newRowId1 = db?.insert(TABLE_NAME, null, row1)
        //2 workshop
        val row2=ContentValues()
        row2.put(Columns.NAME,"Anger Management")
        row2.put(Columns.DES,"Learn skills to control your anger.")
        val newRowId2 = db?.insert(TABLE_NAME, null, row2)
        //3 workshop
        val row3=ContentValues()
        row3.put(Columns.NAME,"Stress Management")
        row3.put(Columns.DES,"Stress can cause your future, learn skills to reduce stress")
        val newRowId3 = db?.insert(TABLE_NAME, null, row3)
        //4 workshop
        val row4=ContentValues()
        row4.put(Columns.NAME,"Manage your Attitude")
        row4.put(Columns.DES,"A good attitude can do good to yourself and others around you.")
        val newRowId4 = db?.insert(TABLE_NAME, null, row4)
        //5 workshop
        val row5=ContentValues()
        row5.put(Columns.NAME,"Impactful presentations")
        row5.put(Columns.DES,"A good and impactful presentation can secure your job.")
        val newRowId5 = db?.insert(TABLE_NAME, null, row5)
        //6 workshop
        val row6=ContentValues()
        row6.put(Columns.NAME,"Effective Study Habits")
        row6.put(Columns.DES,"A good and effective study habits can help plan your preparations")
        val newRowId6 = db?.insert(TABLE_NAME, null, row6)
        //7 workshop
        val row7=ContentValues()
        row7.put(Columns.NAME,"Ethics value and respect")
        row7.put(Columns.DES,"Respect for persons may perhaps be the most fundamental principle in all of ethics.")
        val newRowId7 = db?.insert(TABLE_NAME, null, row7)
        //8 workshop
        val row8=ContentValues()
        row8.put(Columns.NAME,"Polish your personality")
        row8.put(Columns.DES,"Your personality becomes your authentic signature, a trademark that appeals to your target audience.")
        val newRowId8 = db?.insert(TABLE_NAME, null, row8)
        //9 workshop
        val row9=ContentValues()
        row9.put(Columns.NAME,"Managing interpersonal relationships")
        row9.put(Columns.DES,"It refers to a strong association among individuals working together in the same organization.")
        val newRowId9 = db?.insert(TABLE_NAME, null, row9)
        //10 workshop
        val row10=ContentValues()
        row10.put(Columns.NAME,"Wisdom Leadership")
        row10.put(Columns.DES,"Wisdom requires the experience to understand the real world in all of its breadth and complexity.")
        val newRowId10 = db?.insert(TABLE_NAME, null, row10)
        //11 workshop
        val row11=ContentValues()
        row10.put(Columns.NAME,"Stock Market")
        row10.put(Columns.DES,"Make your money grow faster with stock market.")
        val newRowId11 = db?.insert(TABLE_NAME, null, row10)
        //12 workshop
        val row12=ContentValues()
        row10.put(Columns.NAME,"Competitive Coding")
        row10.put(Columns.DES,"Master your coding skills to crack good Companies.")
        val newRowId12 = db?.insert(TABLE_NAME, null, row10)
        //13 workshop
        val row13=ContentValues()
        row10.put(Columns.NAME,"Web Development")
        row10.put(Columns.DES,"Learn to make new and exciting websites within 2 hours.")
        val newRowId13 = db?.insert(TABLE_NAME, null, row10)
        //14 workshop
        val row14=ContentValues()
        row10.put(Columns.NAME,"Android Development")
        row10.put(Columns.DES,"Learn new skills in android development with kotlin and firebase.")
        val newRowId14 = db?.insert(TABLE_NAME, null, row10)
        //10 workshop
        val row15=ContentValues()
        row10.put(Columns.NAME,"Chess")
        row10.put(Columns.DES,"Learn new strategies and master them to become a Grandmaster in chess.")
        val newRowId15 = db?.insert(TABLE_NAME, null, row10)


    }
    fun getAllWS(db:SQLiteDatabase):ArrayList<Workshop> {
        val ws=ArrayList<Workshop>()
        var c=db.query(TABLE_NAME,
        arrayOf(Columns.ID,Columns.NAME,Columns.DES),
        null,null,null,null,null)

        while (c.moveToNext()){
            val w=Workshop(c.getString(1),c.getString(2))
            ws.add(w)
        }
        return ws
    }
}