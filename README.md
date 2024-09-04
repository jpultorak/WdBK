
# Rozwiązania zadań - Pracowania 6

## z3
### X-Frame-Options

Bez - narażamy się na ClickJacking, niewidzialny frame z stroną i jakiś guzik, który jest “nad” klikalną częścią strony


### X-XSS-Protection
Dawniej używany do powstrzymywania ataków XSS.
Według https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/X-XSS-Protection  nieobsługiwane w nowszych przeglądarkach i nie jest zalecane ustawianie tego nagłówka. Dodatkowo Dodatkowo, ten nagłóweg diała poprzez filtrowanie mogą być nowe problemy

Lepsze - Content-Security-Policy
### X-Content-Type-Options
Jest tylko jedna opcja - nosniffa
Wtedy przeglądarka nie może sama interpretować typu danych. Przykładowo drive-by-downloads, np przeglądarka może uznać coś co ma explicite typ text/plain, za application/javascript i wykonać skrypt. Alternatywnie przy uploadowaniu danych.

### Strict-Transport-Security
Bez - Narażamy się na man in the middle attack. Np. załóżmy, że jest wifi na lotnisku, tak naprawdę będące własnością hakera.Wpisujemy http://bank.com i haker przechwytuje nasz request i kieruje do swojej strony. Z HSTS nie ma tego problemu, ponieważ zakładając ze kiedys odwiedzilismy bank, to http od razu zostanie zamienione https przez przeglądarke.

### Flagi dla ciasteczek
* Secure - bez tego ciasateczka mogą być przesyłane przez HTTP, a więc podatne na MiM attack
* HttpOnly - bez tego ciasteczka mogą być czytane, modyfikowane przez JS, z tą flagą tylko przez HTTP (Set-cookie). Daje to jakiś dodatkowy stopień ochrony, jeśli atakujący znajdzie exploit XSS.

* SameSite

przykłady (bez HttpOnly, X-Frame, Same-Site): aplikacja banku w iframe
przykład(Z HttpOnly, bez SameSite): Mamy aplikację bankową bez SameSite, która używa ciasteczek z id sesji. Atakujący tworzy stronę, która automatycznie wysyła request z przelewem do banku. Użytkownik otwiera tę stronę (np. kliknął w złego linka albo coś w tym stylu) i request się wysyła, przekazując przy tym ciasteczko z sesją - bank zatwierdza przelew. Oczywiście żaden bank nie ma tak lipnych zabezpieczeń (np. jest zazwyczaj 2FA).
https://www.invicti.com/learn/cookie-security-flags/


w aplikacji bankowej w network/get-user-info widzimy:
X-Frame-Options: DENY
 X-Xss-Protecion: 0
X-Content-Type-options: DENY
Strict-Transport-Security - nie ma
## z4
BankApp/frontend/src/pages/Login.tsx
Użytkownik się loguje
1. Sprawdzamy czy w localStorage ustawiona jest flaga (licznik  >= 1 w tym przypadku). 
    1.1 Jeśli tak - error, za dużo otwartych kart
    1.1 Jeśli nie - w localStorage zwiększamy licznik do 1
2. Przy zamnknięciu karty - w localStorage zmieniamy licznik na 0.

To jest proste rozwiązanie i działa, ale zapewne Lepszym zabezpieczeniem byłoby zaimplementowanie tego po stronie backendu – przechowywanie aktywnych sesji w bazie danych i na przykład unieważnianie starych sesji przy nowym logowaniu, lub blokowanie nowych sesji, dopóki istnieje aktywna sesja przypisana do danego użytkownika.

## z5
BankApp/backend/src/main/kotlin/com/example/backend/security - konfiguiracja JWT
BankApp/frontend/src/pages/Login.tsx - logowanie

1. Użytkownik loguje się (/auth/authenticate)
2. Wprowadzone dane są porównywane z bazą danych
3. Jeśli są poprawne, generowany jest token JWT
4. Token jest przechowywany w SessionStorage
5. Z każdym requestem (/get-user-info) dodawany jest nagłówek “Authorization”, którego zawartość to Jwt token
6. Sprawdzane jest
    1. podpis
    2. ważność tokenu (czy nie jest przedawniony)
    3. Czy nazwa użytkownika jest taka sama jak nazwa użytkownika do którego należy zasób


## z6
w folderze /cert
nie zadziała
400 Bad Request
No required SSL certificate was sent
curl https://wdbk-bank.com/ 

Zadziała i zwróci stronę przywitalną nginxa
curl --cert client.crt --key client.key https://wdbk-bank.com/ 

## z7
w /ThreeBallot/crack.py znajduje się napisany skrypt do łamania głosów

**Na podstawie artykułu: https://www.cs.princeton.edu/~appel/voting/Strauss-ThreeBallotCritique2v1.5.pdf**

Dlaczego ThreeBallot jest wadliwy?
Gdy mamy 3n pasków, mamy wykładniczo wiele kombinacji żeby uzyskać n kombinacji (niekoniecznie prawidłowych). Nie każda kombinacja 3 "pasków" jest prawidłowa - liczba kombinacji prawidłowych rośnie zdecydowanie wolniej, przez co prawdopodobieństwo że wybierając trzy losowe paski dostaniemy dobry głos jest bardzo mała. Można to wykorzystać i rozwiązywać w stylu sudoku.


### Odtwarzanie głosów
Algorytm deterministyczny (jedna z wersji), który zaimplementowałem działa tak
1. Dla każdego nieużytego do tej pory paska, sprawdzamy ile par tworzy z nim razem poprawny głos
2. Często jest tak, że tylko jedna para jest ok -> wtedy wiemy, że musi ona tworzyć głos razem z tym paskiem, więc mamy z głowy 1 głos/3 paski.
3. Jeśli jakieś paski zostały odgadnięte w kroku 2, idziemy do 1.

W ten sposób, jesteśmy w stanie odtworzyć sporo głosów 
(np 10% przy małej ilości opcji - 94% dla dużej ilości opcji: total_options = 20, total_voters = 500, total_races = 14).
Algorytm jest dosyć niewyrafinowany, więc na pewno dałoby się uzyskac jeszcze lepsze wyniki gdyby dodać jakieś "głębsze" rozumowanie. Zarówno zaimplementowany przeze mnie algorytm, jak i zaprezentowany w artykule nie używa żadnej dodatkowej wiedzy i nie korzysta z żadnych metod statystycznych. Nawet jeśli nie jesteśmy w stanie jednoznacznie złamać jakiegoś głosu, to możemy zawęzić liczbę opcji i użyć dodatkowych założeń/wiedzy żeby oszacować prawdopodobieństwo poszczególnych opcji.
## z8 + z9
rozwiązanie w /FaceDetection
### z8
/FaceDetection/main.py
Tak jak w poleceniu - w przypadku człowieka, jest on zaznaczany na ekranie. W przypadku gdy na ekranie pojawia się też telefon (z jakiegoś problomu model YOLO miał problem z wykrywaniem jabłek, więc telefon został użyty zamiast tego), wyświetla się pierwsza klatka ze streamu. Dzięki temu, obecność telefonu zapewnia "niewidzialność".
### z9
/FaceDetection/mission_impossible.py
W /FaceDetection/known_people umieszczamy zdjęcia osób, które mają być niewidzialne. Przy użyciu biblioteki face_recognition, osoby obecne na kamerce są znajdowane i oznaczane, chyba że są na liście osób niewidzialnych.
