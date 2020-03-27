package th.ac.kku.cis.mobileapp.finalapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import th.ac.kku.cis.mobileapp.finalapplication.R
import th.ac.kku.cis.mobileapp.finalapplication.model.ToDoAdd

class ToDoItemAdapter(context: Context, toDoItemList: MutableList<ToDoAdd>) : BaseAdapter() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var itemList = toDoItemList


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // create object from view
        val name: String = itemList.get(position).name as String
        val view: View
        val vh: ListRowHolder

        // get list view
        if (convertView == null) {
            view = mInflater.inflate(R.layout.listevent, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        vh.label.text =  name

        return view
    }

    override fun getItem(index: Int): Any {
        return itemList.get(index)
    }

    override fun getItemId(index: Int): Long {
        return index.toLong()
    }

    override fun getCount(): Int {
        return itemList.size
    }

    private class ListRowHolder(row: View?) {
        val label: TextView = row!!.findViewById<TextView>(R.id.textView6) as TextView
    }
}