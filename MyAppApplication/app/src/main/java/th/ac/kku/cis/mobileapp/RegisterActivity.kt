package th.ac.kku.cis.mobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    private val TAG: String = "Register Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()

        if (mAuth!!.currentUser != null) {
            startActivity(Intent(this@RegisterActivity, ResultActivity::class.java))
            finish()
        }

        button2.setOnClickListener {
            val email = editText.text.toString().trim { it <= ' ' }
            val password = editText2.text.toString().trim { it <= ' ' }

            if (email.isEmpty()) {
                Toast.makeText(this,"Please enter your email address.", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Email was empty!")
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(this,"Please enter your password.", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Password was empty!")
                return@setOnClickListener
            }

            mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    if (password.length < 6) {
                        Toast.makeText(this,"Please check your password. Password must 6 character.",
                            Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "Enter password less than 6 characters.")
                    } else {
                        Toast.makeText(this,"Authentication Failled."+task.exception, Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "Authentication Failed: " + task.exception!!.message)
                    }
                } else {
                    Toast.makeText(this,"Sign in successfully!.", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Create account successfully!")
                    startActivity(Intent(this@RegisterActivity, ResultActivity::class.java))
                    finish()
                }
            }
        }

        button4.setOnClickListener { startActivity(Intent(this@RegisterActivity, LoginActivity::class.java)) }
    }
}
