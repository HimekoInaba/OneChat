package com.example.stusupbekov.onechat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.chat_layout.*

/**
 * Created by s.tusupbekov on 17.02.2018.
 */
class ChatActivity : AppCompatActivity() {

    lateinit var editMessage: EditText
    lateinit var mDatabase: DatabaseReference
    lateinit var mMessageList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_layout)

        editMessage = findViewById(R.id.editMessage)
        mDatabase = FirebaseDatabase.getInstance().getReference("Message")

        mMessageList.setHasFixedSize(true)
        var linearLayoutManager =  LinearLayoutManager(this)
        linearLayoutManager.stackFromEnd
        mMessageList.layoutManager

        sendButton.setOnClickListener({
            sendButtonClicked()
        })
    }

    fun sendButtonClicked() {
        val messageValue: String = editMessage.toString().trim()
        if(!TextUtils.isEmpty(messageValue)){
            val newPost: DatabaseReference  =  mDatabase.push()
            newPost.child("content").setValue(messageValue)
        }
    }

    override fun onStart(){
        super.onStart()

    }

    class MessageVolderView(itemView: View?) : RecyclerView.ViewHolder(itemView){
        var mView: View = itemView!!
        var message_content: TextView = itemView!!.findViewById(R.id.message_content)

        fun setContent(content: String){
            message_content.text = content
        }


    }
}