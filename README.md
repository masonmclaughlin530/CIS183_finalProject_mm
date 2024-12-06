# CIS183_finalProject_mm


create table
{
db.execSQL(" CREATE TABLE " + users_table_name + " (username varChar(50), userId integer primary key autoincrement not null, password varchar(50))");
db.execSQL(" CREATE TABLE " + cards_table_name + " (year integer, fname varchar(50), lname varchar(50), sport varchar(20), brand varchar(20), userId integer,type varchar(20) ,cardNum integer, cardId integer primary key autoincrement not null, foreign key (userId) references " + users_table_name + "(userId))");
db.execSQL(" CREATE TABLE " + brands_table_name + " (brandId integer primary key autoincrement not null, brand varchar(50) ,foreign key (brand) references " + cards_table_name + "(brand))");
db.execSQL(" CREATE TABLE " + sports_table_name + " (sportsId integer primary key autoincrement not null, sport varChar(50) ,foreign key (sport) references " + cards_table_name + "(sport))");
db.execSQL(" CREATE TABLE " + types_table_name + " (typeId integer primary key autoincrement not null, type varchar(50) ,foreign key (type) references " + cards_table_name + "(type))");
}




if(countDB(cards_table_name) == 0)
{
SQLiteDatabase db = this.getReadableDatabase();

            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lname, sport, brand, type ,cardNum, userId) VALUES ('2018', 'Shohei', 'Ohtani', 'Baseball', 'Topps', 'Rookie', '49', 1)");
            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lname, sport, brand, type ,cardNum, userId) VALUES ('1959', 'Mickey', 'Mantle', 'Baseball', 'Topps', 'All Star', '564', 1)");
            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lname, sport, brand, type ,cardNum, userId) VALUES ('1993', 'Derek', 'Jeter', 'Baseball', 'Topps', 'Rookie', '98', 1)");
            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lname, sport, brand, type ,cardNum, userId) VALUES ('2023', 'Joey', 'Porter Jr.', 'Football', 'Panini', 'Rookie', '386', 2)");
            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lname, sport, brand, type ,cardNum, userId) VALUES ('1958', 'Willie', 'Mays', 'Baseball', 'Topps', 'Common', '5', 2)");
            db.execSQL(" INSERT INTO " + cards_table_name + " (year, fname, lname, sport, brand, type ,cardNum, userId) VALUES ('1990', 'Ken', 'Griffey', 'Baseball', 'Topps', 'Rookie', '336', 2)");

            db.close();
        }
 
