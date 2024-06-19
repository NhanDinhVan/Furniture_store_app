package vn.dvn.portraitstores.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.jetbrains.annotations.NotNull
import vn.dvn.portraitstores.domain.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM user where state=:state")
    suspend fun getUserByState(state: Boolean): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: User)

}