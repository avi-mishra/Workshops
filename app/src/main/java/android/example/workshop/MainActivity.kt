package android.example.workshop

import android.content.Intent
import android.example.workshop.Db.MyDbHelper
import android.example.workshop.Db.UserTable
import android.example.workshop.fragments.AvailableWorkshops
import android.example.workshop.fragments.Dashboard
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var db=MyDbHelper(this).writableDatabase
        LogIn()
        supportFragmentManager.beginTransaction().replace(R.id.flContainer,AvailableWorkshops()).commit()

        btnWorkshops.setOnClickListener {
            Toast.makeText(this,"Btn Workshop clicked",Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.flContainer,AvailableWorkshops()).commit()
        }
        btnDashboard.setOnClickListener{
            val spref=getSharedPreferences("MYPREFS", MODE_PRIVATE)
            val email=spref.getString("email",null)
            if(email!=null){
                Toast.makeText(this,"Btn dashboard clicked",Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction().replace(R.id.flContainer,Dashboard()).commit()
            }
            else{
                Toast.makeText(this, "Please Log in to see registered Workshops",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val spref=getSharedPreferences("MYPREFS", MODE_PRIVATE)
        val email=spref.getString("email",null)
        if (email!=null) {
            menuInflater.inflate(R.menu.nav_menu,menu)
            return true
        }
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val spref=getSharedPreferences("MYPREFS", MODE_PRIVATE)
        val email=spref.getString("email",null)
        when(item.itemId){
            R.id.navSignOut->{
                Toast.makeText(this@MainActivity,"Signing Out",Toast.LENGTH_SHORT).show()
                val editor=spref.edit()
                editor.clear().commit()
                val i= Intent(this@MainActivity,LogIn::class.java)
                startActivity(i)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}