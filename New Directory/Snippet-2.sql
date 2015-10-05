-- phpMyAdmin SQL Dump
-- version 4.4.3
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 05. Okt 2015 um 11:15
-- Server-Version: 5.6.24
-- PHP-Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `Snippet`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Directories`
--

CREATE TABLE IF NOT EXISTS `Directories` (
  `DIrectoryID`   INT(15) NOT NULL,
  `DirectoryName` VARCHAR(200) DEFAULT NULL
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 20
  DEFAULT CHARSET = latin1;

--
-- RELATIONEN DER TABELLE `Directories`:
--

--
-- Daten für Tabelle `Directories`
--

INSERT INTO `Directories` (`DIrectoryID`, `DirectoryName`) VALUES
  (1, 'TestDir1'),
  (2, 'TestDir2'),
  (5, 'Test2'),
  (6, 'TestDirectory'),
  (7, 'TestDirectory2'),
  (19, 'New Directory');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Snippets`
--

CREATE TABLE IF NOT EXISTS `Snippets` (
  `SnippetID`   INT(11) NOT NULL,
  `DirectoryID` INT(11)       DEFAULT NULL,
  `SnippetName` VARCHAR(50)   DEFAULT NULL,
  `Datum`       VARCHAR(20)   DEFAULT NULL,
  `Code`        VARCHAR(5000) DEFAULT NULL,
  `Sprache`     VARCHAR(30)   DEFAULT NULL,
  `Notizen`     VARCHAR(200)  DEFAULT NULL,
  `Quellen`     VARCHAR(200)  DEFAULT NULL,
  `Author`      VARCHAR(200)  DEFAULT NULL
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 26
  DEFAULT CHARSET = latin1;

--
-- RELATIONEN DER TABELLE `Snippets`:
--

--
-- Daten für Tabelle `Snippets`
--

INSERT INTO `Snippets` (`SnippetID`, `DirectoryID`, `SnippetName`, `Datum`, `Code`, `Sprache`, `Notizen`, `Quellen`, `Author`)
VALUES
  (1, 1, 'Snippet1', '25.07.2015', 'Test Code', 'JavaFX', 'TestABC', 'Testquelle', 'TestAuthor'),
  (2, 3, 'Snippet2', '27.07.2015', 'Test Code 2', 'C', 'Testnotiz1', 'Testquell2', 'TestAuthor2'),
  (3, 2, 'Snippet3', '28.08.2015', 'Test Code 3', 'C#', 'Testnotiz2', 'Testqelle3', 'TestAuthor3'),
  (4, 5, 'Snippet4', '02.09.2015', 'Test Code 4', 'C', 'Testnotiz3', 'Testquelle4', 'TestAuthor4'),
  (5, 1, 'Test123', '25.03.2015', 'int main', 'JavaFX', 'Test123', 'TestQelle', 'TestAuthor'),
  (7, 1, 'Test', '01.05.2015', 'int main', 'JavaFX', 'TestNote', 'TestQuelle', 'TestAuthor'),
  (8, 1, 'Test', '01.05.2015', 'int main', 'JavaFX', 'TestNote', 'TestQuelle', 'TestAuthor'),
  (9, 2, 'Test2', '25.09.2015', 'int main2', 'C#', 'Testnotiz2', 'Testquelle2', 'TestAuthor2'),
  (13, 1, 'TestABC123', '', 'dsfdsfdsfsdfsdfsd', 'JavaFX', 'asdadsgdgfsdfsd', 'sdfsdfdsfds', 'fsdfsdfsd'),
  (14, 1, 'TestABC', '', 'asdasdsad', 'JavaFX', 'asdasd', 'asdasd', 'asdasdas'),
  (17, 1, 'Test', '250', 'asdasdsad', 'JavaFX', 'sadasdasd', 'sadasdasd', 'asdasdasdsa'),
  (18, 5, 'Test123ABC', '25.09.2015', 'sdfsdfsdfsdfsdfsd', 'C', 'fsdfsdfsdfssdfsd', 'fsdfsdfsdfdsfdsfsdf',
   'sdfsdfsdfdsfs'),
  (19, 1, 'Test234BCD', '', 'int main(){\ntest();\n}', 'JavaFX',
   'Dies ist ein Test für das hinzufügen eines Snippets		', 'http://diegedankensindfrei.de', 'Darksirion'),
  (23, 1, 'Test', '25.07.2015', 'dasdasd', NULL, 'sadasdasd', 'asdasdas', 'sadasdasd'),
  (24, 1, 'Testaasdasd', '', 'asdasdasd', NULL, 'sadasdas', 'asdasdas', 'asdasdasd'),
  (25, 1, 'Hello World', '25.07.2015', 'public HelloWorld() {\n  System.out.println("Hello World");\n}', 'C#',
   'Erstes kleines Programm', 'Keine', 'Team Turing');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `Directories`
--
ALTER TABLE `Directories`
ADD PRIMARY KEY (`DIrectoryID`);

--
-- Indizes für die Tabelle `Snippets`
--
ALTER TABLE `Snippets`
ADD PRIMARY KEY (`SnippetID`),
ADD KEY `DirectoryID` (`DirectoryID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `Directories`
--
ALTER TABLE `Directories`
MODIFY `DIrectoryID` INT(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = 20;
--
-- AUTO_INCREMENT für Tabelle `Snippets`
--
ALTER TABLE `Snippets`
MODIFY `SnippetID` INT(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = 26;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
