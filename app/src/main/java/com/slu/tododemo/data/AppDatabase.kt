package com.slu.tododemo.data

import androidx.room.Database
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase


@Database(
    entities = [TodoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase()
{
        abstract fun getDao(): TodoDao

}