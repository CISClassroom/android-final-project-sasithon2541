package th.ac.kku.cis.mobileapp.finalapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_show.*
import th.ac.kku.cis.mobileapp.finalapplication.adapter.ToDoItemAdapter
import th.ac.kku.cis.mobileapp.finalapplication.model.ToDoAdd

class showActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference

    var toDoItemList: MutableList<ToDoAdd>? = null
    lateinit var adapter: ToDoItemAdapter
    private var listViewItems: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        mDatabase = FirebaseDatabase.getInstance().reference
        listViewItems = findViewById<View>(R.id.listview) as ListView

        toDoItemList = mutableListOf<ToDoAdd>()
        adapter = ToDoItemAdapter(this, toDoItemList!!)
        listViewItems!!.setAdapter(adapter)
        mDatabase.orderByKey().addListenerForSingleValueEvent(itemListener)

        listview.setOnItemClickListener{ parent, view, position, id ->
            val intent = Intent(this, detailActivity::class.java)
            val selectedItem = parent.getItemAtPosition(position) as ToDoAdd
            intent.putExtra("name",selectedItem.name.toString())
            intent.putExtra("type",selectedItem.type.toString())
            intent.putExtra("detail",selectedItem.detail.toString())
            startActivity(intent)
        }
    }

    var itemListener: ValueEventListener = object : ValueEventListener {

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // call function
            addDataToList(dataSnapshot.child("Yum"))
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Item failed, display log a message
            Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
        }
    }

    private fun addDataToList(dataSnapshot: DataSnapshot) {
        val items = dataSnapshot.children.iterator()
        // Check if current database contains any collection
        if (items.hasNext()) {


            // check if the collection has any to do items or not
            while (items.hasNext()) {
                // get current item
                val currentItem = items.next()
                val map = currentItem.getValue() as HashMap<String, Any>
                // add data to object

                    val todoItem = ToDoAdd.create()
                    todoItem.name = map.get("name") as String
                    todoItem.type = map.get("type") as String
                    todoItem.detail = map.get("detail") as String
                    toDoItemList!!.add(todoItem);

            }

            adapter.notifyDataSetChanged()
        }

    }
}
