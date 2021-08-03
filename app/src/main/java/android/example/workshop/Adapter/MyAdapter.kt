package android.example.workshop.Adapter

import android.content.Context
import android.example.workshop.R
import android.example.workshop.Workshop
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.workshoplayout.view.*

class MyAdapter(var ws:ArrayList<Workshop>, val listener: ItemClick):RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val btnApply=itemView.findViewById<Button>(R.id.btnApply)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.workshoplayout,parent,false))
        itemView.btnApply.setOnClickListener {
            listener.onItemClick(ws[itemView.absoluteAdapterPosition])
        }
        return itemView
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.tvWorkshopName.text=ws[position].name
        holder.itemView.tvWorkshopDes.text=ws[position].des
    }

    override fun getItemCount(): Int {
        return ws.size
    }
}
interface ItemClick{
    fun onItemClick(workshop: Workshop)
}