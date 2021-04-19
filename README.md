<h1>Elevator System</h1>
<ol>
    <h2><li>Opis problemu</h2>
        <p>Implementacja częściowo rozszerza dane zadanie o problem realizacji w czasie rzeczywistym:<p>
        <ul>
        <li>każda winda porusza się niezaleznie od całego systemu, jedynie wykonuje instrukcje zlecane przez system,</li>
        <li>pojedyncza winda porusza się w wirutalniej przestrzeni i na podstawie swojej aktualnej pozycji definuje piętro</li>
        </ul>
        <p>W celu rozwiązania takiego problemu został zastosowany algorytm wielowątkowy. Każda z wind bez przerwy działa w osobnym niezależnym wątku, czekając na kolejne dyspozycje. Dodatkowo działania takie jak obliczenie odległosci od danego piętra wykonują się bez zatrzymania głównego wątku windy, nie zatrzymując jej działania. Takie przedstawienie problemu jest najbliższe faktycznemu systemowi obsługi wind.</p>
        <p>Niestety ze względu na mocno ogarniczony czas nie zdążyłem zaimplementować symulacji osób wsiadających do windy i wybierających piętra. Jednakże rys rozwiązania tego problemu można zauważyć w obecnej implementeacji. Opierałoby sie ono o równoległą kolejkę pienter wybranych przez ludzi, która posiadałaby wyższy priorytet niż obecna kolejka pięter wskazanych przez system. Innymi słowy, w głównej mierze wykonywałyby się rozkazy obsługi pięter wskazancyh przez ludzi, natomiast reszta wykonywana byłaby przy okazji. Z ewentualną rekalkulacją długo oczekujących zleceń.</p>
    </li>
    <h2><li>Obsługa</h2>
    <p>W celu zmiany parametrów wejściowych programu należy zmodyfikować plik parameters.json znajdujący się w katalogu ElevatorSystem/out/artifacts/ElevatorSystem.</p>
    </li>
    <h2><li>Uruchomienie</h2>
    <p>Niestety na kapryśność wykorzystanej przeze mnie biblioteki JavaFx i jej brak natywnego wsparcia przez najnowsze odmiany JDK np.15, nie udało mi sie skonstruować stabilnego kontenera dockerowego, co więcej wymagałoby to też instalacji zewnętrzengo programu tj. XLanuch, do obsługi wyświetlania interfejsu okienkowego.</p>
    <p>Zalecane jest uruchomienie skompilowanego pliku .jar znajdujacego się w katalogu: ElevatorSystem/out/artifacts/ElevatorSystem/ElevatorSystem.jar poleceniem `java -jar ElevatorSystem.jar` **UWAGA** metoda ta działa wyłącznie dla Javy 8, które natywnie wspiera pakiet JavaFx. Dla nowszych dystrybucji niezbędne będzie pobranie biblioteki JavaFx dla odpowiedniego systemu operacyjnego: [JavaFX](https://gluonhq.com/products/javafx/), a następnie uruchomienie programu przy pomocy następującej komendy: `java --module-path path/to/javafx --add-modules javafx.controls -jar ElevatorSystem.jar` dla systemu Linux oraz `java --module-path "path\to\javafx" --add-modules javafx.controls -jar ElevatorSystem.jar` dla systemu Windows. </p>
    </li>
    <h2><li>Uwagi</h2>
    <p>Przedstawione rozwiązanie nie jest w pełni gotowe, zdarzają się nieliczne błędy, aczkolwiek w 100% oddaje idee i schemat działania. W razie wszelkich problemów proszę o kontak indywidualny.</p>
    </li>
</ol>

