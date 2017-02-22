Opis uruchomienia:

Do uruchomienia aplikacji niezbedne są maven, jdk.
Aby zbudować należy wykonać te kroki znajdujac sie w katalogu z plikiem pom.xml

mvn compile

pozniej budujemy paczkę war za pomocą:
 
mvn package

Tak zbudowaną paczkę WAR można uruchomić np na kontenerze servletow tomcat kopiując ją do katalogu webapps i uruchamiajac serwer.

Do poprawnego działania aplikacja wymaga bazy danych
nazwa bazy: softhis_test
user: root
password: arkadiusz
host: localhost:3306

Jeśli nic nie pomieszałem, to aplikacja udostępnia pełną wskazaną funkcjonalność oprucz filtrowania po wynikach (brakło czsu).
Wszystkie parametry przesyłamy tylko za pomocą requestow GET

 Generator jest dostepy pod adresem:

http://localhost:8084/generator

jako parametr customersCount możemy przekazać ilość klientow do utworzenia np:

http://localhost:8084/generator?customersCount=15

UWAGA! Port w tym przypadku 8084 może się nieznacznie różnić zależnie od środowiska.


Raport można obejrzeć pod 

http://localhost:8084/
Gdzie do filtrowania po dacie:

mamy dwa parametry: minDateFilter i maxDateFilter, oba przyjmuja datę w formacie dd-mm-yyyy

http://localhost:8084/?minDateFilter=23-01-2013&maxDateFilter=23-12-2013

Filtrowanie po nazwisku:
mamy jeden parametr surnameFilter - jako wartosc można podac wycinek nazwiska, aplikacja postara się to automatycznie dopasowac.

http://localhost:8084/?surnameFilter=ski


