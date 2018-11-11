package mans.abdullah.abdullah_mansour.mediaplayer;

public class SongData
{
    String song_name;
    int song_picture,song_resource;

    public SongData(String song_name, int song_picture, int song_resource)
    {
        this.song_name = song_name;
        this.song_picture = song_picture;
        this.song_resource = song_resource;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public int getSong_picture() {
        return song_picture;
    }

    public void setSong_picture(int song_picture) {
        this.song_picture = song_picture;
    }

    public int getSong_resource() {
        return song_resource;
    }

    public void setSong_resource(int song_resource) {
        this.song_resource = song_resource;
    }
}
