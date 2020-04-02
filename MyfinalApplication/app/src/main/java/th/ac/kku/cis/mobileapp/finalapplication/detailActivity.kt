package th.ac.kku.cis.mobileapp.finalapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class detailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var name = getIntent().getStringExtra("name")
        var type = getIntent().getStringExtra("type")
        var detail = getIntent().getStringExtra("detail")

        textView2.text = name
        textView13.text = type
        textView12.text = detail

    }
}
