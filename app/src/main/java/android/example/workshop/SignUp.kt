package android.example.workshop

import android.content.Intent
import android.example.workshop.Db.MyDbHelper
import android.example.workshop.Db.UserTable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.lang.NullPointerException

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btnNewSignUp.setOnClickListener {
            var db=MyDbHelper(this).writableDatabase
            try {
                var name=etSignUPName.text.toString()
                var email=etSignUPEmail.text.toString()
                var pass=etSignUpPassword.text.toString()
                UserTable.insertUser(db, name, email, pass)
                val i = Intent(this@SignUp, LogIn::class.java)
                startActivity(i)
                finish()
            }
            catch (e:NullPointerException) {
                Toast.makeText(this,"Please fill all the details",Toast.LENGTH_SHORT).show()
            }
        }
    }
}