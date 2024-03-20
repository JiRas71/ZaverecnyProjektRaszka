-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Počítač: localhost
-- Vytvořeno: Stř 20. bře 2024, 20:44
-- Verze serveru: 10.4.28-MariaDB
-- Verze PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `InvoiceDatabase`
--

-- --------------------------------------------------------

--
-- Struktura tabulky `invoice`
--

CREATE TABLE `invoice` (
  `id` bigint(20) NOT NULL,
  `due_date` date DEFAULT NULL,
  `invoice_number` varchar(255) DEFAULT NULL,
  `issued` date DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `product` varchar(255) DEFAULT NULL,
  `vat` bigint(20) DEFAULT NULL,
  `buyer_id` bigint(20) DEFAULT NULL,
  `seller_id` bigint(20) DEFAULT NULL,
  `buyerid` bigint(20) DEFAULT NULL,
  `sellerid` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Vypisuji data pro tabulku `invoice`
--

INSERT INTO `invoice` (`id`, `due_date`, `invoice_number`, `issued`, `note`, `price`, `product`, `vat`, `buyer_id`, `seller_id`, `buyerid`, `sellerid`) VALUES
(14, '2023-01-30', '12211221', '2023-02-28', 'Zkušební záznam', 333, 'Plastový stůl', 15, 1, 2, NULL, NULL),
(20, '2023-10-10', '222222', '2023-09-15', 'Vybavení kanceláře', 1350, 'Analogové hodiny', 21, 6, 7, NULL, NULL),
(25, '2023-05-20', '2635463', '2023-04-03', 'Autovýbava', 4851, 'Autorádio Blaupunkt', 21, 18, 20, NULL, NULL),
(26, '2023-10-10', '2735354632', '2023-09-17', 'PC pevný disk', 2981, 'HD 500GB', 21, 3, 4, NULL, NULL),
(28, '2024-01-16', '6253425', '2024-01-06', 'Kuchyň', 666, 'Černý čaj', 15, 5, 8, NULL, NULL),
(34, '2024-02-01', '5476546', '2024-01-02', 'Výbava pro turistiku', 3467, 'Spacák', 21, 3, 8, NULL, NULL),
(36, '2024-02-02', '543467898', '2024-01-20', 'Vybavení kanceláře', 999, 'Digitální rádio', 21, 18, 4, NULL, NULL),
(42, '2024-02-24', '54345667', '2024-02-05', '4Ks', 887, 'Židle', 21, 8, 5, NULL, NULL),
(54, '2024-03-23', '45434565', '2024-03-02', '100Ks', 2979, 'Matonni 1,5L', 15, 2, 1, NULL, NULL),
(55, '2024-03-22', '5765765', '2024-03-02', 'Úklidové služby', 1800, 'Čištění vozu', 21, 9, 7, NULL, NULL),
(56, '2024-03-22', '6546565', '2024-03-03', 'Rekvalifikační kurz', 6658, 'Školení REACT', 0, 1, 17, NULL, NULL),
(57, '2024-03-31', '765765765', '2024-03-01', 'Úklidové služby', 3345, 'Úklid kaceláří', 21, 4, 7, NULL, NULL),
(58, '2024-02-24', '2342422', '2024-02-02', 'Taxi služba', 9455, 'Převoz osob 475 Km', 21, 23, 9, NULL, NULL),
(59, '2024-02-28', '35435', '2024-02-04', 'Fjhgjzgjfjgvjh', 3456, 'FTRHFHGF', 15, 23, 19, NULL, NULL),
(60, '2024-02-17', '4656546456', '2024-02-03', 'gncgjgcjgcj', 2345, 'Hhghtdfhtdf', 5, 3, 6, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktura tabulky `person`
--

CREATE TABLE `person` (
  `id` bigint(20) NOT NULL,
  `account_number` varchar(255) NOT NULL,
  `bank_code` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `hidden` bit(1) NOT NULL,
  `iban` varchar(255) DEFAULT NULL,
  `identification_number` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `street` varchar(255) NOT NULL,
  `tax_number` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) NOT NULL,
  `zip` varchar(255) NOT NULL,
  `get_renevue` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Vypisuji data pro tabulku `person`
--

INSERT INTO `person` (`id`, `account_number`, `bank_code`, `city`, `country`, `hidden`, `iban`, `identification_number`, `mail`, `name`, `note`, `street`, `tax_number`, `telephone`, `zip`, `get_renevue`) VALUES
(1, '3003300011', '1155', 'Ostrava', 'CZECHIA', b'0', 'CZ4055000000003003300011', '25900951', 'jan@novy.cz', 'Jan Nový', 'Zkušební', 'Uliční 111', 'CZ25900951', '+420 223 343 445', '73601', NULL),
(2, '4342424242', '1111', 'Praha', 'CZECHIA', b'0', 'CZ51001240000000134425415', '45654345', 'aaa@sdf.com', 'Franta Omáčka', 'K výmazu', 'Janáčkova', 'CZ45654345', '+420 443 234 543', '54345', NULL),
(3, '4332345422', '2222', 'Ostrava', 'SLOVAKIA', b'0', 'CZ4010000000004332345422', '21345432', 'staryp@seznam.cz', 'Petr Starý', 'Zkušební 2', 'Klášterní 11/1111', 'CZ21345432', '+420434543456', '73245', NULL),
(4, '5768698044', '5500', 'Ostrava', 'CZECHIA', b'0', 'CZ455000000005768698044', '24309852', 'cloud-data@gmail.com', 'Cloud-Data spol. s r. o.', 'Softvare - hardvare', 'Krajní 25', 'CZ24309852', '+420 596 415 590', '74356', NULL),
(5, '6787654576', '0100', 'Frýdek-Místek', 'CZECHIA', b'0', 'CZ401000000006787654576', '65789809', 'kapro@seznam.cz', 'Karel Prouza', 'Služby', 'Matiční 4511/34', 'CZ65789809', '+420 567 456 567', '56753', NULL),
(6, '4345676787', '0100', 'Třinec', 'CZECHIA', b'0', 'CZ40100000004345676787', '34567875', 'anva@windowslive.com', 'Andrea Válková', 'Prodej květin', 'Květová 123/1', 'CZ34567875', '+420 543 654 789', '54356', NULL),
(7, '4567654567', '2711', 'Horní-Bludovice', 'CZECHIA', b'0', 'CZ427110000004567654567', '54345678', 'kaci@email.cz', 'Kateřina Čížková', 'Úklidové služby', 'Hadrová 2341/12', 'CZ54345678', '+420 654 323 456', '76545', NULL),
(8, '4567654354', '2132', 'Opava', 'CZECHIA', b'0', 'CZ421320000004567654354', '54656789', 'svak@iclod.com', 'Pavel Svak', 'Autoservis', 'Tesařská 231/222', 'CZ54656789', '+420 876 567 456', '56765', NULL),
(9, '4444444444', '6767', 'Brno', 'CZECHIA', b'1', 'CZ476760000004444444444', '45654321', 'doprava@doprava.com', 'První dopravní a.s.', 'Doprava', 'Košatá 888/1', 'CZ45654321', '+420 657 645 354', '12323', NULL),
(17, '123456789', '5500', 'Praha', 'CZECHIA', b'0', 'CZ000123456789', '05861381', 'redakce@itnetwork.cz', 'ITnetwork s.r.o.', 'Největší IT akademie v Česku.', 'Karlovo náměstí 290/16, Nové Město (Praha 2)', 'CZ05861381', '+420 123 123 123', '120 01', NULL),
(18, '543433345', '5500', 'AŠ', 'CZECHIA', b'1', 'CZ45500000000543433345', '12321232', 'franta@franta.cz', 'Franta Franta', 'Nápoje', 'Frantova 1111/1', 'CZ12321232', '+420 321 123 123', '120 11', NULL),
(19, '6554456599', '3322', 'Plzeň', 'CZECHIA', b'0', 'CZ433220000006554456599', '34543456', 'fogl@email.cz', 'Karel Fogl', 'Pohostinství', 'Ptačí 333/1', 'CZ34543456', '+420 132 342 562', '71200', NULL),
(20, '123456789', '5500', 'Praha', 'CZECHIA', b'1', 'CZ000123456789', '05861381', 'redakce@itnetwork.cz', 'ictdemy s.r.o.', 'Největší IT akademie.', 'Karlovo náměstí 290/16, Nové Město (Praha 2)', 'CZ05861381', '+420 123 123 123', '120 00', NULL),
(21, '777665655', '6565', 'Tachov', 'CZECHIA', b'1', 'CZ65544556677666554', '45345676', 'a@b.cz', 'Karel Hájek', 'Bez komentáře', 'Hlaváčkova 1763/234', 'CZ777', '+420 765 656 765', '76543', NULL),
(22, '6554345699', '2233', 'Hradec Králové', 'CZECHIA', b'0', 'CZ422330000006554345699', '65676543', 's@g.cz', 'Hana Pštrosová', 'Účetní', 'Karasova 17/1', 'CZ65676543', '+420 765 345 654', '56765', NULL),
(23, '8644456723', '5467', 'Trenčín', 'SLOVAKIA', b'0', 'SK654670000008644456723', '434568901', 'jank@stanko.sk', 'Janko Stanko', 'Práce všeho druhu', 'Jánošíkova 2314/11', 'SK434568901', '+421 435 654 234', '43245', NULL),
(24, '5445655543', '5432', 'Olomouc', 'CZECHIA', b'1', 'CZ454320000005445655543', '54565678', 'jaro@slav.cz', 'Jaroslav Slavík 2ě', 'kizgjzhvguzfgvutgv', 'Kadeřavá 123/32', 'CZ54565678', '+420 654 323 456', '454323', NULL),
(25, '5445655543', '5432', 'Olomouc', 'CZECHIA', b'0', 'CZ454320000005445655543', '54565678', 'jaro@slav.cz', 'Jaroslav Slavík', 'kizgjzhvguzfgvutgv', 'Kadeřavá 123/32', 'CZ54565678', '+420 654 323 456', '454323', NULL),
(26, '4444444443', '6767', 'Brno', 'CZECHIA', b'0', 'CZ476760000004444444443', '45654321', 'doprava@doprava.com', 'První dopravní a.s.', 'Doprava', 'Košatá 888/1', 'CZ45654321', '+420 657 645 354', '12323', NULL);

--
-- Indexy pro exportované tabulky
--

--
-- Indexy pro tabulku `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkajlvcm61crtrs7d5gt7x8h0x` (`buyer_id`),
  ADD KEY `FKcsnchr7oirhgjwx57ubp3xt7m` (`seller_id`),
  ADD KEY `FKgkv1k79r19a9gap9gbjuq6aa4` (`buyerid`),
  ADD KEY `FKgw13tl28if2bomfqno69skrw9` (`sellerid`);

--
-- Indexy pro tabulku `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pro tabulky
--

--
-- AUTO_INCREMENT pro tabulku `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT pro tabulku `person`
--
ALTER TABLE `person`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Omezení pro exportované tabulky
--

--
-- Omezení pro tabulku `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `FKcsnchr7oirhgjwx57ubp3xt7m` FOREIGN KEY (`seller_id`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FKgkv1k79r19a9gap9gbjuq6aa4` FOREIGN KEY (`buyerid`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FKgw13tl28if2bomfqno69skrw9` FOREIGN KEY (`sellerid`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FKkajlvcm61crtrs7d5gt7x8h0x` FOREIGN KEY (`buyer_id`) REFERENCES `person` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
