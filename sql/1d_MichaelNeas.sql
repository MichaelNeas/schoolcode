SELECT 
    artist, nameOfPlaylist
FROM
    (SELECT 
        Artist AS artist,
            Title AS nameOfPlaylist,
            COUNT(*) AS songsInAlbum
    FROM
        (SELECT 
        TrackId, Artist.`Name` AS Artist
    FROM
        Track
    LEFT JOIN (SELECT 
        AlbumId, Title, Album.ArtistId, `Name`
    FROM
        Album
    LEFT JOIN Artist ON Artist.ArtistId = Album.ArtistId) Artist ON Track.AlbumId = Artist.AlbumID
    WHERE
        Artist.`Name` = 'AC/DC') ACDCJoin
    INNER JOIN (SELECT 
        PlaylistTrack.PlaylistId AS PlaylistId,
            TrackId,
            `Name` AS Title
    FROM
        PlaylistTrack
    INNER JOIN Playlist ON PlaylistTrack.PlaylistId = Playlist.PlaylistId) PlaylistComp ON ACDCJoin.TrackId = PlaylistComp.TrackId
    GROUP BY Title , PlaylistId) MostPlaylist
WHERE
    MostPlaylist.songsInAlbum = (SELECT 
            MAX(Val)
        FROM
            (SELECT 
                Artist, PlaylistId, Title, COUNT(*) AS Val
            FROM
                (SELECT 
                TrackId, Artist.`Name` AS Artist
            FROM
                Track
            LEFT JOIN (SELECT 
                AlbumId, Title, Album.ArtistId, `Name`
            FROM
                Album
            LEFT JOIN Artist ON Artist.ArtistId = Album.ArtistId) Artist ON Track.AlbumId = Artist.AlbumID
            WHERE
                Artist.`Name` = 'AC/DC') ACDCJoin
            INNER JOIN (SELECT 
                PlaylistTrack.PlaylistId AS PlaylistId,
                    TrackId,
                    `Name` AS Title
            FROM
                PlaylistTrack
            INNER JOIN Playlist ON PlaylistTrack.PlaylistId = Playlist.PlaylistId) PlaylistComp ON ACDCJoin.trackId = PlaylistComp.trackId
            GROUP BY Title , playlistId) MostPlaylist);