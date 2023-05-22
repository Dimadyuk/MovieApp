package com.example.movieapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.data.database.models.FavoriteMovieItemDbModel
import com.example.movieapp.data.database.models.MovieItemDbModel

@Database(
    entities = [MovieItemDbModel::class, FavoriteMovieItemDbModel::class],
    version = 11,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieInfoDao(): MovieDao

    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "main_db"
        private val LOCK = Any()
        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let {
                    return it
                }
                val instance = Room.databaseBuilder(
                    context, AppDatabase::class.java, DB_NAME
                ).fallbackToDestructiveMigration().build()
                db = instance
                return instance
            }
        }
    }

}