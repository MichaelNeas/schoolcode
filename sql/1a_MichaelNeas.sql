SELECT 
    `Name` AS nameOfPlaylist, COUNT(*) AS numTracks
FROM
    (SELECT 
        PlaylistId
    FROM
        (SELECT 
        TrackId
    FROM
        Track
    WHERE
        GenreId = 9) PopSongs
    NATURAL JOIN (SELECT 
        *
    FROM
        PlaylistTrack) PlaylistTracks) PopTracks
        LEFT JOIN
    Playlist ON PopTracks.PlaylistId = Playlist.PlaylistId
GROUP BY PopTracks.PlaylistId;