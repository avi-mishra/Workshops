package android.example.workshop.Adapter

import android.example.workshop.Dashboard
import android.example.workshop.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dashboardlayout.view.*

class DashboardAdapter(var ws:ArrayList<Dashboard>): RecyclerView.Adapter<DashboardAdapter.MyViewHolder>(){
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//        val ibDelete=itemView.findViewById<ImageButton>(R.id.ibDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //        itemView.ibDelete.setOnClickListener {
//            listener.onItemClick(ws[itemView.absoluteAdapterPosition])
//        }
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.dashboardlayout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.tvRegisteredWorkshopName.text=ws[position].name
        holder.itemView.tvRegisteredWorkshopDes.text=ws[position].des
    }

    override fun getItemCount(): Int {
        return ws.size
    }
}
//interface ClickListener{
//    fun onItemClick(dashboard: Dashboard)
//}