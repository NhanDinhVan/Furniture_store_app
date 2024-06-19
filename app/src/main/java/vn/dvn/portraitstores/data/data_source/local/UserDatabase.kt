package vn.dvn.portraitstores.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import vn.dvn.portraitstores.domain.model.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao


}