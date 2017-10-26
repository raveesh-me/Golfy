package `in`.cookytech.golfy

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        val PREFS_FILE = "in.cookytech.golfy.preferences"
        val KEY_STROKECOUNT = "key_strokecount"
    }
    private lateinit var mSharedPreferences : SharedPreferences
    private lateinit var mEditor: SharedPreferences.Editor


    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        mEditor = mSharedPreferences.edit()

        for (i in 0 until mHoles.size){
            mHoles[i].mHoleStrokeCount = mSharedPreferences.getInt(KEY_STROKECOUNT + i, 0)
        }

        holesRecyclerView.adapter = HolesAdapter()
        holesRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onPause() {
        super.onPause()
        for (i in 0 until mHoles.size){
            mEditor.putInt(KEY_STROKECOUNT + i, mHoles[i].mHoleStrokeCount)
        }
        mEditor.apply()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.clearAllPreferences){
            mEditor.clear()
            mEditor.apply()

            for (hole in mHoles){
                hole.mHoleStrokeCount = 0
            }
            holesRecyclerView.adapter.notifyDataSetChanged()
        }
        return true
    }
}
