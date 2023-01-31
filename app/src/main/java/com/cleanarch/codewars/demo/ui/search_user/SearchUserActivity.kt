package com.cleanarch.codewars.demo.ui.search_user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.cleanarch.codewars.demo.R

class SearchUserActivity : AppCompatActivity(R.layout.activity_search_user) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<SearchUserFragment>(R.id.fragment_container_view)
            }
        }
    }
}