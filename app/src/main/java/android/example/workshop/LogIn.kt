package android.example.workshop

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.example.workshop.Db.MyDbHelper
import android.example.workshop.Db.UserTable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_log_in.*
import java.lang.NullPointerException

public class LogIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        var spref=getSharedPreferences("MYPREFS",MODE_PRIVATE)
        val editor=spref.edit()
        val em=spref.getString("email",null)
        Log.d("TAG8", "onCreate: $em")

        if(em!=null){
            Toast.makeText(this,"Log In Successful",Toast.LENGTH_SHORT).show()
            val i=Intent(this,MainActivity::class.java)
            startActivity(i)
            finish()
        }

        btnLogIN.setOnClickListener {
            val db=MyDbHelper(this).writableDatabase
            try {
                var email=etLogInEmail.text.toString()
                var pass=etLogInPassword.text.toString()
                var h=UserTable.getUser(db,email,pass)
                Log.d("TAG9", "onCreate: $h")
                if(h) {
                    Toast.makeText(this,"Log In Successful",Toast.LENGTH_SHORT).show()
                    val i=Intent(this,MainActivity::class.java)
                    editor.putString("email",email)
                    editor.putString("pass",pass)
                    editor.commit()
                    startActivity(i)
                    finish()
                }
                else {
                    Toast.makeText(this,"Error while Logging In",Toast.LENGTH_SHORT).show()
                }
            }
            catch (e:NullPointerException) {
                Toast.makeText(this,"Please fill all the details",Toast.LENGTH_SHORT).show()
            }
        }
        btnSignUp.setOnClickListener {
            val intent=Intent(this@LogIn,SignUp::class.java)
            startActivity(intent)
        }
    }
}