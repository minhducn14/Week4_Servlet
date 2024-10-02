-- --------------------------------------------------------
-- Máy chủ:                      127.0.0.1
-- Server version:               10.11.7-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Phiên bản:           12.8.0.6935
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for quanlydanhmuc
CREATE DATABASE IF NOT EXISTS `quanlydanhmuc` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `quanlydanhmuc`;

-- Dumping structure for table quanlydanhmuc.danhmuc
CREATE TABLE IF NOT EXISTS `danhmuc` (
  `MADM` int(11) NOT NULL,
  `TENDANHMUC` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `NGUOIQUANLY` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `GHICHU` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`MADM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table quanlydanhmuc.danhmuc: ~2 rows (approximately)
INSERT INTO `danhmuc` (`MADM`, `TENDANHMUC`, `NGUOIQUANLY`, `GHICHU`) VALUES
	(1, 'Thể Thao', 'Minh Duc', 'EPL'),
	(2, 'Thời Sự', 'Minh Duc', 'Toàn Cầu');

-- Dumping structure for table quanlydanhmuc.tintuc
CREATE TABLE IF NOT EXISTS `tintuc` (
  `MATT` int(11) NOT NULL,
  `TIEUDE` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `NOIDUNGTT` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `LIENKET` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `MADM` int(11) DEFAULT NULL,
  PRIMARY KEY (`MATT`),
  KEY `MADM` (`MADM`),
  CONSTRAINT `tintuc_ibfk_1` FOREIGN KEY (`MADM`) REFERENCES `danhmuc` (`MADM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table quanlydanhmuc.tintuc: ~1 rows (approximately)
INSERT INTO `tintuc` (`MATT`, `TIEUDE`, `NOIDUNGTT`, `LIENKET`, `MADM`) VALUES
	(1, 'Đội hình ngôi sao chấn thương đầu mùa 2024-2025', 'Rodri và Ter Stegen sớm nói lời chia tay mùa giải, còn Mbappe phải nghỉ sau chuỗi trận khởi đầu tốt với Real.', 'https://vnexpress.net/doi-hinh-ngoi-sao-chan-thuong-dau-mua-2024-2025-4797429.html', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
