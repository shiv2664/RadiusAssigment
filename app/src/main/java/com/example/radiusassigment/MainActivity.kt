package com.example.radiusassigment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.radiusassigment.databinding.ActivityMainBinding
import com.example.radiusassigment.interfaces.IMainActivity

class MainActivity : AppCompatActivity(), IMainActivity {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mViewModel: MainActivityViewModel
    private var refresh: MenuItem? = null

    var isScrolling = false
    var currentItems = 0
    var totalItems: Int = 0
    var scrollOutItems: Int = 0
    var scrollOutItemsDOWN: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)



        mViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
        )[MainActivityViewModel::class.java]


        mViewModel.loadJSON1()

        if (mViewModel.checkForInternet(this)) {
            mViewModel.getFcList1().observe(this) {
                binding.dataList = it
            }
        } else {
            mViewModel.getFcList1()?.observe(this) {
                binding.dataList = it
            }
        }

    }

    override fun onFc1Click(optionId: String) {
        binding.fc1RecyclerView.visibility = View.GONE
        binding.fc2RecyclerView.visibility = View.VISIBLE
        mViewModel.getFcList2().observe(this) {
            binding.dataList = it
        }
    }

    override fun onFc2Click(optionId: String) {}

    override fun onFc3Click(optionId: String) {}

//    override fun onBackPressed() {
//        super.onBackPressed()
//    }

//    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
//        if (binding.fc1RecyclerView.visibility == View.VISIBLE) {
//            return super.getOnBackInvokedDispatcher()
//        }
//    }
}


