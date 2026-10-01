package no.oslomet.cs.algdat;

import java.util.Comparator;
import java.util.Iterator
import java.util.Objects;

public class DobbeltLenketListe<T> implements Liste<T> {
    // Innebygd (Trenger ikke endres)

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;
        private Node<T> forrige, neste;

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi; this.forrige = forrige; this.neste = neste;
        }
        private Node(T verdi) {this(verdi, null, null);}
    }

    private Node<T> hode;
    private Node<T> hale;
    private int antall;
    private int endringer;

    public void fraTilKontroll(int fra, int til) {
        if (fra < 0) throw new IndexOutOfBoundsException("fra("+fra+") er negativ.");
        if (til > antall) throw new IndexOutOfBoundsException("til("+til+") er større enn antall("+antall+")");
        if (fra > til) throw new IllegalArgumentException("fra("+fra+") er større enn til("+til+") - Ulovlig intervall.");
    }

    // Oppgave 0
    public static int gruppeMedlemmer() {
        return 1; // Returner hvor mange som er i gruppa deres
    }

    // Oppgave 1
    public DobbeltLenketListe() {
    }

    public DobbeltLenketListe(T[] a) {
        Objects.requireNonNull(a);

        for (T verdi : a) {
            if (verdi != null) {
                Node<T> ny = new Node<>(verdi);

                if (antall == 0) {
                    hode = hale = ny;
                } else {
                    ny.forrige = hale;
                    hale.neste = ny;
                    hale = ny;
                }

                antall++;
            }
        }
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }
        // Oppgave 2
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> p = hode;

        while (p != null) {
            sb.append(p.verdi);

            if (p.neste != null) {
                sb.append(", ");
            }

            p = p.neste;
        }

        sb.append("]");
        return sb.toString();
    }

    public String omvendtString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> p = hale;

        while (p != null) {
            sb.append(p.verdi);

            if (p.forrige != null) {
                sb.append(", ");
            }

            p = p.forrige;
        }

        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi);

        Node<T> ny = new Node<>(verdi);

        if (antall == 0) {
            hode = hale = ny;
        } else {
            ny.forrige = hale;
            hale.neste = ny;
            hale = ny;
        }

        antall++;
        endringer++;

        return true;
    }

    // Oppgave 3
    private Node<T> finnNode(int indeks) {
        if (indeks < antall / 2) {
            Node<T> p = hode;

            for (int i = 0; i < indeks; i++) {
                p = p.neste;
            }

            return p;
        } else {
            Node<T> p = hale;

            for (int i = antall - 1; i > indeks; i--) {
                p = p.forrige;
            }

            return p;
        }
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi);
        indeksKontroll(indeks, false);

        Node<T> p = finnNode(indeks);
        T gammelverdi = p.verdi;

        p.verdi = nyverdi;
        endringer++;

        return gammelverdi;
    }

    public Liste<T> subliste(int fra, int til) {
        fraTilKontroll(fra, til);

        DobbeltLenketListe<T> liste = new DobbeltLenketListe<>();

        if (fra == til) {
            return liste;
        }

        Node<T> p = finnNode(fra);

        for (int i = fra; i < til; i++) {
            liste.leggInn(p.verdi);
            p = p.neste;
        }

        return liste;
    }
    // Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    // Oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    // Oppgave 6
    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    // Oppgave 7
    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    // Oppgave 8

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean kanFjerne;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;                   // Starter på første i lista
            kanFjerne = false;              // Settes true når next() kalles
            iteratorendringer = endringer;  // Teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        // Oppgave 9:
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // Oppgave 10
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }
}
