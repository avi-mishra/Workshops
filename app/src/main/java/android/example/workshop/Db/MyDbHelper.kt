package android.example.workshop.Db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.example.workshop.Db.DashboardTable.TABLE_NAME1
import android.example.workshop.Db.WorkshopTable.TABLE_NAME

class MyDbHelper(context:Context):SQLiteOpenHelper(
    context,
    "workshops.db",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(WorkshopTable.CMD_CREATE_TABLE)
        db?.execSQL(DashboardTable.CMD_CREATE_TABLE2)
        db?.execSQL(UserTable.CMD_CREATE_TABLE3)
        WorkshopTable.insertWS(db!!)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME1")
        onCreate(db)
        WorkshopTable.insertWS(db!!)
    }
}