package com.example.urmood.presentation.ui.ui.model

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.urmood.R

class ContactListAdapter(
    private val listContact: ArrayList<Contact>): RecyclerView.Adapter<ContactListAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_contact_name)
        val tvNumber: TextView = itemView.findViewById(R.id.tv_contact_number)
        val imgPhoto: ImageView = itemView.findViewById(R.id.iv_contact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listContact.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, photo, number) = listContact[position]
        holder.tvName.text = name
        holder.imgPhoto.setImageResource(photo)
        holder.tvNumber.text = number


        holder.itemView.setOnClickListener {
            val toNumber = "whatsapp:$number"
            val message = "Hallo"
            val uri = Uri.parse("https://api.whatsapp.com/send?phone=$toNumber&text=$message")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            holder.itemView.context.startActivity(intent)
        }
    }

}