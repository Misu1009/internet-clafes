module InternetCLafes {
	opens app;
	opens model;
	opens database;
	opens Controller;
	opens repository;
	opens view;
	requires java.sql;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
}