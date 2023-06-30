package com.example.radiusassigment.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.radiusassigment.model.ModelClass

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data: ModelClass)
//
    @Query("Select * From ModelClass")
    fun getModelList(): LiveData<List<ModelClass>>

//    @Query("Select MoviePoster From MovieList where IMDBID= :IMDBID")
//    fun getMoviePoster(IMDBID: String) :String
//
//    @Query("DELETE FROM MovieList")
//    fun deleteMovieList()
//
//    //for getting pages data
//    @Query("SELECT * FROM MovieList ORDER BY Year desc")
//    fun getAllMovies(): DataSource.Factory<Int,MovieList>



}