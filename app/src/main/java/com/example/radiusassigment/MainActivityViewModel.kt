package com.example.radiusassigment

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.radiusassigment.api.ApiClient
import com.example.radiusassigment.api.ApiInterface
import com.example.radiusassigment.model.ModelClass
import com.example.radiusassigment.model.Option
import com.example.radiusassigment.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val liveDataFc1List: MutableLiveData<MutableList<Option>> = MutableLiveData()
    private val liveDataFc2List: MutableLiveData<MutableList<Option>> = MutableLiveData()
    private val liveDataFc3List: MutableLiveData<MutableList<Option>> = MutableLiveData()

    private val localDatabase: AppDatabase = AppDatabase.getInstance(application)!!

    @JvmName("getModelClassList")
    fun getModelClassList(): LiveData<List<ModelClass>>? {
        return localDatabase.appDao()?.getModelList()
    }

    @JvmName("getFcList1")
    fun getFcList1(): MutableLiveData<MutableList<Option>> {
        return liveDataFc1List
    }

    @JvmName("getFcList2")
    fun getFcList2(): MutableLiveData<MutableList<Option>> {
        return liveDataFc2List
    }

    fun loadJSON1() {
        val apiInterface: ApiInterface? = ApiClient.apiClient?.create(ApiInterface::class.java)
        val call: Call<ModelClass?>? = apiInterface?.getData()
        call!!.enqueue(object : Callback<ModelClass?> {
            override fun onResponse(call: Call<ModelClass?>, response: Response<ModelClass?>) {
                if (response.isSuccessful && response.body()!= null) {
                    Log.d("MyTag", "Successful1"+response.body().toString())

                    viewModelScope.launch(Dispatchers.IO) {
                        liveDataFc1List.postValue(response.body()!!.facilities[0].options.toMutableList())
                        liveDataFc2List.postValue(response.body()!!.facilities[1].options.toMutableList())
                        liveDataFc3List.postValue(response.body()!!.facilities[2].options.toMutableList())
                        localDatabase.appDao()?.insertData(response.body()!!)
//                        response.body()!!.movieList?.let {}
                    }

                } else {
                    val errorCode: String = when (response.code()) {
                        404 -> "404 not found"
                        500 -> "500 server broken"
                        else -> "unknown error"
                    }
                    Log.d("MyTag", errorCode)
                }
            }

            override fun onFailure(call: Call<ModelClass?>, t: Throwable) {

            }
        })
    }


//    private fun loadJSON2() {
//        val apiInterface: ApiInterface? = ApiClient.apiClient?.create(ApiInterface::class.java)
//        val call: Call<ModelClass?>? = apiInterface?.getModelClass2("ModelClass")
//        call!!.enqueue(object : Callback<ModelClass?> {
//            override fun onResponse(call: Call<ModelClass?>, response: Response<ModelClass?>) {
//                if (response.isSuccessful && response.body()?.movieList != null) {
//
//                    Log.d("MyTag","Successful2"+response.body()?.movieList.toString())
//
//                    viewModelScope.launch(Dispatchers.IO) {
//                        liveDataMovieList.postValue(response.body()!!.movieList?.toMutableList()!!)
//                        response.body()!!.movieList?.let {
//                            localDatabase.appDao()?.insertModelClass(it.toMutableList())
//                        }
//                    }
//                } else {
//                    val errorCode: String = when (response.code()) {
//                        404 -> "404 not found"
//                        500 -> "500 server broken"
//                        else -> "unknown error"
//                    }
//                    Log.d("MyTag", errorCode)
//                }
//            }
//
//            override fun onFailure(call: Call<ModelClass?>, t: Throwable) {
//
//            }
//        })
//    }

    fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

}

