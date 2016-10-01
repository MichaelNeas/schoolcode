SELECT 
    artistName,
    MaximumAmounts.genreName AS genre,
    amountOfTracks
FROM
    (SELECT 
        genreName, MAX(totalAmount) AS amountOfTracks
    FROM
        (SELECT 
        GetGenre.`Name` AS artistName,
            Genre.`Name` AS genreName,
            COUNT(*) AS totalAmount
    FROM
        (SELECT 
        Artist.`Name`, TrackID, Artist.AlbumId, GenreId, `Title`
    FROM
        Track
    LEFT JOIN (SELECT 
        AlbumId, Title, Album.ArtistID, `Name`
    FROM
        Album
    LEFT JOIN Artist ON Artist.ArtistId = Album.ArtistId) Artist ON Track.AlbumId = Artist.AlbumId) GetGenre
    LEFT JOIN Genre ON Genre.GenreID = GetGenre.GenreID
    WHERE
        Genre.`Name` IN ('Rock' , 'Jazz', 'Rock And Roll', 'Pop', 'Classical')
    GROUP BY GetGenre.`Name` , Genre.`Name`
    ORDER BY totalAmount) AmountCount
    GROUP BY genreName
    ORDER BY totalAmount) MaximumAmounts
        LEFT JOIN
    (SELECT 
        GetGenre.`Name` AS artistName,
            Genre.`Name` AS genreName,
            COUNT(*) AS totalAmount
    FROM
        (SELECT 
        TrackID, Artist.AlbumId, GenreId, `Title`, Artist.`Name`
    FROM
        Track
    LEFT JOIN (SELECT 
        AlbumId, Title, Album.ArtistID, `Name`
    FROM
        Album
    LEFT JOIN Artist ON Artist.ArtistId = Album.ArtistId) Artist ON Track.AlbumId = Artist.AlbumId) GetGenre
    LEFT JOIN Genre ON Genre.GenreID = GetGenre.GenreID
    WHERE
        Genre.`Name` IN ('Rock' , 'Jazz', 'Rock And Roll', 'Pop', 'Classical')
    GROUP BY GetGenre.`Name` , Genre.`Name`
    ORDER BY totalAmount) AmountCount2 ON MaximumAmounts.amountOfTracks = AmountCount2.totalAmount
        AND MaximumAmounts.genreName = AmountCount2.genreName
ORDER BY genre , artistName;