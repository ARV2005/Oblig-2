# Obligatorisk Oppgave 2 i DATS2300 - Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i DATS2300 - Algoritmer og datastrukturer. Den er innlevert av følgende student:
* advor9666@oslomet.no

## Oppgavebeskrivelser

# Oppgave 0
I oppgave 0 returnerte jeg 1 fordi jeg jobber alene på oppgaven.

# Oppgave 1
I oppgave 1 lagde jeg en tom konstruktør som starter med en tom liste.
Konstruktøren som tar inn en tabell går gjennom tabellen og legger inn alle verdier som ikke er null.
Hvis tabellen er null kastes en NullPointerException.
Jeg kobler nodene sammen direkte i konstruktøren og bruker ikke leggInn().
antall() returnerer antall verdier, mens tom() sjekker om listen er tom.

# Oppgave 2
I oppgave 2 lagde jeg toString() ved å gå igjennom listen fra hode til hale.
omvendtString() gjør det samme, men starter på halen og går bakover.
Begge metodene bruker StringBuilder.
leggInn() leger inn en ny node bakerst i listen.
Jeg passer på å oppdatere pekerne, antall og endringer.

# Oppgave 3
I oppgave 3 lagde jeg finnNode() som finner noden på en bestemt indeks.
Den starter fra hode eller hale, avhengig av hvilken side av indeksen som er nærmest.
hent() bruker finnNode() for å finne riktig verdi.
oppdater() bytter ut verdien på en indeks og returnerer den gammle verdien.
Jeg lagde også subliste() som lager en ny liste med verdiene fra fra til til.

# Oppgave 4
I oppgave 4 lagde jeg indeksTil() som går igjennom listen fra starten.
Den returnerer indeksen til den første verdien som passer.
Hvis verdien ikke finnes, returnerer den -1.
Jeg bruker Objects.equals() slik at det også funker når verdien er null.
inneholder() bruker indeksTil() for å sjekke om verdiene finnes i listen.

# Oppgave 5
I oppgave 5 lagde jeg leggInn() for å legge inn en verdi på en bestemt indeks.
Jeg har tatt hensyn til at verdien kan legges i starten, slutten, midten eller i en tom liste.
Jeg kobler den nye noden sammen med nodene rundt slik at pekerne blir riktige.
antall og endringer økes når en verdi legges inn.
Det er ikke mulig å legge inn en null-verdi.

# Oppgave 6
I oppgave 6 lagde jeg fjern() for å fjerne en verdi eller en verdi på en bestemt indeks.
Jeg passer på å oppdatere pekerne til forrige og neste node når en node fjernes.
Hvis den første eller siste noden fjernes, oppdateres hode eller hale.
antall og endringer oppdateres etter en verdi er fjernet.
fjern(T verdi) går gjennom listen til den finner den første verdien som passer.

# Oppgave 8
I oppgave 8 lagde jeg iterator som går gjennom listen fra starten.
Jeg lagde også en iterator som kan starte på en bestemt indeks.
next() returnerer neste verdi og flytter iteratoren videre i listen.
hasNext() sjekker om det finnes flere verdier å hetne.
Jeg sjekker også om listen har blitt endret etter iteratoren ble laget.
