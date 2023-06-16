package com.example.urmood.presentation.utils

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.urmood.R
import com.example.urmood.data.model.Contact

class ContactListAdapter(
    private val listContact: ArrayList<Contact>
) : RecyclerView.Adapter<ContactListAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_contact_name)
        val tvDesc: TextView = itemView.findViewById(R.id.tv_contact_desc)
        val imgPhoto: ImageView = itemView.findViewById(R.id.iv_contact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listContact.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, photo, number, desc) = listContact[position]
        holder.tvName.text = name
        holder.imgPhoto.setImageResource(photo)
        holder.tvDesc.text = desc


        holder.itemView.setOnClickListener {
            val toNumber = "whatsapp:$number"
            val message = "Hallo"
            val uri = Uri.parse("https://api.whatsapp.com/send?phone=$toNumber&text=$message")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            holder.itemView.context.startActivity(intent)
        }
    }
}