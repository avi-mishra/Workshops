package android.example.workshop.Db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.example.workshop.Dashboard
import android.example.workshop.Workshop
import androidx.lifecycle.LiveData

object DashboardTable {
     var TABLE_NAME1 = "dashboard"

    object Columns{
        val ID="id"
        val EMAIL="user"
        val NAME="name"
        val DES="des"
    }
    val CMD_CREATE_TABLE2="""
        CREATE TABLE IF NOT EXISTS $TABLE_NAME1
        (
        ${Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${Columns.EMAIL} TEXT,
        ${Columns.NAME} TEXT,
        ${Columns.DES} TEXT
        );
         """.trimMargin()

    fun insert(db:SQLiteDatabase,workshop: Workshop,email:String) {
        val row=ContentValues()
        row.put(Columns.EMAIL,email)
        row.put(Columns.NAME,workshop.name)
        row.put(Columns.DES,workshop.des)
        db?.insert(TABLE_NAME1,null,row)
    }

    fun getAllWS(db:SQLiteDatabase,email:String):ArrayList<Dashboard> {
        val ws=ArrayList<Dashboard>()
        val query= "select * from $TABLE_NAME1 where user=\"$email\""
        var c=db.rawQuery(query,null)
        while (c.moveToNext()){
            var w=Dashboard(c.getString(2),c.getString(3))
            ws.add(w)
        }
        return ws
    }
//    fun delete(db: SQLiteDatabase,name: String){
////        db.delete(TABLE_NAME1,"where name=\"$name\"",null)
//        db.delete(TABLE_NAME1, Columns.NAME+"="+name, null)
//    }
//    fun getAllWS(db:SQLiteDatabase,email:String):ArrayList<Dashboard>{
//        val ws=ArrayList<Dashboard>()
//        var c=db.query(
//            TABLE_NAME1,
//            arrayOf(Columns.EMAIL,Columns.ID, Columns.NAME, Columns.DES),
//            null,null,null,null,null)
//
//        while (c.moveToNext()){
//            val w=Dashboard(c.getString(2),c.getString(3))
//            ws.add(w)
//        }
//        return ws
//    }
}