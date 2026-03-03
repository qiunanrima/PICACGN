package com.picacomic.fregata.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.picacomic.fregata.holders.ChatroomAdsViewHolder;
import com.picacomic.fregata.holders.ChatroomAudioViewHolder;
import com.picacomic.fregata.holders.ChatroomBroadcastAdsViewHolder;
import com.picacomic.fregata.holders.ChatroomConnectionViewHolder;
import com.picacomic.fregata.holders.ChatroomImageViewHolder;
import com.picacomic.fregata.holders.ChatroomMessageViewHolder;
import com.picacomic.fregata.holders.ChatroomSystemNotificationViewHolder;
import com.picacomic.fregata.objects.ChatBaseObject;
import com.picacomic.fregata.objects.ChatMessageObject;
import com.picacomic.fregata.objects.ChatSystemObject;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/ChatroomRecyclerViewAdapter.class */
public class ChatroomRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "ChatroomRecyclerViewAdapter";
    private final Context context;
    private ArrayList<ChatBaseObject> ja;
    public boolean jl;
    private com.picacomic.fregata.a.a jn;
    private final LayoutInflater mLayoutInflater;
    public boolean jk = true;
    public boolean jm = false;

    public ChatroomRecyclerViewAdapter(Context context, ArrayList<ChatBaseObject> arrayList, com.picacomic.fregata.a.a aVar) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.ja = arrayList;
        this.jn = aVar;
    }

    public void a(TextView textView, String[] strArr, String str) {
        textView.setText("");
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 1;
            if (i2 >= str.length() || !(str.charAt(i) == 55356 || str.charAt(i) == 55357)) {
                SpannableString spannableString = new SpannableString(str.charAt(i) + "");
                str.charAt(i);
                spannableString.setSpan(new ForegroundColorSpan(x(Color.parseColor(strArr[i % strArr.length]))), 0, spannableString.length(), 33);
                textView.append(spannableString);
            } else {
                f.D(TAG, "Found emoji at index " + i);
                String substring = str.substring(i, i + 2);
                SpannableString spannableString2 = new SpannableString(substring + "");
                spannableString2.setSpan(new ForegroundColorSpan(x(Color.parseColor(strArr[i % strArr.length]))), 0, spannableString2.length(), 33);
                textView.append(substring);
                i = i2;
            }
            i++;
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.ja == null ? 0 : this.ja.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (this.ja == null || this.ja.size() <= i) {
            return super.getItemViewType(i);
        }
        if (this.ja.get(i) instanceof ChatSystemObject) {
            return 12;
        }
        return this.ja.get(i) instanceof ChatMessageObject ? ((ChatMessageObject) this.ja.get(i)).getType() : super.getItemViewType(i);
    }

    public void n(boolean z) {
        this.jk = z;
    }

    public void o(boolean z) {
        this.jl = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0072, code lost:
        if (((com.picacomic.fregata.objects.ChatMessageObject) r7.ja.get(r9)).getAvatar().equalsIgnoreCase("") != false) goto L196;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x033a  */
    @Override // android.support.v7.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder r8, int r9) {
        /*
            Method dump skipped, instructions count: 2613
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.picacomic.fregata.adapters.ChatroomRecyclerViewAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void");
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (viewGroup != null) {
            viewGroup.setClipChildren(false);
        }
        switch (i) {
            case 0:
                return new ChatroomMessageViewHolder(this.mLayoutInflater.inflate(2131427458, viewGroup, false), this.jn);
            case 1:
                return new ChatroomImageViewHolder(this.mLayoutInflater.inflate(2131427455, viewGroup, false), this.jn);
            case 2:
                return new ChatroomAudioViewHolder(this.mLayoutInflater.inflate(2131427449, viewGroup, false), this.jn);
            case 3:
                return new ChatroomMessageViewHolder(this.mLayoutInflater.inflate(2131427457, viewGroup, false), this.jn);
            case 4:
                return new ChatroomImageViewHolder(this.mLayoutInflater.inflate(2131427454, viewGroup, false), this.jn);
            case 5:
                return new ChatroomAudioViewHolder(this.mLayoutInflater.inflate(2131427448, viewGroup, false), this.jn);
            case 6:
                return new ChatroomConnectionViewHolder(this.mLayoutInflater.inflate(2131427452, viewGroup, false));
            case 7:
                return new ChatroomConnectionViewHolder(this.mLayoutInflater.inflate(2131427451, viewGroup, false));
            case 8:
                return new ChatroomAdsViewHolder(this.mLayoutInflater.inflate(2131427450, viewGroup, false), this.jn);
            case 9:
                return new ChatroomMessageViewHolder(this.mLayoutInflater.inflate(2131427458, viewGroup, false), this.jn);
            case 10:
                return new ChatroomMessageViewHolder(this.mLayoutInflater.inflate(2131427457, viewGroup, false), this.jn);
            case 11:
                return new ChatroomBroadcastAdsViewHolder(this.mLayoutInflater.inflate(2131427447, viewGroup, false), this.jn);
            case 12:
                return new ChatroomSystemNotificationViewHolder(this.mLayoutInflater.inflate(2131427459, viewGroup, false));
            default:
                return new ChatroomMessageViewHolder(this.mLayoutInflater.inflate(2131427458, viewGroup, false), this.jn);
        }
    }

    public void p(boolean z) {
        this.jm = z;
    }

    public int x(int i) {
        int af = e.af(this.context);
        int i2 = 255 - af;
        int red = Color.red(i);
        int green = Color.green(i);
        int blue = Color.blue(i);
        if (e.al(this.context) == 0) {
            if (red <= i2 || green <= i2 || blue <= i2) {
                return i;
            }
            int i3 = red - i2;
            int i4 = green - i2;
            int i5 = blue - i2;
            String str = TAG;
            f.D(str, "change color = " + i3 + " " + i4 + " " + i5);
            return Color.argb(255, i3, i4, i5);
        } else if (e.al(this.context) != 1 || red >= af || green >= af || blue >= af) {
            return i;
        } else {
            int i6 = red + i2;
            int i7 = green + i2;
            int i8 = blue + i2;
            String str2 = TAG;
            f.D(str2, "change color = " + i6 + " " + i7 + " " + i8);
            return Color.argb(255, i6, i7, i8);
        }
    }
}
