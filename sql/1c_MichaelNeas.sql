SELECT DISTINCT
    Artist.`Name` AS nameOfArtist, Track.`Name` AS nameOfEachTrack
FROM
    (Artist, Album, Track)
        INNER JOIN
    (SELECT 
        Artist.`Name` AS artistName
    FROM
        (Artist, Album, Track)
    WHERE
        Artist.ArtistId = Album.ArtistId
            AND Track.AlbumId = Album.AlbumId
    GROUP BY Artist.`Name`
    HAVING COUNT(Track.`Name`) >= 6) AS GreaterThanTable ON artistName = Artist.`Name`
        AND Track.AlbumId = Album.AlbumId
        AND Album.ArtistID = Artist.ArtistID;
