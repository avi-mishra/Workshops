package android.example.workshop.fragments

import android.content.Context
import android.content.SharedPreferences
import android.example.workshop.Adapter.DashboardAdapter
import android.example.workshop.Dashboard
import android.example.workshop.Db.DashboardTable
import android.example.workshop.Db.MyDbHelper
import android.example.workshop.LogIn
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.workshop.R
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

class Dashboard : Fragment(){

    var rworkshops=ArrayList<Dashboard>()
    var adapter:DashboardAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var spref= this.activity?.getSharedPreferences("MYPREFS",Context.MODE_PRIVATE)
        getAllRegisteredWorkshops(spref!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view= inflater.inflate(R.layout.fragment_dashboard, container, false)
        recyclerView(view)
        return view
    }

    private fun getAllRegisteredWorkshops(spref:SharedPreferences) {
        var db= MyDbHelper(requireContext()).writableDatabase
        var email=spref.getString("email",null)
        Log.d("Current", "spref current email: $email")
        var ws=DashboardTable.getAllWS(db,email!!)
        rworkshops.clear()
        rworkshops.addAll(ws)
    }
    private fun recyclerView(view: View) {
        view.rvDashboards?.layoutManager= LinearLayoutManager(requireContext())
        view.rvDashboards?.adapter= DashboardAdapter(rworkshops)
    }

//    override fun onItemClick(dashboard: Dashboard) {
//        var db= MyDbHelper(requireContext()).writableDatabase
//        DashboardTable.delete(db,dashboard.name)
//        Toast.makeText(requireContext(),"Workshop Deleted",Toast.LENGTH_SHORT).show()
//    }
}