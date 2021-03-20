package com.mobilestudio.android_105_navigation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobilestudio.android_105_navigation.databinding.ItemMailBinding
import com.mobilestudio.android_105_navigation.models.Mail

class AdapterMailKotlin(private val sourceData: List<Mail>) : RecyclerView.Adapter<AdapterMailKotlin.ViewHolder>() {

    lateinit var listener: (Mail) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflateBinding())
    }

    private fun ViewGroup.inflateBinding(): ItemMailBinding {
        return ItemMailBinding.inflate(
                LayoutInflater.from(context),
                this,
                false
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(sourceData[position])
        holder.itemViewMail.root.setOnClickListener {
            if (::listener.isInitialized) {
                listener(sourceData[position])
            }
        }

    }

    override fun getItemCount(): Int = sourceData.size

    fun setOnItemClickListener(block: (Mail) -> Unit) {
        listener = block
    }

    class ViewHolder(val itemViewMail: ItemMailBinding)
        : RecyclerView.ViewHolder(itemViewMail.root) {

        fun bindView(mail: Mail) {
            itemViewMail.txtItemTitle.text = mail.title
            itemViewMail.txtItemSubject.text = mail.subject
        }
    }
}