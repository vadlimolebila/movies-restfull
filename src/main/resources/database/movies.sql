--
-- Database: `movies`
--

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `created_at` datetime(6) DEFAULT NULL,
     `description` varchar(255) DEFAULT NULL,
     `image` varchar(255) DEFAULT NULL,
     `rating` float DEFAULT NULL,
     `title` varchar(255) DEFAULT NULL,
     `updated_at` datetime(6) DEFAULT NULL,
     PRIMARY KEY (`id`)
);

--
-- Dumping data for table `movie`
--

INSERT INTO movie (id, title, description, image, rating, created_at, updated_at)
VALUES(1, 'movie 1', 'description movie 1', 'image 1', 7.0, '2023-07-21 23:02:17.760135', '2023-07-21 23:02:17.760135'),
VALUES(2, 'movie 2', 'description movie 2', 'image 2', 4.0, '2023-07-21 23:02:17.760135', '2023-07-21 23:02:17.760135');

