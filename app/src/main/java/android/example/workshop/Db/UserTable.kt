package android.example.workshop.Db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log

object UserTable {
    val TABLE_NAME3 = "users"

    object Columns {
        val ID = "id"
        val NAME = "name"
        val EMAIL="email"
        val PASSWORD="password"

    }

    val CMD_CREATE_TABLE3 = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME3
        (
        ${Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${Columns.NAME} TEXT,
        ${Columns.EMAIL} TEXT,
        ${Columns.PASSWORD} TEXT
        );
         """.trimMargin()

    fun insertUser(db:SQLiteDatabase,name:String, email:String, pass:String) {
        val row=ContentValues()
        row.put(Columns.NAME,name)
        row.put(Columns.EMAIL,email)
        row.put(Columns.PASSWORD,pass)
        db.insert(TABLE_NAME3,null,row)
    }

    fun getUser(db:SQLiteDatabase,email:String,pass: String):Boolean {
        val query= "select * from $TABLE_NAME3 where email=\"$email\" and password=\"$pass\""
        val c=db.rawQuery(query,null)
        if(c.count<=0) {
            c.close()
            return false
        }
        else {
            c.close()
            return true
        }
    }
//
//    fun currentUser(db:SQLiteDatabase):Boolean {
//        val query="select * from $TABLE_NAME3 where ${Columns.EMAIL}"
//        val c=db.rawQuery(query,null)
//        if(c.count<=0)
//        {
//            c.close()
//            return false
//        }
//        else {
//            c.close()
//            return true
//        }
//    }
}
