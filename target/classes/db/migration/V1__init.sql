DROP TABLE IF EXISTS vehicle;

# CREATE TABLE vehicle (
# id LONG AUTO_INCREMENT PRIMARY KEY,
# brand VARCHAR(250) NOT NULL,
# model VARCHAR(250) NOT NULL,
# color VARCHAR(250) NOT NULL
# );

CREATE TABLE IF NOT EXISTS `vehicle` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `brand` VARCHAR(250) NOT NULL,
                                `model` VARCHAR(250) NOT NULL,
                                `color` VARCHAR(250) NOT NULL,
                                PRIMARY KEY (`id`)
                                ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;