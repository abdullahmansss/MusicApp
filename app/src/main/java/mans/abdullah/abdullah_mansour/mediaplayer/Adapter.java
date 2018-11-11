package mans.abdullah.abdullah_mansour.mediaplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends ArrayAdapter<SongData>
{
    CircleImageView song_image;
    TextView song_name;

    public Adapter(@NonNull Context context, int resource, ArrayList<SongData> songDataArrayList)
    {
        super(context, resource, songDataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        song_image = convertView.findViewById(R.id.song_image);
        song_name = convertView.findViewById(R.id.song_name);

        SongData songData = getItem(position);

        if (songData != null)
        {
            song_image.setImageResource(songData.getSong_picture());
            song_name.setText(songData.getSong_name());
        }

        return convertView;
    }
}
