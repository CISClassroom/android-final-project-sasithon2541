package th.ac.kku.cis.mobileapp.finalapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_addyum.*
import th.ac.kku.cis.mobileapp.finalapplication.model.ToDoAdd

class AddyumActivity : AppCompatActivity() {
    private val TAG:String = "add activity"

    var mAuth: FirebaseAuth? = null
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addyum)

        mDatabase = FirebaseDatabase.getInstance().reference
        button5.setOnClickListener {
            savedata()
        }
    }

        private fun savedata(){
            var name = editText3.text.toString().trim()
            var type = editText4.text.toString().trim()
            var detail = editText5.text.toString().trim()


            if (name.isEmpty()){
                editText3.error = "Please Enter a name"
                return
            }
            else if (type.isEmpty()) {
                editText4.error = "Please Enter a raw material"
                return
            }else if (detail.isEmpty()){
                editText5.error = "Please Enter a how do yum"
                return
            }

            var  todoItem = ToDoAdd.create()
            val newItem = mDatabase.child("Yum").push()
            // add new key to todoobject
            todoItem.id = newItem.key
            todoItem.name = name
            todoItem.type = type
            todoItem.detail = detail
            newItem.setValue(todoItem)
            finish()
    }
}
