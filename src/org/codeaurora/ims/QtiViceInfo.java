package org.codeaurora.ims;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.telephony.Rlog;
import java.util.ArrayList;
import java.util.List;

public class QtiViceInfo implements Parcelable {
    public static final String CALL_TYPE_VIDEO_HELD = "vtheld";
    public static final String CALL_TYPE_VIDEO_RX = "vtrx";
    public static final String CALL_TYPE_VIDEO_TX = "vttx";
    public static final String CALL_TYPE_VIDEO_TX_RX = "vttxrx";
    public static final String CALL_TYPE_VOICE_ACTIVE = "volteactive";
    public static final String CALL_TYPE_VOICE_HELD = "volteheld";
    public static final Creator<QtiViceInfo> CREATOR = new Creator<QtiViceInfo>() {
        public QtiViceInfo createFromParcel(Parcel in) {
            return new QtiViceInfo(in);
        }

        public QtiViceInfo[] newArray(int size) {
            return new QtiViceInfo[size];
        }
    };
    public static final int INDEX_CALLTYPE = 3;
    public static final int INDEX_DIALOG_ID = 0;
    public static final int INDEX_DIRECTION = 4;
    public static final int INDEX_ISPULLABLE = 2;
    public static final int INDEX_MAX = 5;
    public static final int INDEX_NUMBER = 1;
    public static final String MEDIA_DIRECTION_INACTIVE = "inactive";
    public static final String MEDIA_DIRECTION_RECVONLY = "recvonly";
    public static final String MEDIA_DIRECTION_SENDONLY = "sendonly";
    public static final String MEDIA_DIRECTION_SENDRECV = "sendrecv";
    public static final String MEDIA_TYPE_AUDIO = "audio";
    public static final String MEDIA_TYPE_VIDEO = "video";
    public static final String STATE_CONFIRMED = "confirmed";
    public static final String STATE_TERMINATED = "terminated";
    private static final String TAG = "QtiViceInfo";
    public static List<String[]> callInfo;
    public int dialogSize = 0;
    private String mViceInfoAsString = null;

    public QtiViceInfo(Parcel in) {
        readFromParcel(in);
    }

    public QtiViceInfo(List<String[]> dialogIds) {
        if (dialogIds != null) {
            callInfo = new ArrayList();
            this.dialogSize = dialogIds.size();
            callInfo = dialogIds;
            Rlog.d(TAG, "QtiViceInfo const = " + toString());
        }
    }

    public void setViceDialogInfoAsString(String value) {
        this.mViceInfoAsString = value;
        Rlog.d(TAG, "setViceDialogInfoAsString XML String = " + this.mViceInfoAsString);
    }

    public String getViceDialogInfoAsString() {
        return this.mViceInfoAsString;
    }

    public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in) {
        this.dialogSize = in.readInt();
        Rlog.d(TAG, "readFromParcel size = " + this.dialogSize);
        if (this.dialogSize >= 0) {
            callInfo = new ArrayList();
            for (int i = 0; i < this.dialogSize; i++) {
                String[] strArr = new String[5];
                callInfo.add(in.createStringArray());
            }
            Rlog.d(TAG, "readFromParcel - " + toString());
        }
    }

    public String toString() {
        if (callInfo == null) {
            return null;
        }
        Rlog.d(TAG, "mCallInfo size = " + this.dialogSize);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.dialogSize; i++) {
            String[] callinfo = (String[]) callInfo.get(i);
            sb.append("QtiViceInfo :");
            sb.append("DialogId - ");
            sb.append(callinfo[0]);
            sb.append("Number - ");
            sb.append(callinfo[1]);
            sb.append("IsPullable - ");
            sb.append(callinfo[2]);
            sb.append("CallType - ");
            sb.append(callinfo[3]);
            sb.append("Direction - ");
            sb.append(callinfo[4]);
        }
        return sb.toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        if (callInfo != null) {
            out.writeInt(this.dialogSize);
            for (int i = 0; i < this.dialogSize; i++) {
                out.writeStringArray((String[]) callInfo.get(i));
            }
            out.setDataPosition(0);
        }
    }
}
