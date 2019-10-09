package sistemasfireg.igp.org.sismosperu.entity
import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import sistemasfireg.igp.org.sismosperu.utils.createParcel

/**
 * Created by deividi on 26/12/15.
 */
public class Notification() : Parcelable {

    companion object {
        @SuppressLint("ParcelCreator")
        val CREATOR = createParcel { Notification(it) }
    }

    lateinit var title: String
    lateinit var message:String

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    public constructor(message:String?, url : String?) : this() {
        this.title = message ?: ""
        this.message = url ?: ""
    }

    protected constructor(parcelIn: Parcel) : this() {
        this.title = parcelIn.readString()
        this.message = parcelIn.readString()
    }



}


