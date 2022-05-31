package dev.software.db.database;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H\'\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\'J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\tH\'J!\u0010\r\u001a\u00020\u000e2\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H\'\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0006H\'\u00a8\u0006\u0011"}, d2 = {"Ldev/software/db/database/SongDao;", "", "deleteSong", "", "item", "", "Ldev/software/db/model/Song;", "([Ldev/software/db/model/Song;)V", "findAll", "", "search", "", "getAllSongs", "insertSong", "", "([Ldev/software/db/model/Song;)[J", "updateSong", "app_debug"})
public abstract interface SongDao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract long[] insertSong(@org.jetbrains.annotations.NotNull()
    dev.software.db.model.Song... item);
    
    @androidx.room.Delete()
    public abstract void deleteSong(@org.jetbrains.annotations.NotNull()
    dev.software.db.model.Song... item);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM songs")
    public abstract java.util.List<dev.software.db.model.Song> getAllSongs();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM songs WHERE title LIKE \'%\' || :search || \'%\' OR genre LIKE \'%\' || :search || \'%\' OR author LIKE \'%\' || :search || \'%\'")
    public abstract java.util.List<dev.software.db.model.Song> findAll(@org.jetbrains.annotations.Nullable()
    java.lang.String search);
    
    @androidx.room.Update()
    public abstract void updateSong(@org.jetbrains.annotations.NotNull()
    dev.software.db.model.Song item);
}