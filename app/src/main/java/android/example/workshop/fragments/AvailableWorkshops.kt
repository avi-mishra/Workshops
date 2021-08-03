package android.example.workshop.fragments

import android.content.Context
import android.content.Intent
import android.example.workshop.Adapter.ItemClick
import android.example.workshop.Adapter.MyAdapter
import android.example.workshop.Db.DashboardTable
import android.example.workshop.Db.MyDbHelper
import android.example.workshop.Db.UserTable
import android.example.workshop.Db.WorkshopTable
import android.example.workshop.LogIn
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.workshop.R
import android.example.workshop.Workshop
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_available_workshops.view.*
import kotlinx.android.synthetic.main.workshoplayout.*

class AvailableWorkshops : Fragment(),ItemClick{

    var ws=ArrayList<Workshop>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var spref= this.activity?.getSharedPreferences("MYPREFS",Context.MODE_PRIVATE)
        var email= spref?.getString("email",null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_available_workshops, container, false)
        val db= MyDbHelper(requireContext()).readableDatabase
        ws=WorkshopTable.getAllWS(db)
        Log.d("workshops", "${ws[0]}")
        recyclerView(view)
        return view
    }
    private fun recyclerView(view: View) {
        view.rvWorkshops.layoutManager=LinearLayoutManager(requireContext())
        view.rvWorkshops.adapter=MyAdapter(ws,this)
    }

    override fun onItemClick(workshop: Workshop) {
        var count=0
        var spref= this.activity?.getSharedPreferences("MYPREFS",Context.MODE_PRIVATE)
        var email= spref?.getString("email",null)
        val db=MyDbHelper(requireContext()).writableDatabase
        if(email!=null) {
            var ws=DashboardTable.getAllWS(db,email)
            for(i in ws){
                if(i.name==workshop.name)
                    count++
            }
            if(count==0){
                DashboardTable.insert(db,workshop,email!!)
                Toast.makeText(activity," Workshop Selected",Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(requireContext(),"Already applied to this Workshop",Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(requireContext(),"Please Log In to Register workshop",Toast.LENGTH_SHORT).show()
            val i=Intent(requireContext(),LogIn::class.java)
            startActivity(i)
        }
    }
//    fun disableButton(email:String,workshop: Workshop){
//        val db=MyDbHelper(requireContext()).readableDatabase
//        var ws=DashboardTable.getAllWS(db,email)
//        for(i in ws){
//            if(i.name==workshop.name){
//                btnApply.isEnabled=false
//            }
//        }
//    }
}