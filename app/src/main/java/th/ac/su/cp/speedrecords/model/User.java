package th.ac.su.cp.speedrecords.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "User")
public class User {

    @ColumnInfo (name = "meters")
    public final double meters;

    @ColumnInfo (name = "seconds")
    public final double seconds;

    public User(double meters, double seconds) {
        this.meters = meters;
        this.seconds = seconds;
    }
}
