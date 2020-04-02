package th.ac.kku.cis.mobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    private val TAG: String = "Login Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        if(mAuth!!.currentUser != null){
            startActivity(Intent(this@LoginActivity,ResultActivity::class.java))
            finish()
        }
        button2.setOnClickListener{
            val email = editText.text.toString().trim{it <= ' '}
            val password = editText2.text.toString().trim{it <= ' '}

            if (email.isEmpty()){
                Toast.makeText(this,"Please enter your email address.", Toast.LENGTH_SHORT).show()
                Log.d(TAG,"Email was Empty!")
                return@setOnClickListener
            }
            if (password.isEmpty()){
                Toast.makeText(this,"Please enter your password.", Toast.LENGTH_SHORT).show()
                Log.d(TAG,"Password was Empty!")
                return@setOnClickListener
            }

            mAuth!!.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                    task ->
                if (!task.isSuccessful){
                    if (password.length < 6){
                        Toast.makeText(this,"Please check your password. Password must 6 character.",
                            Toast.LENGTH_SHORT).show()
                        Log.d(TAG,"Enter password less than 6 character.")
                    }else{
                        Toast.makeText(this,"Authentication Failled."+task.exception, Toast.LENGTH_SHORT).show()
                        Log.d(TAG,"Authentication Failled:"+task.exception)
                    }
                }else {
                    Toast.makeText(this,"Sign in successfully!.", Toast.LENGTH_SHORT).show()
                    Log.d(TAG,"Sign in successfully!")
                    startActivity(Intent(this@LoginActivity,ResultActivity::class.java))
                    finish()
                }
            }
        }
        button3.setOnClickListener { startActivity(Intent(this@LoginActivity,RegisterActivity::class.java)) }

    }
}
