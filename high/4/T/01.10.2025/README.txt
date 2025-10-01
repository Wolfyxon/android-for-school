Opis zadania:
Twoim zadaniem jest stworzenie prostej aplikacji mobilnej na platformę Android w języku Java, której główną funkcjonalnością będzie przeprowadzanie walidacji danych wprowadzanych przez użytkownika do pól tekstowych.

Wymagania funkcjonalne:

Aplikacja powinna zawierać ekran z formularzem składającym się z minimum trzech pól tekstowych, np.:
Imię (tylko litery, pierwsza wielka litera),
Adres e-mail (zgodny ze standardowym formatem adresu e-mail),
Hasło (minimum 8 znaków, przynajmniej jedna cyfra i jedna wielka litera).

Po kliknięciu przycisku "Zatwierdź" aplikacja powinna:
Sprawdzić poprawność wprowadzonych danych,
W przypadku błędów wyświetlić komunikat (np. w formie Toast lub pod polem tekstowym),
W przypadku poprawnych danych wyświetlić komunikat o sukcesie.

Walidacja powinna być zaimplementowana w postaci osobnych metod (np. validateName(), validateEmail(), validatePassword()), aby kod był czytelny i łatwy w rozbudowie. 