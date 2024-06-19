package vn.dvn.portraitstores.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class User(
    @PrimaryKey val id: Int=0,
    @ColumnInfo(name = "token") val token: String?,
    @ColumnInfo(name = "state") val state: Boolean?= true
) : Parcelable

